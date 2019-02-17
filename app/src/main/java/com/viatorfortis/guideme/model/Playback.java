package com.viatorfortis.guideme.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Playback {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("order")
    @Expose
    private List<String> order = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getOrder() {
        return order;
    }

    public void setOrder(List<String> order) {
        this.order = order;
    }

}