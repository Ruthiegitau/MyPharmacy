package com.pato.mypharmacy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pato.mypharmacy.R;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.pato.mypharmacy.R;
import com.pato.mypharmacy.adapters.PharmacyPagerAdapter;
import com.pato.mypharmacy.models.Pharmacy;
import com.pato.mypharmacy.adapters.PharmacyPagerAdapter;
import com.pato.mypharmacy.models.Pharmacy;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class PharmacyDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private PharmacyPagerAdapter adapterViewPager;
    ArrayList<Pharmacy> mPharmacy = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_detail);
        ButterKnife.bind(this);

        mPharmacy = Parcels.unwrap(getIntent().getParcelableExtra("pharmacy"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new PharmacyPagerAdapter(getSupportFragmentManager(), mPharmacy);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}