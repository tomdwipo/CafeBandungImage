package com.tommyputranto.tes2.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tommy on 4/27/16.
 */
public class Api {

    @SerializedName("STATUS")
    @Expose
    private Boolean STATUS;
    @SerializedName("STATUS_CODE")
    @Expose
    private Integer STATUSCODE;
    @SerializedName("MESSAGE")
    @Expose
    private com.tommyputranto.tes2.API.MESSAGE MESSAGE;
    @SerializedName("DATA")
    @Expose
    private com.tommyputranto.tes2.API.DATA DATA;

    /**
     *
     * @return
     * The STATUS
     */
    public Boolean getSTATUS() {
        return STATUS;
    }

    /**
     *
     * @param STATUS
     * The STATUS
     */
    public void setSTATUS(Boolean STATUS) {
        this.STATUS = STATUS;
    }

    /**
     *
     * @return
     * The STATUSCODE
     */
    public Integer getSTATUSCODE() {
        return STATUSCODE;
    }

    /**
     *
     * @param STATUSCODE
     * The STATUS_CODE
     */
    public void setSTATUSCODE(Integer STATUSCODE) {
        this.STATUSCODE = STATUSCODE;
    }

    /**
     *
     * @return
     * The MESSAGE
     */
    public com.tommyputranto.tes2.API.MESSAGE getMESSAGE() {
        return MESSAGE;
    }

    /**
     *
     * @param MESSAGE
     * The MESSAGE
     */
    public void setMESSAGE(com.tommyputranto.tes2.API.MESSAGE MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    /**
     *
     * @return
     * The DATA
     */
    public com.tommyputranto.tes2.API.DATA getDATA() {
        return DATA;
    }

    /**
     *
     * @param DATA
     * The DATA
     */
    public void setDATA(com.tommyputranto.tes2.API.DATA DATA) {
        this.DATA = DATA;
    }

}
