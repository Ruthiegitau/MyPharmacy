package com.pato.mypharmacy.services;

import com.pato.mypharmacy.models.Constants;
import com.pato.mypharmacy.models.Pharmacy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

public class YelpService {

    public static void findPharmacy(String location, Callback callback) {
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(Constants.YELP_CONSUMER_KEY, Constants.YELP_CONSUMER_SECRET);
        consumer.setTokenWithSecret(Constants.YELP_TOKEN, Constants.YELP_TOKEN_SECRET);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YELP_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.YELP_LOCATION_QUERY_PARAMETER, location);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Pharmacy> processResults(Response response) {
        ArrayList<Pharmacy> pharmacy = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject yelpJSON = new JSONObject(jsonData);
                JSONArray businessesJSON = yelpJSON.getJSONArray("businesses");
                for (int i = 0; i < businessesJSON.length(); i++) {
                    JSONObject pharmacyJSON = businessesJSON.getJSONObject(i);
                    String name = pharmacyJSON.getString("name");
                    String phone = pharmacyJSON.optString("display_phone", "Phone not available");
                    String website = pharmacyJSON.getString("url");
                    double rating = pharmacyJSON.getDouble("rating");
                    String imageUrl = pharmacyJSON.getString("image_url");
                    double latitude = pharmacyJSON.getJSONObject("location")
                            .getJSONObject("coordinate").getDouble("latitude");
                    double longitude = pharmacyJSON.getJSONObject("location")
                            .getJSONObject("coordinate").getDouble("longitude");
                    ArrayList<String> address = new ArrayList<>();
                    JSONArray addressJSON = pharmacyJSON.getJSONObject("location")
                            .getJSONArray("display_address");
                    for (int y = 0; y < addressJSON.length(); y++) {
                        address.add(addressJSON.get(y).toString());
                    }

                    ArrayList<String> categories = new ArrayList<>();
                    JSONArray categoriesJSON = pharmacyJSON.getJSONArray("categories");

                    for (int y = 0; y < categoriesJSON.length(); y++) {
                        categories.add(categoriesJSON.getJSONArray(y).get(0).toString());
                    }
                    Pharmacy pharmacies = new Pharmacy(name, phone, website, rating,
                            imageUrl, address, latitude, longitude, categories);
                    pharmacy.add(pharmacies);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pharmacy;
    }
}
