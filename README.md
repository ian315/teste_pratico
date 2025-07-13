#connectinng to DB ->
Rodar o docker-compose up
verificar tabelas via db tool -> dbeaver utilizado 
URL : jdbc:postgresql://localhost:5432/pneusdb
username: postgres
pass: 123456


#Anotações-
Pontos futuros de melhoria, atualmente estou fazendo a Marca ser um String 
porem caso fosse uma aplicação profissional seria mais viável criar
uma entidades Marca, talvez uma marca para pneu outra para veiculo
criar uma tabela no banco tambem para evitar que clientes adicionem uma marca 
FORD, outra FORDI. talvez usar uma variavel tipo para nao precisar criar uma marca
para veiculo e outra para pneu

#criar também endpoint para adicionar uma marca

#Script para criar a tabela marca
CREATE TABLE IF NOT EXISTS marca (
id SERIAL PRIMARY KEY,
nome_marca VARCHAR(50) NOT NULL,
tipo_marca VARCHAR(50) NOT NULL,

    CONSTRAINT chk_tipo_marca CHECK (tipo_marca IN ('VEICULO', 'PNEU'))
);

CREATE TYPE tipo_marca_enum AS ENUM ('VEICULO', 'PNEU');

