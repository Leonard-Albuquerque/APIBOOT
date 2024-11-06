
# API REST com Spring Boot e PostgreSQL

Este projeto é uma API REST simples para gerenciar produtos, construída com Spring Boot e PostgreSQL. Abaixo está uma visão geral dos passos e configurações realizadas.

---

## 1. Instalação do PostgreSQL e Configuração Inicial

- **Instalação**: Baixe e instale o PostgreSQL a partir do site oficial.
- **Criação do Banco de Dados e Usuário**:
  - Acesse o console do PostgreSQL com: `psql -U postgres`.
  - Execute os comandos:
    ```sql
    CREATE DATABASE dbzin;
    CREATE USER username WITH PASSWORD 'dbzin';
    GRANT ALL PRIVILEGES ON DATABASE dbzin TO username;
    ```

---

## 2. Configuração do Spring Boot para o PostgreSQL

No arquivo `application.properties` (localizado em `src/main/resources`), configure a conexão com o PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/dbzin
spring.datasource.username=username
spring.datasource.password=dbzin
spring.datasource.driver-class-name=org.postgresql.Driver

# Configurações do JPA e Hibernate
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> Substitua `dbzin`, `username` e `dbzin` pelos valores definidos durante a configuração do banco de dados.

---

## 3. Criação da Entidade `Product`

Na pasta `src/main/java/com/example/APIBOOT/model`, criamos a entidade `Product` para representar um produto no banco de dados.

```java
package com.example.APIBOOT.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
}
```

---

## 4. Criação do Repositório `ProductRepository`

Criamos uma interface `ProductRepository` na pasta `src/main/java/com/example/APIBOOT/repository` para gerenciar operações de banco de dados relacionadas aos produtos.

```java
package com.example.APIBOOT.repository;

import com.example.APIBOOT.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
```

---

## 5. Criação do Controlador `ProductController`

Criamos o controlador `ProductController` para expor endpoints para criar e listar produtos.

```java
package com.example.APIBOOT.controller;

import com.example.APIBOOT.model.Product;
import com.example.APIBOOT.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
```

---

## 6. Teste da API

### Inserção de Produto
Para inserir um produto, faça uma requisição `POST` para `http://localhost:8080/api/products` com o seguinte JSON no corpo:

```json
{
  "name": "Product A",
  "description": "A sample product",
  "price": 19.99
}
```

### Listagem de Produtos
Para listar os produtos, faça uma requisição `GET` para `http://localhost:8080/api/products`.

---

## 7. Verificação no Banco de Dados

Acesse o banco de dados para verificar os dados inseridos:

1. Acesse o console do PostgreSQL: `psql -U username -d dbzin`.
2. Consulte os dados com: `SELECT * FROM product;`.

---

## 8. Debug e Logs

No `application.properties`, habilitamos logs para ver as consultas SQL geradas e facilitar o debug:

```properties
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

---

## Observações Finais

- Este projeto é um exemplo básico de API REST com Spring Boot e PostgreSQL.
- Sinta-se à vontade para expandir com novos endpoints e funcionalidades.
