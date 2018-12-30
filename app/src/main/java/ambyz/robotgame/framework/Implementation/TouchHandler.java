package ambyz.robotgame.framework.Implementation;

/**
 * Created by Rene Ambrose Tang on 16/2/2017.
 */

import android.view.View.OnTouchListener;

import java.util.List;

import ambyz.robotgame.framework.Input.TouchEvent;

public interface TouchHandler extends OnTouchListener {
    boolean isTouchDown(int pointer);

    int getTouchX(int pointer);

    int getTouchY(int pointer);

    List<TouchEvent> getTouchEvents();
}