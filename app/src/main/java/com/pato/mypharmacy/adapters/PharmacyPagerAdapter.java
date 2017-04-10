package com.pato.mypharmacy.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pato.mypharmacy.models.Pharmacy;
import com.pato.mypharmacy.ui.PharmacyDetailFragment;


import java.util.ArrayList;

public class PharmacyPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Pharmacy> mPharmacy;

    public PharmacyPagerAdapter(FragmentManager fm, ArrayList<Pharmacy> pharmacy) {
        super(fm);
        mPharmacy = pharmacy;
    }


    @Override
    public int getCount() {
        return mPharmacy.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPharmacy.get(position).getName();
    }

    @Override
    public Fragment getItem(int position) {
        return PharmacyDetailFragment.newInstance(mPharmacy.get(position));
    }
}