package com.wanderlei.moviecatalog.presenter;

import com.wanderlei.moviecatalog.model.api.PersonApi;
import com.wanderlei.moviecatalog.model.api.asynctask.ApiResultListner;
import com.wanderlei.moviecatalog.model.entity.Movie;
import com.wanderlei.moviecatalog.model.entity.Person;
import com.wanderlei.moviecatalog.view.PersonView;

import java.util.List;

/**
 * Created by Wanderlei Santos on 28/07/2016.
 */
public class PersonPresenter {

    private PersonView personView;
    private PersonApi api;

    public PersonPresenter(PersonView personView, PersonApi api) {
        this.personView = personView;
        this.api = api;
    }

    public void getPersonById(Long id){
        personView.showLoading();
        api.setServiceApiResultListner(new ApiResultListner() {
            @Override
            public void onResult(Object object) {
                personView.showPerson((Person) object);
                personView.closeLoading();
            }

            @Override
            public void onExecption(Exception exception) {
                personView.closeLoading();
                personView.NotLoadPerson();
            }
        });

        api.findById(id);
    }

    public void getMovies(Long id){
        personView.showLoading();
        api.setServiceApiResultListner(new ApiResultListner() {
            @Override
            public void onResult(Object object) {
                personView.showMovies((List<Movie>) object);
                personView.closeLoading();
            }

            @Override
            public void onExecption(Exception exception) {
                personView.closeLoading();
                personView.NotLoadPerson();
            }
        });

        api.findMovies(id);
    }
}
