# O que é esse projeto?
Esse projeto é uma API RESTful capaz de criar usuários, fazer login no sistema, 
criar, alterar, deletar e concluir tarefas que o usuário especificar.

# Tecnologias usadas

- [X] Kotlin
- [X] Spring MVC
- [X] Spring Data JPA
- [X] Spring Security
- [X] Hibernate
- [X] JWT Token
- [X] MySQL
- [X] Docker
- [X] Gradle

# Como executar o projeto?

Primeiramente tenha a JDK 17 e opcionalmente o Gradle no seu sistema.

### Via repositório
Faça um ```git clone``` ou download do meu repositório e coloque na pasta de sua escolha. 
Entre no ```src/main/resources/application.properties``` e altere esses parâmetros abaixo para o seu próprio banco de dados MySQL.
```properties
spring.datasource.url=jdbc:mysql://
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### Via container
Tenha certeza de que suas portas 8080 e 3306 estejam abertas na sua rede e que você já tenha o Docker e Docker Compose, depois siga os passos abaixo na sua Virtual Machine:
```
git clone https://github.com/Matysys/todolist-system-api-backend.git
cd todolist-system-api-backend
docker compose up -d
```
Assim acesse os endpoints pelo IP da sua Virtual Machine ou do seu próprio sistema na porta 8080.

# Requisitos funcionais e não funcionais

### Funcionais
- Deve permitir criar um usuário no sistema
- Deve permitir logar no sistema
- Deve permitir criar, alterar, deletar e concluir uma tarefa no sistema
- Deve listar as tarefas do usuário logado
- Deve mostrar detalhes adicionais sobre as tarefas
- Deve permitir ver os usuários pelo ID para testes

### Não Funcionais
- Kotlin deve ser a linguagem de programação padrão
- A API deve ter autenticação JWT Token
- A senha do usuário deve ser criptografada

# ENDPOINTS

## Endpoints do usuário

Agora irei explicar sobre os endpoints existentes nessa API.

Usarei como endpoint principal o ```http://todolistsystem.dynv6.net:8080/api/```, mas 
se você estiver executando o projeto localmente, substituia o ```todolistsystem.dynv6.net:8080``` por 
```localhost:8080```. Usarei o Insomnia para testes de requisições, mas você também pode utilizar o Postman.

### Criar usuário

Endpoint: http://todolistsystem.dynv6.net:8080/api/user

Método de requisição: POST

HTTP Status se for realizado com sucesso: 201

Retorno: String

Envie os dados no formato JSON como no exemplo abaixo:

![Criar usuário](./imgs/createuser.png)

### Listar usuário pelo ID

Endpoint: http://todolistsystem.dynv6.net:8080/api/user/id

Troque o id pelo número em questão. O método de requisição é GET, o status code 
é 200 se for feito com sucesso, e o retorno é um objeto com os parâmetros do usuário. Veja abaixo:

![Mostrar usuário por ID](./imgs/showuser.png)

Nesse caso eu permiti que a password fosse retornada pra demonstrar que ela está criptografada.

### Fazer login no sistema

Endpoint: http://todolistsystem.dynv6.net:8080/api/user/login

O método de requisição é POST, o status code
é 200 se for feito com sucesso ou 401 se não for autorizado, e o retorno é um JWT Token com alguns dados do usuário no payload, como nome, id e e-mail. Veja abaixo um exemplo com sucesso:

![Login no sistema](./imgs/login200.png)

Agora um exemplo sem sucesso como por exemplo errando a senha:

![Senha errada](./imgs/login401.png)

### Deletar usuário

Endpoint: http://todolistsystem.dynv6.net:8080/api/user/id

Troque o id pelo número em questão. O método de requisição é DELETE, o status code
é 204 para uma exclusão com sucesso, e não há retorno. Veja abaixo:

![Deletar user por id](./imgs/deleteuser.png)

O método de exclusão de usuário não está disponível no frontend, somente diretamente pela API.











