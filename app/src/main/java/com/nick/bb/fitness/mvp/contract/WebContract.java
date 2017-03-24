package com.nick.bb.fitness.mvp.contract;

import android.webkit.WebView;

import com.nick.bb.fitness.api.entity.BeautyBean;
import com.nick.bb.fitness.api.entity.GankBean;
import com.nick.bb.fitness.mvp.presenter.base.BasePresenter;
import com.nick.bb.fitness.mvp.view.BaseView;

import java.util.List;

/**
 * Created by sharpay on 17-3-22.
 */

public interface WebContract {
    interface View extends BaseView{
        void showProgressBar(int progress);
        void setWebTitle(String title);
        void showErrorView();
    }

    interface Presenter extends BasePresenter<View>{
        public void setWebViewSettings(WebView webView, String url);
        public void refresh(WebView webView);
        public void copyUrl(String text);
        public void openInBrowser(String url);
        public void moreOperation(GankBean gankBean);
    }
}
