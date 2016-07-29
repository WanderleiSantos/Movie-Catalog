package com.wanderlei.moviecatalog.presenter;

import com.wanderlei.moviecatalog.model.api.PersonApi;
import com.wanderlei.moviecatalog.view.PersonView;

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
}
