package com.example.tripkuy.createtrip;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tripkuy.R;
import com.example.tripkuy.registration.GenderFragment;
import com.example.tripkuy.registration.PickDestinationFragment;
import com.example.tripkuy.registration.UsiaFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class CreateTripPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public CreateTripPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    public static Fragment launcFragment(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new PenginapanFragment();
                break;
            case 1:
                fragment = new PengunjungFragment();
                break;
            case 2:
                fragment = new ActivityFragment();
                break;
        }
        return fragment;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new PenginapanFragment();
                break;
            case 1:
                fragment = new PengunjungFragment();
                break;
            case 2:
                fragment = new ActivityFragment();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "1";

            case 1:
                return "2";

            case 2:
                return "3";
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}