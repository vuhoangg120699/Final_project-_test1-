package com.dinhvu.quanlysuckhoe.View;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dinhvu.quanlysuckhoe.R;

public class NewsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_baocao);
        WebView wv=findViewById(R.id.wv);
        WebSettings webSettings=wv.getSettings();
         webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
         webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
         webSettings.setDomStorageEnabled(true);
         webSettings.setJavaScriptEnabled(true);
        wv.loadUrl("https://thanhnien.vn/");
        wv.setWebViewClient(new WebViewClient());
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tin Tức");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
