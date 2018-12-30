package ambyz.robotgame.robotgame;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Rene Ambrose Tang on 19/2/2017.
 */

public class MyTimerTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("Timer task started at:" + new Date());
        completeTask();
        System.out.println("Timer task finished at:" + new Date());
    }

    private void completeTask() {
        try {
            //assuming it takes 20 secs to complete the task
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}