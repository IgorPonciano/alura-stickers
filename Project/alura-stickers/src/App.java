import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    // run chcp 65001 on cmd for emojis
    static String legendaFigurinha = "topzera";
    public static void main(String[] args) throws Exception {

        // PrintMovieRatingListOnTerminal("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json",
        // "Top 10 filmes IMDB");
        // PrintMovieRatingListOnTerminal("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json",
        // "Top 3 séries IMDB");
        PrintMovieRatingListOnTerminal("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json",
                "Filmes mais populares IMDB");
    }

    public static void PrintMovieRatingListOnTerminal(String listUrl, String listTitle) throws Exception {
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
        System.out.println("\u001b[m");

        int contadorPosicao = 1;
        FabricaDeFigurinhas fabrica = new FabricaDeFigurinhas();

        for (Map<String, String> filme : listaDeFilmes) {
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

            System.out.println("Link para o poster: " + filme.get("image"));
            System.out.println("\n");

            contadorPosicao++;

            // Gerar figurinhas
            InputStream inputStream = new URL(filme.get("image")).openStream();
            String parsedTitle = ParseTitle(filme.get("title"), ":", "-");
            fabrica.Cria(inputStream, legendaFigurinha,parsedTitle);
        }
    }

    private static String ParseTitle(String originalString, String valueToReplace, String newValue) {
        final String regex = valueToReplace;
        final String subst = newValue;

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(originalString);

        final String result = matcher.replaceAll(subst);
        return result;
    }

    private static void ConvertRatingToEmoji(int numeroEstrelas) {
        switch ((1 <= numeroEstrelas && numeroEstrelas <= 3) ? 0
                : (4 <= numeroEstrelas && numeroEstrelas <= 5) ? 1
                        : (6 <= numeroEstrelas && numeroEstrelas <= 7) ? 2
                                : (8 == numeroEstrelas ? 3 : 9 == numeroEstrelas ? 4 : 5)) {
            case 0:
                System.out.print("🍅"); // trashzão
                legendaFigurinha = "trashzão 🍅";
                break;
            case 1:
                System.out.print("🌚"); // Meh
                legendaFigurinha = "Meh 🌚";
                break;
            case 2:
                System.out.print("🌈"); // Passaria na sessão da tarde
                legendaFigurinha = "Passaria na Sessão da tarde 🌈";
                break;
            case 3:
                System.out.print("🍿"); // Vale o ingresso
                legendaFigurinha = "Vale o ingresso 🍿";
                break;
            case 4:
                System.out.print("🔥"); // Brabo
                legendaFigurinha = "brabo 🔥";
                break;
            case 5:
                System.out.print("🏆"); // Digno de premio
                legendaFigurinha = "Digno de premio 🏆";
                break;
            default:
                break;
        }
    }
}
