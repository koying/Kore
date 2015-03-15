package org.xbmc.kore.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.net.URI;
import java.net.URISyntaxException;

/*
 * Media class represents video entity with title, description, image thumbs and video url.
 */
public class Media implements Parcelable {
    private static final String TAG = "Media";
    static final long serialVersionUID = 727566175075960653L;
    private static int sCount = 0;
    private String mId;
    private String mTitle;
    private String mDescription;
    private String mBgImageUrl;
    private String mCardImageUrl;
    private String mVideoUrl;
    private String mStudio;
    private String mCategory;
    private String mRuntime;
    private String mYear;

    public Media() {

    }

    public Media(Parcel in){
        String[] data = new String[10];

        in.readStringArray(data);
        mId = data[0];
        mTitle = data[1];
        mDescription = data[2];
        mBgImageUrl = data[3];
        mCardImageUrl = data[4];
        mVideoUrl = data[5];
        mStudio = data[6];
        mCategory = data[7];
        mRuntime = data[8];
        mYear = data[9];
    }

    public static String getCount() {
        return Integer.toString(sCount);
    }

    public static void incrementCount() {
        sCount++;
    }

    public int getId() {
        return Integer.parseInt(mId);
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getStudio() {
        return mStudio;
    }

    public void setStudio(String studio) {
        mStudio = studio;
    }

    public String getVideoUrl() {
        return mVideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        mVideoUrl = videoUrl;
    }

    public String getBackgroundImageUrl() {
        return mBgImageUrl;
    }

    public void setBackgroundImageUrl(String bgImageUrl) {
        mBgImageUrl = bgImageUrl;
    }

    public String getCardImageUrl() {
        return mCardImageUrl;
    }

    public void setCardImageUrl(String cardImageUrl) {
        mCardImageUrl = cardImageUrl;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public String getRuntime() {
        return mRuntime;
    }

    public void setRuntime(String mRuntime) {
        this.mRuntime = mRuntime;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String mYear) {
        this.mYear = mYear;
    }

    public URI getBackgroundImageURI() {
        try {
            return new URI(getBackgroundImageUrl());
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                mId,
                mTitle,
                mDescription,
                mBgImageUrl,
                mCardImageUrl,
                mVideoUrl,
                mStudio,
                mCategory,
                mRuntime,
                mYear});
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        sb.append("Media{");
        sb.append("mId=" + mId);
        sb.append(", mTitle='" + mTitle + '\'');
        sb.append(", mVideoUrl='" + mVideoUrl + '\'');
        sb.append(", backgroundImageUrl='" + mBgImageUrl + '\'');
        sb.append(", backgroundImageURI='" + getBackgroundImageURI().toString() + '\'');
        sb.append(", mCardImageUrl='" + mCardImageUrl + '\'');
        sb.append('}');
        return sb.toString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Media createFromParcel(Parcel in) {
            return new Media(in);
        }

        public Media[] newArray(int size) {
            return new Media[size];
        }
    };
}
