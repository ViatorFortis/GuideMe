package com.viatorfortis.guideme.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Playback implements Parcelable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("order")
    @Expose
    private List<String> order;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getOrder() {
        return order;
    }

    public void setOrder(List<String> order) {
        this.order = order;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeStringList(order);
    }

    private Playback (Parcel in) {
        type = in.readString();
        order = in.createStringArrayList();
    }

    public static final Creator<Playback> CREATOR = new Creator<Playback>() {
        @Override
        public Playback createFromParcel(Parcel in) {
            return new Playback(in);
        }

        @Override
        public Playback[] newArray(int size) {
            return new Playback[size];
        }
    };
}