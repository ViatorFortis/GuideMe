
package com.viatorfortis.guideme.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentProvider implements Parcelable {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("copyright")
    @Expose
    private String copyright;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uuid);
        dest.writeString(name);
        dest.writeString(copyright);
    }

    private ContentProvider(Parcel in) {
        uuid = in.readString();
        name = in.readString();
        copyright = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ContentProvider> CREATOR = new Creator<ContentProvider>() {
        @Override
        public ContentProvider createFromParcel(Parcel in) {
            return new ContentProvider(in);
        }

        @Override
        public ContentProvider[] newArray(int size) {
            return new ContentProvider[size];
        }
    };
}
