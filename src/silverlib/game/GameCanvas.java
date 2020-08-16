package silverlib.game;

import silverlib.img.Img;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameCanvas {
  protected static int WINDOWS_OPEN = 0;
  public transient JFrame frame;
  public transient ImgPanel panel;
  protected int width;
  protected int height;
  protected transient WindowAdapter winapt;

  public GameCanvas(int width, int height, String title) {
    this.winapt = new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        --GameCanvas.WINDOWS_OPEN;
        GameCanvas.this.panel.clearPanel();
        if (GameCanvas.WINDOWS_OPEN == 0) {
          System.exit(0);
        }

      }
    };
    this.width = width;
    this.height = height;
    this.frame = new JFrame(title);
    this.frame.setLayout(new BorderLayout());
    this.frame.setResizable(false);
    this.frame.addWindowListener(this.winapt);
    this.frame.setDefaultCloseOperation(1);
    this.panel = new ImgPanel(width, height);
    this.panel.addNotify();
    this.frame.getContentPane().add(this.panel, "Center");
    this.frame.getContentPane().setMinimumSize(new Dimension(width, height));
    this.frame.pack();
    Graphics g = this.panel.getGraphics();
    this.frame.update(g);
    this.frame.setVisible(false);
  }

  public GameCanvas(int width, int height) {
    this(width, height, "Canvas");
  }

  public final Graphics2D getBufferGraphics() {
    return this.panel.getBufferGraphics();
  }

  public Color getColorAt(int x, int y) throws IndexOutOfBoundsException {
    if (x >= 0 && x < this.width) {
      if (y >= 0 && y < this.height) {
        int[] ans = new int[4];
        this.panel.getBuffer().getRaster().getPixel(x, y, ans);
        return new Color(ans[0], ans[1], ans[2], ans[3]);
      } else {
        throw new IndexOutOfBoundsException(String.format("Specified y (%d) is not in range [0, %d)", y, this.height));
      }
    } else {
      throw new IndexOutOfBoundsException(String.format("Specified x (%d) is not in range [0, %d)", x, this.width));
    }
  }

  public boolean show() {
    if (!this.frame.isVisible()) {
      ++WINDOWS_OPEN;
      this.frame.setVisible(true);
      return true;
    } else {
      return true;
    }
  }

  public boolean close() {
    if (this.frame.isVisible()) {
      --WINDOWS_OPEN;
      this.frame.setVisible(false);
      this.panel.clearPanel();
    }

    return true;
  }

  public void clear() {
    this.panel.clearPanel();
  }

  public void drawScene(SceneImg scene) {
    if (scene.width() != this.width || scene.height() != this.height) {
      throw new InvalidSceneSizeException(
          String.format("The provided scene of size [%d, %d] does not match the canvas size [%d, " +
              "%d]", scene.width(), scene.height(), this.width, this.height));
    }

    if (this.frame.getWidth() != scene.width() || this.frame.getHeight() != scene.height()) {
      this.frame.getContentPane().setMinimumSize(new Dimension(scene.width(), scene.height()));
    }

    this.clear();
    this.panel.drawImage(scene, 0,0);
    scene.getStrings().forEach(this.panel::drawString);
  }

  static class InvalidSceneSizeException extends IllegalArgumentException {
    InvalidSceneSizeException(String message) {
      super(message);
    }
  }
}
