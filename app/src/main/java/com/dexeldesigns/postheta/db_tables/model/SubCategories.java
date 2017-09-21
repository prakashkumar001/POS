package com.dexeldesigns.postheta.db_tables.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Creative IT Works on 19-Sep-17.
 */
@Entity
public class SubCategories {

    @Id
    public Long id;
    public String quantity;
    public String price;
    public String title;
    public String imageUrl;
    public String totalPricerow;
    public String status;
    public Long category_id;
    @ToOne(joinProperty = "category_id")
    public Categories menu;
    public String subCategoryId;

    public String discountQtyapplyfor;
    public String discountforparticularItems;
    public boolean isoveralldiscountavailable;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1239074240)
    private transient SubCategoriesDao myDao;
    @Generated(hash = 1027488814)
    public SubCategories(Long id, String quantity, String price, String title,
            String imageUrl, String totalPricerow, String status, Long category_id,
            String subCategoryId, String discountQtyapplyfor,
            String discountforparticularItems, boolean isoveralldiscountavailable) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.title = title;
        this.imageUrl = imageUrl;
        this.totalPricerow = totalPricerow;
        this.status = status;
        this.category_id = category_id;
        this.subCategoryId = subCategoryId;
        this.discountQtyapplyfor = discountQtyapplyfor;
        this.discountforparticularItems = discountforparticularItems;
        this.isoveralldiscountavailable = isoveralldiscountavailable;
    }
    @Generated(hash = 77874667)
    public SubCategories() {
    }

    public SubCategories( String subCategoryId, String quantity, String price, String title,
                         String imageUrl, String totalPricerow, String status, Long category_id,
                         String discountQtyapplyfor,
                         String discountforparticularItems, boolean isoveralldiscountavailable) {

        this.quantity = quantity;
        this.price = price;
        this.title = title;
        this.imageUrl = imageUrl;
        this.totalPricerow = totalPricerow;
        this.status = status;
        this.category_id = category_id;
        this.subCategoryId = subCategoryId;
        this.discountQtyapplyfor = discountQtyapplyfor;
        this.discountforparticularItems = discountforparticularItems;
        this.isoveralldiscountavailable = isoveralldiscountavailable;
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
    public Long getCategory_id() {
        return this.category_id;
    }
    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }
    public String getSubCategoryId() {
        return this.subCategoryId;
    }
    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }
    public String getDiscountQtyapplyfor() {
        return this.discountQtyapplyfor;
    }
    public void setDiscountQtyapplyfor(String discountQtyapplyfor) {
        this.discountQtyapplyfor = discountQtyapplyfor;
    }
    public String getDiscountforparticularItems() {
        return this.discountforparticularItems;
    }
    public void setDiscountforparticularItems(String discountforparticularItems) {
        this.discountforparticularItems = discountforparticularItems;
    }
    public boolean getIsoveralldiscountavailable() {
        return this.isoveralldiscountavailable;
    }
    public void setIsoveralldiscountavailable(boolean isoveralldiscountavailable) {
        this.isoveralldiscountavailable = isoveralldiscountavailable;
    }
    @Generated(hash = 1119378333)
    private transient Long menu__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1988606109)
    public Categories getMenu() {
        Long __key = this.category_id;
        if (menu__resolvedKey == null || !menu__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CategoriesDao targetDao = daoSession.getCategoriesDao();
            Categories menuNew = targetDao.load(__key);
            synchronized (this) {
                menu = menuNew;
                menu__resolvedKey = __key;
            }
        }
        return menu;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1855031753)
    public void setMenu(Categories menu) {
        synchronized (this) {
            this.menu = menu;
            category_id = menu == null ? null : menu.getId();
            menu__resolvedKey = category_id;
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
    @Generated(hash = 1182151328)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSubCategoriesDao() : null;
    }



}
