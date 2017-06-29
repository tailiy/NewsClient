package com.example.administrator.newsclient;

import android.view.View;
import android.widget.ListView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */

public class NewsFragment extends BaseFragment {
    private String channelId;
    private ListView listView;

    public void setChannelId(String channelId) {
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
                System.out.println("----服务器返回的json数据：" + json);

                json = json.replace(channelId, "result");
                Gson gson = new Gson();
                NewsEntity newsDatas = gson.fromJson(json, NewsEntity.class);
                System.out.println("---解析json:" + newsDatas.getResult().size());

                showDatas(newsDatas);


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


        listView = (ListView) mRoot.findViewById(R.id.listview);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_news;
    }

    private void showDatas(NewsEntity newsDatas) {
        if (newsDatas == null || newsDatas.getResult() == null || newsDatas.getResult().size() == 0) {
            System.out.println("---没有获取到服务器的新闻数据");
            return;
        }
        List<NewsEntity.ResultBean.AdsBean> ads = newsDatas.getResult().get(0).getAds();
        if (ads != null && ads.size() > 0) {
            View headerView = View.inflate(mActivity, R.layout.list_header, null);
            SliderLayout sliderLayout = (SliderLayout) headerView.findViewById(R.id.slider_layout);

            for (int i = 0; i < ads.size(); i++) {
                NewsEntity.ResultBean.AdsBean adbean = ads.get(i);
                final TextSliderView sliderView = new TextSliderView(mActivity);
                sliderView.description(adbean.getTitle()).image(adbean.getImgsrc());
                sliderLayout.addSlider(sliderView);
                sliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {
                        showToast(slider.getDescription());
                    }
                });
            }
            listView.addHeaderView(headerView);
        }


        NewsAdapter newsAdapter = new NewsAdapter(mActivity, newsDatas.getResult());
        listView.setAdapter(newsAdapter);
    }
}
