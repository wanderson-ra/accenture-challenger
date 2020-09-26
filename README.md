# Git Accenture Challenger



## Informações Técnicas
* O sistema foi criado usando ** Java ** (openjdk versão ** "11.0.8" 2020-07-14 **).
* **Spring Boot** foi usado
* O sistema foi desenvolvido com **[TDD](https://pt.wikipedia.org/wiki/Test_Driven_Development)**
* O sistema foi desenvolvido usando ** [Clean Architecture](https://stackoverflow.com/tags/clean-architecture/info) **
* O sistema usa **[Lombok](https://projectlombok.org/)**. Para o desenvolvimento é necessário instalar o plug-in no IDE (caso contrário, haverá erros de compilação).
* **Docker** Foi usado. **[Docker Hub](https://hub.docker.com/repository/docker/wander25rda/accenture-challenge)**
* O projeto está disponível na plataforma **[Heroku](https://challenger-acc.herokuapp.com/)**.
* **Swagger** Foi usado para documentar a API. Para acessar, use o endereço: https://challenger-acc.herokuapp.com/swagger-ui.html
* Mais de 80% de cobertura de testes de unidade.
* Utilizou conceitos de SOLID e Design Patterns.

## Docker
#### PS: O projeto está disponível no [Docker Hub](https://hub.docker.com/repository/docker/wander25rda/accenture-challenge)

* Se você deseja gerar a imagem, use (você precisa do lib (jar) no local):
```sudo docker build -t wander25rda/accenture-challenge:1.0.0 .```

* Ou se você deseja apenas executar, use o comando:
```sudo docker container run --name wander25rda/accenture-challenge -p 8000:8000 wander25rda/accenture-challenge:1.0.0```



**Examplos de uso**


Request (Create):

```
curl --request POST \
  --url https://challenger-acc.herokuapp.com/task/v1/tasks \
  --header 'content-type: application/json' \
  --data '{
	"date": "2020-01-02",
	"description": "Minha tarefa"
}'
```
Response:

```
{
  "id": 5,
  "description": "Minha tarefa",
  "date": "2020-01-02",
  "isDone": false
}
```

Request: (Delete)

```
curl --request DELETE \
  --url https://challenger-acc.herokuapp.com/task/v1/tasks/5
```
Response:200

Request: (Mark is Done)

```
curl --request PUT \
  --url https://challenger-acc.herokuapp.com/task/v1/tasks/3
```
Response:200

Request: (Get All)

```
curl --request GET \
  --url https://challenger-acc.herokuapp.com/task/v1/tasks
```
Response:

```
[
  {
    "id": 1,
    "description": "Nova tarefa",
    "date": "2020-09-06",
    "isDone": true
  },
  {
    "id": 2,
    "description": "Qualquer coisa aqui.",
    "date": "2020-01-02",
    "isDone": false
  }
]

```



