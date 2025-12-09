# biblioteca_java

API Web backend que implemente as operações CRUD (Create, Read, Update, Delete) para a entidade Livro, utilizando a arquitetura simplificada de Controller/Repository e ecossistema Java (Spring Boot).

## 📋 Descrição

Sistema de gerenciamento de biblioteca desenvolvido em Java Spring Boot que oferece uma API RESTful completa para gerenciar livros.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.1.5**
- **Spring Data JPA**
- **Hibernate**
- **H2 Database** (banco de dados em memória)
- **Maven**

## 📦 Estrutura do Projeto

```
biblioteca_java/
├── src/
│   ├── main/
│   │   ├── java/com/biblioteca/api/
│   │   │   ├── BibliotecaApiApplication.java
│   │   │   ├── controller/
│   │   │   │   └── LivroController.java
│   │   │   ├── model/
│   │   │   │   └── Livro.java
│   │   │   └── repository/
│   │   │       └── LivroRepository.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/biblioteca/api/
│           └── BibliotecaApiApplicationTests.java
├── pom.xml
└── README.md
```

## 🎯 Entidade Livro

A entidade **Livro** possui os seguintes atributos:

- `id` (Long) - Identificador único gerado automaticamente
- `titulo` (String) - Título do livro
- `autor` (String) - Autor do livro
- `isbn` (String) - ISBN único do livro
- `anoPublicacao` (Integer) - Ano de publicação
- `disponivel` (Boolean) - Status de disponibilidade

## 🔧 Como Executar

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6 ou superior

### Compilar o Projeto

```bash
mvn clean compile
```

### Executar os Testes

```bash
mvn test
```

### Iniciar a Aplicação

```bash
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 📡 Endpoints da API

### 1. Criar um Livro
**POST** `/api/livros`

```json
{
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "isbn": "978-0132350884",
  "anoPublicacao": 2008,
  "disponivel": true
}
```

**Resposta:** `201 Created`
```json
{
  "id": 1,
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "isbn": "978-0132350884",
  "anoPublicacao": 2008,
  "disponivel": true
}
```

### 2. Listar Todos os Livros
**GET** `/api/livros`

**Resposta:** `200 OK`
```json
[
  {
    "id": 1,
    "titulo": "Clean Code",
    "autor": "Robert C. Martin",
    "isbn": "978-0132350884",
    "anoPublicacao": 2008,
    "disponivel": true
  }
]
```

### 3. Buscar Livro por ID
**GET** `/api/livros/{id}`

**Resposta:** `200 OK` ou `404 Not Found`

### 4. Atualizar Livro Completamente
**PUT** `/api/livros/{id}`

```json
{
  "titulo": "Clean Code - Updated",
  "autor": "Robert C. Martin",
  "isbn": "978-0132350884",
  "anoPublicacao": 2008,
  "disponivel": false
}
```

**Resposta:** `200 OK` ou `404 Not Found`

### 5. Atualizar Livro Parcialmente
**PATCH** `/api/livros/{id}`

```json
{
  "disponivel": true
}
```

**Resposta:** `200 OK` ou `404 Not Found`

### 6. Deletar Livro
**DELETE** `/api/livros/{id}`

**Resposta:** `204 No Content` ou `404 Not Found`

## 🗄️ Banco de Dados

A aplicação utiliza o banco de dados H2 em memória. Para acessar o console H2:

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:biblioteca`
- Username: `sa`
- Password: (deixar em branco)

## 🏗️ Arquitetura

O projeto segue a arquitetura **Controller/Repository**:

- **Controller**: Recebe requisições HTTP, aplica validações e regras de negócio, e chama o Repository
- **Repository**: Interface que estende JpaRepository, responsável pela persistência de dados via JPA/Hibernate
- **Model**: Entidade JPA que representa a tabela de livros no banco de dados

## ✅ Boas Práticas Implementadas

- ✨ Uso de anotações Jakarta Validation para validação de dados
- 🔒 Constraint de unicidade para o ISBN
- 📝 Separação clara de responsabilidades (Controller, Service, Repository)
- 🎯 Endpoints RESTful seguindo convenções HTTP
- 🧪 Testes automatizados com JUnit
- 📦 Configuração externalizável via application.properties

## 📝 Exemplos de Uso com cURL

```bash
# Criar um livro
curl -X POST http://localhost:8080/api/livros \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Design Patterns",
    "autor": "Gang of Four",
    "isbn": "978-0201633610",
    "anoPublicacao": 1994,
    "disponivel": true
  }'

# Listar todos os livros
curl -X GET http://localhost:8080/api/livros

# Buscar livro por ID
curl -X GET http://localhost:8080/api/livros/1

# Atualizar livro
curl -X PUT http://localhost:8080/api/livros/1 \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Design Patterns - Updated",
    "autor": "Gang of Four",
    "isbn": "978-0201633610",
    "anoPublicacao": 1994,
    "disponivel": false
  }'

# Atualizar parcialmente
curl -X PATCH http://localhost:8080/api/livros/1 \
  -H "Content-Type: application/json" \
  -d '{"disponivel": true}'

# Deletar livro
curl -X DELETE http://localhost:8080/api/livros/1
```

## 👨‍💻 Autor

Sistema desenvolvido seguindo as melhores práticas de Programação Orientada a Objetos (POO) e padrões REST.
