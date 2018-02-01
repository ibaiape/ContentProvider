package com.example.dm2.contentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Ejercicio1 extends AppCompatActivity {

    private static final String uri = "content://com.example.dm2.sqlite/agenda";
    private static final Uri CONTENT_URI = Uri.parse(uri);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1);
        mostrarAgenda();
    }

    public void mostrarAgenda(){
        String[] TIPO_LLAMADA = {"", "entrante", "saliente", "perdida"};
        TextView salida = (TextView) findViewById(R.id.salida);
        Uri llamadas = Uri.parse("content://call_log/calls");
        Cursor c = getContentResolver().query(llamadas, null, null, null, null);
        while (c.moveToNext()) {
            salida.append("\n"
                + c.getString(c.getColumnIndex(CallLog.Calls.DURATION)) + ") "
                + c.getString(c.getColumnIndex(CallLog.Calls.NUMBER)) + ", "
                + TIPO_LLAMADA[Integer.parseInt(
                c.getString(c.getColumnIndex(CallLog.Calls.TYPE)))]);
        }

    }

}



