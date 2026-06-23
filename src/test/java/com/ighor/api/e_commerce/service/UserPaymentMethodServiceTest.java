package com.ighor.api.e_commerce.service;

import com.ighor.api.e_commerce.dto.request.UserPaymentMethodRequestDTO;
import com.ighor.api.e_commerce.dto.response.UserPaymentMethodResponseDTO;
import com.ighor.api.e_commerce.mapper.PaymentMethodMapper;
import com.ighor.api.e_commerce.model.entity.User;
import com.ighor.api.e_commerce.model.entity.UserPaymentMethod;
import com.ighor.api.e_commerce.model.enums.PaymentMethod;
import com.ighor.api.e_commerce.repo.UserPaymentMethodRepo;
import com.ighor.api.e_commerce.repo.UserRepo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserPaymentMethodServiceTest {

    @Mock
    private UserPaymentMethodRepo paymentMethodRepo;
    @Mock
    private UserRepo userRepo;
    @Mock
    private PaymentMethodMapper payMapper;

    @InjectMocks
    UserPaymentMethodService service;

    @Test
    void salvarMetodoPagamento() {
        User user = criarUser();

        List<UserPaymentMethod> lista = new ArrayList<>();
        UserPaymentMethod method = criarMetodoDePagamento();

        user.addPaymentMethod(method);
        method.setUser(user);
        lista.add(method);


        UserPaymentMethodRequestDTO request = new UserPaymentMethodRequestDTO(
                PaymentMethod.PIX,
                null,
                "User",
                null,
                null,
                null
        );

        when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        when(payMapper.dtoParaEntidade(any())).thenReturn(method);
        when(paymentMethodRepo.findByUserId(1L)).thenReturn(lista);

        UserPaymentMethodResponseDTO result =service.salvarMetodoPagamento(1L,request);

        ArgumentCaptor<UserPaymentMethod> captor = ArgumentCaptor.forClass(UserPaymentMethod.class);
        verify(paymentMethodRepo).save(captor.capture());
        UserPaymentMethod saved = captor.getValue();

        assertEquals(method.getUser(), saved.getUser());
        assertEquals("User", request.cardHolderName());

    }

    @Test
    void listarMetodoPagamentoPorUserId() {
        User user = criarUser();
        UserPaymentMethod method = criarMetodoDePagamento();
        List<UserPaymentMethod> lista = new ArrayList<>();

        user.addPaymentMethod(method);
        method.setUser(user);
        lista.add(method);

        when(paymentMethodRepo.findByUserId(1L)).thenReturn(lista);

        List<UserPaymentMethod> result = service.listarMetodoPagamentoPorUserId(1L);

        assertEquals(lista, result);
        assertEquals(lista.size(), result.size());
        verify(paymentMethodRepo).findByUserId(1L);
    }



    @Test
    void deletarMetodoPagamentoPorId() {
        User user = criarUser();
        UserPaymentMethod method = criarMetodoDePagamento();

        user.addPaymentMethod(method);
        method.setUser(user);

        when(paymentMethodRepo.findByIdAndUserId(1L, 1L)).thenReturn(Optional.of(method));

        service.deletarMetodoPagamentoPorId(1L, 1L);


        verify(paymentMethodRepo).delete(method);
    }

    @Test
    void definirComoPadrao() {

        User user = criarUser();

        UserPaymentMethod antigo = criarMetodoDePagamento();
        antigo.setId(1L);
        antigo.setUser(user);
        antigo.setIsDefault(true);

        UserPaymentMethod novo = criarMetodoDePagamento();
        novo.setId(2L);
        novo.setUser(user);
        novo.setIsDefault(false);

        List<UserPaymentMethod> lista = List.of(antigo, novo);

        when(paymentMethodRepo.findByUserId(1L)).thenReturn(lista);
        when(paymentMethodRepo.findByIdAndUserId(2L, 1L))
                .thenReturn(Optional.of(novo));
        when(paymentMethodRepo.save(any(UserPaymentMethod.class)))
                .thenReturn(novo);

        UserPaymentMethod result = service.definirComoPadrao(2L, 1L);

        verify(paymentMethodRepo).save(novo);

        assertFalse(antigo.getIsDefault());
        assertTrue(novo.getIsDefault());
        assertEquals(novo, result);
    }

    User criarUser(){
        User user = new User();
        user.setId(1L);
        return user;
    }

    UserPaymentMethod criarMetodoDePagamento(){
        UserPaymentMethod pay = new UserPaymentMethod();
        pay.setId(1L);
        pay.setCardHolderName("User");
        return pay;

    }
}