package com.example.a123.mymoney;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by 123 on 2017/12/12.
 */

public class SQLiteHelper extends SQLiteOpenHelper {


    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String cost, String date, String category, String subCategory, String comment) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO FINANCIAL VALUES (NULL, ?, ?, ?, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, cost);
        statement.bindString(2, date);
        statement.bindString(3, category);
        statement.bindString(4, subCategory);
        statement.bindString(5, comment);

        statement.executeInsert();
    }

    public void updateData(String cost, String date, String category, String subCategory, String comment, int id) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE FINANCIAL SET cost = ?, date = ?, category = ?, subCategory = ?, comment = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindString(1, cost);
        statement.bindString(2, date);
        statement.bindString(3, category);
        statement.bindString(4, subCategory);
        statement.bindString(5, comment);

        statement.bindDouble(6, (double) id);
        statement.execute();
        database.close();
    }

    public void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM FINANCIAL WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindDouble(1, (double)id);
        statement.execute();
        database.close();
    }

    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
