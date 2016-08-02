package com.wanderlei.moviecatalog.model.api;

/**
 * Created by wanderlei on 05/03/16.
 */
public interface MovieApi extends AsyncService {

    void getNowPlaying();

    void getMovieCredits(Integer id);

    void getMovieById(Integer id);

    void getPopular();

    void getUpComing();

    void getGenres();

    void getListMovieByGenre(Long id);

}
