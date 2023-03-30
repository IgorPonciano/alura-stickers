import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class FabricaDeFigurinhas {

  static Font comic;

  public void Cria(InputStream imageSource, String legenda, String outputFileName) throws Exception {

    // Ler imagem base
    BufferedImage imagemOriginal = ImageIO.read(imageSource);

    // Criar nova imagem em memória com transparencia e tamanho novo
    int largura = imagemOriginal.getWidth();
    int altura = imagemOriginal.getHeight();
    float offsetBase = altura * 0.3f;
    int novaAltura = Math.round(altura + (offsetBase));
    BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

    // Copiar imagem original para a nova imagem (em memória)
    Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
    graphics.drawImage(imagemOriginal, 0, 0, null);

    // Configurar a font do texto

    try {
      // Criar font a partir de file no projeto
      InputStream fontInputStream = new FileInputStream(new File("src/Font/comic.ttf"));
      comic = Font.createFont(Font.TRUETYPE_FONT, fontInputStream);

    } catch (FontFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    graphics.setFont(comic);

    // Criar outline para a frase
    float offsetAltura = 0.001127f * novaAltura;
    float offsetLargura = largura * 0.003618f;

    // recalcular size da font de acordo com o espaço disponivel
    JLabel label = new JLabel(legenda);
    label.setFont(comic);
    float larguraRect = Math.round(largura * 0.75f);
    Rectangle legendaRect = new Rectangle(Math.round(larguraRect), Math.round(offsetBase));
    graphics.setFont(scaleFont(label, legendaRect, graphics));

    // Desenhar outline da frase
    graphics.setColor(Color.black);
    graphics.drawString(legenda, (largura / 8 + offsetLargura), novaAltura - (novaAltura * 0.1f) + offsetAltura);
    graphics.drawString(legenda, (largura / 8 - offsetLargura), novaAltura - (novaAltura * 0.1f) - offsetAltura);

    // Escrever uma frase na nova imagem
    graphics.setColor(Color.yellow);
    graphics.drawString(legenda, (largura / 8), novaAltura - (novaAltura * 0.1f));

    // Escrever a imagem nova em um arquivo
    File f = new File("src/img/saida/" + outputFileName + ".png");

    try {
      ImageIO.write(novaImagem, "png", f);
    }
     catch (FileNotFoundException | IIOException e) // caso o diretório não exista
    {
      e.printStackTrace();
      if (f.mkdirs())
      {
        ImageIO.write(novaImagem, "png", new File(f.getPath()));
      }
    }
  }

  public Font scaleFont(
      final JLabel label, final Rectangle dst, final Graphics2D graphics) {
    assert label != null;
    assert dst != null;
    assert graphics != null;

    final var font = label.getFont();
    final var text = label.getText();

    final var frc = ((Graphics2D) graphics).getFontRenderContext();

    final var dstWidthPx = dst.getWidth();
    final var dstHeightPx = dst.getHeight();

    var minSizePt = 1f;
    var maxSizePt = 1000f;
    var scaledFont = font;
    float scaledPt = scaledFont.getSize();

    while (maxSizePt - minSizePt > 1f) {
      scaledFont = scaledFont.deriveFont(scaledPt);

      final var layout = new TextLayout(text, scaledFont, frc);
      final var fontWidthPx = layout.getVisibleAdvance();

      final var metrics = scaledFont.getLineMetrics(text, frc);
      final var fontHeightPx = metrics.getHeight();

      if ((fontWidthPx > dstWidthPx) || (fontHeightPx > dstHeightPx)) {
        maxSizePt = scaledPt;
      } else {
        minSizePt = scaledPt;
      }

      scaledPt = (minSizePt + maxSizePt) / 2;
    }

    return scaledFont.deriveFont((float) Math.floor(scaledPt));
  }
  // public static void main(String[] args) throws Exception {
  // FabricaDeFigurinhas fabrica = new FabricaDeFigurinhas();
  // fabrica.Cria(new
  // URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies_3.jpg").openStream(),"figurinha2");
  // }
}
