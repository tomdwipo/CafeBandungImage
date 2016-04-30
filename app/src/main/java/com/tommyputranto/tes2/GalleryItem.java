package com.tommyputranto.tes2;

import java.util.ArrayList;

public class GalleryItem {
    private String mCaption;
    private String mId;
    private String mUrl;
    private String mNomor;
    private ArrayList<String> location;
    private String lokasi;

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public ArrayList<String> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<String> location) {
        this.location = location;
    }

    public String getNomor() {
        return mNomor;
    }

    public void setNomor(String nomor) {
        mNomor = nomor;
    }

    public String getCaption() {
        return mCaption;
    }


    public void setCaption(String caption) {
        mCaption = caption;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
    @Override
    public String toString() {
        return mCaption;
    }
}
