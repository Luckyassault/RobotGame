package ambyz.robotgame.robotgame;

import android.graphics.Rect;

/**
 * Created by Rene Ambrose Tang on 19/2/2017.
 */

public class EnemyProjectile {
    private Background bg = GameScreen.getBg1();

    private int x, y, speedX;
    private boolean visible;
    // Bullet collision boundary
    private Rect r = new Rect();


    public EnemyProjectile(int centerX, int centerY) {
        x = centerX;
        y = centerY;
        speedX = -5;
        visible = true;
        r = new Rect(0, 0, 0, 0);

    }

    public void update() {
        x += speedX + bg.getSpeedX() * 5;
        r.set(x - 5, y- 5, x + 5, y + 5);


        //Memory management
        if (x < 0) {
            visible = false;
        }
        if (x < 800) {
            checkCollision();
        }
    }

    public void checkCollision() {
        if (Rect.intersects(r, Robot.yellowRed)) {
            if (Robot.isDucked() != true) {
                visible = false;
                GameScreen.reduceLivesLeft(1);
            }
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
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
}
