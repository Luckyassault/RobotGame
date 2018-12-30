package ambyz.robotgame.robotgame;

import android.graphics.Rect;
import android.os.CountDownTimer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Rene Ambrose Tang on 16/2/2017.
 */


public class Enemy {

    private ArrayList<EnemyProjectile> projectiles = new ArrayList<EnemyProjectile>();

    private int power, centerX, speedX, centerY, speedY;
    private Background bg = GameScreen.getBg1();
    private Robot robot = GameScreen.getRobot();

    public Rect r = new Rect(0, 0, 0, 0);
    public int health = 5;

    //Mode
    private boolean aggressive = true;
    private boolean defensive = false;

    private boolean readyToFire = false;

    private int movementSpeed;

    // Behavioral Methods
    public void update() {
        if (aggressive == true) {
            follow();

            if (Rect.intersects(r, Robot.yellowRed)) {
                checkCollision();
            }
        } else if (defensive == true) {
            retreat();
        }


    }

    private void checkCollision() {
        if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)
                || Rect.intersects(r, Robot.rect3) || Rect.intersects(r, Robot.rect4)) {

            // highlights robot character if hurt
            if (robot.isHurt()) {

            } else {
                robot.hurt();
                GameScreen.reduceLivesLeft(2);
                //Switch to defensive mode
                aggressive = false;
                defensive = true;

            }
        }
    }


    public void follow() {
        //Horizontal movement
        if (centerX < -95 || centerX > 810) {
            movementSpeed = 0;
        } else if (Math.abs(robot.getCenterX() - centerX) < 5) {
            movementSpeed = 0;
        } else {

            if (robot.getCenterX() >= centerX) {
                movementSpeed = 1;
            } else {
                movementSpeed = -1;
            }
        }

        //Vertical Movement
        if (centerY < -125 || centerY > 500) {
            speedY = 0;
        } else if (Math.abs(robot.getCenterY() - centerY) < 10) {
            speedY = 0;
        } else {

            if (robot.getCenterY() >= centerY) {
                speedY = 4;
            } else {
                speedY = -4;
            }
        }

        // Applying Movement
        centerX += speedX;
        centerY += speedY;
        speedX = bg.getSpeedX() * 5 + movementSpeed;
        r.set(centerX - 25, centerY - 25, centerX + 25, centerY + 35);


    }

    // Causes enemy to jump back once colliding with robot

    public void retreat() {
        if (Math.abs(robot.getCenterX() - centerX) < 480) {
            movementSpeed = 8;
            speedX = bg.getSpeedX() * 5 + movementSpeed;
            centerX += speedX;
            speedY = -2;
            centerY += speedY;
            r.set(centerX - 25, centerY - 25, centerX + 25, centerY + 35);
        } else {
            defensive = false;
            aggressive = true;
        }


    }

    public void die() {

    }

    public void attack() {
        if (readyToFire == true) {

            EnemyProjectile p = new EnemyProjectile(centerX - 10, centerY - 20);
            projectiles.add(p);
        }
    }

    public int getPower() {
        return power;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public Background getBg() {
        return bg;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setBg(Background bg) {
        this.bg = bg;
    }

    public ArrayList getProjectiles() {
        return projectiles;
    }

    public void setReadyToFire(boolean readyToFire) {
        this.readyToFire = readyToFire;
    }
}