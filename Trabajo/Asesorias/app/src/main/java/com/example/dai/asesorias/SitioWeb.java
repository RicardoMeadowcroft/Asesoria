package com.example.dai.asesorias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SitioWeb extends AppCompatActivity {

    private Bundle bundle;
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitio_web);

        bundle = this.getIntent().getExtras();

        web = (WebView) findViewById(R.id.wv);

        //Entra al sitio web del ITAM

        web.loadUrl("https://www.itam.mx/");
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());
    }


    //Regresa a acción
    public void regresar(View view) {
        Intent intent = new Intent(SitioWeb.this, Accion.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
