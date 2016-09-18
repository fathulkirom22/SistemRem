package com.bahwell.sistemrem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;


public class evaluasi extends Activity implements OnClickListener {

    //public int[] random = {1,2,3};
    public Integer[] random = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    //{1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28}

    control ctrl = new control(this);

    public static String jwbKunci, a, b, c, d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        randomin(random);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluasi);

        //button
        Button jwb_a = (Button) findViewById(R.id.jwb_a);
        Button jwb_b = (Button) findViewById(R.id.jwb_b);
        Button jwb_c = (Button) findViewById(R.id.jwb_c);
        Button jwb_d = (Button) findViewById(R.id.jwb_d);

        jwb_a.setOnClickListener(this);
        jwb_b.setOnClickListener(this);
        jwb_c.setOnClickListener(this);
        jwb_d.setOnClickListener(this);

        ctrl.update_soal(random[0]);
        random = ArrayUtils.remove(random,0);
    }
    //animation back
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.back_in, R.anim.back_out);
        finish();
    }
    //on clik
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.jwb_a:
                nextOrFinist(a);
                break;
            case R.id.jwb_b:
                nextOrFinist(b);
                break;
            case R.id.jwb_c:
                nextOrFinist(c);
                break;
            case R.id.jwb_d:
                nextOrFinist(d);
                break;
        }
    }

    public static void randomin(Integer[] random){
        Collections.shuffle(Arrays.asList(random));
    }

    public void nextSoal(String v){
        ctrl.update_soal(random[0]);
        random = ArrayUtils.remove(random, 0);
    }

    public void setJwbKunci(String v){
        this.jwbKunci = v;
    }

    public void setA(String v){
        this.a = v;
    }

    public void setB(String v){
        this.b = v;
    }

    public void setC(String v){
        this.c = v;
    }

    public void setD(String v){
        this.d = v;
    }

    public void nextOrFinist(String v){
        if (random.length != 0){
            ctrl.pushJwbOrang(v);
            nextSoal(v);
        } else {
            ctrl.pushJwbOrang(v);
            Intent intent = new Intent(evaluasi.this, score.class);
            startActivity(intent); // to score
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }
    }

}

