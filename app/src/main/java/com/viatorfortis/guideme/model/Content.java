package com.viatorfortis.guideme.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("audio")
    @Expose
    private List<Audio> audio = null;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
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

}