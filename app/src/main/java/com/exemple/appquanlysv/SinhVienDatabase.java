package com.exemple.appquanlysv;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SinhVienDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QUANLYSINHVIEN.sqlite";
    private static final String TABLE_NAME = "SINHVIEN";
    private static final String ID = "ID";
    private static final String TEN = "TEN";
    private static final String NAMSINH = "NAMSINH";
    private static final String LOP = "LOP";
    private static final String GIOITINH = "GIOITINH";
    private static final String SOTHICH = "SOTHICH";


    public SinhVienDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS SV(ID INTEGER PRIMARY KEY AUTOINCREMENT, TEN text,NAMSINH text,LOP text,GIOITINH integer,SOTHICH text)";
        db.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void ThemSinhVien(SinhVien svMoi)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN,svMoi.getNamsinh());
        values.put(NAMSINH,svMoi.getNamsinh());
        values.put(LOP,svMoi.getLop());
        values.put(GIOITINH,svMoi.getGioitinh());
        values.put(SOTHICH,svMoi.getSoThich());

        db.insert(TABLE_NAME,null,values);
        db.close();

    }
}
