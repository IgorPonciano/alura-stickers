# alura-stickers

Projeto que acompanha meu desenvolvimento durante a imersão Java da Alura.

## Aula 1
Durante essa aula, aprendi a fazer a comunicação com um HTTP para conseguir dados, assim como uma forma básica de trabalhar com parse de strings em java, utilizando regex
Além disso, também experimentei bastante com operações de print e estilização dos textos no terminal.

Na parte de trabalhar com diferentes tags para modificar a cor e estilo do texto, senti um pouco de dificuldade para entender como trabalhar com mais de uma tag ao mesmo tempo, mas após alguns testes, cheguei em um resultado que me agradou.
Para o desafio de adicionar emojis para representar a classificação do filme, comecei implementando o valor de estrelas baseado no Math.round() do valor float da nota.
A partir disso, decidi ir além e testar a implementação de emojis diferentes para cada intervalo da classificação, algo como:
🍅 = notas entre 1 e 4;
🌚 = notas entre 5 e 6;
🌈 = notas entre 7 e 8;
🔥 = nota 9;
🏆 = nota 10;

Para isso, segui a implementação de um switch case baseado nesses intervalos, algo que já havia visto em c#, mas que não sabia se era possível em Java. Após um pouco de pesquisa, descobri uma possível implementação:

Por fim, para que o método de gerar a lista de filmes pudesse ser reutilizada para outras listas do IMDB, como melhores séries ou filmes mais populares, extrai o conteúdo do Main para um método que recebe como parâmetros uma string com o Url da API e uma string para o titulo da lista.


```