package com.example.hajozatuser.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.hajozatuser.MyTab;

import java.util.ArrayList;

public class PagerAdapter  extends FragmentStatePagerAdapter {

    ArrayList<MyTab>tabs=new ArrayList<>(  );

    public PagerAdapter(@NonNull FragmentManager fm) {
        super( fm );
    }
    public  void addtab(MyTab tab){
        tabs.add( tab );
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return tabs.get(position).getFragment();
    }
    public CharSequence getPageTitle(int position){
        return tabs.get( position ).getTabname();
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

}
