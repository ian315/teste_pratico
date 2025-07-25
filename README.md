
# Teste Prático Frota JAVA

1. **Endpoint para consultar todos os veículos (uma listagem)**
- Este endpoint deve retornar todos os veículos disponíveis no banco, sem retornar os pneus aplicados.

2. **Endpoint para consultar um veículo específico (com pneus)**
- Este endpoint deve retornar todas as informações de um veículo específico, incluindo os pneus aplicados e suas respectivas posições.

3. **Endpoint para inserir um veículo específico**
- Este endpoint deve inserir todas as informações de um veículo específico.

4. **Endpoint para inserir um tire específico**
- Este endpoint deve inserir todas as informações de um tire específico.
5. **Endpoint para vincular um tire em um veículo**

- Este endpoint deve vincular um tire com um veículo.

- Lembre-se: um veículo não pode ter dois pneus na mesma posição.

6. **Endpoint para desvincular um tire em um veículo**
    
- Este endpoint deve desvincular um tire com um veículo.

# Tecnologias:
- Java 21
- Spring Boot
- Maven
- PostgreSQL
- Docker & Docker Compose
- JPA / Hibernate
- Flyway (se aplicável)

#Conectar ao DB e subir aplicação ->
-
docker-compose build
-
Rodar o docker-compose up
-
verificar tabelas via db tool -> dbeaver utilizado 
-
URL : jdbc:postgresql://localhost:5432/pneusdb
-
username: postgres
-
pass: 123456
-

LINK SWAGGER ou usar via POSTMAN->
-
http://localhost:8080/swagger-ui/index.html


## Documentação da API

#### Adiciona um veículo

```http localhost:8080/frota/veiculos/
  POST /inserir
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `placa` | `String` | **Obrigatório**.|
| `marca` | `String` | **Obrigatório**.|
| `quilometragem` | `int` | **Obrigatório**.|
| `status` | `VeiculoStatusEnum` | **Obrigatório**.|
| `quantidadeDePneus` | `int` | **Obrigatório**.|

#### Retorna todos os veiculos cadastrados.

```http localhost:8080/frota/veiculos/
  GET /findall
```

#### Recebe um ID de veículo e retorna o veículo com suas informações e lista de Pneus e suas posições.

```http localhost:8080/frota/veiculos/
  GET /findby
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Obrigatório**. ID do veículo|


#### Adiciona um Pneus

```http localhost:8080/frota/tire/
  POST /inserir
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `numeroFogo` | `Long` | **Obrigatório**. Número de fogo do tire|
| `marca` | `String` | **Obrigatório**. Marca do tire|
| `pressao` | `Float` | **Obrigatório**. Pressão do tire|
| `status` | `PneuStatusEnum` | **Obrigatório**. Status do tire|

#### Adiciona um Pneu a um veículo

```http localhost:8080/frota/vehicle-tire/
  POST /inserir
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `veiculoId` | `Long` | **Obrigatório**. ID do veículo|
| `pneuId` | `Long` | **Obrigatório**. ID do tire|
| `posicao` | `int` | **Obrigatório**. posição do tire|

#### Remove um tire de um veículo

```http localhost:8080/frota/vehicle-tire/
  DELETE /remover-tire
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `veiculoId` | `Long` | **Obrigatório**. ID do veículo|
| `pneuId` | `Long` | **Obrigatório**. ID do tire|



