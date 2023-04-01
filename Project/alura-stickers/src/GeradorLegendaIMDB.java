import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeradorLegendaIMDB implements IGeradorLegenda{
   
    public String ConverteAtributoEmLegenda(IExtratorDeConteudo extrator, String imageTitle, String desiredAtribute) {
        
        List<Map<String, String>> generalAtributeList = new ArrayList<>();
        generalAtributeList = extrator.GetListaAtributosCompleta();

        String desiredKeyValue = "";

        for (Map<String,String> atributeMap : generalAtributeList)
        {
            if(atributeMap.containsKey("title") && atributeMap.get("title") == imageTitle){
                desiredKeyValue = atributeMap.get(desiredAtribute);
                break;
            }

        }

        try {
            float ratingAsFloat = Float.parseFloat(desiredKeyValue);
            int numeroEstrelas = Math.round(ratingAsFloat);
            return  ConvertRatingToLegenda(numeroEstrelas);    
        } 
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public String ConvertRatingToLegenda(int numeroEstrelas) {
        switch ((1 <= numeroEstrelas && numeroEstrelas <= 3) ? 0
                : (4 <= numeroEstrelas && numeroEstrelas <= 5) ? 1
                        : (6 <= numeroEstrelas && numeroEstrelas <= 7) ? 2
                                : (8 == numeroEstrelas ? 3 : 9 == numeroEstrelas ? 4 : 5)) {
            case 0:
                return "trashzão 🍅";
            case 1:
                return "Meh 🌚";
            case 2:
                return "Passaria na Sessão da tarde 🌈";
            case 3:
                return "Vale o ingresso 🍿";
            case 4:
                return "brabo 🔥";
            case 5:
                return "Digno de premio 🏆";
            default:
            return "Sem Classificação";
        }
    }


    
   
   
}
