package com.wanderlei.moviecatalog.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wanderlei on 03/03/16.
 */
public class SpokenLanguages implements Parcelable {

    private String iso_639_1;

    private String name;

    public SpokenLanguages(String iso_639_1, String name) {
        this.iso_639_1 = iso_639_1;
        this.name = name;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.iso_639_1);
        dest.writeString(this.name);
    }

    protected SpokenLanguages(Parcel in) {
        this.iso_639_1 = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<SpokenLanguages> CREATOR = new Parcelable.Creator<SpokenLanguages>() {
        public SpokenLanguages createFromParcel(Parcel source) {
            return new SpokenLanguages(source);
        }

        public SpokenLanguages[] newArray(int size) {
            return new SpokenLanguages[size];
        }
    };
}
