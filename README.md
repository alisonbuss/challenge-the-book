
## Desafio! Criar uma API de Livros no Spring Boot 3.

### Documentação de Referência:
Para referência adicional, considere as seguintes seções::

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.1/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.1/reference/htmlsingle/#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.1/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Validation](https://docs.spring.io/spring-boot/docs/3.1.1/reference/htmlsingle/#io.validation)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.1.1/reference/htmlsingle/#using.devtools)

### Inspirado:

Esse projeto foi inspirado e pego como base de um projeto pessoal no GitHub **[Quickstart project for Spring Boot](https://github.com/alisonbuss/quickstart-spring-boot-crud)**

### Objetivo:

Criar uma API para que represente um livro com capítulos, páginas e conteúdo das páginas, deverá acessar essa base de dados e extrair as informações das tabelas criadas para um arquivo JSON (um por livro).

```json
// New Book:
{
   "title":"My first book by an API.",
   "chapters":[
      {
         "chapter":"Chapter 1: adding a new chapter.",
         "pages":[
            {
               "content":"In short, the REST API works by manipulating textual data from somewhere to another without direct access to a database or UI. There are many types of Application Programming Interfaces (APIs), although REST stands out as a modern standard."
            },
            {
               "content":"REST API’s are distributed over the ends of HTTP (HyperText Transfer Protocol) Using JavaScript Object Notation (JSON) for formatting, in simple terms, these technologies provide API access using a unique web address to send data that behaves like JavaScript Objects."
            }
         ]
      },
      {
         "chapter":"Chapter 2: Listing API books.",
         "pages":[
            {
               "content":"En resumen, la API REST funciona mediante la manipulación de datos textuales de un lugar a otro sin acceso directo a una base de datos o interfaz de usuario. Hay muchos tipos de interfaces de programación de aplicaciones (API), aunque REST se destaca como un estándar moderno."
            },
            {
               "content":"Las API REST se distribuyen en los extremos de HTTP (Protocolo de transferencia de hipertexto) Usando la notación de objetos de JavaScript (JSON) para formatear, en términos simples, estas tecnologías brindan acceso a la API mediante una dirección web única para enviar datos que se comportan como objetos de JavaScript."
            }
         ]
      }
   ]
}
```

Depois da extração dessas informações e após exportação desses dados no formato JSON, aplicar uma formatação nos dados. (ex: HTML, PDF, etc)

- Listar livros; ***(IMPLEMENTADO)***
- Listar livros com paginação; ***(IMPLEMENTADO)***
- Inserir um livro com capítulos e páginas; ***(IMPLEMENTADO)***
- Recuperar um livro por código; ***(IMPLEMENTADO)***
- Exportar um livros em PDF; ***(IMPLEMENTADO)***
- Exportar um livros em HTML5; ***(NÃO IMPLEMENTADO)***
- Exclusão do livro por código; ***(IMPLEMENTADO)***

Criterio de aceite:

- Construir a Web-api em Spring Boot 3; ***(IMPLEMENTADO)***
- Construir aplicação no padrão Stateless, sem estado. ***(IMPLEMENTADO)***
- Aplicar documentação de API usando o Swagger. ***(IMPLEMENTADO)***
- Aplicar padrões de Logger, para observabilidade. ***(IMPLEMENTADO)***
- Containerizar serviços [PostgreSQL e pgAdmin]. ***(IMPLEMENTADO)***
- Containerizar aplicação em um Minimal JRE (Java 17). ***(IMPLEMENTADO)***
- Utilizar ORM(JPA); ***(IMPLEMENTADO)***
- Implementar mapeamento de DTOs; ***(IMPLEMENTADO)***
- Implementar testes unitários; ***(IMPLEMENTADO)***

### Dependências do Ambiente:

Todo o projeto foi criado e desenvolvido em uma máquina Linux, com as seguintes dependências:

- **[[VS Code](https://code.visualstudio.com/download)]** 1.74.0 ou superior...
- **[[Maven](https://maven.apache.org/download.cgi)]** 3.8.5 ou superior...
- **[[JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)]** 17...
- **[[Docker](https://docs.docker.com/engine/docker-overview/)]** 20.10.9 ou superior...
- **[[Docker Compose](https://docs.docker.com/compose/)]** 2.7.0 ou superior...

> **Nota:**
>
> - _É necessário ter instalado as dependências citadas a cima, para que o projeto funcione._
> - _O desenvolvimento desse projeto foi feita através de um **Desktop Ubuntu 22.04.1 LTS (Jammy Jellyfish)**._

### Estrutura do projeto:

Descrição dos arquivos e diretórios do projeto:

```text
.
├── .vscode.........................Arquivos de configuração do VS Code
│   ├── extensions.json
│   ├── settings.json
│   └── launch.json
├── files
│   |── docs
│   |   └── ...
│   └── services....................Arquivos de suporte e configuração dos serviços Docker
│       ├── postgresql
│       │   ├── postgresql-custom.conf
│       │   ├── postgresql-default-13.3.conf
│       │   └── sql-scripts
│       │       ├── 01-base.sql
│       │       ├── 02-tables.sql
│       │       ├── 03-procedures.sql
│       │       ├── 04-views.sql
│       │       └── 05-inserts.sql
│       └── pgadmin4
│           └── servers.json
├── src............................................Pasta dos códigos fontes do projeto
│   ├── ...
├── .dockerignore...................Arquivo para excluir arquivos ou diretórios desnecessários do seu contexto de build
├── .env............................Arquivo de variáveis de ambiente
├── docker-compose.services.yml.....Docker Compose dos serviços externos
├── docker-compose.webapi.yml.......Docker Compose da aplicação do projeto
├── Dockerfile-17...................Dockerfile da aplicação do projeto
├── LICENSE.........................Licença (MIT)
└── README.md.......................Documentação Geral do Projeto.
```

### Serviços externos do projeto:

O projeto contém 2 serviços encapsulados em um Docker Compose, os serviços são eles:

- **[PostgreSQL](https://hub.docker.com/_/postgres)**
    - Serviço: postgres_server
    - Imagem: postgres:13.3
    - Porta: 5432
    - Rede: localhost ou postgres_server
    - User: admin
    - Pass: P@ssw0rd!

- **[pgAdmin](https://hub.docker.com/r/dpage/pgadmin4)**
    - Serviço: pgadmin_server
    - Imagem: dpage/pgadmin4:7.4
    - Porta: 8083
    - Rede: localhost
    - UI Web: http://localhost:8083
        - User: admin@email.com
        - Pass: P@ssw0rd!

### Command-lines:

Documentação de apoio:

- **[Maven Command-line](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)**

#### Executando o Serviços via Docker Compose:

Para rodar os serviços no Docker Compose, siga com os comandos abaixo:

```bash
# Exibir informações gerais do ambiente Docker.
$ docker image ls && docker network ls && docker volume ls && docker container ls;

# Valide e visualize o arquivo de composição.
$ docker-compose --file ./docker-compose.services.yml config;

# Criar ou reconstruir serviços e construa imagens em paralelo.
$ docker-compose --file ./docker-compose.services.yml build --parallel;

# Criar ou reconstruir serviços no modo desanexado.
$ docker-compose --file ./docker-compose.services.yml up --detach;

# Lista todos os containers do Compose.
$ docker-compose --file ./docker-compose.services.yml ps;
```

Para destruir todos os serviços, network, volumes e imagens:

```bash
# Parar e remover contêineres, redes, imagens e volumes.
$ docker-compose --file ./docker-compose.services.yml down;
$ docker-compose --file ./docker-compose.services.yml rm -f;

# DANDO UMA LIMPADA NO AMBIENTE:
# Esse comando remove todos os contêineres parados, redes não utilizadas, imagens pendentes e caches de compilação...
$ docker system prune -a;

## OU...
$ docker stop $(docker ps -a -q) && docker rm $(docker ps -a -q) && docker rmi $(docker images -q);
```

### Executando o projeto:

#### Executando via Maven Command-line:

Para executar o projeto, siga com os comandos abaixo:

Documentação de apoio:

- **[Maven Command-line](https://www.digitalocean.com/community/tutorials/maven-commands-options-cheat-sheet)**

```bash
# Restaura as dependências e as ferramentas de um projeto.
$ mvn dependency:treen;

# Compila um projeto e todas as suas dependências.
$ mvn compiler:compile;

# Para aplicar testes unitarios.
$ mvn clean test;

# Publica o aplicativo e suas dependências em uma pasta para implantação.
$ mvn package;

# Executa aplicação.
$ mvn package && java -jar target/challenge-the-book-0.0.1-SNAPSHOT.jar;

# OU:

$ ./mvnw spring-boot:run
```

#### Executando via Docker Compose:

Para rodar a aplicação no Docker Compose, siga com os comandos abaixo:

```bash
# Exibir informações gerais do ambiente Docker.
$ docker image ls && docker network ls && docker volume ls && docker container ls;

# Valide e visualize o arquivo de composição.
$ docker-compose --file ./docker-compose.app.yml config;

# Criar ou reconstruir serviços e construa imagens em paralelo.
$ docker-compose --file ./docker-compose.app.yml build --parallel;

# Criar ou reconstruir serviços no modo desanexado.
$ docker-compose --file ./docker-compose.app.yml up --detach;

# Lista todos os containers do Compose.
$ docker-compose --file ./docker-compose.app.yml ps;
```

Para destruir toda a aplicação, network, volumes e imagens:

```bash
# Parar e remover contêineres, redes, imagens e volumes.
$ docker-compose --file ./docker-compose.app.yml down;
$ docker-compose --file ./docker-compose.app.yml rm -f;

# DANDO UMA LIMPADA NO AMBIENTE:
# Esse comando remove todos os contêineres parados, redes não utilizadas, imagens pendentes e caches de compilação...
$ docker system prune -a;

## OU...
$ docker stop $(docker ps -a -q) && docker rm $(docker ps -a -q) && docker rmi $(docker images -q);
```
