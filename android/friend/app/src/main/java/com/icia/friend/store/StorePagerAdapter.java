package com.icia.friend.store;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.icia.friend.vo.StoreVO;

public class StorePagerAdapter extends FragmentPagerAdapter {
    int count_tab; //탭수
    StoreVO vo;
    public StorePagerAdapter(FragmentManager fm, int tabs, StoreVO vo) {
        super(fm);
        this.count_tab=tabs;
        this.vo=vo;
    }

    @Override
    public Fragment getItem(int position) {
        //정보 fragment에 vo전송
        Bundle bundle = new Bundle();
        bundle.putSerializable("StoreVO", vo);
        switch (position){
            case 0 :
                MenuFragment menu = new MenuFragment();
                menu.setArguments(bundle);
                return menu;
            case 1:
                InfoFragment info= new InfoFragment();
                info.setArguments(bundle);
                return info;
            case 2:
                ReviewFragment review= new ReviewFragment();
                return review;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return count_tab;
    }
}
