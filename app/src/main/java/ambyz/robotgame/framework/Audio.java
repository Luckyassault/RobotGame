package ambyz.robotgame.framework;

/**
 * Created by Rene Ambrose Tang on 16/2/2017.
 */

public interface Audio {
    Music createMusic(String file);

    Sound createSound(String file);
}

