package com.example.administrator.newsclient;

import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
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
    private ListView listView;
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

                json = json.replace(channelId,"result");
                Gson gson = new Gson();
                NewsEntity newsDatas = gson.fromJson(json,NewsEntity.class);
                System.out.println("---解析json:"+newsDatas.getResult().size());

                //showDatas(newDatas);
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
        //listView.findViewById(R.id.);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_news;
    }

    private void showDatas(NewsEntity newsDatas){
        if(newsDatas == null || newsDatas.getResult()==null || newsDatas.getResult().size() ==0){
            System.out.println("---没有获取到服务器的新闻数据");
            return;
        }
        NewsAdapter newsAdapter = new NewsAdapter(mActivity,newsDatas.getResult());
        //listView.setAdapter(newAdapter);
    }
}
