package com.dexeldesigns.postheta.common;

import android.app.Application;
import android.content.Context;

import com.dexeldesigns.postheta.db_tables.model.DaoMaster;
import com.dexeldesigns.postheta.db_tables.model.DaoSession;
import com.dexeldesigns.postheta.db_tables.model.OrderItems;
import com.dexeldesigns.postheta.db_tables.model.TableDetail;
import com.dexeldesigns.postheta.helper.Helper;
import com.dexeldesigns.postheta.model.Order_Detail;
import com.dexeldesigns.postheta.model.Tables;
import com.dexeldesigns.postheta.model.TakeAway;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.greenrobot.greendao.database.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Creative IT Works on 21-Apr-17.
 */

public class GlobalClass extends Application {
    private static GlobalClass mInstance;
    public static Map<String,List<OrderItems>> orders=new TreeMap<>();
    public static ArrayList<Tables> select_tables=new ArrayList<Tables>();
    public static ArrayList<TakeAway> select_takeaway=new ArrayList<TakeAway>();
    public static String TableNo="0";
    public static String TakeAwayno="";
    public static String mOrderType="Quick Order";
    //dummy data need to clear after demo
    public static ArrayList<Order_Detail> order_details=new ArrayList<>();

    public static String tax="18";

    public static Long orderid=null;
    //test
    public static List<TableDetail> tableDetails=new ArrayList<>();


    public static String staffType="";
    public static String staffPin="";
    public static String paytype="";
    Database db;
    public DaoSession daoSession;
    public void onCreate() {



        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "postheta_db");

        // db = helper.getWritableDb();
        db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        new Helper(daoSession, this);
        mInstance = this;

        //clearApplicationData();

        initImageLoader(getApplicationContext());

    }


    public static synchronized GlobalClass getInstance() {
        return mInstance;
    }



    public static void initImageLoader(Context context) {



        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)

                .threadPriority(Thread.NORM_PRIORITY - 2)

                .denyCacheImageMultipleSizesInMemory()

                .discCacheFileNameGenerator(new Md5FileNameGenerator())

                .tasksProcessingOrder(QueueProcessingType.LIFO)

                .build();



        ImageLoader.getInstance().init(config);

    }
}
