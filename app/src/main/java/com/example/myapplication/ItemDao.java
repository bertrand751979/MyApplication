package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.myapplication.models.Item;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM Item ")
    LiveData<List<Item>> getFavItems();

    @Insert
    void insertFavori(Item item);

    @Delete
    void deleteFavori(Item item);


}
