package com.pato.mypharmacy;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PharmacyActivity extends AppCompatActivity {
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView) ListView mListView;
    private String [] pharmacys = new String[] {"Brunet", "Costco", "DRUGStore Pharmacy", "Familiprix", "Jean Coutu", "Lawtons", "PharmaChoice", "Pharmaprix", "Pharmasave", "Proxim", "Rexall", "Uniprix", "Value Drug Mart", "Walmart"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy);
        ButterKnife.bind(this);

        mListView = (ListView) findViewById(R.id.listView);
        mLocationTextView = (TextView) findViewById(R.id.locationTextView);


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pharmacys);
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String pharmacys = ((TextView)view).getText().toString();
                Toast.makeText(PharmacyActivity.this, pharmacys, Toast.LENGTH_LONG).show();
            }
        });


        Intent Intent= getIntent();
        String location = Intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the pharmacies near: " + location);

    }
}
