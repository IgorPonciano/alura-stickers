# alura-stickers

Projeto que acompanha meu desenvolvimento durante a imersão Java da Alura.

## Aula 3

Na aula de hoje, trabalhamos a refatoração, pensando em aspectos como boas práticas e a extensibilidade do nosso código. Vimos questões como o uso de interfaces e polimorfismo para trazer uma abstração e flexibilidade maior para nossos métodos e classes.

### Nova API

Um ponto legal de trabalhar com uma API nova, no caso a API da NASA, foi que como o site da API do IMDB estava com instabilidades, nas outras aulas não tinha tido a experiência de acessar a url utilizando minha própria API, o json vinha de uma url já pronta previamente e isso me frustrava um pouco. Descobrir a sintaxe de uso da api_key e acessar o url por conta própria me trouxe a experiência mais real de trabalhar com esse tipo de ferramenta.

Na [página da API](https://api.nasa.gov/), nós encontramos uma tabela com os parâmetros e um exemplo de como a query fica no url.

![API NASA how to use](https://user-images.githubusercontent.com/79609859/229006448-77ef01e4-7153-45de-a52a-36575252ec12.png)

### String.ReplaceAll()

Durante a aula, descobri que já existe uma solução própria dentro do java para a problemática com a qual nosso parser customizado lidava. Utilizando o método [String.ReplaceAll()]([https://www.javatpoint.com/java-string-replaceall](https://www.javatpoint.com/java-string-replaceall)), podemos substituir facilmente os “:” por “-” em nossos títulos. Assim, o código já fica bem mais limpo:

![String ReplaceAll](https://user-images.githubusercontent.com/79609859/229006781-18781ef6-00c2-4556-882d-9fa6ce657233.png)

## Refatoração do nosso código

### 1. MétodoPrinter da aula 1
Primeiramente, seguindo a próprio andar da aula que buscava tratar o código da classe App, notei que o método que criei durante a aula 1 para printar a lista de filmes e sua classificação, já não funcionava bem naquele espaço. Estavamos desenvolvendo um código de gerador de figurinhas, logo o método de debug no terminal precisava se tornar sua própria classe (Single Responsibility Principle).

![metodoComoNovaClasse](https://user-images.githubusercontent.com/79609859/229007301-33d04ca3-3326-493a-af66-f793e030a275.png)

A chamada do método a partir do nosso main, ficou assim:

![ChamadaDoMoviePrinter](https://user-images.githubusercontent.com/79609859/229007329-59a1e956-0c82-432b-9f93-a02d7c5b4dcb.png)

### 2. Nova API, diferentes keys
Com a adição da nova API da NASA, podemos testar se nosso código sem modificações seria capaz de funcionar para gerar figurinhas do novo url. 

Claro, como os valores das keys da API são diferentes da API do IMDB, alguns ajustes tiverem que ser feitos.

![MudancaNaChamadaDoGetImage](https://user-images.githubusercontent.com/79609859/229007860-d921d635-0146-436c-9950-562d30561811.png)

E o resultado foi o seguinte:

![ImagemNASATest1](https://user-images.githubusercontent.com/79609859/229007918-2c172356-c071-48d1-8e01-6814934689a8.png)

Claro, como nossas legendas customizadas dependiam do valor do “imDbRating” dos filmes, a funcionalidade ainda não é compatível com a API da NASA.

Para resolver tal questão, precisamos primeiro separar o tratamento das APIS do IMDB, NASA e etc.

Para um teste inicial, criei as novas classes, mas por enquanto só adicionei a funcionalidade de converter o valor de uma key da API em legenda para a figurinha.

![TesteExtratorIMDB](https://user-images.githubusercontent.com/79609859/229007991-96cf900d-374b-4882-ba7b-b1bedbd7ffeb.png)
![TesteExtratorNASA](https://user-images.githubusercontent.com/79609859/229008014-462ee47c-ed9a-49d9-8168-dee3df9e93aa.png)
![ChamadaDosMetodosDeGerarLegenda](https://user-images.githubusercontent.com/79609859/229008046-f0d08f74-511e-405b-9cf6-b62a4b0f71a4.png)

E o resultado foi:

![figurinhasAPOD](https://user-images.githubusercontent.com/79609859/229008099-faa24f7b-8e2b-427f-ba30-5bf9e11d0f99.png)

OK, com tudo funcionando corretamente, é hora de realmente repensar a forma como nosso código está escrito.

### 3. Separação das responsabilidades


Classe conteudo: Uma classe criada para substituir as chamadas de "Map<String, String> valor". Um Conteudo armazena a string do titulo e a string com a url da imagem.

As propriedades do Conteudo são campos privados, setados por seu construtor, elas utilizam a [final keyword](https://www.geeksforgeeks.org/final-keyword-in-java/), que permite delimitar que nossas variaveis privadas não poderão ser modificadas após ter o seu valor setado pelo construtor.

[...]

