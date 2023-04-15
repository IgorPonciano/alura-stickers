import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudo implements IExtratorDeConteudo{

    List<Map<String, String>> generalAtributeList = new ArrayList<>();

    private String title;
    private String urlImage;
    
    public List<Conteudo> ExtraiConteudos(String json)
    {
        
       if(generalAtributeList.isEmpty())
       {
            ExtraiListaAtributosCompleta(json);
       }

       return generalAtributeList.stream().map(atributo -> new Conteudo(atributo.get(title), atributo.get(urlImage))).toList();
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

    public ExtratorDeConteudo(String keyTitle, String keyImageUrl) {
        this.title = keyTitle;
        this.urlImage = keyImageUrl;
    }


}
