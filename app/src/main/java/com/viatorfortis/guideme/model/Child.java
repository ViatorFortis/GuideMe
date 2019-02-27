package com.viatorfortis.guideme.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Child implements Parcelable {

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
    private List<String> languages;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("trigger_zones")
    @Expose
    private List<TriggerZone> triggerZones;
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
    private List<Image> images;

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


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uuid);
        dest.writeString(status);
        dest.writeString(type);

        if (languages == null) {
            dest.writeByte( (byte) 0);
        } else {
            dest.writeByte( (byte) 1);
            dest.writeStringList(languages);
        }

        dest.writeString(hash);

        if (triggerZones == null) {
            dest.writeByte( (byte) 0);
        } else {
            dest.writeByte( (byte) 1);
            dest.writeTypedList(triggerZones);
        }

        dest.writeParcelable(contentProvider, flags);
        dest.writeParcelable(publisher, flags);
        dest.writeParcelable(location, flags);
        dest.writeString(language);
        dest.writeString(summary);
        dest.writeString(desc);
        dest.writeString(title);
        dest.writeByte(hidden ? (byte) 1 : (byte) 0);

        if (images == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte( (byte) 1);
            dest.writeTypedList(images);
        }
    }


    private Child(Parcel in) {
        uuid = in.readString();
        status = in.readString();
        type = in.readString();

        if (in.readByte() == 0) {
            languages = null;
        } else {
            languages = new ArrayList<String>();
            in.readStringList(languages);
        }

        hash = in.readString();

        if (in.readByte() == 0) {
            triggerZones = null;
        } else {
            triggerZones = new ArrayList<TriggerZone>();
            in.readTypedList(triggerZones, TriggerZone.CREATOR);
        }

        in.readParcelable(ContentProvider.class.getClassLoader());
        in.readParcelable(Publisher.class.getClassLoader());
        in.readParcelable(Location.class.getClassLoader());

        language = in.readString();
        summary = in.readString();
        desc = in.readString();
        title = in.readString();

        hidden = in.readByte() != 0;

        if (in.readByte() == 0) {
            images = null;
        } else {
            images = new ArrayList<Image>();
            in.readTypedList(images, Image.CREATOR);
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Child> CREATOR = new Parcelable.Creator<Child>() {
        @Override
        public Child createFromParcel(Parcel parcel) {
            return new Child(parcel);
        }

        @Override
        public Child[] newArray(int size) {
            return new Child[size];
        }
    };
}