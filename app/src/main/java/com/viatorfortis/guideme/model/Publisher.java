package com.viatorfortis.guideme.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Publisher implements Parcelable {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("languages")
    @Expose
    private List<String> languages;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("images")
    @Expose
    private List<Image> images;
    @SerializedName("content_provider")
    @Expose
    private ContentProvider contentProvider;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public ContentProvider getContentProvider() {
        return contentProvider;
    }

    public void setContentProvider(ContentProvider contentProvider) {
        this.contentProvider = contentProvider;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uuid);
        dest.writeString(type);

        if (languages == null) {
            dest.writeByte( (byte) 0);
        } else {
            dest.writeByte( (byte) 1);
            dest.writeStringList(languages);
        }

        dest.writeString(status);
        dest.writeString(hash);
        dest.writeString(title);
        dest.writeString(summary);
        dest.writeString(language);

        if (images == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte( (byte) 1);
            dest.writeTypedList(images);
        }

        dest.writeParcelable(contentProvider, flags);

    }

    private Publisher(Parcel in) {
        uuid = in.readString();
        type = in.readString();

        if (in.readByte() == 0) {
            languages = null;
        } else {
            languages = new ArrayList<String>();
            in.readStringList(languages);
        }

        status = in.readString();
        hash = in.readString();
        title = in.readString();
        summary = in.readString();
        language = in.readString();

        if (in.readByte() == 0) {
            images = null;
        } else {
            images = new ArrayList<Image>();
            in.readTypedList(images, Image.CREATOR);
        }

        in.readParcelable(ContentProvider.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Publisher> CREATOR = new Parcelable.Creator<Publisher>() {
        @Override
        public Publisher createFromParcel(Parcel parcel) {
            return new Publisher(parcel);
        }

        @Override
        public Publisher[] newArray(int size) {
            return new Publisher[size];
        }
    };
}