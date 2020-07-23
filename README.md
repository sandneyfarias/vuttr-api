# Desafio Dev. Back-End

Este projeto foi desenvolvido para o processo de seleção da statideia.

A tarefa envolveu a construção de uma API e banco de dados para a aplicação VUTTR (Very Useful Tools to Remember). A aplicação é um simples repositório para gerenciar ferramentas com seus respectivos nomes, links, descrições e tags. Utilize um repositório Git (público, de preferência) para versionamento e disponibilização do código.

A aplicação poderia ser construída utilizando qualquer linguagem de programação backend. Qualquer banco de dados, libraries e ferramentas de sua preferência.

A API deveria ser documentada utilizando o formato API Blueprint ou Swagger.

Mains informações sobre o desafio em [gustavo-startaideia/desafio-backend](https://github.com/gustavo-startaideia/desafio-backend#requisitos)

## Requisitos
- Java 11
- PostgreSQL

## Criação do Banco de Dados
O projeto utiliza o recurso de migrations e na primeira execução criará todas as tabelas e, caso necessário, fará a carga de dados, mas antes da primeira execução é necessário criar o banco de dados e também ajustar os dados de acesso
nos arquivos de configuração localizados na pasta src/main/resources. Modifique o nome do usuário, a senha e, caso necessário, a porta de acesso do PostgreSQL.

### Script de criação do banco
CREATE DATABASE vuttr
    WITH 
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

## Executando a API
1. mvn clean
2. mvn install
3. mvn spring-boot:run

As duas primeiras etapas só precisam ser executadas na primeira vez ou caso você faça alguma modificação nas dependências do projeto.

## Acessando a documentação da API
Após iniciar a execução da API acesse a URL [http://localhost:3000/swagger-ui.html] (http://localhost:3000/swagger-ui.html)



 
