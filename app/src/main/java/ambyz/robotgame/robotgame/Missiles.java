package ambyz.robotgame.robotgame;

import android.graphics.Rect;

/**
 * Created by Rene Ambrose Tang on 21/2/2017.
 */

public class Missiles {
    private Robot robot = GameScreen.getRobot();
    private Background bg = GameScreen.getBg1();

    private int x, y, speed;
    private boolean visible, ready;

    private Rect r = new Rect();
    final private int DAMAGE = 1;
    final private int YSTART = 20;//slightly above screen
    final private int DROPSPEED = 8;


    public Missiles(int centerX) {
        x = centerX;
        y = YSTART;
        speed = DROPSPEED;
        visible = false; // missile is not painted
        ready = false; // missile does not drop yet
        r = new Rect(0, 0, 0, 0);
    }

    public void update() {
        x += bg.getSpeedX() * 5;

        if (isReady() == true) {
            visible = true;
            y += speed;
            r.set(x, y, x + 40, y + 120);
            checkCollision();

            //Memory management
            // missile drop out of screen
            if (y > 480) {//&& robot near) {
                visible = false;
                ready = false;
                x = -100;//temporary
            }

        }

    }


    public void checkCollision() {
        if (Rect.intersects(r, Robot.yellowRed)) {
            visible = false;
            robot.hurt();
            GameScreen.reduceLivesLeft(1);
            x = -100;//temporary

        }
    }

    public boolean isReady() {
        // check if robot in range
        if (x - robot.getCenterX() <= 100) {
            visible = true;
            return ready = true;
        } else {
            visible = false;
            return ready = false;

        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Rect getR() {
        return r;
    }

    public void setR(Rect r) {
        this.r = r;
    }

    public int getY() {
        return y;
    }


}
