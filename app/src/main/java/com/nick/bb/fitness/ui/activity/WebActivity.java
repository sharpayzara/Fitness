package com.nick.bb.fitness.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.nick.bb.fitness.AndroidApplication;
import com.nick.bb.fitness.R;
import com.nick.bb.fitness.api.entity.GankBean;
import com.nick.bb.fitness.injector.components.ApplicationComponent;
import com.nick.bb.fitness.injector.components.DaggerWebComponent;
import com.nick.bb.fitness.injector.components.WebComponent;
import com.nick.bb.fitness.injector.modules.ActivityModule;
import com.nick.bb.fitness.mvp.contract.WebContract;
import com.nick.bb.fitness.util.TipUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WebActivity extends AppCompatActivity implements WebContract.View {
    static final String GANK_TAG = "gank";
    @BindView(R.id.web_content)
    LinearLayout webContent;
    private GankBean gankBean;
    @BindView(R.id.progressbar)
    NumberProgressBar progressbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.web_view)
    WebView webView;
    @Inject
    WebContract.Presenter presenter;


    public static void loadWebViewActivity(Context from, GankBean gankBean) {
        Intent intent = new Intent(from, WebActivity.class);
        intent.putExtra(GANK_TAG, gankBean);
        from.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        injectDependences();
        presenter.attachView(this);
        initView();
    }

    private void injectDependences() {
        ApplicationComponent applicationComponent = ((AndroidApplication) this.getApplication()).getApplicationComponent();
        WebComponent webComponent = DaggerWebComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(new ActivityModule(this))
                .build();
        webComponent.inject(this);

    }

    private void initView() {
        gankBean = (GankBean) getIntent().getSerializableExtra(GANK_TAG);
        setTitle(gankBean.getDesc());
        presenter.setWebViewSettings(webView, gankBean.getUrl());
    }

    @Override
    public void showProgressBar(int progress) {
        if (progressbar == null) return;
        progressbar.setProgress(progress);
        if (progress == 100) {
            progressbar.setVisibility(View.GONE);
        } else {
            progressbar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setWebTitle(String title) {
        setTitle(title);
    }

    @Override
    public void showErrorView() {
        TipUtil.showSnackTip(webView, "打开失败");
    }

    @Override
    protected void onResume() {
        if (webView != null) webView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (webView != null) webView.onPause();
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                presenter.refresh(webView);
                break;
            case R.id.action_copy_url:
                presenter.copyUrl(webView.getUrl());
                break;
            case R.id.action_open_in_browser:
                presenter.openInBrowser(webView.getUrl());
                break;
            case R.id.action_share_gank:
                presenter.moreOperation(gankBean);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return true;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webContent.removeView(webView);
            webView.removeAllViews();
            webView.destroy();
            webView = null;
        }
        presenter.unsubscribe();
    }
}