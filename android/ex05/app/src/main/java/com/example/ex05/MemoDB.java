package com.example.ex05;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MemoDB extends SQLiteOpenHelper {

    public MemoDB(@Nullable Context context) {
        super(context, "memo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table memo(_id integer primary key autoincrement, content text, date text)");
        db.execSQL("insert into memo(content, date) values('안드로이드', '2019-08-20 12:30:22')");
        db.execSQL("insert into memo(content, date) values('HTML5', '2020-07-20 18:40:12')");
        db.execSQL("insert into memo(content, date) values('JAVA', '2021-06-12 07:25:23')");
        db.execSQL("insert into memo(content, date) values('JSP&Servlet', '2022-07-31 11:22:26')");
        db.execSQL("insert into memo(content, date) values('데이터베이스', '2022-08-12 15:43:19')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
