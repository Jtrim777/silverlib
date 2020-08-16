package silverlib.game;

import java.awt.*;

public class ScreenString implements ISceneDrawable {
  public static final Font DEFAULT_FONT =
      GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()[0].deriveFont(12f);

  private int x;
  private int y;
  private String value;
  private Font font = DEFAULT_FONT;
  private Color color = Color.black;

  public ScreenString(String text, int x, int y) {
    this.x = x;
    this.y = y;
    this.value = text;
  }

  public ScreenString setFont(Font font) {
    this.font = font;

    return this;
  }

  public ScreenString setColor(Color color) {
    this.color = color;

    return this;
  }

  public void draw(Graphics2D g2d) {
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

    Font pFont = g2d.getFont();
    Paint oPaint = g2d.getPaint();

    g2d.setFont(font);
    g2d.setPaint(color);

    g2d.drawString(value, x, y);

    g2d.setFont(pFont);
    g2d.setPaint(oPaint);
  }

  @Override
  public void drawOnScene(SceneImg scene) {
    scene.addString(this);
  }

  @Override
  public void drawOnScene(SceneImg scene, int x, int y) {
    this.x = x;
    this.y = y;

    scene.addString(this);
  }
}
