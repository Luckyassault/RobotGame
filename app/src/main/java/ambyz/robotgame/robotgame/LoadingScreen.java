package ambyz.robotgame.robotgame;

import ambyz.robotgame.framework.Game;
import ambyz.robotgame.framework.Graphics;
import ambyz.robotgame.framework.Graphics.ImageFormat;
import ambyz.robotgame.framework.Screen;

/**
 * Created by Rene Ambrose Tang on 16/2/2017.
 */

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {

        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menu = g.newImage("drawable/menu.png", ImageFormat.RGB565);
        Assets.background = g.newImage("drawable/background.png", ImageFormat.RGB565);
        Assets.character = g.newImage("drawable/character.png", ImageFormat.ARGB4444);
        Assets.character2 = g.newImage("drawable/character2.png", ImageFormat.ARGB4444);
        Assets.character3  = g.newImage("drawable/character3.png", ImageFormat.ARGB4444);
        Assets.characterJump = g.newImage("drawable/jumped.png", ImageFormat.ARGB4444);
        Assets.characterDown = g.newImage("drawable/down.png", ImageFormat.ARGB4444);


        Assets.heliboy = g.newImage("drawable/heliboy.png", ImageFormat.ARGB4444);
        Assets.heliboy2 = g.newImage("drawable/heliboy2.png", ImageFormat.ARGB4444);
        Assets.heliboy3  = g.newImage("drawable/heliboy3.png", ImageFormat.ARGB4444);
        Assets.heliboy4  = g.newImage("drawable/heliboy4.png", ImageFormat.ARGB4444);
        Assets.heliboy5  = g.newImage("drawable/heliboy5.png", ImageFormat.ARGB4444);

        Assets.missile = g.newImage("drawable/missile.png",ImageFormat.ARGB4444);

        Assets.tiledirt = g.newImage("drawable/tiledirt.png", ImageFormat.RGB565);
        Assets.tilegrassTop = g.newImage("drawable/tilegrasstop.png", ImageFormat.RGB565);
        Assets.tilegrassBot = g.newImage("drawable/tilegrassbot.png", ImageFormat.RGB565);
        Assets.tilegrassLeft = g.newImage("drawable/tilegrassleft.png", ImageFormat.RGB565);
        Assets.tilegrassRight = g.newImage("drawable/tilegrassright.png", ImageFormat.RGB565);

        Assets.button = g.newImage("drawable/button.jpg", ImageFormat.RGB565);

        //This is how you would load a sound if you had one.
        //Assets.click = game.getAudio().createSound("explode.ogg");


        game.setScreen(new MainMenuScreen(game));

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.splash, 0, 0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {

    }
}
