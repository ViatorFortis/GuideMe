package com.viatorfortis.guideme.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location implements Parcelable {

    @SerializedName("altitude")
    @Expose
    private Integer altitude;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("country_uuid")
    @Expose
    private String countryUuid;
    @SerializedName("city_uuid")
    @Expose
    private String cityUuid;

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryUuid() {
        return countryUuid;
    }

    public void setCountryUuid(String countryUuid) {
        this.countryUuid = countryUuid;
    }

    public String getCityUuid() {
        return cityUuid;
    }

    public void setCityUuid(String cityUuid) {
        this.cityUuid = cityUuid;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (altitude == null) {
            dest.writeByte( (byte) 0);
        } else {
            dest.writeByte( (byte) 1);
            dest.writeInt(altitude);
        }

        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(countryCode);
        dest.writeString(countryUuid);
        dest.writeString(cityUuid);
    }

    private Location (Parcel in) {
        if (in.readByte() == 0) {
            altitude = null;
        } else {
            altitude = in.readInt();
        }

        latitude = in.readDouble();
        longitude = in.readDouble();
        countryCode = in.readString();
        countryUuid = in.readString();
        cityUuid = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Location> CREATOR = new Creator<Location>(){

        @Override
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}