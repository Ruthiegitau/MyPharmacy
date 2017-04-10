package com.pato.mypharmacy.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.pato.mypharmacy.R;
import com.pato.mypharmacy.models.Pharmacy;
import com.pato.mypharmacy.ui.PharmacyDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class PharmacyListAdapter extends RecyclerView.Adapter<PharmacyListAdapter.PharmacyViewHolder> {
    private ArrayList<Pharmacy> mPharmacy = new ArrayList<>();
    private Context mContext;

    public PharmacyListAdapter(Context context, ArrayList<Pharmacy> pharmacy) {
        mContext = context;
        mPharmacy = pharmacy;
    }

    @Override
    public PharmacyListAdapter.PharmacyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pharmacy_list_item, parent, false);
        PharmacyViewHolder viewHolder = new PharmacyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PharmacyListAdapter.PharmacyViewHolder holder, int position) {
        holder.bindPharmacy(mPharmacy.get(position));
    }

    @Override
    public int getItemCount() {
        return mPharmacy.size();
    }

    public class PharmacyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.pharmacyImageView) ImageView mPharmacyImageView;
        @Bind(R.id.pharmacyNameTextView) TextView mNameTextView;
        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;
        private Context mContext;

        public PharmacyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, PharmacyDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("pharmacy", Parcels.wrap(mPharmacy));
            mContext.startActivity(intent);
        }

        public void bindPharmacy(Pharmacy pharmacy) {
            mNameTextView.setText(pharmacy.getName());
            mCategoryTextView.setText(pharmacy.getCategories().get(0));
            mRatingTextView.setText("Rating: " + pharmacy.getRating() + "/5");
        }
    }
}
