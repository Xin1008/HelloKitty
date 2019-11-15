package com.example.hellokitty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class CatFragment extends Fragment {

    private String catID;
    private Cat cat;
    private TextView catName;
    private ImageView catImage;
    private TextView catWeight;
    private TextView catTemperament;
    private TextView catOrigin;
    private TextView catDescription;
    private TextView catLifeSpan;
    private TextView catWikipediaLink;
    private TextView dogFriendlinessLevel;
    private ImageButton starButton;

    public CatFragment() {
    }

    public void fetchImage(final View view, String catID) {

        // set up the response listener
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Image[] image = gson.fromJson(response, Image[].class);
                catImage = view.findViewById(R.id.catImage);
                if (image.length != 0) {
                    Glide.with(view.getContext()).load(image[0].getUrl()).into(catImage);
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(getView(), "Connection failed", Snackbar.LENGTH_LONG).show();
            }
        };

        // send the API request
        String path = "https://api.thecatapi.com/v1/images/search?breed_id=" + catID;
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest myRequestDetail = new StringRequest(Request.Method.GET, path, responseListener, errorListener);
        requestQueue.add(myRequestDetail);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cat_details, container, false);
        catName = view.findViewById(R.id.catName);
        catImage = view.findViewById(R.id.catImage);
        catWeight = view.findViewById(R.id.catWeight);
        catTemperament = view.findViewById(R.id.catTemperament);
        catOrigin = view.findViewById(R.id.catOrigin);
        catDescription = view.findViewById(R.id.catDescription);
        catLifeSpan = view.findViewById(R.id.catLifeSpan);
        catWikipediaLink = view.findViewById(R.id.catWikipediaLink);
        dogFriendlinessLevel = view.findViewById(R.id.dogFriendlinessLevel);
        starButton = view.findViewById(R.id.starButton);

        starButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View myView) {
                if (MainActivity.favouriteList.contains(cat)) {
                    MainActivity.favouriteList.remove(cat);
                } else {
                    MainActivity.favouriteList.add(cat);
                }
            }
        });

        Bundle bundle = getArguments();
        if (bundle != null && cat == null) {
            catID = bundle.getString("catID");
            cat = (Cat) bundle.getSerializable("catInfo");
            catName.setText(cat.getName());
            if (cat.getWeight() != null)
                catWeight.setText("Weight: " + cat.getWeight().getMetric());
            catTemperament.setText("Temperament: " + cat.getTemperament());
            catOrigin.setText("Origin: " + cat.getOrigin());
            catDescription.setText("Description: " + cat.getDescription());
            catLifeSpan.setText("Life Span: " + cat.getLife_span());
            catWikipediaLink.setText("Wikipedia Link: " + cat.getWikipedia_url());
            dogFriendlinessLevel.setText("Dog Friendliness Level: " + cat.getDogFriendlinessLevel());
            fetchImage(view, catID);
        }

        return view;

    }


}
