# alura-stickers

Projeto que acompanha meu desenvolvimento durante a imersão Java da Alura.

## Aula 4

Nesta aula trabalhamos com a criação de nossa própria API. Seguindo a temática de linguagens de programação ranqueadas, o objetivo foi criar uma api similar à que trabalhamos na aula 1, com os top filmes do IMDB.

Para isso, damos um "salto no tempo", deixando de criar todas as funcionalidades "na unha" e passando a explorar o uso de bibliotecas e frameworks.

### Spring Boot 🌱

O Spring framework nos possibilita criar um webservice de forma muito mais fácil do que se fossemos criar tudo na mão. No seu site, podemos acessar o seguinte caminho:

![SpringInitializrPage](https://user-images.githubusercontent.com/79609859/232253591-c6667b79-c1b1-48d4-b495-b01fbee4eeb1.PNG)

A página nos oferece uma forma fácil de, a partir de algumas opções e escolha de dependências, criar um projeto pronto para a criação do nosso webservice.

![SpringInitializr](https://user-images.githubusercontent.com/79609859/232253711-1bb66b93-19a7-4d25-85eb-97f15caa9962.PNG)

Na pasta /src/main/java, temos uma classe base já previamente criada pelo Spring.

![classeBaseSpringBoot](https://user-images.githubusercontent.com/79609859/232254788-d33d88b9-e360-47be-9f9a-67f46431fda4.PNG)

![conexaoSpring](https://user-images.githubusercontent.com/79609859/232253762-ed95e877-1b1c-4c86-a8e4-ecdcadcf70a2.png)

Ao rodar o código, vemos que o Spring inicializa e que o Apache Tomcat iniciou na porta 8080 do localhost.

Utilizando as Annotations do Spring, podemos criar uma classe simples para trabalhar com essa página levantada pelo Tomcat:

![path_resultado](https://user-images.githubusercontent.com/79609859/232253886-1e7a5bac-46c7-468e-875c-50c81445fa9d.png)

No endereço ".../linguagem-preferida", faça => return string "Olá, web!"

![OlaWeb](https://user-images.githubusercontent.com/79609859/232254357-ea618e7c-2737-49d4-888f-1d930b8aa645.PNG)

Este método simplesmente adiciona a string que passamos como conteúdo da página na web. É uma coisa bem simples, mas demonstra a lógica pela qual vamos disponibilizar o JSON em uma url.

Com uma chamada um pouco diferente, passamos uma List< Linguagem > como retorno de nossa requisição.

![PaginaAPILinguagens](https://user-images.githubusercontent.com/79609859/232253900-4795548a-adaf-4ec1-a1ba-7f24c8ef188a.png)

Aqui, ao invés de um simples "hello world", disponibilizamos na web nosso JSON com a lista de linguagens.

### MongoDB 🌿

Para trazer mais dinamismo e organização para nossa documentação de linguagens, seguimos a base utilizada pela List<Linguagem> para a criação de um baco de dados.

O MongoDB é um banco de dados NoSQL, em que ao invés de guardarmos os valores em tabelas, utilizamos um formato BSON, que é uma representação binária dos dados. Esses dados podem ser trabalhados no formato JSON, o que funciona muito bem com nossa aplicação.
Além disso, o MongoDB oferece o uso dos banco de dados através do CLOUD, como um serviço na AWS por exemplo. O uso em uma escala pequena como a do nosso projeto de estudo se enquadra no plano gratuito do MongoDB Atlas.

![ClusterAtlas](https://user-images.githubusercontent.com/79609859/232252794-1712be3d-f7a1-488b-bb3f-b8b8879f195f.PNG)

A infraestrutura da plataforma nos provê com clusters, no caso, optei por utilizar a região de São Paulo.

![DBCollection](https://user-images.githubusercontent.com/79609859/232252819-4ba7c6ee-5606-4359-b881-c27866da2365.PNG)

Dentro da plataforma online, podemos acessar nossas coleções. Aqui, por exemplo, temos nosso banco de dados com as linguagens. Note como a estrutura de cada item é bem parecido com a estrutura que tinhamos no JSON das apis que consumimos nas últimas aulas.

Para fazer sua integração com nosso projeto, precisamos atualizar as dependências no nosso arquivo pom:

![MongoDBinPom](https://user-images.githubusercontent.com/79609859/232254027-b0e1720d-9d7f-4c70-99a3-d29514bff1a6.PNG)

Para saber qual trecho de código era necessário adicionar, voltamos no site do Spring Initializr, onde criamos nosso projeto. Lá podemos adicionar um outro módulo de dependencia(como fizemos ao adicionar suporte para web)

![SelecionarDependenciaNoSpringInitialz](https://user-images.githubusercontent.com/79609859/232254068-128b6470-1eb1-4efe-a5ab-f05e21642b2f.PNG)

Só que desta vez, ao invés de criar um projeto novo, podemos apenas acessar "Ctrl+Space" para ter acesso ao código utilizado para o pom

![TrechoPom](https://user-images.githubusercontent.com/79609859/232254197-214bf08f-c3a3-4baf-a00a-e7db8ec7b886.PNG)


### Postman 🐱‍🏍

Outra ferramenta muito legal para facilitar nosso processo de interação e manutenção do nosso serviço, é o Postman. Ele é uma solução muito popular para trabalhar com APIs e podemos utilizar-lo para testar e explorar interações com a API que estamos criando.

Aqui, podemos fazer requests como um GET, POST e DELETE.

![PostRequest_Postman](https://user-images.githubusercontent.com/79609859/232253086-9e29ef9b-fa4f-415f-97f5-7eba4904f65f.PNG)

Passando os valores necessários para a criação de um novo item, podemos fazer uma chamada POST no endereço em que nossa API se encontra (no momento, localhost).
Caso tudo esteja certo, recebemos um sinal como o "200 OK" e, nossos dados são adicionados ao banco de dados lá no MongoDB.

Isso é possível, pois lá no nosso código, utilizando Annotations junto do Spring, indicamos o seguinte comportamento para essa chamada:

![classeLinguagemController](https://user-images.githubusercontent.com/79609859/232253241-c5271b02-9628-4c0d-9b4e-039423220835.PNG)

Graças à Annotation @Document, o Spring sabe que o nosso objeto Linguagem é aquilo que estamos armazenando na collection "principaisLinguagens" do MongoDB.

![ObjetoAPI](https://user-images.githubusercontent.com/79609859/232253265-7838e5b6-a1f2-4602-be44-caa108330d08.PNG)

