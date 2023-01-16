package com.icia.food;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class FoodListFragment extends Fragment {
    SQLiteDatabase db;
    SQLiteOpenHelper helper;
    RecyclerView list;
    int type=1;
    FoodAdapter adapter;
    ArrayList<FoodVO> array=new ArrayList<>();
    FoodDAO dao=new FoodDAO();
    StaggeredGridLayoutManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_food_list, container, false);

        array=dao.list(new FoodDB(getContext()));
        adapter=new FoodAdapter(getContext(), array, "list");

        list=view.findViewById(R.id.list_food);
        list.setAdapter(adapter);

        manager= new StaggeredGridLayoutManager(type, StaggeredGridLayoutManager.VERTICAL);
        list.setLayoutManager(manager);

        //타입 변경 버튼을 클릭한 경우
        ImageView list_type = view.findViewById(R.id.list_type);
        list_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1) {
                    type = 2;
                    list_type.setImageResource(R.drawable.ic_list2);
                }
                else {
                    type = 1;
                    list_type.setImageResource(R.drawable.ic_list);
                }
                manager = new StaggeredGridLayoutManager(type, StaggeredGridLayoutManager.VERTICAL);
                list.setLayoutManager(manager);
            }
        });

        return view;
    }
}