package com.wanderlei.moviecatalog.dagger;

import com.wanderlei.moviecatalog.model.api.PersonApi;
import com.wanderlei.moviecatalog.presenter.PersonPresenter;
import com.wanderlei.moviecatalog.view.PersonView;
import com.wanderlei.moviecatalog.view.activity.PersonActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Wanderlei Santos on 28/07/2016.
 */
@Module(injects = PersonActivity.class, includes = {AppModule.class, ApiModule.class}, library = true)
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
