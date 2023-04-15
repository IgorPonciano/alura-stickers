import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeradorLegendaLinguagens implements IGeradorLegenda{

    @Override
    public String ConverteAtributoEmLegenda(IExtratorDeConteudo extrator, String imageTitle, String desiredAtribute) {
        
        List<Map<String, String>> generalAtributeList = new ArrayList<>();
        generalAtributeList = extrator.GetListaAtributosCompleta();

        String desiredKeyValue = "";
        
        // Um dos atributos aparece como int, fazemos essa conversão?
        for (Map<String,String> atributeMap : generalAtributeList)
        {
            if(atributeMap.containsKey("title") && atributeMap.get("title") == imageTitle){
                desiredKeyValue = atributeMap.get(desiredAtribute);
                break;
            }

        }

        return "Número: "+ desiredKeyValue;
    }
    
}
