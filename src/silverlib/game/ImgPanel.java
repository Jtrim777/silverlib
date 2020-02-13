package silverlib.game;

import silverlib.img.Img;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.util.Hashtable;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRootPane;

public class ImgPanel extends JPanel {
  protected transient BufferedImage buffer = null;
  protected transient ImgPanel.Painter painter = null;

  public ImgPanel(int width, int height) {
    super(new BorderLayout());

    this.setBufferSize(width, height);
    this.painter = new ImgPanel.Painter(this);
    this.add(painter);
  }

  public final Graphics2D getBufferGraphics() {
    return this.buffer.createGraphics();
  }

  public synchronized void setBufferSize(int width, int height) {
    width = Math.max(width, 1);
    height = Math.max(height, 1);
    BufferedImage oldBuffer = this.buffer;
    this.buffer = new BufferedImage(width, height, 1);
    this.clearPanel();
    if (oldBuffer != null) {
      Graphics2D g2 = this.getBufferGraphics();
      g2.drawImage(oldBuffer, 0, 0, this);
    }

    ImgPanel.Refresh.packParentWindow(this);
  }

  public final synchronized int getBufferWidth() {
    return this.buffer.getWidth();
  }

  public final synchronized int getBufferHeight() {
    return this.buffer.getHeight();
  }

  public final BufferedImage getBuffer() {
    return this.buffer;
  }

  public final JPanel getInnerPanel() {
    if (this.painter == null) {
      this.painter = new ImgPanel.Painter(this);
    }
    return this.painter;
  }

  public final void clearPanel() {
    Graphics2D g2 = this.getBufferGraphics();
    g2.setPaint(Color.white);
    g2.fillRect(0, 0, this.buffer.getWidth(), this.buffer.getHeight());
  }

  public void drawImage(Img imread, int x, int y) {
    Graphics2D g = this.getBufferGraphics();
    Paint oldPaint = g.getPaint();

    for(int col = 0; col < imread.width(); ++col) {
      for(int row = 0; row < imread.height(); ++row) {
        Color c = imread.get(col, row);
        if (!(c.getRed() == 255 && c.getBlue() == 255 && c.getGreen() == 255)) {
          g.setPaint(c);
          g.fillRect(x + col, y + row, 1, 1);
        }
      }
    }

    g.setPaint(oldPaint);
    this.repaint();
  }

  public final void setFocusable(boolean focusable) {
    this.getInnerPanel().setFocusable(focusable);
  }

  public final boolean isFocusable() {
    return this.getInnerPanel().isFocusable();
  }

  public final boolean isRequestFocusEnabled() {
    return this.getInnerPanel().isRequestFocusEnabled();
  }

  public final void requestFocus() {
    this.getInnerPanel().requestFocus();
  }

  public final boolean requestFocusInWindow() {
    return this.getInnerPanel().requestFocusInWindow();
  }

  public void paintOver(Graphics2D g2) {
  }

  public static class Refresh {
    private static Hashtable<Window, Window> windowHashtable = new Hashtable();

    private Refresh() {
    }

    public static void packParentWindow(JComponent component) {
      if (component != null) {
        component.revalidate();
        JRootPane pane = component.getRootPane();
        if (pane != null) {
          Object parent = pane.getParent();
          if (parent instanceof Window) {
            synchronized(windowHashtable) {
              Window window = (Window)parent;
              if (!windowHashtable.containsKey(window)) {
                windowHashtable.put(window, window);
                window.setVisible(false);
                window.pack();
                window.setVisible(true);
                windowHashtable.remove(window);
              }
            }
          }
        }

        component.repaint();
      }
    }
  }

  protected static class Painter extends JPanel {
    private static final long serialVersionUID = -1221482625170884907L;
    protected ImgPanel panel = null;

    protected Painter(ImgPanel panel) {
      this.panel = panel;
    }

    public Dimension getPreferredSize() {
      return new Dimension(this.panel.getBufferWidth(), this.panel.getBufferHeight());
    }

    protected void paintComponent(Graphics g) {
      synchronized(this.panel) {
        Insets in = this.getInsets();
        int x = in.left;
        int y = in.top;
        g.drawImage(this.panel.getBuffer(), x, y, this);
        g.translate(x, y);
        g.translate(-x, -y);
      }
    }

    public void paint(Graphics g) {
      synchronized(this.panel) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        Insets in = this.getInsets();
        int x = in.left;
        int y = in.top;
        g2.translate(x, y);
        this.panel.paintOver(g2);
        g2.translate(-x, -y);
      }
    }
  }
}
