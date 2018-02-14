package com.example.dm2.contentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Ejercicio2 extends AppCompatActivity {

    private TextView txt;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2);

        txt = (TextView) findViewById(R.id.txtResultado);
        String[] projection = new String[]{
                Provider.Colores._ID,
                Provider.Colores.COL_NOMBRE,
                Provider.Colores.COL_HEX
        };

        Uri coloresURI = Provider.CONTENT_URI;
        ContentResolver cr = getContentResolver();

        Cursor c = cr.query(coloresURI, projection,
                null, null, null
        );

        Log.i("",c.toString());

        if (c.moveToFirst()) {
            String nombre;
            String hex;
            int colNombre = c.getColumnIndex(Provider.Colores.COL_NOMBRE);
            int colHex = c.getColumnIndex(Provider.Colores.COL_HEX);
            txt.setText("");
            do {
                nombre = c.getString(colNombre);
                hex = c.getString(colHex);
                txt.append(nombre + " - #" + hex + "\n");
            }
            while (c.moveToNext());
        }

    }
}
