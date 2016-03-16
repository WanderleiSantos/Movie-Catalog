package com.wanderlei.moviecatalog.view.adapter;

import android.content.Context;
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
import com.wanderlei.moviecatalog.model.entity.Cast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wanderlei on 12/03/16.
 */
public class ActorsAdapter extends RecyclerView.Adapter<ActorsAdapter.ViewHolder> implements View.OnClickListener{

    private List<Cast> castList;
    private OnItemClickListener<Cast> castOnItemClickListener;


    public ActorsAdapter(List<Cast> castList, OnItemClickListener<Cast> castOnItemClickListener) {
        this.castList = castList;
        this.castOnItemClickListener = castOnItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card_actors, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Cast cast = castList.get(position);
        holder.itemView.setTag(cast);
        holder.name_actor.setText(cast.getName());
        if (cast.getProfile_path() != null){
            holder.progressBar.setVisibility(View.VISIBLE);

            Picasso.with(holder.img_actor.getContext())
                    .load(holder.img_actor.getContext().getString(R.string.base_url_img__logo185) + cast.getProfile_path())
                    .placeholder(R.drawable.noimagemovie)
                    .into(holder.img_actor, new Callback() {
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
            holder.img_actor.setImageDrawable(holder.img_actor.getContext().getResources().getDrawable(R.drawable.noimagemovie));
        }
    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    @Override
    public void onClick(View v) {
        Cast cast = (Cast) v.getTag();
        castOnItemClickListener.onClick(cast);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.img_actor)
        ImageView img_actor;

        @Bind(R.id.name_actor)
        TextView name_actor;

        @Bind(R.id.progressbar)
        ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
