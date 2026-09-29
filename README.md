# teste_tecnico_java_spring_boot_controle_dividas_api

## Docker

Para subir a API com Postgres:

```bash
docker compose up --build
```

Para subir somente o Postgres e rodar a aplicacao localmente:

```bash
docker compose up -d postgres
mvn spring-boot:run
```

## Swagger

Com a aplicacao em execucao, acesse:

- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs
