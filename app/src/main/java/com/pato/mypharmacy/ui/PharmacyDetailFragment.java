package com.pato.mypharmacy.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pato.mypharmacy.R;
import com.pato.mypharmacy.models.Pharmacy;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;



public class PharmacyDetailFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;
    @Bind(R.id.pharmacyImageView) ImageView mImageLabel;
    @Bind(R.id.pharmacyNameTextView) TextView mNameLabel;
    @Bind(R.id.categoriesTextView) TextView mCategoriesLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.savePharmacyButton) TextView mSavePharmacyButton;

    private Pharmacy mPharmacy;

    public static PharmacyDetailFragment newInstance(Pharmacy pharmacy) {
        PharmacyDetailFragment pharmacyDetailFragment = new PharmacyDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("pharmacy", Parcels.wrap(pharmacy));
        pharmacyDetailFragment.setArguments(args);
        return pharmacyDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPharmacy = Parcels.unwrap(getArguments().getParcelable("pharmacy"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pharmacy_detail, container, false);
        ButterKnife.bind(this, view);
        ButterKnife.bind(this, view);
        Picasso.with(view.getContext())
                .load(mPharmacy.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);

        Picasso.with(view.getContext()).load(mPharmacy.getImageUrl()).into(mImageLabel);

        mNameLabel.setText(mPharmacy.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", mPharmacy.getCategories()));
        mRatingLabel.setText(Double.toString(mPharmacy.getRating()) + "/5");
        mPhoneLabel.setText(mPharmacy.getPhone());
        mAddressLabel.setText(android.text.TextUtils.join(", ", mPharmacy.getAddress()));

        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);

        return view;


    }

    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mPharmacy.getWebsite()));
            startActivity(webIntent);
        }
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mPharmacy.getPhone()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + mPharmacy.getLatitude()
                            + "," + mPharmacy.getLongitude()
                            + "?q=(" + mPharmacy.getName() + ")"));
            startActivity(mapIntent);
        }
    }
}
