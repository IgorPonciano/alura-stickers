import java.util.List;
import java.util.Map;

public interface IExtratorDeConteudo {

    public List<Conteudo> ExtraiConteudos(String json);

    public List<Map<String, String>> GetListaAtributosCompleta();
}
