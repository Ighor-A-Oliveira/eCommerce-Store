package com.ighor.api.e_commerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighor.api.e_commerce.dto.request.ProductRequestDTO;
import com.ighor.api.e_commerce.dto.response.ProductResponseDTO;
import com.ighor.api.e_commerce.security.SecurityFilter;
import com.ighor.api.e_commerce.security.TokenConfig;
import com.ighor.api.e_commerce.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
// ignora parte de segurança e permite a execução dos testes
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TokenConfig tokenConfig;

    @MockBean
    private SecurityFilter securityFilter;

    @MockBean
    private ProductService productService;

    @Test
    void deveCriarProduto() throws Exception {

        ProductRequestDTO request = new ProductRequestDTO(
                "Mouse",
                "Mouse gamer",
                BigDecimal.valueOf(100),
                10,
                "img.com",
                "SKU123",
                true,
                LocalDateTime.now(),
                1L
        );

        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        verify(productService).criarProduto(any());
    }

    @Test
    void deveBuscarProdutoPorId() throws Exception {

        ProductResponseDTO response = new ProductResponseDTO(
                1L,
                "Mouse",
                "Mouse gamer",
                BigDecimal.valueOf(100),
                10,
                "img.com",
                "SKU123",
                true,
                LocalDateTime.now(),
                1L
        );

        when(productService.buscarProdutoPorId(1L)).thenReturn(response);

        mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk());

        verify(productService).buscarProdutoPorId(1L);
    }

    @Test
    void deveBuscarTodosProdutos() throws Exception {

        when(productService.buscarTodosProdutos()).thenReturn(List.of());

        mockMvc.perform(get("/product"))
                .andExpect(status().isOk());

        verify(productService).buscarTodosProdutos();
    }

    @Test
    void deveAtualizarProduto() throws Exception {

        mockMvc.perform(put("/product/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "name": "Mouse atualizado",
                            "description": "desc",
                            "price": 200,
                            "stockQuantity": 5,
                            "imageUrl": "img.com",
                            "sku": "SKU999",
                            "active": true,
                            "category": {
                                "id": 1
                            }
                        }
                        """))
                .andExpect(status().isNoContent());

        verify(productService).atualizarProdutoPorId(anyLong(), any());
    }

    @Test
    void deveDeletarProduto() throws Exception {

        mockMvc.perform(delete("/product/1"))
                .andExpect(status().isNoContent());

        verify(productService).deletarProdutoPorId(1L);
    }
}