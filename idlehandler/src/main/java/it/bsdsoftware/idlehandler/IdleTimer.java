package it.bsdsoftware.idlehandler;

import android.os.CountDownTimer;
import android.util.Log;

/**
 * Created by Simone on 22/10/15.
 */
public class IdleTimer extends CountDownTimer {

    private static IdleTimer idleTimer;
    private Callback callback;
    private boolean isRunning = false;
    private boolean isAppInIdle = false;

    public static IdleTimer getInstance(long idleTimeout){
        if(idleTimer==null)
            idleTimer = new IdleTimer(idleTimeout, idleTimeout);
        return idleTimer;
    }

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    private IdleTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {
        isRunning = false;
        isAppInIdle = true;
        Log.d("SESSION", "APP_IN_IDLE");
        if(callback!=null) {
            callback.onIdle();
        }
    }

    public void reset(){
        isRunning = true;
        isAppInIdle = false;
        Log.d("SESSION", "TIMER_RESET");
        this.cancel();
        this.start();
    }


    public void startTimer(){
        if(!isRunning){
            reset();
        }
    }

    public void stop(){
        isRunning = false;
        isAppInIdle = false;
        Log.d("SESSION", "TIMER_STOP");
        this.cancel();
    }

    public boolean appIsInIdle(){
        return isAppInIdle;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback{
        void onIdle();
    }
}