package com.viatorfortis.guideme.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TriggerZone implements Parcelable {

    @SerializedName("circle_altitude")
    @Expose
    private Integer circleAltitude;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("circle_latitude")
    @Expose
    private Double circleLatitude;
    @SerializedName("circle_longitude")
    @Expose
    private Double circleLongitude;
    @SerializedName("circle_radius")
    @Expose
    private Double circleRadius;
    @SerializedName("polygon_corners")
    @Expose
    private String polygonCorners;

    public Integer getCircleAltitude() {
        return circleAltitude;
    }

    public void setCircleAltitude(Integer circleAltitude) {
        this.circleAltitude = circleAltitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getCircleLatitude() {
        return circleLatitude;
    }

    public void setCircleLatitude(Double circleLatitude) {
        this.circleLatitude = circleLatitude;
    }

    public Double getCircleLongitude() {
        return circleLongitude;
    }

    public void setCircleLongitude(Double circleLongitude) {
        this.circleLongitude = circleLongitude;
    }

    public Double getCircleRadius() {
        return circleRadius;
    }

    public void setCircleRadius(Double circleRadius) {
        this.circleRadius = circleRadius;
    }

    public String getPolygonCorners() {
        return polygonCorners;
    }

    public void setPolygonCorners(String polygonCorners) {
        this.polygonCorners = polygonCorners;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (circleAltitude == null) {
            dest.writeByte( (byte) 0);
        } else {
            dest.writeByte( (byte) 1);
            dest.writeInt(circleAltitude);
        }

        dest.writeString(type);

        if (circleLatitude == null) {
            dest.writeByte( (byte) 0);
        } else {
            dest.writeByte( (byte) 1);
            dest.writeDouble(circleLatitude);
        }

        if (circleLongitude == null) {
            dest.writeByte( (byte) 0);
        } else {
            dest.writeByte( (byte) 1);
            dest.writeDouble(circleLongitude);
        }

        if (circleRadius == null) {
            dest.writeByte( (byte) 0);
        } else {
            dest.writeByte( (byte) 1);
            dest.writeDouble(circleRadius);
        }

        dest.writeString(polygonCorners);
    }

    private TriggerZone(Parcel in) {
        if (in.readByte() == 0) {
            circleAltitude = null;
        } else {
            circleAltitude = in.readInt();
        }

        type = in.readString();

        if (in.readByte() == 0) {
            circleLatitude = null;
        } else {
            circleLatitude = in.readDouble();
        }

        if (in.readByte() == 0) {
            circleLongitude = null;
        } else {
            circleLongitude = in.readDouble();
        }

        if (in.readByte() == 0) {
            circleRadius = null;
        } else {
            circleRadius = in.readDouble();
        }

        polygonCorners = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<TriggerZone> CREATOR = new Parcelable.Creator<TriggerZone>() {
        @Override
        public TriggerZone createFromParcel(Parcel parcel) {
            return new TriggerZone(parcel);
        }

        @Override
        public TriggerZone[] newArray(int size) {
            return new TriggerZone[size];
        }
    };



}