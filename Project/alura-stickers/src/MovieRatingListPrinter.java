import java.util.List;
import java.util.Map;

public class MovieRatingListPrinter {

    // run chcp 65001 on cmd terminal for emojis
    public void Print(API source) throws Exception {
        
        ClientHttp client = new ClientHttp();
        String urlDataJson = client.BuscaDados(source.Url());

        // Extrair(parse) somente os dados desejados (titulo, poster, rating)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(urlDataJson);

        // Exibir e manipular os dados
        System.out.println("\u001b[32m \u001b[1m");
        System.out.println(source.toString());
        System.out.println("\u001b[m");

        int contadorPosicao = 1;

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
        }
    }

    private static void ConvertRatingToEmoji(int numeroEstrelas) {
        switch ((1 <= numeroEstrelas && numeroEstrelas <= 3) ? 0
                : (4 <= numeroEstrelas && numeroEstrelas <= 5) ? 1
                        : (6 <= numeroEstrelas && numeroEstrelas <= 7) ? 2
                                : (8 == numeroEstrelas ? 3 : 9 == numeroEstrelas ? 4 : 5)) {
            case 0:
                System.out.print("🍅"); // trashzão
                break;
            case 1:
                System.out.print("🌚"); // Meh
                break;
            case 2:
                System.out.print("🌈"); // Passaria na sessão da tarde
                break;
            case 3:
                System.out.print("🍿"); // Vale o ingresso
                break;
            case 4:
                System.out.print("🔥"); // Brabo
                break;
            case 5:
                System.out.print("🏆"); // Digno de premio
                break;
            default:
                break;
        }
    }
}
