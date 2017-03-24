package com.nick.bb.fitness.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.jiongbull.jlog.JLog;

import butterknife.ButterKnife;

/**
 * Created by sharpay on 17-3-24.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preView();
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        initView();
        initData();
        handData();
    }

    protected abstract int getLayoutResId();

    protected void preView(){}

    protected void initView(){}

    protected void initData(){}

    protected void handData(){}


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //在Action Bar的最左边，就是Home icon和标题的区域
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        JLog.i(TAG, "onRestart");

    }

    @Override
    protected void onStart() {
        super.onStart();
        JLog.i(TAG, "onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        JLog.i(TAG, "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        JLog.i(TAG, "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        JLog.i(TAG, "onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JLog.i(TAG, "onDestroy");

    }
}
