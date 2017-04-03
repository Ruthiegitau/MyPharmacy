package com.pato.mypharmacy;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by Pato on 03/04/2017.
 */

public class MyPharmacyArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String [] mPharmacys;
    private String [] mCategories;

    public MyPharmacyArrayAdapter(Context mContext, int resource, String [] mPharmacy, String[] mCategories){
        super(mContext, resource);
        this.mContext = mContext;
        this.mPharmacys = mPharmacy;
        this.mCategories = mCategories;
    }

    @Override
    public  Object getItem(int position){
        String Pharmacy = mPharmacys[position];
        String Categories = mCategories[position];
        return String.format("%s \nServes great: %s", Pharmacy, Categories);

    }

    @Override
    public int getCount(){
        return mPharmacys.length;
    }
}
