package com.icia.food;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FoodDAO {
    SQLiteDatabase db;

    //맛집 등록
    public void  insert(FoodDB helper, FoodVO vo){
        db=helper.getWritableDatabase();
        String sql="insert into tbl_food(name,tel,address,latitude,longitude,description,image,keep) values('"
                +vo.getName()+"','"+vo.getTel()+"','"+vo.getAddress()+"','"
                +vo.getLatitude()+"','"+vo.getLongitude()+"','"+vo.getDescription()+"','"+vo.getImage()+"',0)";
        db.execSQL(sql);
    }

    //음식점정보
    public FoodVO read(FoodDB helper, int id) {
        db = helper.getWritableDatabase();
        FoodVO vo = new FoodVO();

        String sql = "select * from tbl_food where _id =" + id;
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToNext()) {
            vo.setId(cursor.getInt(0));
            vo.setName(cursor.getString(1));
            vo.setAddress(cursor.getString(2));
            vo.setTel(cursor.getString(3));
            vo.setLatitude(cursor.getDouble(4));
            vo.setLongitude(cursor.getDouble(5));
            vo.setImage(cursor.getString(6));
            vo.setDescription(cursor.getString(7));
            vo.setKeep(cursor.getInt(8));
        }

        return vo;
    }

    //즐겨찾기 해지
    public void updateKeep(FoodDB helper, int keep, int id) {
        db = helper.getWritableDatabase();
        String sql = "update tbl_food set keep = " + keep + " where _id = " + id;
        db.execSQL(sql);
    }

    //즐겨찾기 목록
    public ArrayList<FoodVO> keeplist(FoodDB helper){
        ArrayList<FoodVO> array=new ArrayList<FoodVO>();
        String sql="select * from tbl_food where keep = 1 order by _id desc";

        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery(sql, null);

        while(cursor.moveToNext()){
            FoodVO vo=new FoodVO();
            vo.setId(cursor.getInt(0));
            vo.setName(cursor.getString(1));
            vo.setAddress(cursor.getString(2));
            vo.setTel(cursor.getString(3));
            vo.setLatitude(cursor.getDouble(4));
            vo.setLongitude(cursor.getDouble(5));
            vo.setImage(cursor.getString(6));
            vo.setDescription(cursor.getString(7));
            vo.setKeep(cursor.getInt(8));
            array.add(vo);
        }
        return array;
    }

    //음식목록
    public ArrayList<FoodVO> list(FoodDB heler){
        ArrayList<FoodVO> array=new ArrayList<FoodVO>();
        String sql="select * from tbl_food order by _id desc";

        db=heler.getWritableDatabase();
        Cursor cursor=db.rawQuery(sql, null);

        while(cursor.moveToNext()){
            FoodVO vo=new FoodVO();
            vo.setId(cursor.getInt(0));
            vo.setName(cursor.getString(1));
            vo.setAddress(cursor.getString(2));
            vo.setTel(cursor.getString(3));
            vo.setLatitude(cursor.getDouble(4));
            vo.setLongitude(cursor.getDouble(5));
            vo.setImage(cursor.getString(6));
            vo.setDescription(cursor.getString(7));
            vo.setKeep(cursor.getInt(8));
            array.add(vo);
        }
        return array;
    }
}
