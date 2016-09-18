package com.bahwell.sistemrem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class materi extends Activity implements OnClickListener {

    public static TextView textViewObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);

        //button
        Button materi1 = (Button) findViewById(R.id.materi1);
        materi1.setOnClickListener(this);
        Button materi2 = (Button) findViewById(R.id.materi2);
        materi2.setOnClickListener(this);
        Button materi3 = (Button) findViewById(R.id.materi3);
        materi3.setOnClickListener(this);
        Button materi4 = (Button) findViewById(R.id.materi4);
        materi4.setOnClickListener(this);
        Button materi5 = (Button) findViewById(R.id.materi5);
        materi5.setOnClickListener(this);
        Button materi6 = (Button) findViewById(R.id.materi6);
        materi6.setOnClickListener(this);
        Button materi7 = (Button) findViewById(R.id.materi7);
        materi7.setOnClickListener(this);
        Button materi8 = (Button) findViewById(R.id.materi8);
        materi8.setOnClickListener(this);
        Button materi9 = (Button) findViewById(R.id.materi9);
        materi9.setOnClickListener(this);
        Button materi10 = (Button) findViewById(R.id.materi10);
        materi10.setOnClickListener(this);
    }

    //animation back
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.back_in, R.anim.back_out);
        finish();
    }

    public void onClick(View v) {
        switch(v.getId()) {
            //materi
            case R.id.materi1:
                tampilkanMateri(1);;
                break;
            case R.id.materi2:
                tampilkanMateri(2);
                break;
            case R.id.materi3:
                tampilkanMateri(3);
                break;
            case R.id.materi4:
                tampilkanMateri(4);
                break;
            case R.id.materi5:
                tampilkanMateri(5);
                break;
            case R.id.materi6:
                tampilkanMateri(6);
                break;
            case R.id.materi7:
                tampilkanMateri(7);
                break;
            case R.id.materi8:
                tampilkanMateri(8);
                break;
            case R.id.materi9:
                tampilkanMateri(9);
                break;
            case R.id.materi10:
                tampilkanMateri(10);
                break;
        }
    }

    public void tampilkanMateri(int v){
        Intent intent = new Intent(materi.this, tampilkan_materi.class);
        Bundle b = new Bundle();

        b.putInt("key", v); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
