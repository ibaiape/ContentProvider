package com.example.dm2.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SqliteHelper extends SQLiteOpenHelper {

        //Sentencia SQL para crear la tabla de Clientes
        String sqlCreate ="CREATE TABLE colores" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nombre TEXT," +
                " hex TEXT)";
        public  SqliteHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String[] nombres = {"Verde","Azul", "Rojo", "Negro", "Blanco"};
            String[] codigos = {"008000","0000FF", "FF0000", "000000", "FFFFFF"};
            //Se ejecuta la sentencia SQL de reación de la tabla
            db.execSQL(sqlCreate);
            //Insertamos 15 clientes de ejemplo
            for (int i=0; i<nombres.length; i++){
                //Insertamos los datos en la tabla Clientes
                db.execSQL("INSERT INTO colores (nombre, hex) " +
                        "VALUES ('"+nombres[i]+"','"+codigos[i]+"')");
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            //Se elimina la versión anterior de la tabla
            db.execSQL("DROP TABLE IF EXISTS colores");
            //Se crea la nueva versión de la tabla
            db.execSQL(sqlCreate);
        }


}
