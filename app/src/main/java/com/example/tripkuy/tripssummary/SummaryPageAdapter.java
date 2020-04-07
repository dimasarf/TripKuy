package com.example.tripkuy.tripssummary;

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
public class SummaryPageAdapter extends FragmentPagerAdapter {


    private final Context mContext;
    int numOfTabs;

    public SummaryPageAdapter(Context context, FragmentManager fm, int numOfTab) {
        super(fm);
        mContext = context;
        numOfTabs = numOfTab;
    }

    @Override
    public Fragment getItem(int position) {
        return TripSummaryFragment.newInstance(position);
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
        return numOfTabs;
    }
}