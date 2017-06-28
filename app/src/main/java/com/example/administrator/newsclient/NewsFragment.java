package com.example.administrator.newsclient;

import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by Administrator on 2017/6/28.
 */

public class NewsFragment extends BaseFragment{
    private String channelId;

    public void setChannelId(String channelId){
        this.channelId = channelId;
    }
    @Override
    protected void intitData() {
        getDataFromServer();
    }

    private void getDataFromServer() {
        String url = URLManager.getUrl(channelId);

        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String json = responseInfo.result;
                System.out.println("----服务器返回的json数据："+json);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                error.printStackTrace();
            }
        });
    }

    @Override
    protected void inintListener() {

    }

    @Override
    protected void initView() {
        TextView textView = (TextView) mRoot.findViewById(R.id.tv_01);
        textView.setText(channelId);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_news;
    }
}
