package com.icia.food;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;


public class FoodKeepFragment extends Fragment {
    FoodAdapter adapter;
    FoodDAO dao = new FoodDAO();
    RecyclerView list;
    StaggeredGridLayoutManager manager;
    int type = 1;
    ArrayList<FoodVO> array = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_list, container, false);

        FoodDB helper = new FoodDB(getContext());
        array = dao.keeplist(helper);
        System.out.println("데이터갯수 : " + array.size());

        adapter = new FoodAdapter(getContext(), array, "keep");
        list=view.findViewById(R.id.list_food);

        manager= new StaggeredGridLayoutManager(type, StaggeredGridLayoutManager.VERTICAL);
        list.setLayoutManager(manager);
        list.setAdapter(adapter);

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