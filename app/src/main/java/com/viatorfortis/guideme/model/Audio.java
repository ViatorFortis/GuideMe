package com.viatorfortis.guideme.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Audio implements Parcelable {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("size")
    @Expose
    private Integer size;

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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uuid);
        dest.writeString(type);

        if (duration == null) {
            dest.writeByte( (byte) 0);
        } else {
            dest.writeByte( (byte) 1);
            dest.writeInt(duration);
        }

        if (order == null) {
            dest.writeByte( (byte) 0);
        } else {
            dest.writeByte( (byte) 1);
            dest.writeInt(order);
        }

        dest.writeString(hash);

        if (size == null) {
            dest.writeByte( (byte) 0);
        } else {
            dest.writeByte( (byte) 1);
            dest.writeInt(size);
        }
    }

    private Audio (Parcel in) {
        uuid = in.readString();
        type = in.readString();

        if (in.readByte() == 0) {
            duration = null;
        } else {
            duration = in.readInt();
        }

        if (in.readByte() == 0) {
            order = null;
        } else {
            order = in.readInt();
        }

        hash = in.readString();

        if (in.readByte() == 0) {
            size = null;
        } else {
            size = in.readInt();
        }
    }

    public static final Creator<Audio> CREATOR = new Creator<Audio>() {
        @Override
        public Audio createFromParcel(Parcel in) {
            return new Audio(in);
        }

        @Override
        public Audio[] newArray(int size) {
            return new Audio[size];
        }
    };
}