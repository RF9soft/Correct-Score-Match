package com.correct.score.adapter;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.correct.score.fragment.Fragment_Free;
import com.correct.score.fragment.Fragment_More;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        final Fragment[] fragment = {null};

        if (position == 0) {
            fragment[0] = new Fragment_Free();
        } else if (position == 1) {
            fragment[0] = new Fragment_More();
        }
        return fragment[0];
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return "FREE";

        } else if (position == 1) {
            return "VIP";
        }
        return null;
    }

}
