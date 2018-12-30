package ambyz.robotgame.robotgame;

import ambyz.robotgame.framework.Image;
import ambyz.robotgame.framework.Music;
import ambyz.robotgame.framework.Sound;

/**
 * Created by Rene Ambrose Tang on 16/2/2017.
 */


public class Assets {

    public static Image menu, splash, background, character, character2, character3, heliboy, heliboy2, heliboy3, heliboy4, heliboy5, missile;
    public static Image shield1, shield2, shield3;
    public static Image tiledirt, tilegrassTop, tilegrassBot, tilegrassLeft, tilegrassRight, characterJump, characterDown;
    public static Image button;
    public static Sound click;
    public static Music theme;

    public static void load(SampleGame sampleGame) {
        // TODO Auto-generated method stub
        theme = sampleGame.getAudio().createMusic("menutheme.mp3");
        theme.setLooping(true);
        theme.setVolume(0.85f);
        theme.play();
    }

}