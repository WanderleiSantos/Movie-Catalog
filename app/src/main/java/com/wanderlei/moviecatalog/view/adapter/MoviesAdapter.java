package com.wanderlei.moviecatalog.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wanderlei.moviecatalog.R;
import com.wanderlei.moviecatalog.model.entity.Movie;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wanderlei Santos on 29/07/2016.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> implements View.OnClickListener {

    private List<Movie> movieList;
    private OnItemClickListener<Movie> movieOnItemClickListener;

    public MoviesAdapter(List<Movie> movieList, OnItemClickListener<Movie> movieOnItemClickListener) {
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.itemView.setTag(movie);
        holder.title_movie.setText(movie.getTitle());
        if (movie.getPoster_path() != null){
            holder.progressBar.setVisibility(View.VISIBLE);

            Picasso.with(holder.img_movie.getContext())
                    .load(holder.img_movie.getContext().getString(R.string.base_url_img__logo185) + movie.getPoster_path())
                    .placeholder(R.drawable.noimagemovie)
                    .into(holder.img_movie, new Callback() {
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
            holder.img_movie.setImageDrawable(holder.img_movie.getContext().getResources().getDrawable(R.drawable.noimagemovie));
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    @Override
    public void onClick(View view) {
        Movie movie = (Movie) view.getTag();
        movieOnItemClickListener.onClick(movie);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.img_movie)
        ImageView img_movie;

        @Bind(R.id.title_movie)
        TextView title_movie;

        @Bind(R.id.progressbar)
        ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
