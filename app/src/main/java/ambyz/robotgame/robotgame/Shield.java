package ambyz.robotgame.robotgame;

import android.graphics.Rect;

/**
 * Created by Rene Ambrose Tang on 23/2/2017.
 */

public class Shield {
    private boolean visible;
    private int x,y;
    public Rect r = new Rect();
    private Robot robot = GameScreen.getRobot();
    private Missiles mis1 = GameScreen.getMis1();
    private Missiles mis2 = GameScreen.getMis2();

    public Shield(){
        visible = false;

    }

      public void update(){
          if (visible == true) {
              checkCollision();
              x = robot.getCenterX()-61;
              y = robot.getCenterY()-56;
              new Rect(x - 61, y - 63, x + 61, y + 63);
          }
    }


    public void checkCollision() {
        if (Rect.intersects(r, mis1.getR())) {
            //mis1.setShield(true);

        } else if (Rect.intersects(r, mis2.getR())) {
            //mis2.setShield(true);
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
