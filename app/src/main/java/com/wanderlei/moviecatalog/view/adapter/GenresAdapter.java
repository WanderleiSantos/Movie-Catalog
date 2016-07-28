package com.wanderlei.moviecatalog.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wanderlei.moviecatalog.R;
import com.wanderlei.moviecatalog.model.entity.Genre;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wanderlei Santos on 25/07/2016.
 */
public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> implements View.OnClickListener {

    private List<Genre> genreList;
    private OnItemClickListener<Genre> genreOnItemClickListener;

    public GenresAdapter(List<Genre> genreList, OnItemClickListener<Genre> genreOnItemClickListener) {
        this.genreList = genreList;
        this.genreOnItemClickListener = genreOnItemClickListener;
    }

    @Override
    public GenresAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card_genres, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GenresAdapter.ViewHolder holder, int position) {
        Genre genre = genreList.get(position);
        holder.itemView.setTag(genre);
        holder.textViewCategoria.setText(genre.getName());
        holder.getTextViewAbv.setText(genre.getName().substring(0, 2));
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    @Override
    public void onClick(View view) {
        Genre genre = (Genre) view.getTag();
        genreOnItemClickListener.onClick(genre);

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.text_view_categoria)
        TextView textViewCategoria;

        @Bind(R.id.text_view_abv)
        TextView getTextViewAbv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
