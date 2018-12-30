package ambyz.robotgame.framework;

/**
 * Created by Rene Ambrose Tang on 16/2/2017.
 */

public interface Game {

    Audio getAudio();

    Input getInput();

    FileIO getFileIO();

    Graphics getGraphics();

    void setScreen(Screen screen);

    Screen getCurrentScreen();

    Screen getInitScreen();
}
