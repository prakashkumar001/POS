package com.dexeldesigns.postheta.db_tables.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Creative IT Works on 21-Apr-17.
 */

@Entity
public class Product {
    @Id(autoincrement = true)
    public Long id;
    public String product_id;
    public String quantity;
    public String price;
    public String title;
    public String imageUrl;
    public String totalPricerow;
    public String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public Product( String product_id, String quantity, String price,
                   String title, String imageUrl, String totalPricerow,String status) {
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
        this.title = title;
        this.imageUrl = imageUrl;
        this.totalPricerow = totalPricerow;
        this.status=status;

    }

    @Generated(hash = 1890278724)
    public Product() {
    }

    @Generated(hash = 814440370)
    public Product(Long id, String product_id, String quantity, String price, String title,
            String imageUrl, String totalPricerow, String status) {
        this.id = id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
        this.title = title;
        this.imageUrl = imageUrl;
        this.totalPricerow = totalPricerow;
        this.status = status;
    }

    public String getTotal_price_row() {
        return totalPricerow;
    }

    public void setTotalPricerow(String totalPricerow) {
        this.totalPricerow = totalPricerow;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_id() {
        return this.product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTotalPricerow() {
        return this.totalPricerow;
    }

    public void setId(long id) {
        this.id = id;
    }




}