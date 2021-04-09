package me.pgb.a2021_04_02_radioservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;

import me.pgb.a2021_04_02_radioservice.controllers.MediaPlayerHandler;

public class RadioService extends Service {

    private MediaPlayerHandler mediaPlayerHandler;
    private String URL = "http://stream.whus.org:8000/whusfm";
//    private String URL = RadioFragment.getURL();
    private final String TAG = "_SERVICE";
    private final IBinder binder = new LocalBinder();
    private int counter = 0;
    private MyHandler myHandler;
    private HandlerThread handlerThread;

    private boolean runningInBackground = false;
    private boolean firstMediaPlayerRun = true;

    public class LocalBinder extends Binder {

        public RadioService getService() {
            Log.i(TAG,"LocalBinder extends Binder");
            return RadioService.this;
        }
    }

    public class MyHandler extends Handler {
        public MyHandler(Looper looper) {
            super(looper);
            mediaPlayerHandler = new MediaPlayerHandler();
            mediaPlayerHandler.setUpMediaPlayer(URL);
            Log.i(TAG,"MyHandler extends Handler");
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Object someObject = msg.obj;

            Log.i(TAG,"msg from Handler: " + someObject.toString());
            String myMessage = someObject.toString();
            if (myMessage == "OFF") {
                mediaPlayerHandler.pauseMediaPlayer();
                runningInBackground =false;
            } else {
                if (!runningInBackground) {
                    runningInBackground = true;
                    if(firstMediaPlayerRun){
                        mediaPlayerHandler.asyncLaunchMediaPlayer();
                        firstMediaPlayerRun = false;
                    }else{
                        mediaPlayerHandler.startPlayingAgain();
                    }

                }
            }

            stopSelf(msg.arg1);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate()");

        handlerThread = new HandlerThread("My Thread", Process.THREAD_PRIORITY_FOREGROUND);

        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        myHandler = new MyHandler(looper);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
        handlerThread.quitSafely();
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind");

        if(intent != null) { // May not have an Intent is the service was killed and restarted (See STICKY_SERVICE).
            Log.i(TAG,"do stuff in onBind");
        }
        return binder;
    }

    public void radioOn(){
        Message msg = myHandler.obtainMessage();
        msg.arg1 = 1;
        msg.obj = "ON";
        myHandler.sendMessage(msg);
    }

    public void radioOff(){
        Message msg = myHandler.obtainMessage();
        msg.arg1 = 0;
        msg.obj = "OFF";
        myHandler.sendMessage(msg);
    }
    
    public void setURL(String link){
        URL = link;
    }


}