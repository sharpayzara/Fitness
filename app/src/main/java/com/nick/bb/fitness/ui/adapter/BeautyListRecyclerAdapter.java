package com.nick.bb.fitness.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nick.bb.fitness.R;
import com.nick.bb.fitness.api.entity.BeautyBean;
import com.nick.bb.fitness.util.CommonUtil;
import com.nick.bb.fitness.util.ImageLoaderProxy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sharpay on 2016/5/26.
 */
public class BeautyListRecyclerAdapter extends RecyclerView.Adapter<BeautyListRecyclerAdapter.MyViewHolder> {

    private Context mContext;
    List<BeautyBean> list;

    public BeautyListRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
        this.list = new ArrayList<>();
    }

    public void setBeautyList(List<BeautyBean> list) {
        if(list == null){
            this.list.clear();
        }else{
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beauty, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bean = list.get(position);
        holder.specialTv.setText(" @"+ list.get(position).getWho() +" from:"+ list.get(position).getSource()+" time:"+list.get(position).getDesc());
        ImageLoaderProxy.getInstance().loadImageCF(mContext,list.get(position).getUrl(),holder.imgIv, ImageLoaderProxy.FormatType.CenterCrop);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        BeautyBean bean;
        @BindView(R.id.img_iv)
        ImageView imgIv;
        @BindView(R.id.special_tv)
        TextView specialTv;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ViewGroup.LayoutParams params = imgIv.getLayoutParams();
            params.height = CommonUtil.screenWidth(mContext) / 2;

        }

        @OnClick(R.id.img_iv)
        void onClick() {
            
        }
    }
}
