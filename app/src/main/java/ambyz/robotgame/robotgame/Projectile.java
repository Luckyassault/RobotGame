package ambyz.robotgame.robotgame;

/**
 * Created by Rene Ambrose Tang on 16/2/2017.
 */

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Canvas;


public class Projectile {

    private int x, y, speedX;
    private boolean visible;

    private int[] bulletx = new int[5];
    private int[] bullety = new int[5];

    private Paint bulletpaint = new Paint();


    //private bulletpath;
    private Path bulletpath = new Path();
    // projectile box
    private Rect r = new Rect();

    public Projectile(int startX, int startY) {
        x = startX;
        y = startY;
        speedX = 7;
        visible = true;


        bulletpaint.setColor(Color.RED);
        bulletpaint.setStyle(Paint.Style.STROKE);

        bulletpath = new Path();
        r = new Rect(0, 0, 0, 0);

    }

    public void update() {
        x += speedX;

        bulletx = new int[]{x, x + 10, x + 13, x + 10, x + 2};
        bullety = new int[]{y, y, y + 2, y + 5, y + 5};
        r.set(x, y, x + 10, y + 5);
        for (int i = 0; i < bulletx.length; i++) {
            if (i == 0) {
                bulletpath.reset();
                bulletpath.moveTo(bulletx[i], bullety[i]);
            } else {
                bulletpath.lineTo(bulletx[i], bullety[i]);
            }
        }

        if (x > 800) {
            visible = false;
            bulletpath.reset();
        }
        if (x < 800) {
            checkCollision();
        }
    }

    private void checkCollision() {
        if (Rect.intersects(r, GameScreen.hb.r)) {
            visible = false;

            if (GameScreen.hb.health > 0) {
                GameScreen.hb.health -= 1;
            }
            if (GameScreen.hb.health == 0) {
                GameScreen.hb.setCenterX(-100);

            }

        }

        if (Rect.intersects(r, GameScreen.hb2.r)) {
            visible = false;

            if (GameScreen.hb2.health > 0) {
                GameScreen.hb2.health -= 1;
            }
            if (GameScreen.hb2.health == 0) {
                GameScreen.hb2.setCenterX(-100);


            }

        }
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Paint getBulletpaint() {
        return bulletpaint;
    }

    public Path getBulletpath() {
        return bulletpath;
    }


}
