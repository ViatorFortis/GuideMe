package com.viatorfortis.guideme.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Child {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("languages")
    @Expose
    private List<String> languages = null;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("trigger_zones")
    @Expose
    private List<TriggerZone> triggerZones = null;
    @SerializedName("content_provider")
    @Expose
    private ContentProvider contentProvider;
    @SerializedName("publisher")
    @Expose
    private Publisher publisher;
    @SerializedName("location")
    @Expose
    private Location location;
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
    @SerializedName("hidden")
    @Expose
    private Boolean hidden;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public List<TriggerZone> getTriggerZones() {
        return triggerZones;
    }

    public void setTriggerZones(List<TriggerZone> triggerZones) {
        this.triggerZones = triggerZones;
    }

    public ContentProvider getContentProvider() {
        return contentProvider;
    }

    public void setContentProvider(ContentProvider contentProvider) {
        this.contentProvider = contentProvider;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

}