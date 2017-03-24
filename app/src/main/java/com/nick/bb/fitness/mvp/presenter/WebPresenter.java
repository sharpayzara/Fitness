package com.nick.bb.fitness.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.nick.bb.fitness.api.entity.GankBean;
import com.nick.bb.fitness.mvp.contract.GankListContract;
import com.nick.bb.fitness.mvp.contract.WebContract;
import com.nick.bb.fitness.util.CommonUtil;


/**
 * Created by sharpay on 17-3-22.
 */

public class WebPresenter implements WebContract.Presenter {
    private WebContract.View mView;
    private Context mContext;

    public WebPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void setWebViewSettings(WebView webView, String url) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        webView.setWebChromeClient(new ChromeClient());
        webView.setWebViewClient(new GankClient());
        webView.loadUrl(url);
    }
    @Override
    public void attachView(WebContract.View view) {
        mView = view;
    }


    public void refresh(WebView webView) {
        webView.reload();
    }

    public void copyUrl(String text) {
        CommonUtil.copyToClipBoard(mContext, text, "复制成功");
    }

    public void openInBrowser(String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(url);
        intent.setData(uri);
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(intent);
        } else {
            mView.showErrorView();
        }
    }

    public void moreOperation(GankBean gankBean) {
        if (gankBean != null){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, gankBean.getDesc()+ gankBean.getUrl()+"点击下载");
            intent.setType("text/plain");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(Intent.createChooser(intent, "分享资源到"));
        }
    }
    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }


    private class ChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mView.showProgressBar(newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            mView.setWebTitle(title);
        }
    }

    private class GankClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null) view.loadUrl(url);
            return true;
        }
    }
}
