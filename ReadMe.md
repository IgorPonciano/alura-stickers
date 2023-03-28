# alura-stickers

Projeto que acompanha meu desenvolvimento durante a imersão Java da Alura.

## Aula 1
Durante essa aula, aprendi a fazer a comunicação com um HTTP para conseguir consumir dados de uma API, assim como uma forma básica de trabalhar com parse de strings em java, utilizando regex.
Além disso, também experimentei bastante com operações de print e estilização dos textos no terminal.

Na parte de trabalhar com diferentes tags para modificar a cor e estilo do texto, senti um pouco de dificuldade para entender como trabalhar com mais de uma tag ao mesmo tempo, mas após alguns testes, cheguei em um resultado que me agradou.

Para o desafio de adicionar emojis para representar a classificação do filme, comecei implementando o valor de estrelas baseado no Math.round() do valor da classificação como float.
A partir disso, decidi ir além e testar a implementação de emojis diferentes para cada intervalo da classificação, algo como:

``` 
🍅 = notas entre 1 e 3
🌚 = notas entre 4 e 5
🍿 = notas entre 6 e 7
🌈 = nota 8
🔥 = nota 9
🏆 = nota 10
``` 

Para isso, segui a implementação de um switch case baseado nesses intervalos, algo que já havia visto em c#, mas que não sabia se era possível em Java. Após um pouco de pesquisa, descobri uma possível implementação:

![switchCaseComCondicionais](https://user-images.githubusercontent.com/79609859/228282403-e27be361-0242-455f-bee3-7def831c427d.PNG)


Por fim, para que o método de gerar a lista de filmes pudesse ser reutilizada para outras listas do IMDB, como melhores séries ou filmes mais populares, extrai o conteúdo do Main para um método que recebe como parâmetros uma string com o Url da API e uma string para o titulo da lista.

![CodigoComoMetodo](https://user-images.githubusercontent.com/79609859/228272839-b034511b-957c-43f6-86b6-07b527f5cc94.PNG)

``` 
Este foi o resultado atingido:
``` 
![Output do código](https://user-images.githubusercontent.com/79609859/228272655-26bbcd67-07c4-43ad-8080-59c532046c88.PNG)

