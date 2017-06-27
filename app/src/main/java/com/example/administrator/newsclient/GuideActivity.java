package com.example.administrator.newsclient;

import android.animation.Animator;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

/**
 * Created by Administrator on 2017/6/26.
 */

public class GuideActivity extends  BaseActivity{
    private ImageView iv01;
    private Button btnGO;

    private int count = 0;
    private  int[] imagesArray = new int[]{
            R.drawable.ad_new_version1_img1,
            R.drawable.ad_new_version1_img2,
            R.drawable.ad_new_version1_img3,
            R.drawable.ad_new_version1_img4,
            R.drawable.ad_new_version1_img5,
            R.drawable.ad_new_version1_img6,
            R.drawable.ad_new_version1_img7,
    };

    private MediaPlayer mMediaPlayer;
    @Override
    protected int LayoutR() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    private Handler mHandle = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 0:
                    startAnimation();
                    break;
            }
        }
    };

    private void startAnimation() {
        count ++;
        count = count %imagesArray.length;//取余数
        iv01.setBackgroundResource(imagesArray[count]);

        iv01.setScaleX(1.0f);//控件恢复原来的大小
        iv01.setScaleY(1.0f);

        iv01.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(3500)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        mHandle.sendEmptyMessageDelayed(0,1000);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                }).start();
    }
    public  void playBackgroundMusic(){
        try {
            AssetFileDescriptor fileDescriptor = getAssets().openFd("new_version.mp3");

            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),0L,fileDescriptor.getLength());
            mMediaPlayer.setLooping(true);
            mMediaPlayer.setVolume(1.0f,1.0f);
            mMediaPlayer.prepare();
            mMediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onStart(){
        super.onStart();
        playBackgroundMusic();
    }
    protected void onStep(){
        super.onStop();
        if(mMediaPlayer!=null){
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer=null;
        }
    }
}
