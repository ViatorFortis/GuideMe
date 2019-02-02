
package com.viatorfortis.guideme.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.UUID;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MTGObject implements Parcelable{

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("placement")
    @Expose
    private String placement;
    @SerializedName("languages")
    @Expose
    private List<String> languages = null;
    @SerializedName("map")
    @Expose
    private Map map;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("children_count")
    @Expose
    private Integer childrenCount;
    @SerializedName("route")
    @Expose
    private String route;
    @SerializedName("content_provider")
    @Expose
    private ContentProvider contentProvider;
    @SerializedName("reviews")
    @Expose
    private Reviews reviews;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("title")
    @Expose
    private String title;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
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

    public Integer getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(Integer childrenCount) {
        this.childrenCount = childrenCount;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public ContentProvider getContentProvider() {
        return contentProvider;
    }

    public void setContentProvider(ContentProvider contentProvider) {
        this.contentProvider = contentProvider;
    }

    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uuid);
        dest.writeString(status);
        dest.writeString(type);
        dest.writeString(category);

        if (duration == null) {
            dest.writeByte( (byte) 0);
        } else {
            dest.writeByte( (byte) 1);
            dest.writeInt(duration);
        }

        if (distance == null) {
            dest.writeByte( (byte) 0);
        } else {
            dest.writeByte( (byte) 1);
            dest.writeInt(distance);
        }

        dest.writeString(placement);
        dest.writeStringList(languages);
        dest.writeParcelable(map, flags);
        dest.writeString(hash);

        if (childrenCount == null) {
            dest.writeByte( (byte) 0);
        } else {
            dest.writeByte( (byte) 1);
            dest.writeInt(childrenCount);
        }

        dest.writeString(route);
        dest.writeParcelable(contentProvider, flags);
        dest.writeParcelable(reviews, flags);
        dest.writeTypedList(images);
        dest.writeParcelable(location, flags);
        dest.writeString(language);
        dest.writeString(summary);
        dest.writeString(title);
    }

    private MTGObject(Parcel in) {
        uuid = in.readString();
        status = in.readString();
        type = in.readString();
        category = in.readString();

        if (in.readByte() == 0) {
            duration = null;
        } else {
            duration = in.readInt();
        }

        if (in.readByte() == 0) {
            distance = null;
        } else {
            distance = in.readInt();
        }

        placement = in.readString();
        in.readStringList(languages);
        map = in.readParcelable(Map.class.getClassLoader() );
        hash = in.readString();

        if (in.readByte() == 0) {
            childrenCount = null;
        } else {
            childrenCount = in.readInt();
        }

        route = in.readString();
        contentProvider = in.readParcelable(ContentProvider.class.getClassLoader() );
        reviews = in.readParcelable(Reviews.class.getClassLoader() );
        in.readTypedList(images, Image.CREATOR);
        location = in.readParcelable(Location.class.getClassLoader() );
        language = in.readString();
        summary = in.readString();
        title = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<MTGObject> CREATOR = new Parcelable.Creator<MTGObject>() {
        @Override
        public MTGObject createFromParcel(Parcel parcel) {
            return new MTGObject(parcel);
        }

        @Override
        public MTGObject[] newArray(int size) {
            return new MTGObject[size];
        }
    };
}
