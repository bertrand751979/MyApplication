package com.example.myapplication.fragments;

import static com.example.myapplication.activities.LoginActivity.BOOK_EXTRA;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DisplayBookFragmentViewModel;
import com.example.myapplication.InterfaceEditSearchText;
import com.example.myapplication.MyAlertDialogFragment;
import com.example.myapplication.OnClickedAddButtonAction;
import com.example.myapplication.OnMatCardClickedAction;
import com.example.myapplication.R;
import com.example.myapplication.activities.ScrollingActivity;
import com.example.myapplication.adapters.BookAdapter;
import com.example.myapplication.models.Item;
import com.example.myapplication.repositories.RepositoryBook;

import java.util.ArrayList;
import java.util.List;

public class DisplayBookFragment extends Fragment {
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private ArrayList<Item>listBook = new ArrayList<>();
    public DisplayBookFragmentViewModel displayBookFragmentViewModel;
    private ArrayList<Item> returnDescription = new ArrayList<>();
    private InterfaceEditSearchText interfaceEditSearchText;
    public MenuItem itemYear;
    public  MenuItem itemMenu;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listBook = RepositoryBook.getInstance().bookList;
        displayBookFragmentViewModel = new ViewModelProvider(this).get(DisplayBookFragmentViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_display_books, container, false);    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewDisplayBooks);
        setViewItem();
    }

    public void setViewItem(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        OnClickedAddButtonAction onClickedAddButtonAction = new OnClickedAddButtonAction() {
            @Override
            public void addToFavorite(Item item) {
                displayBookFragmentViewModel.addToFavorite(item,getContext());
            }
        };
        OnMatCardClickedAction onMatCardClickedAction = new OnMatCardClickedAction() {
            @Override
            public void goToDescription(Item item) {
                Intent intent = new Intent(DisplayBookFragment.this.getContext(), ScrollingActivity.class);
                intent.putExtra(BOOK_EXTRA, item);
                startActivity(intent);
                Toast.makeText(DisplayBookFragment.this.getContext(), "Vers Description", Toast.LENGTH_SHORT).show();
            }
        };

        interfaceEditSearchText = new InterfaceEditSearchText() {
            @Override
            public void displaySearchChoice(String textEdit) {
                if(RepositoryBook.getInstance().isInTheList(textEdit)){
                    bookAdapter.setListAdapter(RepositoryBook.getInstance().mySearchChoiceList);
                }else{
                    bookAdapter.setListAdapter(RepositoryBook.getInstance().bookList);
                }
            }
        };
        bookAdapter = new BookAdapter(listBook,onClickedAddButtonAction,onMatCardClickedAction);
        recyclerView.setAdapter(bookAdapter);
        displayBookFragmentViewModel.itemLiveData.observe(getViewLifecycleOwner(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                bookAdapter.setListAdapter(new ArrayList<>(items));
                returnDescription= (ArrayList<Item>) items;
            }
        });
        displayBookFragmentViewModel.toPostMyItemsList();
        displayBookFragmentViewModel.getFavoriteList(getContext()).observe(getViewLifecycleOwner(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                RepositoryBook.getInstance().favList= (ArrayList<Item>) items;
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
        itemMenu=menu.findItem(R.id.menu_icon);
        itemYear = menu.findItem(R.id.search_year);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_icon:
                return (true);

            case R.id.search_year:
                showAlertDialog();
                return (true);

        }
        return (super.onOptionsItemSelected(item));
    }

    private void showAlertDialog() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        MyAlertDialogFragment alertDialog = MyAlertDialogFragment.newInstance("Some title");
        //j'ai besoin de cette interface que j'ai declaré dans le MyAlaertDialog en creant son setter
        alertDialog.setInterfaceEditSearchText(interfaceEditSearchText);
        alertDialog.show(fm, "fragment_alert");
    }











}
