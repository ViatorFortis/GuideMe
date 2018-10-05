package com.viatorfortis.guideme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Region {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("languages")
    @Expose
    private List<String> languages = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("children_count")
    @Expose
    private Integer childrenCount;
    @SerializedName("map")
    @Expose
    private Map map;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("visible")
    @Expose
    private Boolean visible;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("location")
    @Expose
    private Location location;

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

    public Integer getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(Integer childrenCount) {
        this.childrenCount = childrenCount;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}