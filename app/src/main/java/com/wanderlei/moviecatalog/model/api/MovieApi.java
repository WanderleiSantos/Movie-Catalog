package com.wanderlei.moviecatalog.model.api;

/**
 * Created by wanderlei on 05/03/16.
 */
public interface MovieApi extends AsyncService {

    void getInTheaters(String primary_release_date_gte, String primary_release_date_lte);

    void getMovieCredits(Integer id);

    void getMovieById(Integer id);
}
