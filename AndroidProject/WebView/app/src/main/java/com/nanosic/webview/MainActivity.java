package com.nanosic.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private WebViewBase webViewBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webViewBase = new WebViewBase(MainActivity.this, "http://www.taobao.com/");
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(webViewBase);
    }

    @Override
    public void onBackPressed() {
        if (webViewBase.canGoBack()) {
            webViewBase.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
