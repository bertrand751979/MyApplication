package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.DisplayFavoryFragmentViewModel;
import com.example.myapplication.R;
import com.example.myapplication.adapters.FavoriAdapter;
import com.example.myapplication.models.Item;
import com.example.myapplication.repositories.RepositoryBook;

import java.util.ArrayList;
import java.util.List;

public class DisplayFavoryFragment extends Fragment {
    private ArrayList<Item> displayFavoriteList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FavoriAdapter favoriteAdapter;
    public DisplayFavoryFragmentViewModel displayFavoryFragmentViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        displayFavoriteList= RepositoryBook.getInstance().getFavList();
        displayFavoryFragmentViewModel = new ViewModelProvider(this).get(DisplayFavoryFragmentViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favory_books, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView= view.findViewById(R.id.recyclerViewDisplayFavory);
        setViewItem();
    }


    private void setViewItem(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        favoriteAdapter = new FavoriAdapter(new FavoriAdapter.BookFavoriteAdapterInteface() {
            @Override
            public void delete(Item item) {
                displayFavoryFragmentViewModel.deleteBookFavorir(item,getContext());

            }
        });
        recyclerView.setAdapter(favoriteAdapter);

        displayFavoryFragmentViewModel.getFavoriteList(getContext()).observe(getViewLifecycleOwner(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                favoriteAdapter.setListFavoriteAdapter(new ArrayList<>(items));
                RepositoryBook.getInstance().favList = (ArrayList<Item>) items;
            }
        });
    }










}










