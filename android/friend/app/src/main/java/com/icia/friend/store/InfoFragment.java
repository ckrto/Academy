package com.icia.friend.store;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icia.friend.R;
import com.icia.friend.vo.StoreVO;


public class InfoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_info, container, false);
        StoreVO vo=(StoreVO) this.getArguments().getSerializable("StoreVO");
        System.out.println(vo.toString());
        return view;
    }
}