
# Teste Prático Frota JAVA

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

#### Retorna todos os itens

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

#### Adiciona um veículo

```http localhost:8080/frota/veiculos/
  GET /findall
```

#### Retorna todos os veiculos cadastrados.

```http localhost:8080/frota/veiculos/
  GET /findby
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Obrigatório**. ID do veículo|


#### Recebe um ID de veículo e retorna o veículo com suas informações e lista de Pneus e suas posições.

```http localhost:8080/frota/pneu/
  POST /inserir
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `numeroFogo` | `Long` | **Obrigatório**. Número de fogo do pneu|
| `marca` | `String` | **Obrigatório**. Marca do pneu|
| `pressao` | `Float` | **Obrigatório**. Pressão do pneu|
| `status` | `PneuStatusEnum` | **Obrigatório**. Status do pneu|

#### Adiciona um Pneus

```http localhost:8080/frota/veiculo-pneu/
  POST /inserir
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `veiculoId` | `Long` | **Obrigatório**. ID do veículo|
| `pneuId` | `Long` | **Obrigatório**. ID do pneu|
| `posicao` | `int` | **Obrigatório**. posição do pneu|

#### Adiciona um Pneu a um veículo

```http localhost:8080/frota/veiculo-pneu/
  DELETE /remover-pneu
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `veiculoId` | `Long` | **Obrigatório**. ID do veículo|
| `pneuId` | `Long` | **Obrigatório**. ID do pneu|

#### Remove um pneu de um veículo



