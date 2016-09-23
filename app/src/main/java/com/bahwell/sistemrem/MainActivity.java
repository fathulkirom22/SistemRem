package com.bahwell.sistemrem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        control ctrl = new control(this);

        Button petunjuk = (Button) findViewById(R.id.petujuk);
        petunjuk.setOnClickListener(this);

        Button materi = (Button) findViewById(R.id.materi);
        materi.setOnClickListener(this);

        Button evaluasi = (Button) findViewById(R.id.evaluasi);
        evaluasi.setOnClickListener(this);

        Button tentang = (Button) findViewById(R.id.tentang);
        tentang.setOnClickListener(this);

        ctrl.highscore();
    }

    public void onClick(View v) {
        // Perform action on click
        switch(v.getId()) {
            case R.id.petujuk:
                //buka petunjuk
                startActivity(new Intent(MainActivity.this, petunjuk.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case R.id.materi:
                //buka materi
                startActivity(new Intent(MainActivity.this, materi.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case R.id.evaluasi:
                //buka evaluasi
                startActivity(new Intent(MainActivity.this, evaluasi.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case R.id.tentang:
                //buka tentang
                startActivity(new Intent(MainActivity.this, tentang.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
        }
    }
}
