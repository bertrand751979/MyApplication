package com.example.myapplication;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.models.Item;
import com.example.myapplication.repositories.RepositoryBook;

import java.util.List;

public class DisplayFavoryFragmentViewModel extends ViewModel {
    public LiveData<List<Item>> getFavoriteList(Context context){
        return RepositoryBook.getInstance().getFavoriteBooks(context);
    }

    public void deleteBookFavorir(Item item, Context context){
        RepositoryBook.getInstance().delete(item, context);
    }


}
