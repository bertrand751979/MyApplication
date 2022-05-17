package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Item;
import com.example.myapplication.viewHolders.FavoryViewHolder;

import java.util.ArrayList;

public class FavoriAdapter extends RecyclerView.Adapter<FavoryViewHolder> {

    public interface BookFavoriteAdapterInteface {
        void delete(Item item);
    }

    private ArrayList<Item> listFavoriteAdapter = new ArrayList<>();
    private BookFavoriteAdapterInteface bookFavoriteAdapterInteface;

    public FavoriAdapter(BookFavoriteAdapterInteface bookFavoriteAdapterInteface) {
        this.bookFavoriteAdapterInteface = bookFavoriteAdapterInteface;
    }

    public void setListFavoriteAdapter(ArrayList<Item> listFavoriteAdapter) {
        this.listFavoriteAdapter = listFavoriteAdapter;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.raw_favory,parent,false);
        return new FavoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoryViewHolder holder, int position) {
        holder.bind(listFavoriteAdapter.get(position),bookFavoriteAdapterInteface);
    }

    @Override
    public int getItemCount() {
        return listFavoriteAdapter.size();
    }






}
