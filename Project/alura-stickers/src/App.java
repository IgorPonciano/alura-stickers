import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    static String legendaFigurinha = "topzera";

    public static void main(String[] args) throws Exception {

        API api = API.NASA_APOD;
        
        // Fazer conexão HTTP para pegar os dados do IMDB
        String url = api.Url();

        ClientHttp client = new ClientHttp();
        String urlDataJson = client.BuscaDados(url); 
        
        List<Conteudo> conteudos = api.Extrator().ExtraiConteudos(urlDataJson);

        FabricaDeFigurinhas fabrica = new FabricaDeFigurinhas();
        
        // Gerar figurinhas
        for (Conteudo conteudo : conteudos) {
           
            InputStream imgInputStream = new URL(conteudo.urlImagem()).openStream();
            String parsedTitle = conteudo.titulo().replaceAll(":", "-");

            // Gerar legenda custom -> WIP
            GenerateCustomStickerTextBasedOn(api, conteudo, "date"); // API NASA: date, API IMDB: imDbRating - valores teste, API Alura: ranking
           

            fabrica.Cria(imgInputStream, legendaFigurinha, parsedTitle);
        }

        // Printer de classificação aula 1
        // MovieRatingListPrinter printer = new MovieRatingListPrinter();
        // printer.Print(topMoviesIMDB);
    }

    private static void GenerateCustomStickerTextBasedOn(API api, Conteudo conteudo, String desiredAtributeFromAPI) {
        legendaFigurinha = api.GeradorLegenda().ConverteAtributoEmLegenda(api.Extrator(), conteudo.titulo(),  desiredAtributeFromAPI);
    }
}