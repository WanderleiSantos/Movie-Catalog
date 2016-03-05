package com.wanderlei.moviecatalog.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanderlei.moviecatalog.R;
import com.wanderlei.moviecatalog.model.entity.Movie;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wanderlei on 04/03/16.
 */
public class MovieInTheatersAdapter extends RecyclerView.Adapter<MovieInTheatersAdapter.ViewHolder> implements View.OnClickListener {

    private List<Movie> movieList;
    private OnItemClickListener<Movie> movieOnItemClickListener;

    public MovieInTheatersAdapter(List<Movie> movieList, OnItemClickListener<Movie> movieOnItemClickListener) {
        this.movieList = movieList;
        this.movieOnItemClickListener = movieOnItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card_listmovies, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.itemView.setTag(movie);

        //holder.title_movie.setText("teste 002");
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    @Override
    public void onClick(View v) {
        Movie movie = (Movie) v.getTag();
        movieOnItemClickListener.onClick(movie);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.img_movie)
        ImageView img_moview;

        @Bind(R.id.title_movie)
        TextView title_movie;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
