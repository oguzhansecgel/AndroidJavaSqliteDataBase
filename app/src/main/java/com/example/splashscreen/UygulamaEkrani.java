package com.example.splashscreen;

import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class UygulamaEkrani extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uygulama_ekrani);
        Intent intent = getIntent();
        String gelenIl = intent.getStringExtra("il");
        String gelenIlce = intent.getStringExtra("ilce");
        webView = findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl("https://www.eczaneler.gen.tr/nobetci-"+gelenIl+"-"+gelenIlce);
    }
}