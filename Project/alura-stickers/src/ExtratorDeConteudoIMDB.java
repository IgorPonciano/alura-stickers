import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoIMDB implements IExtratorDeConteudo{

    List<Map<String, String>> generalAtributeList = new ArrayList<>();

    public List<Conteudo> ExtraiConteudos(String json)
    {
        
       if(generalAtributeList.isEmpty())
       {
            ExtraiListaAtributosCompleta(json);
       }

       return generalAtributeList.stream().map(atributo -> new Conteudo(atributo.get("title"), atributo.get("image"))).toList();
    }

    public void ExtraiListaAtributosCompleta (String json)
    {
        var parser = new JsonParser();
        generalAtributeList = parser.parse(json);
    }

    public List<Map<String, String>> GetListaAtributosCompleta() 
    {
        return generalAtributeList;
    }
}
