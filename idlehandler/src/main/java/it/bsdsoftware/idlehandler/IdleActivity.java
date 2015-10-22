package it.bsdsoftware.idlehandler;

import android.content.Context;
import android.os.Build;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public abstract class IdleActivity extends AppCompatActivity {

    private IdleTimer idleTimer;
    private boolean idleInScreenOff = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        idleTimer = IdleTimer.getInstance(getTimeoutIdle());
        idleTimer.setCallback(new IdleTimer.Callback() {
            @Override
            public void onIdle() {
                idleInScreenOff = isScreenOff();
                if(!idleInScreenOff)
                    doOnIdle();
            }
        });
        if(idleInScreenOff){
            idleTimer.onFinish();
        }else {
            idleTimer.startTimer();
        }
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        idleTimer.reset();
    }

    public void stopTimer(){
        idleTimer.stop();
    }

    private boolean isScreenOff() {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn;
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP)
            isScreenOn = pm.isInteractive();
        else
            isScreenOn = pm.isScreenOn();
        return !isScreenOn;
    }

    public boolean isAppInIdle(){
        return idleTimer.appIsInIdle();
    }

    /**
     * specify what to do when the app is in the idle state
     */
    public abstract void doOnIdle();

    /**
     * It specifies how long the app has to go into idle state
     *
     * @return timeout in millseconds when the app has to go in idle
     */
    public abstract long getTimeoutIdle();

}
