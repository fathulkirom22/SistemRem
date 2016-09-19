package com.bahwell.sistemrem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class tentang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);

        control ctrl = new control(this);
        ctrl.tentang();
    }

    //animation back
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.back_in, R.anim.back_out);
        finish();
    }
}
