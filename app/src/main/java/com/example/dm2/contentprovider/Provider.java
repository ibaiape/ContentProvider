package com.example.dm2.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Provider extends ContentProvider {


    private static final String uri = "content://com.example.dm2.contentprovider/colores";
    public static final Uri CONTENT_URI = Uri.parse(uri);

    public static final class Colores implements BaseColumns {
        private Colores() {
        }

        //Nombres de columnas
        public static final
        String COL_NOMBRE = "nombre";
        public static final
        String COL_HEX = "hex";
    }

    private SqliteHelper clidbh;
    private static final String BD_NOMBRE = "DBColores";
    private static final int BD_VERSION = 1;
    private static final String TABLA_COLORES = "Colores";

    //Necesario para UriMatcher
    private static final int COLOR = 1;
    private static final int COLOR_ID = 2;
    private static final
    UriMatcher uriMatcher;

    //Inicializamos el UriMatcher
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.dm2.contentprovider" , "colores" , COLOR);
        uriMatcher.addURI("com.example.dm2.contentprovider" , "colores/#" , COLOR_ID);
    }

    @Override
    public boolean onCreate() {
        //inicializar la base de datos a traves de su nombre y versión
        clidbh = new SqliteHelper(getContext(), BD_NOMBRE, null, BD_VERSION);
        return true;
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
//Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if (uriMatcher.match(uri) == COLOR_ID) {
            where = "_id=" + uri.getLastPathSegment();
        }
        SQLiteDatabase db = clidbh.getWritableDatabase();
        Cursor c = db.query(TABLA_COLORES, projection, where, selectionArgs, null, null, sortOrder);
        return c;
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int cont;
//Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if (uriMatcher.match(uri)== COLOR_ID) {
            where = "_id=" + uri.getLastPathSegment();
        }
        SQLiteDatabase db = clidbh.getWritableDatabase();
        cont = db.update(TABLA_COLORES, values, where, selectionArgs);
        return cont;
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int cont;
//Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if (uriMatcher.match(uri) == COLOR_ID) {
            where = "_id=" + uri.getLastPathSegment();
        }
        SQLiteDatabase db = clidbh.getWritableDatabase();
        cont = db.delete(TABLA_COLORES, where,selectionArgs);
        return cont;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long regId = 1;
        SQLiteDatabase db = clidbh.getWritableDatabase();
        regId = db.insert(TABLA_COLORES, null, values);
        Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);
        return newUri;
    }

    @Override
    public
    String getType(Uri uri) {
        int match = uriMatcher.match(uri);
        switch (match){
            case COLOR:
                return "vnd.android.cursor.dir/vnd.ejemplo.color";
            case COLOR_ID:
                return "vnd.android.cursor.item/vnd.ejemplo.color";
            default:
                return null;
        }
    }


    /*

    private static final String uri="content://com.example.dm2.contentprovider";
    private static final Uri CONTENT_URI=Uri.parse(uri);


    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    public class Colores implements BaseColumns {

        private Colores () {}

        public static final String COL_NOMBRE = "nombre";
        public static final String COL_HEX = "hex";

    }

    //Base de datos
    private SqliteHelper sqlDBH;
    private  static final String BD_NOMBRE="BDSeries";
    private static final int BD_VERSION=1;
    private static final String TABLA_SERIES="series";
    */
}
