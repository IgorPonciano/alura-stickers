# alura-stickers

Projeto que acompanha meu desenvolvimento durante a imersão Java da Alura.

## Aula 2
Nesta aula, trabalhamos com elementos gráficos do java, tais como BufferedImage, Graphics2D, ImageIO e Font. Criamos uma nova classe com a funcionalidade de Fábrica de figurinhas para o whats. Nela, podemos inserir uma imagem base, criar e modificar uma nova imagem baseada nela e exportar-la como um arquivo de imagem novo. Assim, podemos pegar um poster de filme, por exemplo, e baseado em alguns parâmetros, gerar uma imagem como:

![ResultadoFinalExemplo](https://user-images.githubusercontent.com/79609859/228674755-e6a9c44c-3f01-4660-96cc-419ca81ace32.png)

## Bug encontrado e sua solução

Durante a geração de figurinhas seguindo as urls com as lists de topfilmes e séries, notei que alguns titulos não geravam a imagem. Tais filmes eram "Thor: Love and Thunder", "The Godfather: Part II", "The Lord of the Rings: The Fellowship of the Ring", dentre outros.
Inicialmente, apenas pelo resultado imediato, pensei que o problema estava ligado à sagas de filmes com mais de uma sequência presente, visto que em casos como o "The Godfather", o filme original tinha figurinha, porém a sequência gerava um arquivo quebrado.
Parando para analisar calmamente, percebi que nenhum dos filmes do "The Lord of the Rings" estava presente. Logo, o problema deveria ser em outro detalhe em comum.

Tive um palpite que o erro estava ligado ao nome do filme, todos eles continham ":", mas ainda não sabia o porquê de tal erro. 

Testando isoladamente com o filme do Thor, decidi setar um nome manualmente ao invés de utilizar o titulo. Passei o valor "Thor" e tudo funcionou normalmente, imagem gerada.

![filenameInvalid](https://user-images.githubusercontent.com/79609859/228700900-2658a5d1-54f7-4ec6-98c6-1331dbe611c6.png)

Descobri que o motivo do erro, é que o caractere ":" é na verdade um dos caracteres invalidos para a nomenclatura de um arquivo, algo que não me recordava na hora do teste. Assim, partindo da noção de que precisava encontrar uma valor padrão nas strings de titulo e, modificar seu valor, lembrei-me do parser criado na aula passada e, me pareceu um bom momento para praticar a criação de um novo parser em java.

Utilizando o site [regex101](https://regex101.com/), setei as condições desejadas e utilizei da função Code Generator, para gerar a expressão regular.

![parserTitulos](https://user-images.githubusercontent.com/79609859/228702300-a276603b-3643-47f2-ad9c-24763a37eac2.png)

O método pega todos os caracteres ":" encontrados nos titulos e os substitui por "-", um caractere valido. Assim, "Thor: Love and Thunder" fica "Thor- Love and Thunder".


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

### 5. Colocar uma imagem de você que está fazendo esse curso sorrindo, fazendo joinha e fazer com que o texto da figurinha seja personalizado de acordo com as classificações do IMDB:
Ok, neste desafio, apenas cumpri uma parte (ao menos por enquanto). Eu compreendo como seria o processo de desenhar a minha foto sobre a figurinha, que poderia ser feito utilizando o método [graphics.drawImage](https://docs.oracle.com/javase/tutorial/2d/images/drawimage.html), mas no momento não me senti confortável para tirar fotos e posta-las como figurinhas.

No entanto, a possibilidade de adicionar um foto minha fazendo joinha, me trouxe a ideia de trabalhar aquele switch de intervalos de nota criado na aula 1 e, dependendo da avaliação do filme, adicionar uma foto minha mais empolgado ou entediado, trazendo qual seria minha reação ao assistir tal filme.
Seguindo esta lógica, também atualizei o método Cria() para receber um input diferente dependendo da nota, no caso deste exercício, decidi que a frase legenda da foto (o topzera) seria substituido por uma String diferente para cada avaliação.

![MetodoCria](https://user-images.githubusercontent.com/79609859/228697725-5044fe21-a46b-4225-86d3-933424f34744.png)
![AtualizacaoDoMetodoDeAvaliarNota](https://user-images.githubusercontent.com/79609859/228697873-74cf18e4-cd9c-4d42-a464-a0ee4fa9ffa6.png)

O método acima deverá ser refatorado em breve. Acabei adicionando essa segunda responsabilidade (gerar texto e emoji) para o teste, mas acredito que pensando na limpeza do código, será melhor no mínimo renomear o método e possibilitar a saida separada de diferentes resultados (gerar textoLegendaFigurinha ou emoji avaliação no terminal, ou...)

```
Obs:Sobre a adição de emojis na String da legenda - Na maioria da fonts, não há suporte para um caractere correspondente ao emoji,
por isso o emoji será renderizado como [] ou similares.
Para ter a presença do emoji, evitando essa dependencia direta de utilizar uma font que suporta emojis, podemos realizar o draw
dos emojis como imagem, seguindo a mesma lógica pensada para a inserção da minha foto.
```

