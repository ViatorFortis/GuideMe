
package com.viatorfortis.guideme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reviews {

    @SerializedName("rating_average")
    @Expose
    private Integer ratingAverage;
    @SerializedName("ratings_count")
    @Expose
    private Integer ratingsCount;
    @SerializedName("reviews_count")
    @Expose
    private Integer reviewsCount;

    public Integer getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(Integer ratingAverage) {
        this.ratingAverage = ratingAverage;
    }

    public Integer getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(Integer ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public Integer getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

}
