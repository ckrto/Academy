package com.example.ex03;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//DB정의
public class AddressDB extends SQLiteOpenHelper {
    public AddressDB(@Nullable Context context) {
        super(context, "address.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {   //DB가 생성될 때
        //로컬 DB의 데이터를 가져올때 커서 어댑터를 사용하는데 사용하기 위해서는 primary key인 _id가 반드시 있어야 한다.
        String str="create table address(_id integer primary key autoincrement, name text, tel text, juso text);";
        db.execSQL(str);

        str = "insert into address(name,tel,juso) values('홍길동','010-0000-0000','인천시 미추홀구');";
        db.execSQL(str);

        str = "insert into address(name,tel,juso) values('심청이','010-1111-1111','인천시 연수구');";
        db.execSQL(str);

        str = "insert into address(name,tel,juso) values('강감찬','010-2222-2222','인천시 중구');";
        db.execSQL(str);

        str = "insert into address(name,tel,juso) values('홍길동','010-0000-0000','인천시 미추홀구');";
        db.execSQL(str);

        str = "insert into address(name,tel,juso) values('심청이','010-1111-1111','인천시 연수구');";
        db.execSQL(str);

        str = "insert into address(name,tel,juso) values('강감찬','010-2222-2222','인천시 중구');";
        db.execSQL(str);

        str = "insert into address(name,tel,juso) values('홍길동','010-0000-0000','인천시 미추홀구');";
        db.execSQL(str);

        str = "insert into address(name,tel,juso) values('심청이','010-1111-1111','인천시 연수구');";
        db.execSQL(str);

        str = "insert into address(name,tel,juso) values('강감찬','010-2222-2222','인천시 중구');";
        db.execSQL(str);

        str = "insert into address(name,tel,juso) values('홍길동','010-0000-0000','인천시 미추홀구');";
        db.execSQL(str);

        str = "insert into address(name,tel,juso) values('심청이','010-1111-1111','인천시 연수구');";
        db.execSQL(str);

        str = "insert into address(name,tel,juso) values('강감찬','010-2222-2222','인천시 중구');";
        db.execSQL(str);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
