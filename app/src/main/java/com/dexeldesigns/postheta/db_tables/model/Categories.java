package com.dexeldesigns.postheta.db_tables.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Creative IT Works on 19-Sep-17.
 */

@Entity
public class Categories {
    @Id(autoincrement = true)
    public Long id;
    public String quantity;
    public String category_id;
    public String price;
    public String title;
    public String imageUrl;
    public String totalPricerow;
    public String status;

    @ToMany(referencedJoinProperty = "category_id")
    List<SubCategories> productItems;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 8531031)
    private transient CategoriesDao myDao;

    @Generated(hash = 2142385814)
    public Categories(Long id, String quantity, String category_id, String price,
            String title, String imageUrl, String totalPricerow, String status) {
        this.id = id;
        this.quantity = quantity;
        this.category_id = category_id;
        this.price = price;
        this.title = title;
        this.imageUrl = imageUrl;
        this.totalPricerow = totalPricerow;
        this.status = status;
    }

    @Generated(hash = 267348489)
    public Categories() {
    }
    public Categories(String category_id, String quantity, String price,
                      String title, String imageUrl, String totalPricerow, String status) {
        this.quantity = quantity;
        this.category_id = category_id;
        this.price = price;
        this.title = title;
        this.imageUrl = imageUrl;
        this.totalPricerow = totalPricerow;
        this.status = status;
    }
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCategory_id() {
        return this.category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
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

    public void setTotalPricerow(String totalPricerow) {
        this.totalPricerow = totalPricerow;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2009405346)
    public List<SubCategories> getProductItems() {
        if (productItems == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SubCategoriesDao targetDao = daoSession.getSubCategoriesDao();
            List<SubCategories> productItemsNew = targetDao
                    ._queryCategories_ProductItems(id);
            synchronized (this) {
                if (productItems == null) {
                    productItems = productItemsNew;
                }
            }
        }
        return productItems;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 646922753)
    public synchronized void resetProductItems() {
        productItems = null;
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
    @Generated(hash = 478861724)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCategoriesDao() : null;
    }

}