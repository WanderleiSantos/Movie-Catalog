package com.wanderlei.moviecatalog.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wanderlei on 03/03/16.
 */
public class ProductionCountries implements Parcelable{

    private String iso_3166_1;

    private String name;

    public ProductionCountries(String iso_3166_1, String name) {
        this.iso_3166_1 = iso_3166_1;
        this.name = name;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
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
        dest.writeString(this.iso_3166_1);
        dest.writeString(this.name);
    }

    protected ProductionCountries(Parcel in) {
        this.iso_3166_1 = in.readString();
        this.name = in.readString();
    }

    public static final Creator<ProductionCountries> CREATOR = new Creator<ProductionCountries>() {
        public ProductionCountries createFromParcel(Parcel source) {
            return new ProductionCountries(source);
        }

        public ProductionCountries[] newArray(int size) {
            return new ProductionCountries[size];
        }
    };
}
