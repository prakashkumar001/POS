package com.dexeldesigns.postheta.db_tables.model;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * Created by Creative IT Works on 10-Aug-17.
 */

@Entity
public class Orders {

    @Id
    Long id;

    @ToMany(referencedJoinProperty = "orderId")
    private List<OrderItems> orderItems;

    private String subTotal;

    private String Total;

    private String orderTime;

    private String orderStatus;

    private String Table_no;

    private String TakeAwayno;

    private String orderType;

    private String payment_status;

    private String gst_amount;

    private boolean isSync;

    private boolean isContainsvoid;

    private boolean isHold;

    private String discount;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1717339351)
    private transient OrdersDao myDao;

    @Generated(hash = 2090816863)
    public Orders(Long id, String subTotal, String Total, String orderTime, String orderStatus,
            String Table_no, String TakeAwayno, String orderType, String payment_status,
            String gst_amount, boolean isSync, boolean isContainsvoid, boolean isHold,
            String discount) {
        this.id = id;
        this.subTotal = subTotal;
        this.Total = Total;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
        this.Table_no = Table_no;
        this.TakeAwayno = TakeAwayno;
        this.orderType = orderType;
        this.payment_status = payment_status;
        this.gst_amount = gst_amount;
        this.isSync = isSync;
        this.isContainsvoid = isContainsvoid;
        this.isHold = isHold;
        this.discount = discount;
    }

    @Generated(hash = 1753857294)
    public Orders() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubTotal() {
        return this.subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getTotal() {
        return this.Total;
    }

    public void setTotal(String Total) {
        this.Total = Total;
    }

    public String getOrderTime() {
        return this.orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getTable_no() {
        return this.Table_no;
    }

    public void setTable_no(String Table_no) {
        this.Table_no = Table_no;
    }

    public String getTakeAwayno() {
        return this.TakeAwayno;
    }

    public void setTakeAwayno(String TakeAwayno) {
        this.TakeAwayno = TakeAwayno;
    }

    public String getOrderType() {
        return this.orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPayment_status() {
        return this.payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getGst_amount() {
        return this.gst_amount;
    }

    public void setGst_amount(String gst_amount) {
        this.gst_amount = gst_amount;
    }

    public boolean getIsSync() {
        return this.isSync;
    }

    public void setIsSync(boolean isSync) {
        this.isSync = isSync;
    }

    public boolean getIsContainsvoid() {
        return this.isContainsvoid;
    }

    public void setIsContainsvoid(boolean isContainsvoid) {
        this.isContainsvoid = isContainsvoid;
    }

    public boolean getIsHold() {
        return this.isHold;
    }

    public void setIsHold(boolean isHold) {
        this.isHold = isHold;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1794615759)
    public List<OrderItems> getOrderItems() {
        if (orderItems == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            OrderItemsDao targetDao = daoSession.getOrderItemsDao();
            List<OrderItems> orderItemsNew = targetDao._queryOrders_OrderItems(id);
            synchronized (this) {
                if (orderItems == null) {
                    orderItems = orderItemsNew;
                }
            }
        }
        return orderItems;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1029058822)
    public synchronized void resetOrderItems() {
        orderItems = null;
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

    public String getDiscount() {
        return this.discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 121826766)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getOrdersDao() : null;
    }




}