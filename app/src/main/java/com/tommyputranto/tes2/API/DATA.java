package com.tommyputranto.tes2.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tommy on 4/27/16.
 */
public class DATA {
    @SerializedName("nama")
    @Expose
    private String nama;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @SerializedName("location")
    @Expose
    private com.tommyputranto.tes2.API.Location location;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("fasilitas")
    @Expose
    private List<Fasilita> fasilitas = new ArrayList<Fasilita>();

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

    /**
     *
     * @return
     * The location
     */
 /*   public Location getLocation2() {
        return location;
    }

    *//**
     *
     * @param location
     * The location
     *//*
    public void setLocation(Location location) {
        this.location = location;
    }
*/
    /**
     *
     * @return
     * The alamat
     */
    public String getAlamat() {
        return alamat;
    }

    /**
     *
     * @param alamat
     * The alamat
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    /**
     *
     * @return
     * The deskripsi
     */
    public String getDeskripsi() {
        return deskripsi;
    }

    /**
     *
     * @param deskripsi
     * The deskripsi
     */
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    /**
     *
     * @return
     * The fasilitas
     */
    public List<Fasilita> getFasilitas() {
        return fasilitas;
    }

    /**
     *
     * @param fasilitas
     * The fasilitas
     */
    public void setFasilitas(List<Fasilita> fasilitas) {
        this.fasilitas = fasilitas;
    }

}
