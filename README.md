# CRUD de Cadastros de Professores, Alunos e Turmas - API REST com Java Spring
## Introdução
Este projeto é uma API REST desenvolvida em Java usando o framework Spring. A aplicação permite o gerenciamento de cadastros de professores, alunos e turmas, suportando operações CRUD (Create, Read, Update, Delete). A persistência de dados é realizada utilizando o MySQL, e o Spring Data JPA facilita as operações de banco de dados. Para validação de dados, utilizamos as anotações de validação do Spring, e o Lombok é empregado para reduzir a verbosidade do código junto a tratamento de erros.

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3.x
- Spring Data JPA
- Spring MVC
- MySQL
- Lombok
- Hibernate Validator

## Estrutura do Projeto
A estrutura básica do projeto é a seguinte:

 ![image](https://github.com/superkarlos/JAVA-API-REST-SPRING-MVC-DATA-MYSQL/assets/50372440/803a9092-7d33-4287-95bd-502335f67a25)

## Configuração do Banco de Dados
O projeto utiliza o MySQL como banco de dados. Certifique-se de ter o MySQL instalado e configurado. Crie um banco de dados chamado crud_db e atualize as configurações no arquivo application.properties:

![image](https://github.com/superkarlos/JAVA-API-REST-SPRING-MVC-DATA-MYSQL/assets/50372440/d2e29106-01f8-4196-9728-5a4ef605a30a)

```
spring.datasource.url=jdbc:mysql://localhost:3306/crud_db
spring.datasource.username= root (use nome so seu banco de dados)
spring.datasource.password=senha (use sua senha de banco de dados)
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

```
## Como Baixar e Testar o Projeto
### Pré-requisitos
Antes de começar, certifique-se de ter os seguintes itens instalados em seu sistema:

- Java Development Kit (JDK) 17
- Maven
- MySQL
### Passos para Clonar e Configurar o Projeto
1 - Clone o repositório:
Abra o terminal e execute o comando:
```
git clone https://github.com/superkarlos/JAVA-API-REST-SPRING-MVC-DATA-MYSQL.git
cd seu-repositorio
```

### Configure o banco de dados:

2 - Crie um banco de dados no MySQL chamado crud_db.
- Atualize o arquivo src/main/resources/application.properties com as credenciais do seu banco de dados:
properties
Copiar código

```
spring.datasource.url=jdbc:mysql://localhost:3306/crud_db
spring.datasource.username= root (use nome so seu banco de dados)
spring.datasource.password=senha (use sua senha de banco de dados)
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

```

3 - Instale as dependências e compile o projeto:
No terminal, dentro do diretório do projeto, execute:
```
mvn clean install
```
4- Execute a aplicação:
Ainda no terminal, execute:
```
mvn spring-boot:run
```
A aplicação estará disponível em http://localhost:8080.

### Testando a API
Para testar a API, você pode usar ferramentas como Postman ou Insomnia. Disponibilizamos um arquivo JSON de configuração do Insomnia que contém todas as requisições necessárias para testar os endpoints.

1 - Baixe e instale o Insomnia:

- Insomnia
2 - Importe as requisições no Insomnia:

- Baixe o arquivo de configuração JSON do Insomnia disponível aqui.
- No Insomnia, clique em File > Import > From File e selecione o arquivo JSON baixado.
3 -Utilize as requisições importadas para testar a API:

- Você verá uma lista de requisições organizadas para testar as operações CRUD para as entidades Aluno, Professor e Turma.
Exemplos de Requisições via cURL
Caso prefira, você também pode usar o cURL para testar a API. Aqui estão alguns exemplos de comandos cURL para cada operação:

Cadastrar um novo aluno:



Conclusão
Agora você está pronto para baixar, configurar e testar o projeto em sua máquina local. Sinta-se à vontade para explorar o código, fazer modificações e adicionar novas funcionalidades conforme necessário. Se encontrar algum problema, verifique novamente as configurações do banco de dados e certifique-se de que todas as dependências foram instaladas corretamente. Boa sorte!
