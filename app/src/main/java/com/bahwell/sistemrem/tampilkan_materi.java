package com.bahwell.sistemrem;

import android.app.Activity;
import android.os.Bundle;

public class tampilkan_materi extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilkan_materi);

        control c = new control(this);

        Bundle b = getIntent().getExtras();
        int value = -1; // or other values
        if(b != null)
            value = b.getInt("key");
        c.Update_materi(value);
    }

    //animation back
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.back_in, R.anim.back_out);
        finish();
    }
}
