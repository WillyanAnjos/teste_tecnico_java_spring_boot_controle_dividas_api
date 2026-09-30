# Controle de Dividas API

API REST desenvolvida em Java com Spring Boot para cadastro, consulta, atualizacao e remocao de dividas vinculadas ao CPF do devedor.

## Tecnologias utilizadas

- Java 21
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- PostgreSQL 16
- Flyway
- Maven
- MapStruct
- Lombok
- Bean Validation
- Springdoc OpenAPI / Swagger UI
- Docker e Docker Compose
- JUnit / Spring Boot Test

## Funcionalidades

- Cadastro de dividas
- Listagem paginada de dividas
- Consulta de divida por identificador
- Consulta de divida por CPF
- Atualizacao de divida existente
- Remocao de divida
- Validacao de dados de entrada
- Versionamento de banco de dados com Flyway
- Documentacao interativa da API com Swagger

## Requisitos

Para executar a aplicacao localmente, e necessario ter instalado:

- Java 21
- Maven 3.9 ou superior, ou utilizar o Maven Wrapper do projeto
- Docker e Docker Compose

## Como executar com Docker

Suba a API e o banco PostgreSQL:

```bash
docker compose up --build
```

A aplicacao ficara disponivel em:

```text
http://localhost:8080
```

## Como executar localmente

Suba apenas o banco de dados:

```bash
docker compose up -d postgres
```

Execute a aplicacao com o Maven Wrapper:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

## Configuracao

A aplicacao utiliza variaveis de ambiente para configuracao do banco de dados.
Caso nao sejam informadas, os valores padrao abaixo serao utilizados:

| Variavel | Valor padrao |
| --- | --- |
| `DB_HOST` | `localhost` |
| `DB_PORT` | `5432` |
| `DB_NAME` | `dividas` |
| `DB_USER` | `dividas` |
| `DB_PASSWORD` | `dividas` |

No Docker Compose, tambem podem ser configuradas:

| Variavel | Valor padrao |
| --- | --- |
| `POSTGRES_DB` | `dividas` |
| `POSTGRES_USER` | `dividas` |
| `POSTGRES_PASSWORD` | `dividas` |
| `POSTGRES_PORT` | `5432` |
| `APP_PORT` | `8080` |

## Documentacao da API

Com a aplicacao em execucao, acesse:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Endpoints principais

| Metodo | Endpoint | Descricao |
| --- | --- | --- |
| `POST` | `/debts` | Cadastra uma nova divida |
| `GET` | `/debts` | Lista dividas com paginacao |
| `GET` | `/debts/{debtId}` | Consulta uma divida pelo identificador |
| `GET` | `/debts/{cpf}/search` | Consulta uma divida pelo CPF |
| `PUT` | `/debts/{debtId}` | Atualiza uma divida existente |
| `DELETE` | `/debts/{debtId}` | Remove uma divida existente |

## Exemplo de payload

```json
{
  "cpfDevedor": "12345678909",
  "valorPego": 1000.00,
  "valorComJuros": 1200.00,
  "valorComDesconto": 900.00
}
```

## Testes

Execute os testes automatizados com:

```bash
./mvnw test
```

No Windows:

```bash
mvnw.cmd test
```

## Estrutura do projeto

```text
src/main/java/com/willyan/dividas
|-- config
|-- controller
|-- dto
|-- exceptions
|-- mapper
|-- models
|-- repository
`-- service
```

As migrations do banco de dados ficam em:

```text
src/main/resources/db/migration
```
