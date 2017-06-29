package com.example.administrator.newsclient;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by Administrator on 2017/6/29.
 */

public class NewsDetailActivity extends BaseActivity{
    private WebView webView;
    private ProgressBar progressBar;
    @Override
    protected int LayoutR() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        progressBar = (ProgressBar) findViewById(R.id.pb_01);
        initWebView();
    }

    private void initWebView() {
        webView = (WebView) findViewById(R.id.web_view);

        webView.setWebViewClient(new WebViewClient());

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress == 100){
                    progressBar.setVisibility(View.GONE);
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                    System.out.println("--------percent:"+newProgress);
                }
            }
        });
    }

    @Override
    protected void initData() {
        NewsEntity.ResultBean newsBean = (NewsEntity.ResultBean) getIntent().getSerializableExtra("news");
        //webView.loadUrl(newsBean.get);
    }
}
