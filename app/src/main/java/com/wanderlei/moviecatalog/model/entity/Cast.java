package com.wanderlei.moviecatalog.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wanderlei on 14/03/16.
 */
public class Cast implements Parcelable{

    private Integer cast_id;
    private String character;
    private String credit_id;
    private Integer id;
    private String name;
    private Integer order;
    private String profile_path;

    public Cast(Integer cast_id, String character, String credit_id, Integer id, String name, Integer order, String profile_path) {
        this.cast_id = cast_id;
        this.character = character;
        this.credit_id = credit_id;
        this.id = id;
        this.name = name;
        this.order = order;
        this.profile_path = profile_path;
    }

    public Integer getCast_id() {
        return cast_id;
    }

    public void setCast_id(Integer cast_id) {
        this.cast_id = cast_id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(String credit_id) {
        this.credit_id = credit_id;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.cast_id);
        dest.writeString(this.character);
        dest.writeString(this.credit_id);
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeValue(this.order);
        dest.writeString(this.profile_path);
    }

    protected Cast(Parcel in) {
        this.cast_id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.character = in.readString();
        this.credit_id = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.order = (Integer) in.readValue(Integer.class.getClassLoader());
        this.profile_path = in.readString();
    }

    public static final Creator<Cast> CREATOR = new Creator<Cast>() {
        public Cast createFromParcel(Parcel source) {
            return new Cast(source);
        }

        public Cast[] newArray(int size) {
            return new Cast[size];
        }
    };
}
