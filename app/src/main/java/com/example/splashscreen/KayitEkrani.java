package com.example.splashscreen;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class KayitEkrani extends AppCompatActivity {
    EditText kayitIsim;
    EditText kayitKad;
    EditText kayitSifre;
    Spinner spinnerIl;
    RadioButton rb_Erkek;
    RadioButton rb_Kadin;
    CheckBox cb_Checkbox1;




    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ekrani);
        kayitIsim = findViewById(R.id.kayitIsim);
        kayitKad = findViewById(R.id.kayitKad);
        kayitSifre = findViewById(R.id.kayitSifre);
        spinnerIl = findViewById(R.id.spinnerIl);
        rb_Erkek = findViewById(R.id.rb_Erkek);
        rb_Kadin = findViewById(R.id.rb_Kadin);
        cb_Checkbox1 = findViewById(R.id.cb_Checkbox1);

        try{
            database = this.openOrCreateDatabase("Okul", MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS okul(id INTEGER PRIMARY KEY,isim VARCHAR,kadi VARCHAR,sifre VARCHAR,dogum_yeri VARCHAR,cinsiyet TEXT)");
           //database.execSQL("INSERT INTO okul (isim,kadi,sifre,dogum_yeri,cinsiyet) VALUES ('oguzhan','oguzhansecgel19','oguzhansecgel','Çorum','Erkek')");
            //database.execSQL("INSERT INTO okul (isim,kadi,sifre,dogum_yeri,cinsiyet) VALUES ('oguz','oguzhans','oguzhansecgel','Ankara','Erkek')");

           Cursor cursor = database.rawQuery("SELECT * FROM okul",null);
           int isimIndex = cursor.getColumnIndex("isim");
           int kadiIndex = cursor.getColumnIndex("kadi");
           int sifreIndex = cursor.getColumnIndex("sifre");
           int dogum_yeriIndex = cursor.getColumnIndex("dogum_yeri");
           int cinsiyetIndex = cursor.getColumnIndex("cinsiyet");
           int idIndex = cursor.getColumnIndex("id");
           while(cursor.moveToNext())
           {
               System.out.println("İd : "+cursor.getInt(idIndex));
               System.out.println("Isım : "+cursor.getString(isimIndex));
               System.out.println("Kullanici Adı : "+cursor.getString(kadiIndex));
               System.out.println("sifreIndex : "+cursor.getString(sifreIndex));
               System.out.println("Dogum Yeri  : "+cursor.getString(dogum_yeriIndex));
               System.out.println("Cinsiyet : "+cursor.getString(cinsiyetIndex));
           }
           cursor.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



        String[] iller = {"Adana", "Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin", "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir", "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa", "Uşak", "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak", "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"};

        Spinner spinner = findViewById(R.id.spinnerIl);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, iller);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        Button btnKayit = findViewById(R.id.kayitBtn);

        btnKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isim = kayitIsim.getText().toString();
                String kullaniciAdi = kayitKad.getText().toString();
                String sifre = kayitSifre.getText().toString();
                String secilenIl = spinnerIl.getSelectedItem().toString();
                boolean erkekSecili = rb_Erkek.isChecked();
                boolean kadinSecili = rb_Kadin.isChecked();
                boolean checkboxSecili = cb_Checkbox1.isChecked();

                String cinsiyet;
                if (erkekSecili) {
                    cinsiyet = "Erkek";
                } else if (kadinSecili) {
                    cinsiyet = "Kadın";
                } else {
                    cinsiyet = "";
                }

                if (isim.isEmpty() || kullaniciAdi.isEmpty() || sifre.isEmpty() || secilenIl.isEmpty() || (!erkekSecili && !kadinSecili) || checkboxSecili==false) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(KayitEkrani.this);
                    builder.setTitle("Hata");
                    builder.setMessage("Lütfen tüm alanları doldurun");
                    builder.setPositiveButton("Tamam", null);
                    builder.show();
                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(KayitEkrani.this);
                    builder.setTitle("Kayıt Başarılı");
                    builder.setMessage("Başarıyla Kayıt Oldunuz.");
                    builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String insertQuery = "INSERT INTO okul (isim, kadi, sifre, dogum_yeri, cinsiyet) VALUES ('" + isim + "', '" + kullaniciAdi + "', '" + sifre + "', '" + secilenIl + "', '" + cinsiyet + "')";
                            database.execSQL(insertQuery);

                            Intent giriEkrani = new Intent(KayitEkrani.this,GirisEkrani.class);
                            startActivity(giriEkrani);
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

    }
}