public enum API{
    IMDB_TOPMOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json",
     new ExtratorDeConteudo("title", "image"), new GeradorLegendaIMDB()),
    IMDB_TOPSERIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json",
     new ExtratorDeConteudo("title", "image"), new GeradorLegendaIMDB()),
    NASA_APOD("https://api.nasa.gov/planetary/apod?api_key=zwGQ4PIv39QDIzyDSzrgBZFbZCcVl7Xua2F5bWJY&start_date=2023-03-27&end_date=2023-03-30",
     new ExtratorDeConteudo("title", "url"), new GeradorLegendaNasa()),
    Alura_Linguagens("http://localhost:8080/linguagens", new ExtratorDeConteudo("title", "image"),
     new GeradorLegendaLinguagens());

    private String url;
    private IExtratorDeConteudo extrator;
    private IGeradorLegenda geradorLegenda;
    
    public String Url() {
        return url;
    }

    public IExtratorDeConteudo Extrator(){
        return extrator;
    }

    public IGeradorLegenda GeradorLegenda(){
        return geradorLegenda;
    }

    API(String url, IExtratorDeConteudo extrator, IGeradorLegenda geradorLegenda){
        this.url = url;
        this.extrator = extrator;
        this.geradorLegenda = geradorLegenda;
    }
}
