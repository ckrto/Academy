package com.example.memo;

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
        db.execSQL("create table memo(_id integer primary key autoincrement, content text, date text);");
        db.execSQL("insert into memo(content,date) values('안드로이드 스튜디오 소개','2022-08-10 12:10:10');");
        db.execSQL("insert into memo(content,date) values('JSP&Servlet','2022-08-12 11:10:09');");
        db.execSQL("insert into memo(content,date) values('HTML5','2022-08-10 12:10:10');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
