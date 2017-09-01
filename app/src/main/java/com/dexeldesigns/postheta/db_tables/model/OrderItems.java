package com.dexeldesigns.postheta.db_tables.model;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

/**
 * Created by Creative IT Works on 10-Aug-17.
 */

@Entity
public class OrderItems {

    @Id
    Long id;
    public String product_id;
    public String quantity;
    public String price;
    public String title;
    public String imageUrl;
    public String total_price_row;
    public String status;
    public String order_items_time;
    public String void_quantity;
    public String information;

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    private Long orderId;
    @ToOne(joinProperty = "orderId")
    private Orders order;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 871759418)
    private transient OrderItemsDao myDao;
    @Generated(hash = 219913283)
    private transient Long order__resolvedKey;


    public OrderItems(String product_id, String quantity, String price,
                      String title, String imageUrl, String total_price_row, String status,String order_items_time) {
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
        this.title = title;
        this.imageUrl = imageUrl;
        this.total_price_row = total_price_row;
        this.status = status;
        this.order_items_time=order_items_time;

    }


    @Generated(hash = 656650667)
    public OrderItems(Long id, String product_id, String quantity, String price, String title, String imageUrl,
            String total_price_row, String status, String order_items_time, String void_quantity, String information,
            Long orderId) {
        this.id = id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
        this.title = title;
        this.imageUrl = imageUrl;
        this.total_price_row = total_price_row;
        this.status = status;
        this.order_items_time = order_items_time;
        this.void_quantity = void_quantity;
        this.information = information;
        this.orderId = orderId;
    }

    @Generated(hash = 1757272169)
    public OrderItems() {
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


    public String getTotal_price_row() {
        return this.total_price_row;
    }


    public void setTotal_price_row(String total_price_row) {
        this.total_price_row = total_price_row;
    }


    public String getStatus() {
        return this.status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getOrder_items_time() {
        return this.order_items_time;
    }


    public void setOrder_items_time(String order_items_time) {
        this.order_items_time = order_items_time;
    }


    public String getVoid_quantity() {
        return this.void_quantity;
    }


    public void setVoid_quantity(String void_quantity) {
        this.void_quantity = void_quantity;
    }


    public Long getOrderId() {
        return this.orderId;
    }


    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }


    /** To-one relationship, resolved on first access. */
    @Generated(hash = 455849695)
    public Orders getOrder() {
        Long __key = this.orderId;
        if (order__resolvedKey == null || !order__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            OrdersDao targetDao = daoSession.getOrdersDao();
            Orders orderNew = targetDao.load(__key);
            synchronized (this) {
                order = orderNew;
                order__resolvedKey = __key;
            }
        }
        return order;
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 668355306)
    public void setOrder(Orders order) {
        synchronized (this) {
            this.order = order;
            orderId = order == null ? null : order.getId();
            order__resolvedKey = orderId;
        }
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1923971465)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getOrderItemsDao() : null;
    }


}