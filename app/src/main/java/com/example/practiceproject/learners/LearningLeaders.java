package com.example.practiceproject.learners;

import android.os.Parcel;
import android.os.Parcelable;

public class LearningLeaders implements Parcelable {
    public String name;
    public int hours;
    public String country;
    public String badgeUrl;

    public LearningLeaders(){

    }
 public LearningLeaders(Parcel parcel){
        name=parcel.readString();
        hours=parcel.readInt();
        country=parcel.readString();
        badgeUrl=parcel.readString();
 }

    public LearningLeaders(String name, int hours, String country, String badgeUrl) {
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
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
    public String toString(){
        return String.format("%n%s%6d%s%s%n",name, hours,country,badgeUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
      parcel.writeString(name);
      parcel.writeInt(hours);
      parcel.writeString(country);
      parcel.writeString(badgeUrl);
    }
    public static Parcelable.Creator<LearningLeaders>CREATOR=new Creator<LearningLeaders>() {
        @Override
        public LearningLeaders createFromParcel(Parcel parcel) {
            return new LearningLeaders(parcel);
        }

        @Override
        public LearningLeaders[] newArray(int i) {
            return new LearningLeaders[i];
        }
    };
}
