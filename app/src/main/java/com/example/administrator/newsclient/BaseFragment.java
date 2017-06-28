package com.example.administrator.newsclient;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/6/27.
 */

public abstract class BaseFragment extends Fragment{
    protected View mRoot;
    protected Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRoot == null){
            mRoot = LayoutInflater.from(mActivity).inflate(getLayoutRes(),container,false);
            initView();
            inintListener();
            intitData();
        }
        return mRoot;
    }

    protected abstract void intitData();

    protected abstract void inintListener();

    protected abstract void initView();

    protected abstract int getLayoutRes();

    private Toast mtoast;

    public void showToast(String msg){
        if(mtoast == null){
            mtoast = Toast.makeText(mActivity,"",Toast.LENGTH_SHORT);
        }
        mtoast.setText(msg);
        mtoast.show();
    }
}
