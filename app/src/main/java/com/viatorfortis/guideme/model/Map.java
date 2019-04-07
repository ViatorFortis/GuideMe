package com.viatorfortis.guideme.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Map implements Parcelable{

    @SerializedName("route")
    @Expose
    private String route;

    @SerializedName("bounds")
    @Expose
    private String bounds;

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getBounds() {
        return bounds;
    }

    public void setBounds(String bounds) {
        this.bounds = bounds;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(route);
        dest.writeString(bounds);
    }

    private Map(Parcel in) {
        route = in.readString();
        bounds = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Map> CREATOR = new Creator<Map>() {
        @Override
        public Map createFromParcel(Parcel source) {
            return new Map(source);
        }

        @Override
        public Map[] newArray(int size) {
            return new Map[size];
        }
    };
}