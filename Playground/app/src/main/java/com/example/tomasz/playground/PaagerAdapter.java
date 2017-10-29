package com.example.tomasz.playground;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by uczen on 2017-10-29.
 */

public class PaagerAdapter extends FragmentPagerAdapter {
    public PaagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Wragment.newInstance();
            case 1:
                return Hragment.newInstance();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
