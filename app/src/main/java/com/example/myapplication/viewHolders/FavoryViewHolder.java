package com.example.myapplication.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.adapters.FavoriAdapter;
import com.example.myapplication.models.Item;

public class FavoryViewHolder extends RecyclerView.ViewHolder {
    private ImageView vhFavoryPicture;
    private ImageView vhFavoryBtnDelete;
    private TextView vhFavoryAuthor;
    private TextView vhFavoryTitle;
    private TextView vhFavoryYear;
    private TextView vhFavoryDescription;

    public FavoryViewHolder(@NonNull View view) {
        super(view);
        vhFavoryPicture = view.findViewById(R.id.rawFavoryPicture);
        vhFavoryBtnDelete = view.findViewById(R.id.rawFavoryDelete);
        vhFavoryAuthor = view.findViewById(R.id.rawFavoryAuthor);
        vhFavoryTitle = view.findViewById(R.id.rawFavoryTitle);
        vhFavoryYear = view.findViewById(R.id.rawFavoryYear);
        vhFavoryDescription = view.findViewById(R.id.rawFavoryDescription);
    }

    public ImageView getVhFavoryPicture() {
        return vhFavoryPicture;
    }

    public void setVhFavoryPicture(ImageView vhFavoryPicture) {
        this.vhFavoryPicture = vhFavoryPicture;
    }

    public ImageView getVhFavoryBtnDelete() {
        return vhFavoryBtnDelete;
    }

    public void setVhFavoryBtnDelete(ImageView vhFavoryBtnDelete) {
        this.vhFavoryBtnDelete = vhFavoryBtnDelete;
    }

    public TextView getVhFavoryAuthor() {
        return vhFavoryAuthor;
    }

    public void setVhFavoryAuthor(TextView vhFavoryAuthor) {
        this.vhFavoryAuthor = vhFavoryAuthor;
    }

    public TextView getVhFavoryTitle() {
        return vhFavoryTitle;
    }

    public void setVhFavoryTitle(TextView vhFavoryTitle) {
        this.vhFavoryTitle = vhFavoryTitle;
    }

    public TextView getVhFavoryYear() {
        return vhFavoryYear;
    }

    public void setVhFavoryYear(TextView vhFavoryYear) {
        this.vhFavoryYear = vhFavoryYear;
    }

    public TextView getVhFavoryDescription() {
        return vhFavoryDescription;
    }

    public void setVhFavoryDescription(TextView vhFavoryDescription) {
        this.vhFavoryDescription = vhFavoryDescription;
    }

    public void bind(Item item, FavoriAdapter.BookFavoriteAdapterInteface favoriAdapterInteface ){
        vhFavoryAuthor.setText(item.getVolumeInfo().getAuthors().get(0));
        vhFavoryTitle.setText(item.getVolumeInfo().getTitle());
        vhFavoryYear.setText(item.getVolumeInfo().getPublishedDate());
        vhFavoryDescription.setText(item.getVolumeInfo().getDescription());
        if(item.getVolumeInfo().imageLinks!=null){
            Glide.with(vhFavoryPicture.getContext())
                .load(item.getVolumeInfo().getImageLinks())
                .into(vhFavoryPicture);
        }
        else{
            vhFavoryPicture.setImageResource(R.drawable.ic_error);

        }
        vhFavoryBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favoriAdapterInteface.delete(item);
            }
        });
    }
}
