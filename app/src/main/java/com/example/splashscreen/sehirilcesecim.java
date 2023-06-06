package com.example.splashscreen;

import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class sehirilcesecim extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sehirilcesecim);
        Spinner ilSpinner = findViewById(R.id.ilSpinner);
        Spinner ilceSpinner = findViewById(R.id.ilceSpinner);
        Button bulBtn = findViewById(R.id.bulBtn);

        WebView webView = findViewById(R.id.eczaneView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        bulBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String ilSecimi = ilSpinner.getSelectedItem().toString();
                String ilceSecimi = ilceSpinner.getSelectedItem().toString();
                webView.loadUrl("https://www.eczaneler.gen.tr/nobetci-"+ilSecimi+"-"+ilceSecimi);
                System.out.println("Seçtiğiniz İl : "+ilSecimi);
                /*Intent intent = new Intent(sehirilcesecim.this,UygulamaEkrani.class);
                intent.putExtra("il", ilSecimi);
                intent.putExtra("ilce", ilceSecimi);
                startActivity(intent);*/
            }
        });

        String[] iller = {"adana",  "amasya", "ankara", "antalya", "corum","erzincan", "erzurum", "eskisehir","istanbul","sakarya", "samsun"};
        Spinner spinner = findViewById(R.id.ilSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, iller);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ilSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedIl = iller[position];


                updateIlceler(selectedIl);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(sehirilcesecim.this, "Secim Yapiniz.", Toast.LENGTH_SHORT).show();
            }
            private void updateIlceler(String selectedIl) {
                String[] ilceler;

                if (selectedIl.equals("adana")) {
                    ilceler = new String[]{"ceyhan", "feke", "karaisali", "karatas", "kozan", "pazarcik", "pozanti", "saimbeyli", "saricam", "seydisehir", "tufanbeyli", "yuregir"};
                } else if (selectedIl.equals("amasya")) {
                    ilceler = new String[]{ "goynuce", "gulumbe", "gokmedrese", "hamamozu", "ipekkoy", "karagol", "kayacik", "kizilirmak", "kizoglu", "mesudiye", "oguzlar", "sariyer", "seki", "seyhhasan", "taskopru", "tasmahmutlu", "tosya", "yeniay", "zara"};
                } else if (selectedIl.equals("ankara")) {
                    ilceler = new String[]{"akyurt", "bala", "beypazari", "camlidere", "cankaya", "cubuk", "elvankent", "etimesgut", "evren", "golbasi", "haymana", "kalecik", "kecioren", "kizilcahamam", "mamak", "nallihan", "polatli", "pursaklar", "sincan", "sereflikoçhisar", "yenimahalle"};
                } else if (selectedIl.equals("antalya")) {
                    ilceler = new String[]{"aksu", "alanya", "demre", "dosemealti", "elmali", "finike", "gazipasa", "gundogmus", "ibradi", "kas", "kemer", "kepez", "konyaalti", "korkuteli", "kumluca", "manavgat", "muratpasa", "serik"};
                } else if (selectedIl.equals("corum")) {
                    ilceler = new String[]{"merkez","alaca", "bayat", "bogazkale", "dodurga", "kargi", "lacin", "mecitozu", "oguzlar", "ortakoy", "osmancik", "sungurlu", "ugurludag"};
                } else if (selectedIl.equals("erzincan")) {
                    ilceler = new String[]{"merkez","cayırlı", "ilanlı", "kemah", "kemaliye", "oguzeli", "refahiye", "tazehan", "tercan", "ulalar", "yolagzı"};
                } else if (selectedIl.equals("erzurum")) {
                    ilceler = new String[]{"askale", "aziziye", "cat", "hınıs", "horasan", "ispir", "karayazi", "koprukoy", "narman", "oltu", "olur", "palandoken", "pasinler", "pazaryolu", "senkaya", "tortum", "uzundere"};
                } else if (selectedIl.equals("istanbul")) {
                    ilceler = new String[]{"adalar", "arnavutkoy", "atasehir", "avcilar", "bagcilar", "bahcelievler", "bakirkoy", "basaksehir", "bayrampasa", "besiktas", "beykoz", "beylikduzu", "beyoglu", "buyukcekmece", "catalca", "cekmekoy", "esenler", "esenyurt", "eyup", "fatih", "gaziosmanpasa", "gungoren", "kadikoy", "kagithane", "kartal", "kucukcekmece", "maltepe", "pendik", "sancaktepe", "sariyer", "silivri", "sultanbeyli", "sultangazi", "sile", "sisli", "tuzla", "umraniye", "uskudar", "zeytinburnu"};
                } else if (selectedIl.equals("samsun")) {
                    ilceler = new String[]{"atakum", "ayvacik", "bafra", "canik", "carsamba", "havza", "ilkadim", "kavak", "ladik", "salipazari", "tekkekoy", "terme", "vezirkopru", "yakakent"};
                } else {
                    ilceler = new String[0];
                }

                ArrayAdapter<String> ilceAdapter = new ArrayAdapter<>(sehirilcesecim.this, android.R.layout.simple_spinner_item, ilceler);
                ilceSpinner.setAdapter(ilceAdapter);
            }
        });


    }
}
