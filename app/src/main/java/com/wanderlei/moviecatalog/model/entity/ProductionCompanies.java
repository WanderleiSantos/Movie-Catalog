package com.wanderlei.moviecatalog.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wanderlei on 03/03/16.
 */
public class ProductionCompanies implements Parcelable {

    private Integer id;

    private String name;

    public ProductionCompanies(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        dest.writeValue(this.id);
        dest.writeString(this.name);
    }

    protected ProductionCompanies(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
    }

    public static final Creator<ProductionCompanies> CREATOR = new Creator<ProductionCompanies>() {
        public ProductionCompanies createFromParcel(Parcel source) {
            return new ProductionCompanies(source);
        }

        public ProductionCompanies[] newArray(int size) {
            return new ProductionCompanies[size];
        }
    };
}
