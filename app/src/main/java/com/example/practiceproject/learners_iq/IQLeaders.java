package com.example.practiceproject.learners_iq;

import android.os.Parcel;
import android.os.Parcelable;

public class IQLeaders implements Parcelable {
    public String name;
    public int score;
    public String country;
    public String badgeUrl;

    public IQLeaders() {
    }

    private IQLeaders(Parcel parcel){
        name=parcel.readString();
        score=parcel.readInt();
        country=parcel.readString();
        badgeUrl=parcel.readString();
    }

    public IQLeaders(String name, int score, String country, String badgeUrl) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(score);
        parcel.writeString(country);
        parcel.writeString(badgeUrl);
    }

    public static Parcelable.Creator<IQLeaders> CREATOR= new Creator<IQLeaders>() {
        @Override
        public IQLeaders createFromParcel(Parcel parcel) {

          return new IQLeaders(parcel);
        }

        @Override
        public IQLeaders[] newArray(int i) {
            return new IQLeaders[i];
        }
    };
}
