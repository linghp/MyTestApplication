package com.example.huanpengling.myapplication.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.huanpengling.myapplication.R;

public class WebViewSample extends AppCompatActivity {

  @BindView(R.id.html_view) WebView webView;
  @BindView(R.id.next_button) TextView nextButton;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_web_view_sample);
    ButterKnife.bind(this);
    webView.setWebViewClient(new WebViewClient() {
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
      }
    });
    webView.loadUrl("http://www.jb51.net/article/70420.htm");
  }
}
