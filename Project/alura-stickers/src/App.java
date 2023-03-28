import java.io.IOException; 
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
      PrintMovieRatingListOnTerminal("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json", "Top 10 filmes IMDB");
      PrintMovieRatingListOnTerminal("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json", "Top 3 séries IMDB");
      PrintMovieRatingListOnTerminal("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json", "Filmes mais populares IMDB");
    }

    public static void PrintMovieRatingListOnTerminal(String listUrl, String listTitle) throws IOException, InterruptedException
    {
         // Fazer conexão HTTP para pegar os dados do IMDB
        String url = listUrl;
        URI endereco = URI.create(url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Extrair(parse) somente os dados desejados (titulo, poster, rating)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        // Exibir e manipular os dados
        System.out.println("\u001b[32m \u001b[1m");
        System.out.println(listTitle);
        System.out.println("\u001b[m" );

        int contadorPosicao = 1;

        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[38;2;2;42;122;228m \u001b[48;2;255;255;0m");
            System.out.println("\u001b[1m" + contadorPosicao + ". " + filme.get("title") + "\u001b[m");
            System.out.println("\u001b[m");

            System.out.print("Classificação: ");
            String rating = filme.get("imDbRating");
            System.out.print(rating + " ");
            float ratingAsFloat = Float.parseFloat(rating);
            int numeroEstrelas = Math.round(ratingAsFloat);
            ConvertRatingToEmoji(numeroEstrelas);
            System.out.println("\n");

            System.out.println("Link para o poster: "+ filme.get("image"));
            System.out.println("\n");

            contadorPosicao++;
        }
    }

    private static void ConvertRatingToEmoji(int numeroEstrelas) {
        switch ((1 <= numeroEstrelas && numeroEstrelas <= 3 ) ? 0 :
        (4 <= numeroEstrelas && numeroEstrelas <= 5) ? 1 :
         (6 <= numeroEstrelas && numeroEstrelas <= 7) ? 2 :
         (8 == numeroEstrelas ? 3 :
         9 <= numeroEstrelas ? 4 :
          5)) {
            case 0:
                System.out.print("🍅");
                break;
                case 1:
                System.out.print("🌚");
                break;
                case 2:
                System.out.print("🍿");
                break;
                case 3:
                System.out.print("🌈");
                break;
                case 4:
                System.out.print("🔥");
                break;
                case 5:
                System.out.print("🏆");
                break;
            default:
                break;
        }
    }
}
