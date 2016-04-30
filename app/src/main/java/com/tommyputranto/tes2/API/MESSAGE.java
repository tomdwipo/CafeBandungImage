package com.tommyputranto.tes2.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tommy on 4/27/16.
 */
public class MESSAGE {
    @SerializedName("PROD")
    @Expose
    private String PROD;
    @SerializedName("DEVEL")
    @Expose
    private String DEVEL;

    /**
     *
     * @return
     * The PROD
     */
    public String getPROD() {
        return PROD;
    }

    /**
     *
     * @param PROD
     * The PROD
     */
    public void setPROD(String PROD) {
        this.PROD = PROD;
    }

    /**
     *
     * @return
     * The DEVEL
     */
    public String getDEVEL() {
        return DEVEL;
    }

    /**
     *
     * @param DEVEL
     * The DEVEL
     */
    public void setDEVEL(String DEVEL) {
        this.DEVEL = DEVEL;
    }
}
