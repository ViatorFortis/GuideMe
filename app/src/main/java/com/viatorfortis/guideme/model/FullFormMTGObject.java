package com.viatorfortis.guideme.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FullFormMTGObject implements Parcelable {

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
    private List<String> languages;
    @SerializedName("map")
    @Expose
    private Map map;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("content_provider")
    @Expose
    private ContentProvider contentProvider;
    @SerializedName("reviews")
    @Expose
    private Reviews reviews;
    @SerializedName("publisher")
    @Expose
    private Publisher publisher;
    @SerializedName("content")
    @Expose
    private List<Content> contentList;
    @SerializedName("location")
    @Expose
    private Location location;

    protected FullFormMTGObject(Parcel in) {
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
        languages = in.createStringArrayList();
        map = in.readParcelable(Map.class.getClassLoader());
        hash = in.readString();
        if (in.readByte() == 0) {
            size = null;
        } else {
            size = in.readInt();
        }
        contentProvider = in.readParcelable(ContentProvider.class.getClassLoader());
        reviews = in.readParcelable(Reviews.class.getClassLoader());
        publisher = in.readParcelable(Publisher.class.getClassLoader());
        contentList = in.createTypedArrayList(Content.CREATOR);
        location = in.readParcelable(Location.class.getClassLoader());
    }

    public static final Creator<FullFormMTGObject> CREATOR = new Creator<FullFormMTGObject>() {
        @Override
        public FullFormMTGObject createFromParcel(Parcel in) {
            return new FullFormMTGObject(in);
        }

        @Override
        public FullFormMTGObject[] newArray(int size) {
            return new FullFormMTGObject[size];
        }
    };

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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
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

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uuid);
        dest.writeString(status);
        dest.writeString(type);
        dest.writeString(category);
        if (duration == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(duration);
        }
        if (distance == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(distance);
        }
        dest.writeString(placement);
        dest.writeStringList(languages);
        dest.writeParcelable(map, flags);
        dest.writeString(hash);
        if (size == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(size);
        }
        dest.writeParcelable(contentProvider, flags);
        dest.writeParcelable(reviews, flags);
        dest.writeParcelable(publisher, flags);
        dest.writeTypedList(contentList);
        dest.writeParcelable(location, flags);
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(uuid);
//        dest.writeString(status);
//        dest.writeString(type);
//        dest.writeString(category);
//
//        if (duration == null) {
//            dest.writeByte( (byte) 0);
//        } else {
//            dest.writeByte( (byte) 1);
//            dest.writeInt(duration);
//        }
//
//        if (distance == null) {
//            dest.writeByte( (byte) 0);
//        } else {
//            dest.writeByte( (byte) 1);
//            dest.writeInt(distance);
//        }
//
//        dest.writeString(placement);
//
//        if (languages == null) {
//            dest.writeByte( (byte) 0);
//        } else {
//            dest.writeByte( (byte) 1);
//            dest.writeStringList(languages);
//        }
//
//        dest.writeParcelable(map, flags);
//
//        dest.writeString(hash);
//
//        if (size == null) {
//            dest.writeByte( (byte) 0);
//        } else {
//            dest.writeByte( (byte) 1);
//            dest.writeInt(size);
//        }
//
//        dest.writeParcelable(contentProvider, flags);
//
//        dest.writeParcelable(reviews, flags);
//        dest.writeParcelable(publisher, flags);
//
//        if (contentList == null) {
//            dest.writeByte( (byte) 0);
//        } else {
//            dest.writeByte( (byte) 1);
//            dest.writeTypedList(contentList);
//        }
//
//        dest.writeParcelable(location, flags);
//    }
//
//    private FullFormMTGObject(Parcel in) {
//
//    }
}