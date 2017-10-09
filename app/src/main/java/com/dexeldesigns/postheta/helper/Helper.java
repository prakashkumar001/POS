package com.dexeldesigns.postheta.helper;

import android.content.Context;


import com.dexeldesigns.postheta.db_tables.model.Break;
import com.dexeldesigns.postheta.db_tables.model.BreakDao;
import com.dexeldesigns.postheta.db_tables.model.Categories;
import com.dexeldesigns.postheta.db_tables.model.CategoriesDao;
import com.dexeldesigns.postheta.db_tables.model.Clock;
import com.dexeldesigns.postheta.db_tables.model.ClockDao;
import com.dexeldesigns.postheta.db_tables.model.DaoSession;
import com.dexeldesigns.postheta.db_tables.model.Discount;
import com.dexeldesigns.postheta.db_tables.model.DiscountDao;
import com.dexeldesigns.postheta.db_tables.model.GST;
import com.dexeldesigns.postheta.db_tables.model.GSTDao;
import com.dexeldesigns.postheta.db_tables.model.OrderItems;
import com.dexeldesigns.postheta.db_tables.model.OrderItemsDao;
import com.dexeldesigns.postheta.db_tables.model.Orders;
import com.dexeldesigns.postheta.db_tables.model.OrdersDao;
import com.dexeldesigns.postheta.db_tables.model.Product;
import com.dexeldesigns.postheta.db_tables.model.ProductDao;
import com.dexeldesigns.postheta.db_tables.model.Reserve;
import com.dexeldesigns.postheta.db_tables.model.Staff;
import com.dexeldesigns.postheta.db_tables.model.StaffDao;
import com.dexeldesigns.postheta.db_tables.model.SubCategories;
import com.dexeldesigns.postheta.db_tables.model.SubCategoriesDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Creative IT Works on 10-Aug-17.
 */

public class Helper {
    private final DaoSession daoSession;

    Context context;
    private Orders tempOrder;
    private Staff staff;

    public Helper(DaoSession daoSession, Context context) {
        this.daoSession = daoSession;
        this.context = context;
        helper = this;
    }

    public static Helper getHelper() {
        return helper;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static Helper helper;



    //getMenuItems
    public List<Categories> getMenuItems() {

        QueryBuilder<Categories> qb = daoSession.queryBuilder(Categories.class);
        return qb.list();

    }


    //getMenuItems
    public List<SubCategories> getProductItems(Long category_id) {

        QueryBuilder<SubCategories> qb = daoSession.queryBuilder(SubCategories.class);
        qb.where(SubCategoriesDao.Properties.Category_id.eq(category_id));
        return qb.list();

    }

    public Orders getTempOrder(){
        if(tempOrder==null){
            tempOrder=new Orders();
            //tempOrder.setOrderItems(new ArrayList<OrderItems>());
        }
        return tempOrder;
    }

    /**
     * Sets the temporary order which may have been created
     * @param order
     */
    public void setTempOrder(Orders order){
        tempOrder = order;
    }

    //get Order items from single order
    public List<OrderItems> getOrderItems(Long id) {
        QueryBuilder<OrderItems> qb = daoSession.queryBuilder(OrderItems.class);
        qb.where(OrderItemsDao.Properties.OrderId.eq(id));
        return qb.list();
    }


    //get All Orders
    public List<Orders> getAllOrder() {
        QueryBuilder<Orders> qb = daoSession.queryBuilder(Orders.class);
        return qb.list();
    }

    //get All Orders
    public Orders getOrderById(Long orderId) {
        QueryBuilder<Orders> qb = daoSession.queryBuilder(Orders.class);
        qb.where(OrdersDao.Properties.Id.eq(orderId));
        return qb.unique();
    }
    public long insertOrUpdateCategories(Categories product) {
        QueryBuilder<Categories> qb = daoSession.queryBuilder(Categories.class);
        qb.where(CategoriesDao.Properties.Category_id.eq(product.getCategory_id()));
        Categories existingCustomer=qb.unique();
        if(existingCustomer==null)
            return daoSession.insert(product);
        else {
            long id = existingCustomer.getId();
            return id;
        }

    }

    public long insertOrUpdateSubCategories(SubCategories product) {
        QueryBuilder<SubCategories> qb = daoSession.queryBuilder(SubCategories.class);
        qb.where(SubCategoriesDao.Properties.SubCategoryId.eq(product.getSubCategoryId()));
        qb.where(SubCategoriesDao.Properties.Category_id.eq(product.getCategory_id()));
        SubCategories existingCustomer=qb.unique();
        if(existingCustomer==null)
            return daoSession.insert(product);
        else {
            long id = existingCustomer.getId();
            return id;
        }

    }

    public Staff getStaff(String pin)
    {
        QueryBuilder<Staff> qb = daoSession.queryBuilder(Staff.class);
        qb.where(StaffDao.Properties.Pin.eq(pin));
        return qb.unique();

    }


    public List<Clock> getClockData(Long pin)
    {
        QueryBuilder<Clock> qb = daoSession.queryBuilder(Clock.class);
        qb.where(ClockDao.Properties.StaffId.eq(pin)).orderDesc(ClockDao.Properties.Id);

        // qb.where(qb.and(ClockDao.Properties.StaffId.eq(pin),ClockDao.Properties.Date.eq(date))).orderDesc(ClockDao.Properties.Id).limit(1);
        return qb.list();

    }

    public List<Break> getBreakData(Long clockId)
    {

            QueryBuilder<Break>  qb = daoSession.queryBuilder(Break.class);
            qb.where(BreakDao.Properties.ClockId.eq(clockId)).orderDesc(BreakDao.Properties.Id).limit(1);
            return qb.list();



        // qb.where(qb.and(ClockDao.Properties.StaffId.eq(pin),ClockDao.Properties.Date.eq(date))).orderDesc(ClockDao.Properties.Id).limit(1);



    }
    public List<Break> getBreakData(String staffId)
    {

        QueryBuilder<Break>  qb = daoSession.queryBuilder(Break.class);
        qb.where(BreakDao.Properties.StaffId.eq(staffId)).orderDesc(BreakDao.Properties.Id);
        return qb.list();



        // qb.where(qb.and(ClockDao.Properties.StaffId.eq(pin),ClockDao.Properties.Date.eq(date))).orderDesc(ClockDao.Properties.Id).limit(1);



    }

    public GST getGST()
    {
        QueryBuilder<GST>  qb = daoSession.queryBuilder(GST.class);
        qb.where(GSTDao.Properties.Id.eq(1));
        return qb.unique();


    }

    public Discount getDiscount()
    {
        QueryBuilder<Discount>  qb = daoSession.queryBuilder(Discount.class);
        qb.where(DiscountDao.Properties.Id.eq(1));
        return qb.unique();


    }

    public List<Staff> getStaffList() {

        QueryBuilder<Staff> qb = daoSession.queryBuilder(Staff.class);
        return qb.list();

    }

    public List<Reserve> getReservationList() {

        QueryBuilder<Reserve> qb = daoSession.queryBuilder(Reserve.class);
        return qb.list();

    }

}
