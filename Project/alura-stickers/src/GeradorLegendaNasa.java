import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeradorLegendaNasa implements IGeradorLegenda{

    // SELECT atributeName FROM generalAtributeList WHERE atributeTitle = titleString
    // return "APOD -" + atributeName
    //NASA
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

        return "APOD - " + desiredKeyValue;
    }
   
}
