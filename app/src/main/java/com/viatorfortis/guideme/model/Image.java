
package com.viatorfortis.guideme.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image implements Parcelable {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("type")
    @Expose
    private String type;
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
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uuid);
        dest.writeString(type);

        if (order == null) {
            dest.writeByte( (byte) 0);
        } else {
            dest.writeByte( (byte) 1);
            dest.writeInt(order);
        }

        dest.writeString(hash);

        if (size == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte( (byte) 1);
            dest.writeInt(size);
        }
    }

    private Image (Parcel in) {
        uuid = in.readString();
        type = in.readString();

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

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
