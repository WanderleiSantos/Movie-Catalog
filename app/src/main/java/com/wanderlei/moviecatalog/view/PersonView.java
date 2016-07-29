package com.wanderlei.moviecatalog.view;

import com.wanderlei.moviecatalog.model.entity.Person;

/**
 * Created by Wanderlei Santos on 28/07/2016.
 */
public interface PersonView {

    void showLoading();

    void closeLoading();

    void NotLoadPerson();

    void showPerson(Person person);
}
