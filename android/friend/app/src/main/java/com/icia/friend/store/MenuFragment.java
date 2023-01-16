package com.icia.friend.store;

import static com.icia.friend.remote.MenuRemoteService.BASE_URL;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icia.friend.R;
import com.icia.friend.remote.MenuRemoteService;
import com.icia.friend.remote.StoreRemoteService;
import com.icia.friend.vo.MenuVO;
import com.icia.friend.vo.StoreVO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuFragment extends Fragment {
    RecyclerView list;
    Retrofit retrofit;
    MenuRemoteService m_service;
    List<MenuVO> array= new ArrayList<>();
    Menu_Adapter menu_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_menu, container, false);

        StoreVO vo=(StoreVO) this.getArguments().getSerializable("StoreVO");

        list=view.findViewById(R.id.menu_list);
        list.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        m_service=retrofit.create(MenuRemoteService.class);

        System.out.println(vo.getS_code());
        Call<List<MenuVO>> call=m_service.list(vo.getS_code());
        call.enqueue(new Callback<List<MenuVO>>() {
            @Override
            public void onResponse(Call<List<MenuVO>> call, Response<List<MenuVO>> response) {
                array=response.body();
            }

            @Override
            public void onFailure(Call<List<MenuVO>> call, Throwable t) {
                System.out.println(t.toString()+"오류.............................");
            }
        });

        menu_adapter=new Menu_Adapter(getContext(),array);
        list.setAdapter(menu_adapter);
        return view;
    }

}