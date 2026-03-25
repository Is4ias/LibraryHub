# 📚 Library API - UDEMY

API REST para gerenciamento de **autores e livros**, desenvolvida com foco em boas práticas de backend utilizando o ecossistema Spring.

---

## 🚀 Tecnologias utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Lombok

---

## 📌 Funcionalidades

* ✅ Cadastro de autores
* ✅ Cadastro de livros
* ✅ Relacionamento entre autores e livros
* ✅ Busca de livros por gênero
* ✅ Ordenação dinâmica de resultados
* ✅ Persistência com JPA/Hibernate

---

## 🧱 Estrutura do Projeto

O projeto segue uma arquitetura organizada em camadas:

```
📦 libraryapi
 ┣ 📂 model        → Entidades JPA
 ┣ 📂 repository   → Interfaces de acesso a dados
 ┣ 📂 service      → Regras de negócio
 ┣ 📂 config       → Configurações da aplicação
 ┗ 📂 test         → Testes automatizados
```

---

## ⚙️ Configuração do ambiente

### 1. Clone o projeto

```bash
git clone https://github.com/seu-usuario/libraryapi.git
cd libraryapi
```

---

### 2. Configure o banco de dados

Certifique-se de ter o PostgreSQL rodando e crie o banco:

```sql
CREATE DATABASE library;
```

---

### 3. Configure o `application.yml`

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/library
    username: postgres
    password: sua_senha

  jpa:
    hibernate:
      ddl-auto: none
    show-sql: true
```

---

### 4. Execute o projeto

```bash
mvn spring-boot:run
```

---

## 🧪 Testes

Para rodar os testes:

```bash
mvn test
```

---

## 🧠 Aprendizados

Este projeto aborda conceitos importantes como:

* Mapeamento objeto-relacional (JPA)
* Relacionamentos entre entidades
* Uso de repositories no Spring Data
* Configuração de banco de dados
* Boas práticas com Lombok
* Tratamento de queries personalizadas

---

## 📌 Melhorias futuras

* 🔲 Documentação com Swagger/OpenAPI
* 🔲 Paginação e filtros avançados
* 🔲 Deploy em nuvem (Render, Railway ou AWS)

---

## 📄 Licença

Este projeto é para fins de estudo e portfólio.
