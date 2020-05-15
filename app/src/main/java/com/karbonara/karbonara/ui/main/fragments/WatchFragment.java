package com.karbonara.karbonara.ui.main.fragments;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.karbonara.karbonara.R;

public class WatchFragment extends Fragment {
    private static String videoSource;
    WebView videoView;

    public WatchFragment() {
    }

    public WatchFragment(String url) {
        videoSource = url;
    }

    @SuppressLint("SourceLockedOrientationActivity") //при повороте телефона видео останавливается
    //и не запоминает своего положения
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_watch, container, false);
        RelativeLayout customViewContainer = v.findViewById(R.id.container);

        videoView = v.findViewById(R.id.videoView);
        videoView.setWebViewClient(new Browser_Home());
        videoView.setWebChromeClient(new ChromeClient());

        videoView.getSettings().setJavaScriptEnabled(true);
        videoView.getSettings().setAllowFileAccess(true);
        videoView.getSettings().setAppCacheEnabled(true);

        Log.e("LOADING", "VIDEO FROM " + videoSource);
        videoView.loadUrl(videoSource);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        return v;
    }
    private class ChromeClient extends WebChromeClient {
        private View mCustomView;
        private WebChromeClient.CustomViewCallback mCustomViewCallback;
        protected FrameLayout mFullscreenContainer;
        private int mOriginalOrientation;
        private int mOriginalSystemUiVisibility;

        ChromeClient() {
        }

        public Bitmap getDefaultVideoPoster() {
            if (mCustomView == null) {
                return null;
            }
            return BitmapFactory.decodeResource(getActivity().getApplicationContext().getResources(), 2130837573);
        }

        public void onHideCustomView() {
            ((FrameLayout) getActivity().getWindow().getDecorView()).removeView(this.mCustomView);
            this.mCustomView = null;
            getActivity().getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
            getActivity().setRequestedOrientation(this.mOriginalOrientation);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mCustomViewCallback = null;
        }

        public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback) {
            if (this.mCustomView != null) {
                onHideCustomView();
                return;
            }
            this.mCustomView = paramView;
            this.mOriginalSystemUiVisibility = getActivity().getWindow().getDecorView().getSystemUiVisibility();
            this.mOriginalOrientation = getActivity().getRequestedOrientation();
            this.mCustomViewCallback = paramCustomViewCallback;
            ((FrameLayout) getActivity().getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
            getActivity().getWindow().getDecorView().setSystemUiVisibility(3846 | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
    private class Browser_Home extends WebViewClient {
        Browser_Home() {
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }
}
