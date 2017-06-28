package com.example.administrator.newsclient;

import android.content.Intent;
import android.os.SystemClock;

/**
 * Created by Administrator on 2017/6/26.
 */

public class startActivity extends BaseActivity {
    @Override
    protected int LayoutR() {
        return R.layout.activity_start;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                SystemClock.sleep(1500);
                       boolean firstRun = SharePrefUril.getBoolean(getApplicationContext(),"firstRun",true);
                if(firstRun){
                    SharePrefUril.saveBoolean(startActivity.this,"firstRun",false);
                    enterGuideActivity();
                }else{
                    enterMainActivity();
                }
            }
        }.start();
    }

    private void enterGuideActivity() {
        Intent intent = new Intent(this,GuideActivity.class);
        startActivity(intent);
        finish();
    }

    private void enterMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}
