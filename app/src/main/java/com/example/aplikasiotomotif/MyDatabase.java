package com.example.aplikasiotomotif;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_otomotif";
    private static final String tb_oto = "tb_otomotif";
    private static final String tb_oto_id = "id";
    private static final String tb_oto_nama = "nama";
    private static final String tb_oto_pabrikan = "pabrikan";
    private static final String tb_oto_cc = "cc";
    private static final String CREATE_TABLE_OTOMOTIF = "CREATE TABLE " +
            tb_oto + "("
            + tb_oto_id + " INTEGER PRIMARY KEY ,"
            + tb_oto_nama + " TEXT,"
            + tb_oto_pabrikan + " TEXT,"
            + tb_oto_cc + " TEXT" + ")";

    public MyDatabase (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_OTOMOTIF);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void CreateOtomotif (otomotif mdNotif) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_oto_id, mdNotif.get_id());
        values.put(tb_oto_nama, mdNotif.get_nama());
        values.put(tb_oto_pabrikan, mdNotif.get_pabrik());
        values.put(tb_oto_cc, mdNotif.get_cc());
        db.insert(tb_oto, null, values);
        db.close();
    }

    public List<otomotif> ReadOtomotif() {
        List<otomotif> judulModelList = new ArrayList<otomotif>();
        String selectQuery = "SELECT * FROM " + tb_oto;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                otomotif mdKontak = new otomotif();
                mdKontak.set_id(cursor.getString(0));
                mdKontak.set_nama(cursor.getString(1));
                mdKontak.set_pabrik(cursor.getString(2));
                mdKontak.set_cc(cursor.getString(3));
                judulModelList.add(mdKontak);
            } while (cursor.moveToNext());
        }
        db.close();
        return judulModelList;
    }
    public int UpdateOtomotif (otomotif mdNotif) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_oto_nama, mdNotif.get_nama());
        values.put(tb_oto_pabrikan, mdNotif.get_pabrik());
        values.put(tb_oto_cc, mdNotif.get_cc());
        return db.update(tb_oto, values, tb_oto_id + " = ?",
                new String[] { String.valueOf(mdNotif.get_id())});
    }
    public void DeleteOtomotif (otomotif mdNotif) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_oto, tb_oto_id+ " = ?",
                new String[]{String.valueOf(mdNotif.get_id())});
        db.close();
    }
}

