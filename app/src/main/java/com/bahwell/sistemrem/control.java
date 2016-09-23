package com.bahwell.sistemrem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import android.app.Activity;

import com.squareup.picasso.Picasso;
import com.bahwell.sistemrem.evaluasi;

import java.util.ArrayList;
import java.util.List;

import static android.database.sqlite.SQLiteDatabase.*;

/**
 * Created by bahwell on 05/09/16.
 */
public class control {

    public static List<String> jwbOrng = new ArrayList<String>();
    public static List<String> jwbBnr = new ArrayList<String>();

    public static int noSoal = 1;

    Context context;
    public control(Context context){
        this.context=context;
    }

    public void petunjuk(){
        WebView html = (WebView) ((Activity) context).findViewById(R.id.petujuk_html);
        html.loadUrl("file:///android_asset/petunjuk.html");
    }

    public void tentang(){
        WebView html = (WebView) ((Activity) context).findViewById(R.id.tentang_html);
        html.loadUrl("file:///android_asset/tentang.html");
    }

    public void Update_materi(int v) {
        WebView html = (WebView) ((Activity) context).findViewById(R.id.materi_html);
        if (v==1){
            html.loadUrl("file:///android_asset/materi1.html");
        }if (v==2) {
            html.loadUrl("file:///android_asset/materi2.html");
        }if (v==3) {
            html.loadUrl("file:///android_asset/materi3.html");
        }if (v==4) {
            html.loadUrl("file:///android_asset/materi4.html");
        }if (v==5) {
            html.loadUrl("file:///android_asset/materi5.html");
        }if (v==6) {
            html.loadUrl("file:///android_asset/materi6.html");
        }if (v==7) {
            html.loadUrl("file:///android_asset/materi7.html");
        }if (v==8) {
            html.loadUrl("file:///android_asset/materi8.html");
        }if (v==9) {
            html.loadUrl("file:///android_asset/materi9.html");
        }if (v==10) {
            html.loadUrl("file:///android_asset/materi10.html");
        }
    }

    public void update_soal(int v){
        DBManager dataBaseHelper = new DBManager((Activity) context);
        //img
        TextView no_soal = (TextView) ((Activity) context).findViewById(R.id.no_soal);
        //test
        TextView soal_text = (TextView) ((Activity) context).findViewById(R.id.text_soal);
        //button
        Button jwb_a = (Button) ((Activity) context).findViewById(R.id.jwb_a);
        Button jwb_b = (Button) ((Activity) context).findViewById(R.id.jwb_b);
        Button jwb_c = (Button) ((Activity) context).findViewById(R.id.jwb_c);
        Button jwb_d = (Button) ((Activity) context).findViewById(R.id.jwb_d);

        try {
            dataBaseHelper.createDataBase();
            SQLiteDatabase db = dataBaseHelper.openDataBase();
            Cursor cursor = db.rawQuery("SELECT * FROM soal Where id ="+v, null);
            cursor.moveToFirst();

            evaluasi eva = new evaluasi();
            do {
                //no
                String tempNo = Integer.toString(noSoal) + "/40";
                no_soal.setText(tempNo);
                //soal
                String soal = cursor.getString(cursor.getColumnIndex("text_soal"));
                soal_text.setText(soal);
                //jawaban
                String jwbA = cursor.getString(cursor.getColumnIndex("jwb_a"));
                String jwbB = cursor.getString(cursor.getColumnIndex("jwb_b"));
                String jwbC = cursor.getString(cursor.getColumnIndex("jwb_c"));
                String jwbD = cursor.getString(cursor.getColumnIndex("jwb_d"));
                String jwbKey = cursor.getString(cursor.getColumnIndex("jwb_kunci"));

                jwb_a.setText(jwbA);
                jwb_b.setText(jwbB);
                jwb_c.setText(jwbC);
                jwb_d.setText(jwbD);

                eva.setA(jwbA);
                eva.setB(jwbB);
                eva.setC(jwbC);
                eva.setD(jwbD);
                eva.setJwbKunci(jwbKey);

                jwbBnr.add(jwbKey);
                noSoal ++;

            }while (cursor.moveToNext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pushJwbOrang(String v){
        jwbOrng.add(v);
    }

    public void viewScore(){
        TextView viewScore = (TextView) ((Activity) context).findViewById(R.id.score);
        TableLayout viewSalah = (TableLayout) ((Activity) context).findViewById(R.id.salah);


        int score = 0;
        int rowNo=1;
        for (int counter = 0; counter < jwbBnr.size(); counter++) {
            if (jwbOrng.get(counter).equals(jwbBnr.get(counter))) {
                score += 10;
            } else {
                TableRow row= new TableRow(context);
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);
                viewSalah.setColumnShrinkable(3,true);
                viewSalah.setLayoutParams(lp);
                TextView noSalah = new TextView(context);
                TextView salah = new TextView(context);
                TextView benar = new TextView(context);

                salah.setText(jwbOrng.get(counter).toString());
                benar.setText(jwbBnr.get(counter).toString());
                noSalah.setText(Integer.toString(counter+1));

                salah.setWidth(0);
                benar.setWidth(0);
                noSalah.setWidth(0);

                row.addView(noSalah);
                row.addView(salah);
                row.addView(benar);


                viewSalah.addView(row,rowNo);
                rowNo ++;
            }
        }

        viewScore.setText(Integer.toString(score));
        updatehighscore(score);
        jwbBnr.clear();
        jwbOrng.clear();
        noSoal = 1;
    }

    public void highscore(){
        DBManager dataBaseHelper = new DBManager((Activity) context);
        TextView viewScore = (TextView) ((Activity) context).findViewById(R.id.highScore);

        try {
            dataBaseHelper.createDataBase();
            SQLiteDatabase db = dataBaseHelper.openDataBase();
            Cursor cursor = db.rawQuery("SELECT * FROM score Where id = 1", null);
            cursor.moveToFirst();
            do {
                //soal
                String score = cursor.getString(cursor.getColumnIndex("highscore"));
                viewScore.setText(score);
            }while (cursor.moveToNext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean bandingkanscore(int v){
        DBManager dataBaseHelper = new DBManager((Activity) context);
        int oldscore;
        try {
            dataBaseHelper.createDataBase();
            SQLiteDatabase db = dataBaseHelper.openDataBase();
            Cursor cursor = db.rawQuery("SELECT * FROM score Where id = 1", null);
            cursor.moveToFirst();

            String score = cursor.getString(cursor.getColumnIndex("highscore"));
            oldscore = Integer.parseInt(score);
            if (oldscore > v){
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void updatehighscore(int v) {
        DBManager dataBaseHelper = new DBManager((Activity) context);
        if (bandingkanscore(v)){
            try {
                dataBaseHelper.createDataBase();
                SQLiteDatabase db = dataBaseHelper.getWritableDatabase() ;
                String strFilter = "id=1";
                ContentValues args = new ContentValues();
                args.put("highscore", v);
                db.update("score", args, strFilter, null);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
