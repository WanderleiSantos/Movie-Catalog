package com.wanderlei.moviecatalog.model.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntegerRes;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wanderlei on 03/03/16.
 */
public class Movie implements Parcelable {

    private Integer id;

    private String title;

    private String original_title;

    private String original_language;

    private String backdrop_path;

    private String poster_path;

    private String overview;

    private Date release_date;

    private String status;

    private String tagline;

    private Boolean adult;

    private Double popularity;

    private Integer revenue;

    private Integer runtime;

    private Integer vote_count;

    private Double vote_average;

    @SerializedName("genre_ids")
    private List<Integer> genreIdsList;

    @SerializedName("genres")
    private List<Genre> genreList;

    @SerializedName("production_companies")
    private List<ProductionCompanies> productionCompaniesList;

    @SerializedName("production_countries")
    private List<ProductionCountries> productionCountriesList;

    @SerializedName("spoken_languages")
    private List<SpokenLanguages> spokenLanguagesList;


    public Movie(Integer id, String title, String original_title, String original_language, String backdrop_path, String poster_path, String overview, Date release_date, String status, String tagline, Boolean adult, Double popularity, Integer revenue, Integer runtime, Integer vote_count, Double vote_average, List<Integer> genreIdsList, List<Genre> genreList, List<ProductionCompanies> productionCompaniesList, List<ProductionCountries> productionCountriesList, List<SpokenLanguages> spokenLanguagesList) {
        this.id = id;
        this.title = title;
        this.original_title = original_title;
        this.original_language = original_language;
        this.backdrop_path = backdrop_path;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
        this.status = status;
        this.tagline = tagline;
        this.adult = adult;
        this.popularity = popularity;
        this.revenue = revenue;
        this.runtime = runtime;
        this.vote_count = vote_count;
        this.vote_average = vote_average;
        this.genreIdsList = genreIdsList;
        this.genreList = genreList;
        this.productionCompaniesList = productionCompaniesList;
        this.productionCountriesList = productionCountriesList;
        this.spokenLanguagesList = spokenLanguagesList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public List<Integer> getGenreIdsList() {
        return genreIdsList;
    }

    public void setGenreIdsList(List<Integer> genreIdsList) {
        this.genreIdsList = genreIdsList;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public List<ProductionCompanies> getProductionCompaniesList() {
        return productionCompaniesList;
    }

    public void setProductionCompaniesList(List<ProductionCompanies> productionCompaniesList) {
        this.productionCompaniesList = productionCompaniesList;
    }

    public List<ProductionCountries> getProductionCountriesList() {
        return productionCountriesList;
    }

    public void setProductionCountriesList(List<ProductionCountries> productionCountriesList) {
        this.productionCountriesList = productionCountriesList;
    }

    public List<SpokenLanguages> getSpokenLanguagesList() {
        return spokenLanguagesList;
    }

    public void setSpokenLanguagesList(List<SpokenLanguages> spokenLanguagesList) {
        this.spokenLanguagesList = spokenLanguagesList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeString(this.original_title);
        dest.writeString(this.original_language);
        dest.writeString(this.backdrop_path);
        dest.writeString(this.poster_path);
        dest.writeString(this.overview);
        dest.writeLong(release_date != null ? release_date.getTime() : -1);
        dest.writeString(this.status);
        dest.writeString(this.tagline);
        dest.writeValue(this.adult);
        dest.writeValue(this.popularity);
        dest.writeValue(this.revenue);
        dest.writeValue(this.runtime);
        dest.writeValue(this.vote_count);
        dest.writeValue(this.vote_average);
        dest.writeList(this.genreIdsList);
        dest.writeTypedList(genreList);
        dest.writeTypedList(productionCompaniesList);
        dest.writeTypedList(productionCountriesList);
        dest.writeTypedList(spokenLanguagesList);
    }

    protected Movie(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.original_title = in.readString();
        this.original_language = in.readString();
        this.backdrop_path = in.readString();
        this.poster_path = in.readString();
        this.overview = in.readString();
        long tmpRelease_date = in.readLong();
        this.release_date = tmpRelease_date == -1 ? null : new Date(tmpRelease_date);
        this.status = in.readString();
        this.tagline = in.readString();
        this.adult = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.revenue = (Integer) in.readValue(Integer.class.getClassLoader());
        this.runtime = (Integer) in.readValue(Integer.class.getClassLoader());
        this.vote_count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.vote_average = (Double) in.readValue(Double.class.getClassLoader());
        this.genreIdsList = new ArrayList<Integer>();
        in.readList(this.genreIdsList, List.class.getClassLoader());
        this.genreList = in.createTypedArrayList(Genre.CREATOR);
        this.productionCompaniesList = in.createTypedArrayList(ProductionCompanies.CREATOR);
        this.productionCountriesList = in.createTypedArrayList(ProductionCountries.CREATOR);
        this.spokenLanguagesList = in.createTypedArrayList(SpokenLanguages.CREATOR);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
