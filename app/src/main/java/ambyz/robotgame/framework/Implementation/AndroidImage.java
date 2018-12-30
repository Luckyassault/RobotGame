package ambyz.robotgame.framework.Implementation;

/**
 * Created by Rene Ambrose Tang on 16/2/2017.
 */


import android.graphics.Bitmap;

import ambyz.robotgame.framework.Graphics.ImageFormat;
import ambyz.robotgame.framework.Image;

public class AndroidImage implements Image {
    Bitmap bitmap;
    ImageFormat format;

    public AndroidImage(Bitmap bitmap, ImageFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public ImageFormat getFormat() {
        return format;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }
}