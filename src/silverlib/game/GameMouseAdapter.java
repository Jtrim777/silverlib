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
    this.currentGame.processMouseEvent(GameUtils.MEventType.CLICKED, getButtonForEvent(e),
        this.adjustMousePosn(this.mousePosn));
    this.currentGame.stopTimer = false;
  }

  public void mouseEntered(MouseEvent e) {
    this.currentGame.stopTimer = true;
    this.mousePosn = new Point(e.getX(), e.getY());
    this.currentGame.processMouseEvent(GameUtils.MEventType.ENTERED, getButtonForEvent(e),
        this.adjustMousePosn(this.mousePosn));
    this.currentGame.stopTimer = false;
  }

  public void mouseExited(MouseEvent e) {
    this.currentGame.stopTimer = true;
    this.mousePosn = new Point(e.getX(), e.getY());
    this.currentGame.processMouseEvent(GameUtils.MEventType.EXITED, getButtonForEvent(e),
        this.adjustMousePosn(this.mousePosn));
    this.currentGame.stopTimer = false;
  }

  public void mousePressed(MouseEvent e) {
    this.currentGame.stopTimer = true;
    this.mousePosn = new Point(e.getX(), e.getY());
    this.currentGame.processMouseEvent(GameUtils.MEventType.PRESSED, getButtonForEvent(e),
        this.adjustMousePosn(this.mousePosn));
    this.currentGame.stopTimer = false;
  }

  public void mouseReleased(MouseEvent e) {
    this.currentGame.stopTimer = true;
    this.mousePosn = new Point(e.getX(), e.getY());
    this.currentGame.processMouseEvent(GameUtils.MEventType.RELEASED, getButtonForEvent(e),
        this.adjustMousePosn(this.mousePosn));
    this.currentGame.stopTimer = false;
  }

  public void mouseMoved(MouseEvent e) {
    this.currentGame.stopTimer = true;
    this.mousePosn = new Point(e.getX(), e.getY());
    this.currentGame.processMouseEvent(GameUtils.MEventType.MOVED, getButtonForEvent(e),
        this.adjustMousePosn(this.mousePosn));
    this.currentGame.stopTimer = false;
  }

  private static GameUtils.MButton getButtonForEvent(MouseEvent e){
    if (SwingUtilities.isLeftMouseButton(e)) {
      return GameUtils.MButton.LEFT;
    } else if (SwingUtilities.isMiddleMouseButton(e)) {
      return GameUtils.MButton.MIDDLE;
    } else {
      return SwingUtilities.isRightMouseButton(e) ? GameUtils.MButton.RIGHT : GameUtils.MButton.UNKNOWN;
    }
  }
}

