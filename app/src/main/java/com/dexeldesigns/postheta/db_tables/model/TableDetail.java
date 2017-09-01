package com.dexeldesigns.postheta.db_tables.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Creative IT Works on 13-Jul-17.
 */

@Entity
public class TableDetail {
    @Id
    private Long id;
    public String imgid;
    public String table_no;
    public String tableX;
    public String tableY;
    public String img_name;
    public String rotation;

    public TableDetail(String imgid, String table_no, String tableX, String tableY, String img_name, String rotation) {
        this.imgid = imgid;
        this.table_no = table_no;
        this.tableX = tableX;
        this.tableY = tableY;
        this.img_name = img_name;
        this.rotation = rotation;
    }

    @Generated(hash = 1367115075)
    public TableDetail(Long id, String imgid, String table_no, String tableX, String tableY, String img_name,
                       String rotation) {
        this.id = id;
        this.imgid = imgid;
        this.table_no = table_no;
        this.tableX = tableX;
        this.tableY = tableY;
        this.img_name = img_name;
        this.rotation = rotation;
    }

    @Generated(hash = 1292974705)
    public TableDetail() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgid() {
        return this.imgid;
    }

    public void setImgid(String imgid) {
        this.imgid = imgid;
    }

    public String getTable_no() {
        return this.table_no;
    }

    public void setTable_no(String table_no) {
        this.table_no = table_no;
    }

    public String getTableX() {
        return this.tableX;
    }

    public void setTableX(String tableX) {
        this.tableX = tableX;
    }

    public String getTableY() {
        return this.tableY;
    }

    public void setTableY(String tableY) {
        this.tableY = tableY;
    }

    public String getImg_name() {
        return this.img_name;
    }

    public void setImg_name(String img_name) {
        this.img_name = img_name;
    }

    public String getRotation() {
        return this.rotation;
    }

    public void setRotation(String rotation) {
        this.rotation = rotation;
    }


}
