package com.example.splashscreen;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GirisEkrani extends AppCompatActivity {

    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_ekrani);
        EditText tv_KullaniciAdi = findViewById(R.id.et_KullaniciAdi);
        EditText tv_Sifre = findViewById(R.id.et_Sifre);
        Button btn_Giris = findViewById(R.id.btn_Giris);
        btn_Giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kullaniciAdi = tv_KullaniciAdi.getText().toString();
                String sifre = tv_Sifre.getText().toString();

                database = getApplicationContext().openOrCreateDatabase("Okul", MODE_PRIVATE, null);
                String sorgu = "SELECT * FROM okul WHERE kadi = ? AND sifre = ?";
                String[] kontrol = {kullaniciAdi, sifre};
                Cursor cursor = database.rawQuery(sorgu, kontrol);


                if (cursor.getCount() > 0) {

                    Intent uygulamaEkrani = new Intent(GirisEkrani.this,sehirilcesecim.class);
                    startActivity(uygulamaEkrani);
                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(GirisEkrani.this);
                    builder.setTitle("Hata");
                    builder.setMessage("Kullanıcı Adınız veya Şifreiniz Hatalı");
                    builder.setPositiveButton("Tamam", null);
                    builder.show();
                }

                // Cursor ve veritabanı bağlantısını kapatın
                cursor.close();
                database.close();
            }


        });



        Button btnKayit = findViewById(R.id.girisBtnKayıt);
        btnKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GirisEkrani.this,KayitEkrani.class);
                startActivity(intent);
            }
        });

        Button girisKayitSorgula = findViewById(R.id.girisKayitSorgula);
        girisKayitSorgula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GirisEkrani.this, KayitSorgula.class);
                startActivity(intent);
            }
        });
    }



}