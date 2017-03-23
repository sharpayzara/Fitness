package com.nick.bb.fitness.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nick.bb.fitness.R;
import com.nick.bb.fitness.api.entity.GankBean;
import com.nick.bb.fitness.api.entity.decor.GankList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sharpay on 2016/5/26.
 */
public class GankListRecyclerAdapter extends RecyclerView.Adapter<GankListRecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<GankBean> list;

    public GankListRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    public void setGankList(List<GankBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gank, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.descTv.setText(list.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        GankList bean;
        @BindView(R.id.desc_tv)
        TextView descTv;

        @OnClick({R.id.desc_tv})
        public void onClick(View view) {
        }

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
