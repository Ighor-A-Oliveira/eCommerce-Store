# 🛒 E-Commerce API

API REST de um sistema de e-commerce desenvolvida com **Spring Boot**, com foco em práticas de backend, organização em camadas e estrutura pronta para portfólio de desenvolvedor Java.

O projeto simula funcionalidades reais de um sistema de loja online, incluindo produtos, categorias, usuários e regras de segurança.


---

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- H2 Database
- Maven
- Lombok
- Swagger / OpenAPI
- JUnit + Mockito (testes)

---

## 🧠 Objetivo do projeto

Este projeto foi desenvolvido com foco em aprendizado prático e portfólio, simulando um cenário real de backend para e-commerce, com:

- Arquitetura em camadas (Controller, Service, Repository)
- Uso de DTOs para entrada e saída de dados
- Validações com Bean Validation
- Segurança com Spring Security
- Testes unitários com Mockito
- Documentação da API com Swagger

---

## 📂 Arquitetura

O projeto segue uma arquitetura em camadas:

```
Controller
 ↓
Service
 ↓
Repository
 ↓
Database
```

---

## 📁 Estrutura do Projeto

```text
src/main/java/com/ighor/api/e_commerce

├── controller
├── service
├── repo
├── dto
│   ├── request
│   ├── response
│   └── entity
├── mapper
├── model
│   ├── entity
│   └── enums
├── security
└── exception
```

---
Além disso, foi utilizada separação entre:

- DTOs de Request
- DTOs de Response
- DTOs de Entidade
- Mappers
- Entidades
- Configurações de Segurança

---

## 📌 Funcionalidades

### 👤 Usuários

- Cadastro de usuário
- Login com autenticação JWT
- Busca de usuário
- Atualização de dados

### 📦 Produtos

- Cadastro de produtos
- Atualização
- Remoção
- Busca por ID
- Listagem de produtos

### 🏷 Categorias

- Cadastro de categorias
- Atualização
- Exclusão
- Listagem

### 🛒 Carrinho

- Criar carrinho automaticamente para usuário
- Adicionar produtos
- Atualizar quantidade
- Remover itens
- Calcular total do carrinho

### 📍 Endereços

- Cadastro de endereços
- Definir endereço padrão
- Buscar endereços do usuário

### 💳 Métodos de pagamento

- Cadastro de método de pagamento
- Definir método padrão
- Listar métodos cadastrados

### 📦 Pedidos

- Criar pedido a partir do carrinho
- Buscar pedido por ID
- Listar pedidos do usuário
- Atualizar pedido
- Cancelar pedido

---

## 🔐 Segurança

A aplicação utiliza:

- Spring Security
- JWT (JSON Web Token)
- BCrypt Password Encoder
- Filtro de autenticação JWT
- Controle de acesso baseado em Roles

Roles disponíveis:

```java
ADMIN
CUSTOMER
```

## 🧪 Testes

O projeto possui testes unitários focados em controllers utilizando:

- MockMvc
- Mockito
- @WebMvcTest

Cobertura dos principais endpoints da API.

---

## ⚙️ Como executar

### Clone o projeto

```bash
git clone https://github.com/seu-usuario/e-commerce-api.git
```

### Entre na pasta

```bash
cd e-commerce-api
```

### Execute

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

---

## Banco de Dados

O projeto utiliza H2 Database para desenvolvimento.

Console H2:

```text
http://localhost:8080/h2-console
```

URL:

```text
jdbc:h2:mem:usuario
```

Usuário:

```text
sa
```

Senha:

```text
(sem senha)
```

"""## Como usar a API (Postman / Insomnia)

Base URL (local):
http://localhost:8080

------------------------------------------------------------

## 👤 Usuário

### Registrar usuário
POST /user/register

{
  "name": "ighor",
  "email": "ighor@email.com",
  "password": "123",
  "phone": "1234"
}

### Login
POST /user/login

{
  "email": "ighor@email.com",
  "password": "123"
}

------------------------------------------------------------

## 🗂️ Categorias

### Criar categoria
POST /category

{
  "name": "Movies",
  "slug": "movies",
  "description": "Products related to movies, DVDs, Blu-ray discs, etc"
}

------------------------------------------------------------

## 📦 Produtos

### Criar produto
POST /product

{
  "name": "Blu-ray",
  "description": "A disc that contains a movie",
  "price": 100.50,
  "stockQuantity": 50,
  "imageUrl": "https://example.com/image.jpg",
  "sku": "brd01",
  "active": true,
  "categoryId": 1
}

------------------------------------------------------------

## 🏠 Endereços

### Adicionar endereço
POST /address

{
  "street": "Rua Usuario",
  "number": "24",
  "complement": "",
  "neighborhood": "Vila do Usuario",
  "city": "Sao Paulo",
  "state": "SP",
  "zipCode": "000100-000",
  "userId": 1
}

------------------------------------------------------------

## 💳 Métodos de pagamento

### PIX
POST /payment-method/1

{
  "method": "PIX",
  "isDefault": true
}

### Cartão de crédito
POST /payment-method/1

{
  "method": "CREDIT_CARD",
  "cardHolderName": "Ighor Alves",
  "lastFourDigits": "1234",
  "expiryMonth": "12",
  "expiryYear": "2030",
  "token": "fake-token",
  "isDefault": true
}

------------------------------------------------------------

## 🛒 Carrinho

### Adicionar item ao carrinho
POST /cart/1/items

{
  "productId": 1,
  "quantity": 2
}

------------------------------------------------------------

## 📑 Pedidos (Orders)

### Criar pedido
POST /order

{
  "addressId": 1,
  "paymentId": 1,
  "userId": 1
}

### Buscar pedidos do usuário
GET /order/user/1
"""

## Próximos passos

- [ ] Melhorar Exception Handling
- [ ] Testes unitários com JUnit e Mockito
- [ ] Front-end em React
- [ ] Dockerização da aplicação
- [ ] Deploy

---

## Autor

**Ighor**

Estudante de Análise e Desenvolvimento de Sistemas.
