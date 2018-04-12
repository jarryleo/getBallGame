package cn.leo.clib;

/**
 * Created by Leo on 2018/4/12.
 */

public class GameBrainLib {
    static {
        System.loadLibrary("gameBrain");
    }

    private native int[] think(int ball[]);

    public void computer(int ball[]){
        int[] b = think(ball);
        ball[0] = b[0];
        ball[1] = b[1];
        ball[2] = b[2];
    }

}
