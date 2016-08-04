package com.wanderlei.moviecatalog.model.api;

/**
 * Created by Wanderlei Santos on 28/07/2016.
 */
public interface PersonApi extends AsyncService {

    void findById(Long id);

    void findMovies(Long id);

    void findGalery(Long id);

}
