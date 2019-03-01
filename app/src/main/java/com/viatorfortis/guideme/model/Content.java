package com.viatorfortis.guideme.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content implements Parcelable {

    @SerializedName("audio")
    @Expose
    private List<Audio> audio;
    @SerializedName("images")
    @Expose
    private List<Image> images;
    @SerializedName("playback")
    @Expose
    private Playback playback;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("children")
    @Expose
    private List<Child> children = null;

    public List<Audio> getAudio() {
        return audio;
    }

    public void setAudio(List<Audio> audio) {
        this.audio = audio;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Playback getPlayback() {
        return playback;
    }

    public void setPlayback(Playback playback) {
        this.playback = playback;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(audio);
        dest.writeTypedList(images);
        dest.writeParcelable(playback, flags);
        dest.writeString(language);
        dest.writeString(summary);
        dest.writeString(desc);
        dest.writeString(title);
        dest.writeTypedList(children);
    }

    protected Content(Parcel in) {
        audio = in.createTypedArrayList(Audio.CREATOR);
        images = in.createTypedArrayList(Image.CREATOR);
        playback = in.readParcelable(Playback.class.getClassLoader());
        language = in.readString();
        summary = in.readString();
        desc = in.readString();
        title = in.readString();
        children = in.createTypedArrayList(Child.CREATOR);
    }

    public static final Creator<Content> CREATOR = new Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel in) {
            return new Content(in);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };
}