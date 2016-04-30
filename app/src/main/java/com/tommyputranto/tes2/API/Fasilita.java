package com.tommyputranto.tes2.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tommy on 4/27/16.
 */
public class Fasilita {

    @SerializedName("nama")
    @Expose
    private String nama;

    /**
     *
     * @return
     * The nama
     */
    public String getNama() {
        return nama;
    }

    /**
     *
     * @param nama
     * The nama
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

}
