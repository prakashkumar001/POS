package com.dexeldesigns.postheta.db_tables.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Creative IT Works on 21-Sep-17.
 */

@Entity
public class Discount {

    @Id
    Long id;
    public String discountValue;
    @Generated(hash = 2021241098)
    public Discount(Long id, String discountValue) {
        this.id = id;
        this.discountValue = discountValue;
    }
    @Generated(hash = 1777606421)
    public Discount() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDiscountValue() {
        return this.discountValue;
    }
    public void setDiscountValue(String discountValue) {
        this.discountValue = discountValue;
    }
}
