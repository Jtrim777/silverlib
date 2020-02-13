package silverlib.game;

import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import silverlib.geo.Point;
import javax.swing.SwingUtilities;

final class GameMouseAdapter extends MouseAdapter {
  Game currentGame;
  Point mousePosn;

  GameMouseAdapter(Game currentGame) {
    this.currentGame = currentGame;
  }

  Point adjustMousePosn(Point mousePosn) {
    Insets ins = this.currentGame.canvas.frame.getInsets();
    return new Point(mousePosn.x()-ins.left, mousePosn.y()-ins.top);
  }

  public void mouseClicked(MouseEvent e) {
    this.currentGame.stopTimer = true;
    this.mousePosn = new Point(e.getX(), e.getY());
    this.currentGame.processMouseEvent(MEventType.CLICKED, getButtonForEvent(e),
        this.adjustMousePosn(this.mousePosn));
    this.currentGame.stopTimer = false;
  }

  public void mouseEntered(MouseEvent e) {
    this.currentGame.stopTimer = true;
    this.mousePosn = new Point(e.getX(), e.getY());
    this.currentGame.processMouseEvent(MEventType.ENTERED, getButtonForEvent(e),
        this.adjustMousePosn(this.mousePosn));
    this.currentGame.stopTimer = false;
  }

  public void mouseExited(MouseEvent e) {
    this.currentGame.stopTimer = true;
    this.mousePosn = new Point(e.getX(), e.getY());
    this.currentGame.processMouseEvent(MEventType.EXITED, getButtonForEvent(e),
        this.adjustMousePosn(this.mousePosn));
    this.currentGame.stopTimer = false;
  }

  public void mousePressed(MouseEvent e) {
    this.currentGame.stopTimer = true;
    this.mousePosn = new Point(e.getX(), e.getY());
    this.currentGame.processMouseEvent(MEventType.PRESSED, getButtonForEvent(e),
        this.adjustMousePosn(this.mousePosn));
    this.currentGame.stopTimer = false;
  }

  public void mouseReleased(MouseEvent e) {
    this.currentGame.stopTimer = true;
    this.mousePosn = new Point(e.getX(), e.getY());
    this.currentGame.processMouseEvent(MEventType.RELEASED, getButtonForEvent(e),
        this.adjustMousePosn(this.mousePosn));
    this.currentGame.stopTimer = false;
  }

  public void mouseMoved(MouseEvent e) {
    this.currentGame.stopTimer = true;
    this.mousePosn = new Point(e.getX(), e.getY());
    this.currentGame.processMouseEvent(MEventType.MOVED, getButtonForEvent(e),
        this.adjustMousePosn(this.mousePosn));
    this.currentGame.stopTimer = false;
  }

  private static MButton getButtonForEvent(MouseEvent e){
    if (SwingUtilities.isLeftMouseButton(e)) {
      return MButton.LEFT;
    } else if (SwingUtilities.isMiddleMouseButton(e)) {
      return MButton.MIDDLE;
    } else {
      return SwingUtilities.isRightMouseButton(e) ? MButton.RIGHT : MButton.UNKNOWN;
    }
  }

  enum MEventType {
    CLICKED,
    ENTERED,
    EXITED,
    PRESSED,
    RELEASED,
    MOVED
  }

  enum MButton {
    LEFT,
    MIDDLE,
    RIGHT,
    UNKNOWN
  }
}

