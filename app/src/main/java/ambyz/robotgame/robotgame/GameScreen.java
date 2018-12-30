package ambyz.robotgame.robotgame;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import ambyz.robotgame.framework.Game;
import ambyz.robotgame.framework.Graphics;
import ambyz.robotgame.framework.Image;
import ambyz.robotgame.framework.Input.TouchEvent;
import ambyz.robotgame.framework.Screen;

import android.graphics.PorterDuff;
import android.widget.ImageView;
import android.graphics.PorterDuff.Mode;

/**
 * Created by Rene Ambrose Tang on 16/2/2017.
 */


public class GameScreen extends Screen {
    enum GameState {
        Ready, Running, Paused, GameOver
    }

    GameState state = GameState.Ready;

    // Variable Setup
    private final int STARTLIVES = 10;

    private static Background bg1, bg2;
    private static Robot robot;
    private static Missiles mis1, mis2;
    public static Heliboy hb, hb2;
    private static Shield shield;


    private Image currentSprite, character, character2, character3, heliboy,
            heliboy2, heliboy3, heliboy4, heliboy5, shield1, shield2, shield3;
    private Animation anim, hanim, sanim;

    private ArrayList tilearray = new ArrayList();

    private static int livesLeft;

    // Random number between 0 -99
    Random rand = new Random();
    private int randno;

    Paint paint, paint2;

