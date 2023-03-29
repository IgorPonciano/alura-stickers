# alura-stickers

Projeto que acompanha meu desenvolvimento durante a imersão Java da Alura.

## Aula 2
Nesta aula, trabalhamos com elementos gráficos do java, tais como BufferedImage, Graphics2D, ImageIO e Font. Criamos uma nova classe com a funcionalidade de Fábrica de figurinhas para o whats. Nela, podemos inserir uma imagem base, criar e modificar uma nova imagem baseada nela e exportar-la como um arquivo de imagem novo. Assim, podemos pegar um poster de filme, por exemplo, e baseado em alguns parâmetros, gerar uma imagem como:

![ResultadoFinalExemplo](https://user-images.githubusercontent.com/79609859/228674755-e6a9c44c-3f01-4660-96cc-419ca81ace32.png)

## Desafios

### 1. Criar diretório de saída das imagens, se ainda não existir:
Para este desafio, descobri e explorei tópicos como [File.mkdirs](https://www.geeksforgeeks.org/file-mkdirs-method-in-java-with-examples/). Caso estejamos tentando salvar um arquivo em um path que não existe, o mkdirs permite a criação automática de tal diretório.

![Utilização do mkdirs](https://user-images.githubusercontent.com/79609859/228676483-433c51e8-0825-4840-b762-79d89ef98d29.png)


### 2. Centralizar o texto na figurinha:

Aqui, comecei testando alguns valores até conseguir centralizar a mensagem "topzera", rodando o código em uma imagem de teste. No entanto, quando rodei o código utilizando o url da lista, notei um problema. Nem todos os posters possuem escala similar, nos arquivos com resolução maior que a imagem teste, por exemplo, o texto ficava muito pequenininho. Isso ocorreu, pois nossa font permanecia com o mesmo size independente do tamanho da imagem.

Para encontrar uma forma de escalonar a font de acordo com o tamanho da imagem, recorri ao bom e velho caderninho.
<img src=https://user-images.githubusercontent.com/79609859/228677524-54d7695d-71cd-42b4-9486-4a4b49c0cee8.jpeg width="321" height="426.6">

Utilizando ao menos três imagens, podemos encontrar uma "constante", que nos permite trabalhar o escalonamento para n opções de tamanho.

`
graphics.setFont(graphics.getFont().deriveFont(Font.BOLD, largura * 0.16f )); // tamanho bom para a palavra topzera
`
![exemploTopzera](https://user-images.githubusercontent.com/79609859/228680158-8cced64c-963c-4339-91fa-6c35d493b04b.png)

Tudo funcionava bem, no entanto, ao implementar frases maiores do que "topzera", nosso texto ficava totalmente vazado/cortado.
Então, passei a estudar formas de reescalonar o texto baseado na área disponível para a frase na figurinha.

#### Nova solução:
Aqui, definimos um retângulo como a área limite para o nosso texto, evitando que ele vaze para fora das margens de nossa imagem.

O método de escalonamento da font baseado no rect, veio da seguinte [thread do StackOverflow](https://stackoverflow.com/questions/876234/need-a-way-to-scale-a-font-to-fit-a-rectangle). A partir dessa leitura e uma pequena pesquisa acerca do uso de labels, cheguei a seguinte solução:

![image](https://user-images.githubusercontent.com/79609859/228680620-feb8d496-6f83-4bad-a0e2-d1864b82357e.png)

![scaleFontMethod](https://user-images.githubusercontent.com/79609859/228680823-e3566aa1-0f92-499e-bba8-835bb19caad8.png)


![offsetsTexto](https://user-images.githubusercontent.com/79609859/228680349-8a17cf25-b626-4835-b361-5d1c7ebc6747.png)

Chegando ao seguinte resultado:

<img src=https://user-images.githubusercontent.com/79609859/228684246-e342be4f-f072-4c83-98e9-f7e01bd925bd.png width="168.8" height="325">

```
Apesar do escalonamento funcionar, para o uso como figurinha, textos muito pequenos ficam com leitura ruim.
Por isso, pretendo refatorar o código para buscar uma solução com quebra de linha baseado na área do rect.
``` 
### 3. Colocar outra fonte como a Comic Sans ou a Impact, a fonte usada em memes:

De forma similar ao uso das imagens a partir de um InputStream, podemos importar um arquivo de Font para uso no projeto.

![fontsDentroDoProjeto](https://user-images.githubusercontent.com/79609859/228685342-0ecd6a08-8ca6-4e28-813b-f8b5e8ae7203.png)

![setupCustomFontJava](https://user-images.githubusercontent.com/79609859/228686481-91f477d3-ea54-4975-b64e-d05947b81a09.png)

### 4. Colocar contorno (outline) no texto da imagem:

Para este desafio, primeiro tentei descobrir uma possível solução para a criação de outlines dentro do próprio Java. Após alguns minutos um pouco afogado com os novos conteúdos, decidi parar e pensar em uma solução manual.

Pensando na forma como alguns shaders de outline funcionam na Unity, criei a seguinte solução:

![outlineAntesDoTexto](https://user-images.githubusercontent.com/79609859/228687254-d6093e5b-2868-4afc-ad85-c8a8922f4fe4.png)

Aqui, renderizamos dois outros textos na cor desejada para a outline. Como sua chamada ocorre primeiro, eles serão desenhados por trás do texto em si. Ao modificar a posição das duas cópias, partes delas ficam "sobrando" através do texto original, criando o efeito de outline.

![ExemploOutline](https://user-images.githubusercontent.com/79609859/228687598-d82de06c-5afa-443a-81b8-e71e85a89b59.png)

Claro, essa provavelmente está longe de ser a melhor forma de atingir o resultado desejado, mas como parte do meu aprendizado na imersão, fiquei muito satisfeito com o resultado de uma solução própria.


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

