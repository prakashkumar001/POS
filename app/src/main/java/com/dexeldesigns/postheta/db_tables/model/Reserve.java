package com.dexeldesigns.postheta.db_tables.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Creative IT Works on 05-Oct-17.
 */

@Entity
public class Reserve {
    @Id
    Long id;
    private String name;
    private String email;
    private String address;
    private String date;
    private String time;
    private String phone;
    @Generated(hash = 599317959)
    public Reserve(Long id, String name, String email, String address, String date,
            String time, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.date = date;
        this.time = time;
        this.phone = phone;
    }
    @Generated(hash = 1293022648)
    public Reserve() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}