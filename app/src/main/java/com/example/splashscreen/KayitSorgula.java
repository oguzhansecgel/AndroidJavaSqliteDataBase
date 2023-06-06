package com.example.splashscreen;

import android.R.layout;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class KayitSorgula extends AppCompatActivity {
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_sorgula);


        Button btn_kayitSorgu = findViewById(R.id.btn_kayitSorgu);
        ListView listView = findViewById(R.id.kayitSorgulaList);
        EditText etSorguKadi = findViewById(R.id.sorguEt);


        btn_kayitSorgu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String arananAdi = etSorguKadi.getText().toString();

                try {
                    database = openOrCreateDatabase("Okul", MODE_PRIVATE, null);
                    String query = "SELECT * FROM okul WHERE isim = '" + arananAdi + "'";
                    Cursor cursor = database.rawQuery(query, null);

                    if (cursor.getCount() == 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(KayitSorgula.this);
                        builder.setTitle("Kullanıcı Bulunamadı")
                                .setMessage("Girilen isimle ilgili kullanıcı bulunamadı.")
                                .setPositiveButton("Tamam", null)
                                .show();
                    } else {
                        int isimIndex = cursor.getColumnIndex("isim");
                        int kadiIndex = cursor.getColumnIndex("kadi");
                        int sifreIndex = cursor.getColumnIndex("sifre");
                        int dogum_yeriIndex = cursor.getColumnIndex("dogum_yeri");
                        int cinsiyetIndex = cursor.getColumnIndex("cinsiyet");
                        int idIndex = cursor.getColumnIndex("id");

                        ArrayList<String> bilgilerListesi = new ArrayList<>();

                        while (cursor.moveToNext()) {
                            String bilgi = "İd: " + cursor.getInt(idIndex) +
                                    "\nİsim: " + cursor.getString(isimIndex) +
                                    "\nKullanıcı Adı: " + cursor.getString(kadiIndex) +
                                    "\nDoğum Yeri: " + cursor.getString(dogum_yeriIndex) +
                                    "\nCinsiyet: " + cursor.getString(cinsiyetIndex) +
                                    "\n";

                            bilgilerListesi.add(bilgi);

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(KayitSorgula.this, android.R.layout.simple_list_item_1, bilgilerListesi);
                            listView.setAdapter(adapter);
                        }
                    }

                    cursor.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
}