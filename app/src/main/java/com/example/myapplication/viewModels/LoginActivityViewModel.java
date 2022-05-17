package com.example.myapplication.viewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.myapplication.models.Item;
import com.example.myapplication.repositories.RepositoryBook;

import java.util.List;

public class LoginActivityViewModel extends ViewModel {
    public LiveData<List<Item>> getForBeginingFavList(Context context) {
        return RepositoryBook.getInstance().getFavoriteBooks(context);
    }

    public LiveData<List<Item>> getFavoriteItem(Context context){
        return RepositoryBook.getInstance().getFavoriteBooks(context);
    }

}
