package edu.feicui.newsday.Activity;

import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.List;

import edu.feicui.newsday.Base.BaseActivity;
import edu.feicui.newsday.R;
import edu.feicui.newsday.entity.HotNewsInfo;


/**
 * Created by 太上老君 on 2016/7/10.
 */
public class WelcomeActivity extends BaseActivity {
    Animation animation;
    ImageView imageView;
    List<HotNewsInfo> hotlist;
    @Override
    public void setLayout() {
        setContentView(R.layout.welcome);
    }

    @Override
    public void getview() {
        animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        imageView = (ImageView) findViewById(R.id.welcome);
    }

    @Override
    public void setview() {
        animation.setAnimationListener(animationListener);
        imageView.startAnimation(animation);
    }

    Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Intent intent = new Intent(WelcomeActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    @Override
    public void onClick(View v) {

    }
//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what){
//                case 0:
//                    Toast.makeText(WelcomeActivity.this,"路过",Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//    };
}
