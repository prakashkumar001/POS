package com.dexeldesigns.postheta.model;

/**
 * Created by Creative IT Works on 21-Jun-17.
 */

public class Order_Detail {
    public String tableno;

    public Order_Detail(String tableno, String progress, String type, String status) {
        this.tableno = tableno;
        this.progress = progress;
        this.type = type;
        this.status = status;
    }

    public String progress;
    public String type;
    public String status;
}
