package edu.feicui.newsday.Server;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import edu.feicui.newsday.R;
import edu.feicui.newsday.Utils.SaveMessage;

/**
 * Created by 太上老君 on 2016/7/22.
 */
public class SetServer extends Service {
    MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.wjdbal);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("mssdg", "qqwweerr");
        loadMessage();
        if(intent!=null) {
            Bundle bundle = intent.getExtras();
            if(bundle!=null){
                int sta = bundle.getInt("state");
                switch (sta) {
                    case 1:
                        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                            mediaPlayer.start();
                        }
                        break;
                    case 2:
                        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                            mediaPlayer.pause();
                        }
                        break;
                    case 3:
                        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                        }
                        break;
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }
    public void loadMessage(){
        Log.i("mssdg","走了这里");
        SaveMessage save = new SaveMessage(SetServer.this);
        String boo = save.tzload(); //将提取到的共享参数赋值给boo
        if(boo.equals("true")&&(mediaPlayer!=null&&!mediaPlayer.isPlaying())){
            mediaPlayer.start();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
