package ambyz.robotgame.framework;

/**
 * Created by Rene Ambrose Tang on 16/2/2017.
 */


import ambyz.robotgame.framework.Graphics.ImageFormat;

public interface Image {
    int getWidth();
    int getHeight();
    ImageFormat getFormat();
    void dispose();
}
