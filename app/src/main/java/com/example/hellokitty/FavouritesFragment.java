package com.example.hellokitty;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FavouritesFragment extends Fragment {

    private RecyclerView recyclerView;

    public FavouritesFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favourites_fragment, container, false);
        recyclerView = view.findViewById(R.id.favouritesList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        FavouritesFragmentAdapter adapter = new FavouritesFragmentAdapter(getActivity(), MainActivity.favouriteList);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
