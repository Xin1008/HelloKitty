package com.example.hellokitty;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class FavouritesFragmentAdapter extends RecyclerView.Adapter<FavouritesFragmentAdapter.FavouritesViewHolder> {

    public Context context;
    public List<Cat> catList;

    public FavouritesFragmentAdapter(Context context, List<Cat> catList) {
        this.context = context;
        this.catList = catList;
    }

    @NonNull
    @Override
    public FavouritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_list, parent, false);
        FavouritesViewHolder holder = new FavouritesFragmentAdapter.FavouritesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesFragmentAdapter.FavouritesViewHolder holder, final int position) {
        holder.catTextView.setText(catList.get(position).getName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View myView) {
                String catID = catList.get(position).getId();
                Bundle bundle = new Bundle();
                bundle.putString("catID", catID);
                bundle.putSerializable("catInfo", catList.get(position));
                CatFragment catFragment = new CatFragment();
                catFragment.setArguments(bundle);
                AppCompatActivity activity = (AppCompatActivity) context;
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.main, catFragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public class FavouritesViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout layout;
        TextView catTextView;

        public FavouritesViewHolder(View view) {
            super(view);
            layout = view.findViewById(R.id.cat_list_layout);
            catTextView = view.findViewById(R.id.catTextView);
        }

    }

}
