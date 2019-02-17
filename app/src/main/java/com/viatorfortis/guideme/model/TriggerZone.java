package com.viatorfortis.guideme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TriggerZone {

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

}