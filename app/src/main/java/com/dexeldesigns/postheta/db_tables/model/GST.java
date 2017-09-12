package com.dexeldesigns.postheta.db_tables.model;

import android.util.Log;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Creative IT Works on 11-Sep-17.
 */

@Entity
public class GST {
    @Id
    Long id;
    public String gstamount;
    @Generated(hash = 135005552)
    public GST(Long id, String gstamount) {
        this.id = id;
        this.gstamount = gstamount;
    }
    @Generated(hash = 1559056591)
    public GST() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getGstamount() {
        return this.gstamount;
    }
    public void setGstamount(String gstamount) {
        this.gstamount = gstamount;
    }
}
