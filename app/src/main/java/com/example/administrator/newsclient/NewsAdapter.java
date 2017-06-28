package com.example.administrator.newsclient;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */

public class NewsAdapter extends BaseAdapter{
    private Context context;

    private List<NewsEntity.ResultBean> listDatas;

    public NewsAdapter(Context context,List<NewsEntity.ResultBean> listDatas){
        this.context =context;
        this.listDatas = listDatas;
    }
    @Override
    public int getCount() {
        return (listDatas==null)?0 :listDatas.size();
    }

    @Override
    public NewsEntity.ResultBean getItem(int position) {
        return listDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsEntity.ResultBean info = getItem(position);

        if(convertView==null){
            convertView = View.inflate(context,R.layout.item_news_1,null);
            if(convertView == null){
                convertView = View.inflate(context,R.layout.item_news_1,null);
            }
            ImageView ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            TextView tvSource = (TextView) convertView.findViewById(R.id.tv_source);
            TextView tvComment = (TextView) convertView.findViewById(R.id.tv_comment);

            tvTitle.setText(info.getTitle());
            tvSource.setText(info.getSource());
            tvComment.setText(info.getReplyCount()+"跟帖");
            Picasso.with(context).load(info.getImgsrc()).into(ivIcon);
        }
        return convertView;
    }
}
