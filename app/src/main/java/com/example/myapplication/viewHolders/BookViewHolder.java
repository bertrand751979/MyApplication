package com.example.myapplication.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.OnClickedAddButtonAction;
import com.example.myapplication.OnMatCardClickedAction;
import com.example.myapplication.R;
import com.example.myapplication.models.Item;
import com.example.myapplication.repositories.RepositoryBook;
import com.google.android.material.card.MaterialCardView;

public class BookViewHolder extends RecyclerView.ViewHolder {
    private ImageView vhPicture;
    private ImageView vhBtnTofavorite;
    private TextView vhAuthor;
    private TextView vhTitle;
    private TextView vhYear;
    private TextView vhDescription;
    private MaterialCardView materialCardView;

    public BookViewHolder(@NonNull View view) {
        super(view);
        vhPicture = view.findViewById(R.id.rawPicture);
        vhBtnTofavorite = view.findViewById(R.id.rawAddToFavory);
        vhAuthor = view.findViewById(R.id.rawAuthor);
        vhTitle = view.findViewById(R.id.rawTitle);
        vhYear = view.findViewById(R.id.rawYear);
        vhDescription = view.findViewById(R.id.rawDescription);
        materialCardView = view.findViewById(R.id.matCard);
    }

    public ImageView getVhPicture() {
        return vhPicture;
    }

    public void setVhPicture(ImageView vhPicture) {
        this.vhPicture = vhPicture;
    }

    public ImageView getVhBtnTofavorite() {
        return vhBtnTofavorite;
    }

    public void setVhBtnTofavorite(ImageView vhBtnTofavorite) {
        this.vhBtnTofavorite = vhBtnTofavorite;
    }

    public TextView getVhAuthor() {
        return vhAuthor;
    }

    public void setVhAuthor(TextView vhAuthor) {
        this.vhAuthor = vhAuthor;
    }

    public TextView getVhTitle() {
        return vhTitle;
    }

    public void setVhTitle(TextView vhTitle) {
        this.vhTitle = vhTitle;
    }

    public TextView getVhYear() {
        return vhYear;
    }

    public void setVhYear(TextView vhYear) {
        this.vhYear = vhYear;
    }

    public TextView getVhDescription() {
        return vhDescription;
    }

    public void setVhDescription(TextView vhDescription) {
        this.vhDescription = vhDescription;
    }

    public MaterialCardView getMaterialCardView() {
        return materialCardView;
    }

    public void setMaterialCardView(MaterialCardView materialCardView) {
        this.materialCardView = materialCardView;
    }

    public void bind(Item item, OnClickedAddButtonAction onClickedAddButtonAction, OnMatCardClickedAction onMatCardClickedAction){
        vhBtnTofavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickedAddButtonAction.addToFavorite(item);
            }
        });
        materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMatCardClickedAction.goToDescription(item);
            }
        });
        vhAuthor.setText(item.getVolumeInfo().getAuthors().get(0));
        vhTitle.setText(item.getVolumeInfo().getTitle());
        vhYear.setText(item.getVolumeInfo().getPublishedDate());
        vhDescription.setText(item.getVolumeInfo().getDescription());
        Glide.with(vhPicture.getContext())
                .load(item.getVolumeInfo().getImageLinks())
                .into(vhPicture);




        if (item.getVolumeInfo().getImageLinks() == null) {
            vhPicture.setImageResource(R.drawable.ic_error);
        } else {
            Glide.with(vhPicture.getContext())
                    .load(item.getVolumeInfo().getImageLinks().getSmallThumbnail())
                    .into(vhPicture);
        }

        if (RepositoryBook.getInstance().isFavorite(item)) {
            vhBtnTofavorite.setImageResource(R.drawable.ic_favorite_full);
        } else {
            vhBtnTofavorite.setImageResource(R.drawable.ic_favorite);
        }


        vhBtnTofavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(RepositoryBook.getInstance().isFavorite(item)){
                    vhBtnTofavorite.setImageResource(R.drawable.ic_error);
                }else{
                    onClickedAddButtonAction.addToFavorite(item);
                    vhBtnTofavorite.setImageResource(R.drawable.ic_favorite_full);
                }
            }
        });









    }



}
