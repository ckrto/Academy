package com.example.ex06;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AddressDB extends SQLiteOpenHelper {
    public AddressDB(@Nullable Context context) {
        super(context, "address.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table address(_id integer primary key autoincrement, name text, tel text, juso text, image text)");
        db.execSQL("insert into address(name, tel, juso) values('홍길동', '010-1010-1010', '인천 서구 경서동')");
        db.execSQL("insert into address(name, tel, juso) values('심청이', '010-2020-2020', '인천 부평구 부평5동')");
        db.execSQL("insert into address(name, tel, juso) values('강감찬', '010-3030-3030', '인천 미추홀구 주안동')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
