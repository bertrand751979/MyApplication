package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.OnMatCardClickedAction;
import com.example.myapplication.viewHolders.BookViewHolder;
import com.example.myapplication.OnClickedAddButtonAction;
import com.example.myapplication.R;
import com.example.myapplication.models.Item;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {
    private ArrayList<Item> listAdapter =new ArrayList<>();
    private OnClickedAddButtonAction onClickedAddButtonAction;
    private OnMatCardClickedAction onMatCardClickedAction;

    public BookAdapter(ArrayList<Item> listAdapter, OnClickedAddButtonAction onClickedAddButtonAction, OnMatCardClickedAction onMatCardClickedAction) {
        this.listAdapter = listAdapter;
        this.onClickedAddButtonAction = onClickedAddButtonAction;
        this.onMatCardClickedAction = onMatCardClickedAction;
    }

    public void setListAdapter(ArrayList<Item> listAdapter) {
        this.listAdapter = listAdapter;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.raw_books,parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.bind(listAdapter.get(position),onClickedAddButtonAction, onMatCardClickedAction);
    }

    @Override
    public int getItemCount() {
        return listAdapter.size();
    }
}