    public GameScreen(Game game) {
        super(game);

        // Initialize game objects here

        bg1 = new Background(0, 0);
        bg2 = new Background(2160, 0);
        robot = new Robot();
        hb = new Heliboy(340, 360);
        hb2 = new Heliboy(700, 360);
        mis1 = new Missiles(400);
        mis2 = new Missiles(600);
        shield = new Shield();

        character = Assets.character;
        character2 = Assets.character2;
        character3 = Assets.character3;

        heliboy = Assets.heliboy;
        heliboy2 = Assets.heliboy2;
        heliboy3 = Assets.heliboy3;
        heliboy4 = Assets.heliboy4;
        heliboy5 = Assets.heliboy5;

        shield1 = Assets.shield1;
        shield2 = Assets.shield2;
        shield3 = Assets.shield3;

        anim = new Animation();
        anim.addFrame(character, 1250);
        anim.addFrame(character2, 50);
        anim.addFrame(character3, 50);
        anim.addFrame(character2, 50);

        hanim = new Animation();
        hanim.addFrame(heliboy, 100);
        hanim.addFrame(heliboy2, 100);
        hanim.addFrame(heliboy3, 100);
        hanim.addFrame(heliboy4, 100);
        hanim.addFrame(heliboy5, 100);
        hanim.addFrame(heliboy4, 100);
        hanim.addFrame(heliboy3, 100);
        hanim.addFrame(heliboy2, 100);

        sanim = new Animation();
        sanim.addFrame(shield1, 300);
        sanim.addFrame(shield2, 300);
        sanim.addFrame(shield3, 1000);
        sanim.addFrame(shield2, 300);
        sanim.addFrame(shield1, 300);

        currentSprite = anim.getImage();

        loadMap();

        // Defining a paint object
        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        paint2 = new Paint();
        paint2.setTextSize(100);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.WHITE);

    }

    private void loadMap() {
        ArrayList lines = new ArrayList();
        int width = 0;
        int height = 0;

        Scanner scanner = new Scanner(SampleGame.map);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            // no more lines to read
            if (line == null) {
                break;
            }

            if (!line.startsWith("!")) {
                lines.add(line);
                width = Math.max(width, line.length());

            }
        }
        height = lines.size();

        for (int j = 0; j < 12; j++) {
            String line = (String) lines.get(j);
            for (int i = 0; i < width; i++) {

                if (i < line.length()) {
                    char ch = line.charAt(i);
                    Tile t = new Tile(i, j, Character.getNumericValue(ch));
                    tilearray.add(t);
                }

            }
        }

    }

    @Override
    public void update(float deltaTime) {
        List touchEvents = game.getInput().getTouchEvents();

        // We have four separate update methods in this example.
        // Depending on the state of the game, we call different update methods.
        // Refer to Unit 3's code. We did a similar thing without separating the
        // update methods.

        if (state == GameState.Ready)
            updateReady(touchEvents);
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if (state == GameState.Paused)
            updatePaused(touchEvents);
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    private void updateReady(List touchEvents) {

        // This example starts with a "Ready" screen.
        // When the user touches the screen, the game begins.
        // state now becomes GameState.Running.
        // Now the updateRunning() method will be called!

        if (touchEvents.size() > 0)
            state = GameState.Running;

        livesLeft = STARTLIVES;
    }

    private void updateRunning(List touchEvents, float deltaTime) {

        // This is identical to the update() method from our Unit 2/3 game.

        // 1. All touch input is handled here:
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent) touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {

                if (inBounds(event, 0, 285, 65, 65)) {
                    robot.jump();
                    currentSprite = anim.getImage();
                    robot.setDucked(false);
                } else if (inBounds(event, 0, 350, 65, 65)) {

                    if (robot.isDucked() == false && robot.isJumped() == false
                            && robot.isReadyToFire()) {
                        robot.shoot();
                    }
                } else if (inBounds(event, 0, 415, 65, 65)
                        && robot.isJumped() == false) {
                    currentSprite = Assets.characterDown;
                    robot.setDucked(true);
                    robot.setSpeedX(0);
                    shield.setVisible(true);


                }

                if (event.x > 400) {
                    // Move right.
                    robot.moveRight();
                    robot.setMovingRight(true);

                }

            }

            if (event.type == TouchEvent.TOUCH_UP) {

                if (inBounds(event, 0, 415, 65, 65)) {
                    currentSprite = anim.getImage();
                    robot.setDucked(false);

                }

                if (inBounds(event, 0, 0, 35, 35)) {
                    pause();

                }

                if (event.x > 400) {
                    // Move right.
                    robot.stopRight();
                }
            }

        }

        // 2. Check miscellaneous events like death:

        if (livesLeft <= 0) {
            state = GameState.GameOver;
        }

        // 3. Call individual update() methods here.
        // This is where all the game updates happen.
        // For example, robot.update();
        randno = rand.nextInt(1000);
        enemyattack();

        robot.update();
        if (robot.isJumped()) {
            currentSprite = Assets.characterJump;
        } else if (robot.isHurt()) {
            currentSprite = Assets.characterJump;
        } else if (robot.isJumped() == false && robot.isDucked() == false && robot.isHurt() == false) {
            currentSprite = anim.getImage();
            shield.setVisible(false);
        }

        // Updating the projectiles based on elements in ArrayList made in robot class
        ArrayList projectiles = robot.getProjectiles();
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile p = (Projectile) projectiles.get(i);
            if (p.isVisible() == true) {
                p.update();
            } else {
                projectiles.remove(i);
            }
        }

        ArrayList hbprojectiles = hb.getProjectiles();
        for (int i = 0; i < hbprojectiles.size(); i++) {
            EnemyProjectile p = (EnemyProjectile) hbprojectiles.get(i);
            if (p.isVisible() == true) {
                p.update();
            } else {
                hbprojectiles.remove(i);
            }
        }
        ArrayList hb2projectiles = hb2.getProjectiles();
        for (int i = 0; i < hb2projectiles.size(); i++) {
            EnemyProjectile p = (EnemyProjectile) hb2projectiles.get(i);
            if (p.isVisible() == true) {
                p.update();
            } else {
                hb2projectiles.remove(i);
            }
        }

        updateTiles();
        hb.update();
        hb2.update();
        bg1.update();
        bg2.update();
        animate();
        mis1.update();
        mis2.update();
        shield.update();

        //if (shield.isVisible() == true) {
           /* sanim = new Animation();
            sanim.addFrame(shield1, 500);
            sanim.addFrame(shield2, 500);
            sanim.addFrame(shield3, 1000);
            sanim.addFrame(shield2, 500);
            sanim.addFrame(shield1, 500);*/
        //shield.setVisible(false);
        //shield = null;
        //sanim = null;

        //}

        if (robot.getCenterY() > 500) {
            state = GameState.GameOver;
        }


    }

    private void enemyattack() {
        if (randno < 25) {
            hb.setReadyToFire(true);
            hb.attack();
        } else if (randno < 50) {
            hb2.setReadyToFire(true);
            hb2.attack();
        } else {
            hb.setReadyToFire(false);
            hb2.setReadyToFire(false);
        }

    }

    private boolean inBounds(TouchEvent event, int x, int y, int width,
                             int height) {
        return event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1;
    }

    private void updatePaused(List touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent) touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 0, 0, 800, 240)) {

                    if (!inBounds(event, 0, 0, 35, 35)) {
                        resume();
                    }
                }

                if (inBounds(event, 0, 240, 800, 240)) {
                    nullify();
                    goToMenu();
                }
            }
        }
    }

    private void updateGameOver(List touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent) touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {
                if (inBounds(event, 0, 0, 800, 480)) {
                    nullify();
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }

    }

    private void updateTiles() {

        for (int i = 0; i < tilearray.size(); i++) {
            Tile t = (Tile) tilearray.get(i);
            t.update();
        }

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
        g.drawImage(Assets.background, bg2.getBgX(), bg2.getBgY());
        paintTiles(g);

        ArrayList projectiles = robot.getProjectiles();
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile p = (Projectile) projectiles.get(i);
            g.drawPath(p.getBulletpath(), p.getBulletpaint());
        }

        ArrayList hbprojectiles = hb.getProjectiles();
        for (int i = 0; i < hbprojectiles.size(); i++) {
            EnemyProjectile p = (EnemyProjectile) hbprojectiles.get(i);
            g.drawCircle(p.getX() - 25, p.getY() + 30, 5, Color.BLACK);
            //g.drawRect(p.getR().left,p.getR().top,p.getR().right-p.getR().left,p.getR().bottom-p.getR().top,Color.RED);

        }
        ArrayList hb2projectiles = hb2.getProjectiles();
        for (int i = 0; i < hb2projectiles.size(); i++) {
            EnemyProjectile p = (EnemyProjectile) hb2projectiles.get(i);
            g.drawCircle(p.getX() - 25, p.getY() + 30, 5, Color.BLACK);
            // g.drawRect(p.getR().left,p.getR().top,10,10,Color.GREEN);

        }
        // First draw the game elements.

        g.drawImage(currentSprite, robot.getCenterX() - 61,
                robot.getCenterY() - 63);
        g.drawImage(hanim.getImage(), hb.getCenterX() - 48,
                hb.getCenterY() - 48);
        g.drawImage(hanim.getImage(), hb2.getCenterX() - 48,
                hb2.getCenterY() - 48);

        if (mis1.isVisible()) {
            g.drawImage(Assets.missile, mis1.getX(), mis1.getY());
        }
        if (mis2.isVisible()) {
            g.drawImage(Assets.missile, mis2.getX(), mis2.getY());
            // g.drawRect(mis1.getX(), mis1.getY(), 80, 120, Color.RED);
        }

        if (shield.isVisible()) {
            g.drawImage(sanim.getImage(), shield.getX(),
                    shield.getY());
        }

        // Example:
        // g.drawImage(Assets.background, 0, 0);
        // g.drawImage(Assets.character, characterX, characterY);

        // Secondly, draw the UI above the game elements.
        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();

    }

    private void paintTiles(Graphics g) {
        for (int i = 0; i < tilearray.size(); i++) {
            Tile t = (Tile) tilearray.get(i);
            if (t.type != 0) {
                g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY());
            }
        }
    }

    public void animate() {
        anim.update(10);
        hanim.update(50);
        if(shield.isVisible()) {
            sanim.update(100);
        }
    }

    private void nullify() {

        // Set all variables to null. You will be recreating them in the
        // constructor.
        paint = null;
        bg1 = null;
        bg2 = null;
        robot = null;
        hb = null;
        hb2 = null;
        currentSprite = null;
        character = null;
        character2 = null;
        character3 = null;
        heliboy = null;
        heliboy2 = null;
        heliboy3 = null;
        heliboy4 = null;
        heliboy5 = null;
        anim = null;
        hanim = null;
        rand = null;
        mis1 = null;
        mis2 = null;
        shield = null;
        sanim = null;


        // Call garbage collector to clean up memory.
        System.gc();

    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();

        g.drawARGB(155, 0, 0, 0);
        g.drawString("Tap to Start.", 400, 240, paint);

    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.button, 0, 285, 0, 0, 65, 65);
        g.drawImage(Assets.button, 0, 350, 0, 65, 65, 65);
        g.drawImage(Assets.button, 0, 415, 0, 130, 65, 65);
        g.drawImage(Assets.button, 0, 0, 0, 195, 35, 35);

        //g.drawARGB(155,0,0,0);
        g.drawString("Lives Left: " + livesLeft, 700, 30, paint);
    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(155, 0, 0, 0);
        g.drawString("Resume", 400, 165, paint2);
        g.drawString("Menu", 400, 360, paint2);

    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);
        g.drawString("GAME OVER.", 400, 240, paint2);
        g.drawString("Tap to return.", 400, 290, paint);

    }

    @Override
    public void pause() {
        if (state == GameState.Running)
            state = GameState.Paused;

    }

    @Override
    public void resume() {
        if (state == GameState.Paused)
            state = GameState.Running;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        pause();
    }

    private void goToMenu() {
        // TODO Auto-generated method stub
        game.setScreen(new MainMenuScreen(game));

    }

    public static Background getBg1() {
        // TODO Auto-generated method stub
        return bg1;
    }

    public static Background getBg2() {
        // TODO Auto-generated method stub
        return bg2;
    }

    public static Robot getRobot() {
        // TODO Auto-generated method stub
        return robot;
    }

    public Image getCurrentSprite() {
        return currentSprite;
    }

    public static void reduceLivesLeft(int damage) {
        livesLeft -= damage;
    }

    public int getRandno() {
        return randno;
    }

    public static Missiles getMis1() {
        return mis1;
    }

    public static Missiles getMis2() {
        return mis2;
    }
}