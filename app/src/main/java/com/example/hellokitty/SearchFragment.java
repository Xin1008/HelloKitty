package com.example.hellokitty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;

    public SearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        recyclerView = view.findViewById(R.id.search_result);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        final EditText searchInput = view.findViewById(R.id.search_input);
        Button searchButton = view.findViewById(R.id.search_btn);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View myView) {
                String kittyName = searchInput.getText().toString();

                // set up the response listener
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        String back = response;
                        Cat[] catArray = gson.fromJson(back, Cat[].class);
                        List<Cat> catList = Arrays.asList(catArray);
                        if (catList.size() == 0)
                            Snackbar.make(getView(), "😿 not found", Snackbar.LENGTH_LONG).show();
                        SearchFragmentAdapter searchFragmentAdapter = new SearchFragmentAdapter(getActivity(), catList);
                        recyclerView.setAdapter(searchFragmentAdapter);
                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Snackbar.make(getView(), "Connection failed", Snackbar.LENGTH_LONG).show();
                    }
                };

                // send the API request
                String path = "https://api.thecatapi.com/v1/breeds/search?q=" + kittyName;
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                StringRequest stringRequest = new StringRequest(Request.Method.GET, path, responseListener, errorListener);
                requestQueue.add(stringRequest);

            }
        });

        return view;

    }

}
