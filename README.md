# Quarkus User CRUD API 🚀

Este projeto é um exemplo de API RESTful para gerenciamento de usuários (CRUD), desenvolvida com o framework **Quarkus** utilizando o padrão **Active Record** (via Hibernate ORM com Panache) e persistência de dados em **MySQL** (gerenciado via Docker com Quarkus Dev Services).

---

## 🛠️ Tecnologias Utilizadas

- **Java 25**
- **Quarkus 3.35+**
- **Hibernate ORM com Panache** (Padrão *Active Record*)
- **RESTEasy Reactive Jackson** (Serialização JSON e Endpoints REST)
- **MySQL JDBC Driver**
- **Docker** (para execução automática do banco de dados em desenvolvimento)

---

## 📐 Estrutura do Projeto

O projeto adota uma arquitetura simplificada e de fácil manutenção:

- **Entity (`UserEntity`)**: Define a tabela `tb_users` no banco de dados e expõe as operações de persistência utilizando o padrão Active Record. O identificador único do usuário é um `UUID`.
- **Service (`UserService`)**: Contém a lógica de negócio da aplicação (criação, paginação, validação, deleção e atualização).
- **Controller (`UserController`)**: Expõe os endpoints REST e mapeia os parâmetros de entrada e códigos de resposta HTTP.
- **Exceptions (`UserNotFoundExceptionMapper`)**: Intercepta exceções de negócio e as mapeia em respostas HTTP correspondentes (ex: `404 Not Found`).

---

## 🔌 Endpoints da API

Abaixo estão listadas as rotas expostas pela aplicação para o recurso `/users`:

| Método | Endpoint | Parâmetros de Query | Descrição | Status Retornado |
| :--- | :--- | :--- | :--- | :--- |
| **POST** | `/users` | N/A | Cria um novo usuário. | `200 OK` (com o objeto criado) |
| **GET** | `/users` | `page` (default 0), `pageSize` (default 10) | Lista usuários com paginação. | `200 OK` |
| **GET** | `/users/{id}` | N/A | Busca um usuário específico por seu UUID. | `200 OK` ou `404 Not Found` |
| **PUT** | `/users/{id}` | N/A | Atualiza o `username` de um usuário existente. | `200 OK` ou `404 Not Found` |
| **DELETE**| `/users/{id}` | N/A | Exclui um usuário por seu UUID. | `244 No Content` ou `404 Not Found` |

### Exemplos de Cargas Úteis (Payload)

#### Criar/Atualizar Usuário (`POST` / `PUT`)
```json
{
  "username": "warlen_silva"
}
```

#### Retorno de Usuário (`GET /users/{id}`)
```json
{
  "userId": "d718b5b7-720c-4ec8-bbf7-9f4f1ff8109d",
  "username": "warlen_silva"
}
```

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
- **Java 25** instalado.
- **Docker** ou Podman rodando na sua máquina (necessário para que o Quarkus inicialize o banco de dados automaticamente através do *Quarkus Dev Services*).

### Executando em modo de Desenvolvimento (Live Coding)

Para iniciar o Quarkus com live-reload ativo:

```bash
./mvnw quarkus:dev
```

> 💡 **Nota:** O Quarkus iniciará automaticamente um container Docker do MySQL nos bastidores. Você não precisa se preocupar em criar ou configurar o banco manualmente no modo de desenvolvimento.

A API estará disponível em: `http://localhost:8080/users`
O painel do Dev UI do Quarkus pode ser acessado em: `http://localhost:8080/q/dev/`

---

## 📦 Empacotamento e Execução em Produção

### Como JAR tradicional
Para empacotar a aplicação em um arquivo JAR executável:
```bash
./mvnw clean package
```
Isso gerará o artefato executável no diretório `target/quarkus-app/`. Para rodar:
```bash
java -jar target/quarkus-app/quarkus-run.jar
```

### Como Executável Nativo (GraalVM)
Para compilar um executável nativo otimizado (baixo tempo de boot e consumo de memória extremamente reduzido):
```bash
./mvnw clean package -Dnative
```
Ou, se preferir buildar dentro de um container Docker (caso não tenha a GraalVM instalada localmente):
```bash
./mvnw clean package -Dnative -Dquarkus.native.container-build=true
```
Execute o binário gerado com:
```bash
./target/quarkuscrud-1.0.0-SNAPSHOT-runner
```
