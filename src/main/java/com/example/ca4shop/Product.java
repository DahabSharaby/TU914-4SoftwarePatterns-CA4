package com.example.ca4shop;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Product implements Parcelable {
    private String name;
    private String manufacturer;
    private String price;
    private String category;
    private String imageURL;
    private float rating;
    private List<String> comments;

    public Product() {
    }

    public Product(String name, String manufacturer, String price, String category, String imageURL, float rating, List<String> comments) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.category = category;
        this.imageURL = imageURL;
        this.rating = rating;
        this.comments = comments;
    }

    protected Product(Parcel in) {
        name = in.readString();
        manufacturer = in.readString();
        price = in.readString();
        category = in.readString();
        imageURL = in.readString();
        rating = in.readFloat();
        comments = in.createStringArrayList();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getImageURL() {
        return imageURL;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(manufacturer);
        dest.writeString(price);
        dest.writeString(category);
        dest.writeString(imageURL);
        dest.writeFloat(rating);
        dest.writeStringList(comments);
    }
}
