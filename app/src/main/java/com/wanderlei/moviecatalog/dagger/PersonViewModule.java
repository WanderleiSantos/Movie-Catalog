package com.wanderlei.moviecatalog.dagger;

import com.wanderlei.moviecatalog.model.api.PersonApi;
import com.wanderlei.moviecatalog.model.entity.Person;
import com.wanderlei.moviecatalog.presenter.PersonPresenter;
import com.wanderlei.moviecatalog.view.PersonView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Wanderlei Santos on 28/07/2016.
 */
@Module(injects = Person.class, library = true, includes = {AppModule.class, ApiModule.class})
public class PersonViewModule {

    private PersonView personView;

    public PersonViewModule(PersonView personView) {
        this.personView = personView;
    }

    @Provides
    public PersonPresenter providePersonPresenter(PersonApi api){
        return new PersonPresenter(personView, api);
    }
}
