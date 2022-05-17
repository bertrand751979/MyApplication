package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.MyBooksRetrofitApi;
import com.example.myapplication.R;
import com.example.myapplication.models.Item;
import com.example.myapplication.models.Root;
import com.example.myapplication.repositories.RepositoryBook;
import com.example.myapplication.viewModels.LoginActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button btnSearch;
    private EditText searchText;
    public static String BOOK_EXTRA = "book_extra";
    private ArrayList<Item> keepFav =new ArrayList<>();
    public LoginActivityViewModel viewModelAtBeginigFav;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        keepFav =RepositoryBook.getInstance().favList;
        viewModelAtBeginigFav = new ViewModelProvider(this).get(LoginActivityViewModel.class);
        searchText = findViewById(R.id.edit_query);
        btnSearch = findViewById(R.id.search_go_btn);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Recherche lancée", Toast.LENGTH_SHORT).show();
                callService();
            }
        });
        putListFavatBegining();
    }
    public void callService(){
        MyBooksRetrofitApi.MyBooksRetrofitService service = MyBooksRetrofitApi.getInstance().getClient().create(MyBooksRetrofitApi.MyBooksRetrofitService.class);
        Call<Root> call= service.getRoot(searchText.getText().toString());
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                updateView(response);
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Erreur", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateView(Response<Root> response) {
        if (response.body().getItems().size() > 0) {
            RepositoryBook.getInstance().bookList = (ArrayList<Item>) response.body().getItems();
            Log.e("La taille de la liste", String.valueOf(RepositoryBook.getInstance().bookList.size()));
            Toast.makeText(LoginActivity.this, "Reponse du serveur", Toast.LENGTH_SHORT).show();
        }
    }

    private void putListFavatBegining(){
        viewModelAtBeginigFav.getForBeginingFavList(this).observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                RepositoryBook.getInstance().favList= (ArrayList<Item>) items;
            }
        });
    }



}
