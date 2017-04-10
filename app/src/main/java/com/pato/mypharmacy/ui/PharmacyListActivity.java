package com.pato.mypharmacy.ui;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.pato.mypharmacy.R;
import com.pato.mypharmacy.adapters.PharmacyListAdapter;
import com.pato.mypharmacy.models.Pharmacy;
import com.pato.mypharmacy.services.YelpService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PharmacyListActivity extends AppCompatActivity {
    public static final String TAG = PharmacyListActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private PharmacyListAdapter mAdapter;

    public ArrayList<Pharmacy> mPharmacy = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity_pharmacy);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        getPharmacy(location);
        Log.d(TAG,"HELLO");
    }

    private void getPharmacy(String location) {
        final YelpService yelpService = new YelpService();

        yelpService.findPharmacy(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) {
                mPharmacy = yelpService.processResults(response);

                PharmacyListActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new PharmacyListAdapter(getApplicationContext(), mPharmacy);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PharmacyListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        for(Pharmacy pharmacy : mPharmacy){
                            Log.d(TAG,pharmacy.getName());
                        }
                    }
                });
            }
        });
    }
}