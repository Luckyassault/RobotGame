package ambyz.robotgame.robotgame;

import ambyz.robotgame.framework.Game;
import ambyz.robotgame.framework.Graphics;
import ambyz.robotgame.framework.Screen;

/**
 * Created by Rene Ambrose Tang on 16/2/2017.
 */


public class SplashLoadingScreen extends Screen {
    public SplashLoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.splash= g.newImage("drawable/splash.jpg", Graphics.ImageFormat.RGB565);


        game.setScreen(new LoadingScreen(game));

    }

    @Override
    public void paint(float deltaTime) {

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