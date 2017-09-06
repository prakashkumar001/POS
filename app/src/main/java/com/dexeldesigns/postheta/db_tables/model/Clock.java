package com.dexeldesigns.postheta.db_tables.model;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;

/**
 * Created by Creative IT Works on 22-Aug-17.
 */

@Entity
public class Clock {
    @Id
    private Long id;
    public String clock_in_time;
    public String clock_out_time;
    public String total_hours;
    public String total_working_hours;
    public String date;

    @ToMany(referencedJoinProperty ="clockId" )
    List<Break> breaklist;

    private Long staffId;
    @ToOne(joinProperty = "staffId")
    Staff staff;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 150378241)
    private transient ClockDao myDao;
    @Generated(hash = 521850980)
    public Clock(Long id, String clock_in_time, String clock_out_time,
            String total_hours, String total_working_hours, String date,
            Long staffId) {
        this.id = id;
        this.clock_in_time = clock_in_time;
        this.clock_out_time = clock_out_time;
        this.total_hours = total_hours;
        this.total_working_hours = total_working_hours;
        this.date = date;
        this.staffId = staffId;
    }
    @Generated(hash = 1588708936)
    public Clock() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getClock_in_time() {
        return this.clock_in_time;
    }
    public void setClock_in_time(String clock_in_time) {
        this.clock_in_time = clock_in_time;
    }
    public String getClock_out_time() {
        return this.clock_out_time;
    }
    public void setClock_out_time(String clock_out_time) {
        this.clock_out_time = clock_out_time;
    }
    public String getTotal_hours() {
        return this.total_hours;
    }
    public void setTotal_hours(String total_hours) {
        this.total_hours = total_hours;
    }
    public String getTotal_working_hours() {
        return this.total_working_hours;
    }
    public void setTotal_working_hours(String total_working_hours) {
        this.total_working_hours = total_working_hours;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public Long getStaffId() {
        return this.staffId;
    }
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
    @Generated(hash = 210054657)
    private transient Long staff__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 522720902)
    public Staff getStaff() {
        Long __key = this.staffId;
        if (staff__resolvedKey == null || !staff__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            StaffDao targetDao = daoSession.getStaffDao();
            Staff staffNew = targetDao.load(__key);
            synchronized (this) {
                staff = staffNew;
                staff__resolvedKey = __key;
            }
        }
        return staff;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1508928099)
    public void setStaff(Staff staff) {
        synchronized (this) {
            this.staff = staff;
            staffId = staff == null ? null : staff.getId();
            staff__resolvedKey = staffId;
        }
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 627371751)
    public List<Break> getBreaklist() {
        if (breaklist == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BreakDao targetDao = daoSession.getBreakDao();
            List<Break> breaklistNew = targetDao._queryClock_Breaklist(id);
            synchronized (this) {
                if (breaklist == null) {
                    breaklist = breaklistNew;
                }
            }
        }
        return breaklist;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1611767048)
    public synchronized void resetBreaklist() {
        breaklist = null;
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
    @Generated(hash = 2117050450)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getClockDao() : null;
    }

}
