package com.dexeldesigns.postheta.db_tables.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Creative IT Works on 01-Sep-17.
 */

@Entity
public class Break {
    @Id
    Long id;
    private String break_start_time;
    private String break_end_time;
    private String total_break_time;
    private String StaffId;

    Long clockId;
    @ToOne(joinProperty = "clockId")
    private Clock clock;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 2084034239)
    private transient BreakDao myDao;
    @Generated(hash = 1377155062)
    public Break(Long id, String break_start_time, String break_end_time,
            String total_break_time, String StaffId, Long clockId) {
        this.id = id;
        this.break_start_time = break_start_time;
        this.break_end_time = break_end_time;
        this.total_break_time = total_break_time;
        this.StaffId = StaffId;
        this.clockId = clockId;
    }
    @Generated(hash = 1769291149)
    public Break() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBreak_start_time() {
        return this.break_start_time;
    }
    public void setBreak_start_time(String break_start_time) {
        this.break_start_time = break_start_time;
    }
    public String getBreak_end_time() {
        return this.break_end_time;
    }
    public void setBreak_end_time(String break_end_time) {
        this.break_end_time = break_end_time;
    }
    public String getTotal_break_time() {
        return this.total_break_time;
    }
    public void setTotal_break_time(String total_break_time) {
        this.total_break_time = total_break_time;
    }
    public String getStaffId() {
        return this.StaffId;
    }
    public void setStaffId(String StaffId) {
        this.StaffId = StaffId;
    }
    public Long getClockId() {
        return this.clockId;
    }
    public void setClockId(Long clockId) {
        this.clockId = clockId;
    }
    @Generated(hash = 1057748879)
    private transient Long clock__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 291219457)
    public Clock getClock() {
        Long __key = this.clockId;
        if (clock__resolvedKey == null || !clock__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ClockDao targetDao = daoSession.getClockDao();
            Clock clockNew = targetDao.load(__key);
            synchronized (this) {
                clock = clockNew;
                clock__resolvedKey = __key;
            }
        }
        return clock;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 107859307)
    public void setClock(Clock clock) {
        synchronized (this) {
            this.clock = clock;
            clockId = clock == null ? null : clock.getId();
            clock__resolvedKey = clockId;
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
    @Generated(hash = 1650394701)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBreakDao() : null;
    }
}