# 🚀 Desafio Java Jr - API REST com Spring Boot

Este projeto foi desenvolvido como parte de um **desafio Java Júnior**, aplicando os princípios de **boas práticas de programação** (SOLID, DRY, YAGNI e KISS) e utilizando o ecossistema do **Spring Boot** para construção de uma API REST robusta, escalável e bem documentada.

---

## 📌 Tecnologias e Conceitos Utilizados

- **Java 17+**
- **Spring Boot 3**
- **Spring Data JPA** → consultas e persistência de dados
- **Injeção de Dependências** com Spring
- **Tratamento de respostas de erro** customizadas
- **Swagger (OpenAPI 3)** → documentação automática
- **Banco de dados relacional** (PostgreSQL ou H2 em memória para testes)

---

## 🎯 Objetivo do Projeto

Criar uma **API REST** que possibilite operações de **CRUD** (Create, Read, Update e Delete) sobre uma entidade de exemplo (ex.: `Produto`, `Cliente` ou `Livro`).  
O projeto foca em **boas práticas** de desenvolvimento, respeitando os princípios:

- **SOLID** → código mais modular, coeso e de fácil manutenção  
- **DRY** (*Don't Repeat Yourself*) → evitar duplicação de lógica  
- **YAGNI** (*You Aren't Gonna Need It*) → implementar apenas o necessário  
- **KISS** (*Keep It Simple, Stupid*) → manter a simplicidade sem complexidade desnecessária  

---

## ⚙️ Configuração e Execução

### ✅ Pré-requisitos
- Java 17+
- Maven 3.8+
- Docker (opcional, caso queira rodar o banco de dados PostgreSQL)

### ▶️ Rodando o Projeto
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/desafio-java-jr.git
   cd desafio-java-jr
Configure o banco de dados em application.properties ou use o H2 em memória:

properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
Execute o projeto:

mvn spring-boot:run
Acesse a API em:

http://localhost:8080/api
📖 Documentação da API
A documentação é gerada automaticamente com Swagger (OpenAPI 3).

URL: http://localhost:8080/swagger-ui.html

📦 Estrutura do Projeto

src/main/java/com/exemplo/desafio
│
├── controller    → Camada de controle (endpoints REST)
├── dto           → Objetos de transferência de dados
├── entity        → Classes de entidade (JPA)
├── exception     → Tratamento de exceções e respostas customizadas
├── repository    → Interfaces do Spring Data JPA
├── service       → Regras de negócio e lógica de aplicação
└── config        → Configurações do projeto (Swagger, etc.)

🚦 Exemplos de Endpoints
GET /api/produtos → lista todos os produtos

GET /api/produtos/{id} → busca um produto por ID

POST /api/produtos → cria um novo produto

PUT /api/produtos/{id} → atualiza um produto existente

DELETE /api/produtos/{id} → remove um produto

⚠️ Tratamento de Erros
Respostas padronizadas em caso de falhas:

json

{
  "timestamp": "2025-09-30T09:00:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "Produto não encontrado",
  "path": "/api/produtos/99"
}
📌 Boas Práticas Aplicadas
✅ SOLID → Controllers finos, serviços coesos e repositórios específicos

✅ DRY → Reuso de componentes e DTOs

✅ YAGNI → Implementado apenas o necessário para o desafio

✅ KISS → Código simples, legível e fácil de manter