package mykit_campus.noogle.com.languagetranslator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static mykit_campus.noogle.com.languagetranslator.Config.link;


public class TranslateClass extends Activity {
    private ProgressBar progressBar;
    private WebView webView;
    private LinearLayout errorLayout;
    private RelativeLayout webviewLayout,progressLayout;
    private Button tryConnectionAgainButton;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        init();
        webViewMethod();

        tryConnectionAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorLayout.setVisibility(View.GONE);
                webviewLayout.setVisibility(View.VISIBLE);
                webView.setWebViewClient(new WebViewClientDemo());
                webView.setWebChromeClient(new WebChromeClientDemo());
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl(link);
            }
        });
    }
    void init(){
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        errorLayout=(LinearLayout)findViewById(R.id.errorLayout);
        webviewLayout=(RelativeLayout)findViewById(R.id.webviewLayout);
        progressLayout=(RelativeLayout)findViewById(R.id.progressLayout);
        tryConnectionAgainButton = (Button) findViewById(R.id.internetTryAgainButton);
        progressBar.setMax(100);
    }

    void webViewMethod(){
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClientDemo());
        webView.setWebChromeClient(new WebChromeClientDemo());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(link);
    }
    void showErrorLayout(){
        webviewLayout.setVisibility(View.GONE);
        errorLayout.setVisibility(View.VISIBLE);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    private class WebViewClientDemo extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
            progressLayout.setVisibility(View.GONE);
            progressBar.setProgress(100);
        }
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
          //  mbErrorOccured = true;
            showErrorLayout();

            super.onReceivedError(view, errorCode, description, failingUrl);
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(5);
        }
    }
    private class WebChromeClientDemo extends WebChromeClient {
        public void onProgressChanged(WebView view, int progress) {
            progressBar.setProgress(progress);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        else {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
