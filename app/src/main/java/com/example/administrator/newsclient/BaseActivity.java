package com.example.administrator.newsclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/6/26.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(LayoutR());
        initView();
        initListener();
        initData();
        LayoutR();
    }

    protected abstract int LayoutR();

    protected abstract void initListener();

    protected abstract void initView();

    protected   abstract void initData();

public Toast mtoast;

    public void showToast(String msg){
        if(mtoast == null){
            mtoast = Toast.makeText(this,"",Toast.LENGTH_SHORT);
        }
        mtoast.setText(msg);
        mtoast.show();
    }

}
