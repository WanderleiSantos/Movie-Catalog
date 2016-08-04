package com.wanderlei.moviecatalog.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wanderlei.moviecatalog.R;
import com.wanderlei.moviecatalog.model.entity.Image;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wanderlei Santos on 03/08/2016.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> implements View.OnClickListener {

    private List<Image> imageList;
    private OnItemClickListener<Image> imageOnItemClickListener;

    public ImageAdapter(List<Image> imageList, OnItemClickListener<Image> imageOnItemClickListener) {
        this.imageList = imageList;
        this.imageOnItemClickListener = imageOnItemClickListener;
    }



    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card_galery, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ImageAdapter.ViewHolder holder, int position) {
        Image image = imageList.get(position);
        holder.itemView.setTag(image);

        if (image.getFile_path() != null){
            holder.progressBar.setVisibility(View.VISIBLE);

            Picasso.with(holder.imageView.getContext())
                    .load(holder.imageView.getContext().getString(R.string.base_url_img_logo500) + image.getFile_path())
                    .placeholder(R.drawable.noimagemovie)
                    .into(holder.imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            holder.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            holder.progressBar.setVisibility(View.GONE);
                        }
                    });

        } else {
            holder.imageView.setImageDrawable(holder.imageView.getContext().getResources().getDrawable(R.drawable.noimagemovie));
        }
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    @Override
    public void onClick(View view) {
        Image image = (Image) view.getTag();
        imageOnItemClickListener.onClick(image);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_galery)
        ImageView imageView;

        @Bind(R.id.progressbar)
        ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
