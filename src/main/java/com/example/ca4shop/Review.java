package com.example.ca4shop;

import android.os.Parcel;
import android.os.Parcelable;

public class Review implements Parcelable {
    private String userId;
    private String comment;
    private double rating;


    public Review() {
    }

    public Review(String userId, String comment, double rating) {
        this.userId = userId;
        this.comment = comment;
        this.rating = rating;
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    // Parcelable implementation
    protected Review(Parcel in) {
        userId = in.readString();
        comment = in.readString();
        rating = in.readDouble();
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(comment);
        dest.writeDouble(rating);
    }
}
