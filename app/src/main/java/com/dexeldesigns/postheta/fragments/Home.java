package com.dexeldesigns.postheta.fragments;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dexeldesigns.postheta.Login;
import com.dexeldesigns.postheta.R;
import com.dexeldesigns.postheta.Utils.OrderTotal;
import com.dexeldesigns.postheta.adapters.MenuListAdapter;
import com.dexeldesigns.postheta.adapters.OrderAdapter;
import com.dexeldesigns.postheta.adapters.OrderDetailsAdapter;
import com.dexeldesigns.postheta.adapters.ProductListAdapter;
import com.dexeldesigns.postheta.common.GlobalClass;
import com.dexeldesigns.postheta.db_tables.model.Categories;
import com.dexeldesigns.postheta.db_tables.model.OrderItems;
import com.dexeldesigns.postheta.db_tables.model.Orders;
import com.dexeldesigns.postheta.db_tables.model.Product;
import com.dexeldesigns.postheta.db_tables.model.SubCategories;
import com.dexeldesigns.postheta.helper.SimpleItemTouchHelperCallback;
import com.dexeldesigns.postheta.model.Tables;
import com.dexeldesigns.postheta.payment.PaymentFragment;
import com.dexeldesigns.postheta.splitbill.SplitFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.dexeldesigns.postheta.helper.Helper.getHelper;


/**
 * Created by Creative IT Works on 11-Jul-17.
 */

public class Home extends Fragment {
    public static TextView total,subtotal,tax,discount;
    public static RecyclerView menulist, productlist, orderlist;
    public static OrderAdapter recyclerAdapter1;
    public String tableNo, takewayno;
    Bundle bundle;
    TabLayout tabLayout, tabLayoutHeader;
    LinearLayout table_data;
    TextView table_no, clock_in_time, clock_in_Timer;
    ImageView cancel_order, confirm_order, staff_clock, addcheck, payment;
    MenuListAdapter menuListAdapter;
    List<Categories> data;
    List<SubCategories> subCategories;
    GlobalClass global;
    Button split, order_detail,clear,hold;
    private ItemTouchHelper mItemTouchHelper;
    public static ProductListAdapter productListAdapter;
    Orders orders;
    int index=-1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dashboard, container, false);
        global = new GlobalClass();
        init(view);
        setupTabLayout();
        global.staffType = "";
        orders = new Orders();
        bundle = this.getArguments();

        if (bundle != null) {

            tableNo = bundle.getString("TableNo");
            takewayno = bundle.getString("TakeAwayno");
            menulist();
            resultFromTable();

            if (tableNo != null) {


                new Handler().postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                // table_edit_selection.setEnabled(true);
                                tabLayout.setScrollPosition(0, 0f, true);


                            }
                        }, 100);
            } else if (takewayno != null) {
                new Handler().postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                global.TableNo="0";

                                table_no.setText("Take Away No. \n" + global.TakeAwayno);

                                tabLayout.setScrollPosition(1, 0f, true);


                            }
                        }, 100);
            }

            // tabLayout.getTabAt(1).select();

        } else {

            menulist();

            global.orders.put("0", new ArrayList<OrderItems>());
            // global.completeorder.put(global.TableNo, global.orders);

            recyclerAdapter1 = new OrderAdapter(getActivity());
            LinearLayoutManager ll = new LinearLayoutManager(getActivity());
            ll.setOrientation(LinearLayoutManager.VERTICAL);
            orderlist.setLayoutManager(ll);

            orderlist.setAdapter(recyclerAdapter1);
        }





hold.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if(global.orderid!=null)
        {
            if( getHelper().getOrderById(global.orderid).getPayment_status()==null)
            {
                Toast.makeText(getActivity(),"This order wont be hold",Toast.LENGTH_SHORT).show();
            }else if(getHelper().getOrderById(global.orderid).getIsHold()==true) {
                Toast.makeText(getActivity(),"Already this order is on hold",Toast.LENGTH_SHORT).show();
            }else
            {

                Toast.makeText(getActivity(),"This Order will be hold",Toast.LENGTH_SHORT).show();

                Orders orders=getHelper().getOrderById(global.orderid);
                orders.setIsHold(true);
                getHelper().getDaoSession().update(orders);


                global.TableNo="0";
                table_no.setText("");
                global.orderid=null;
                global.orders=new HashMap<String, List<OrderItems>>();
                global.orders.put(global.TableNo,new ArrayList<OrderItems>());
                resetDiplayamount();
                recyclerAdapter1=new OrderAdapter(getActivity());
                orderlist.setAdapter(recyclerAdapter1);
            }
        }
    }
});



       /* //global.staffSelection = "";

        global.orders.put("0",new ArrayList<OrderItems>());
        // global.completeorder.put(global.TableNo, global.orders);

        recyclerAdapter1 = new OrderAdapter(getActivity());
        LinearLayoutManager ll = new LinearLayoutManager(getActivity());
        ll.setOrientation(LinearLayoutManager.VERTICAL);
        orderlist.setLayoutManager(ll);
        orderlist.setAdapter(recyclerAdapter1);
            menulist();
*/


        confirm_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /*   orders.setOrderTime(new Date().toString());
                orders.setIsSync(false);
                orders.setOrderItems(global.orders.get(global.TableNo));
*/


                if (global.orderid != null) {
                    for (int i = 0; i < global.orders.get(global.TableNo).size(); i++) {
                        if (global.orders.get(global.TableNo).get(i).getStatus().equalsIgnoreCase("")) {

                            Log.i("ORDER_ID", "ORDER_ID" + global.orderid);
                            OrderItems order = new OrderItems();
                            global.orders.get(global.TableNo).get(i).setStatus("Kitchen");
                            order.setProduct_id(global.orders.get(global.TableNo).get(i).getProduct_id());
                            order.setTitle(global.orders.get(global.TableNo).get(i).getTitle());
                            order.setImageUrl(global.orders.get(global.TableNo).get(i).getImageUrl());
                            order.setQuantity(global.orders.get(global.TableNo).get(i).getQuantity());
                            order.setPrice(global.orders.get(global.TableNo).get(i).getPrice());
                            order.setTotal_price_row(global.orders.get(global.TableNo).get(i).getTotal_price_row());
                            order.setStatus(global.orders.get(global.TableNo).get(i).getStatus());
                            order.setOrderId(global.orderid);
                            Long id = getHelper().getDaoSession().insert(order);
                            order.setId(id);


                        }
                    }


                    Orders orders1=getHelper().getOrderById(global.orderid);
                    orders1.setGst_amount(tax.getText().toString());
                    orders1.setSubTotal(subtotal.getText().toString());
                    orders1.setTotal(total.getText().toString());
                    getHelper().getDaoSession().update(orders1);

                    global.orderid=null;
                    global.TableNo="0";

                    Toast.makeText(getActivity(), "Order Sent", Toast.LENGTH_SHORT).show();

                    global.orders.put(global.TableNo,new ArrayList<OrderItems>());
                    recyclerAdapter1=new OrderAdapter(getActivity());
                    orderlist.setAdapter(recyclerAdapter1);



                    logout();

                } else {
                    orders.setOrderTime(new Date().toString());
                    orders.setIsSync(false);
                    orders.setOrderStatus("Kitchen");
                    orders.setIsContainsvoid(false);
                    if (global.TableNo.equalsIgnoreCase("0")) {
                        orders.setOrderType("TAKE AWAY");
                        orders.setTakeAwayno(global.TakeAwayno);
                    } else {
                        orders.setOrderType("DINE IN");
                        orders.setTable_no(global.TableNo);
                    }

                    orders.setGst_amount(tax.getText().toString());
                    orders.setSubTotal(subtotal.getText().toString());
                    orders.setPayment_status("Un Paid");
                    orders.setTotal(total.getText().toString());
                    orders.setIsHold(false);

                    Long id = getHelper().getDaoSession().insertOrReplace(orders);
                    orders.setId(id);

                    for (int i = 0; i < global.orders.get(global.TableNo).size(); i++) {
                        OrderItems order = new OrderItems();
                        global.orders.get(global.TableNo).get(i).setStatus("Kitchen");
                        order.setProduct_id(global.orders.get(global.TableNo).get(i).getProduct_id());
                        order.setTitle(global.orders.get(global.TableNo).get(i).getTitle());
                        order.setImageUrl(global.orders.get(global.TableNo).get(i).getImageUrl());
                        order.setQuantity(global.orders.get(global.TableNo).get(i).getQuantity());
                        order.setPrice(global.orders.get(global.TableNo).get(i).getPrice());
                        order.setTotal_price_row(global.orders.get(global.TableNo).get(i).getTotal_price_row());
                        order.setStatus(global.orders.get(global.TableNo).get(i).getStatus());
                        order.setOrderId(id);
                        Long ids = getHelper().getDaoSession().insert(order);
                        order.setId(ids);


                    }
                    global.orderid=null;
                    global.TableNo="0";

                    Toast.makeText(getActivity(), "Order Sent", Toast.LENGTH_SHORT).show();

                    global.orders.put(global.TableNo,new ArrayList<OrderItems>());
                    recyclerAdapter1=new OrderAdapter(getActivity());
                    orderlist.setAdapter(recyclerAdapter1);



                    logout();
                }



            }
        });

        order_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOrderDialog();
            }
        });

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                global.paytype = "";
                SplitFragment fragments = new SplitFragment();
                Bundle bundle = new Bundle();
                bundle.putString("Totalpayment", total.getText().toString()); // Put anything what you want
                bundle.putString("orderid",String.valueOf(global.orderid));

                loadFragment(fragments);

            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(global.orderid!=null)
                {
                    if(getHelper().getOrderById(global.orderid).getPayment_status()!=null && !getHelper().getOrderById(global.orderid).getPayment_status().equalsIgnoreCase("Un Paid"))
                    {
                        Toast.makeText(getActivity(),"Already Paid ",Toast.LENGTH_SHORT).show();
                    }else {


                        if(containsTable(global.select_tables,global.TableNo))
                        {
                           global.select_tables.remove(index);
                            index=-1;
                        }

                        PaymentFragment fragment = new PaymentFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("Totalpayment", total.getText().toString()); // Put anything what you want
                        bundle.putString("orderid",String.valueOf(global.orderid));
                        fragment.setArguments(bundle);
                        loadFragment(fragment);



                    }
                }else
                {
                    Toast.makeText(getActivity(),"Click confirm to sent order to kitchen then make payment",Toast.LENGTH_SHORT).show();


                }

            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                global.TableNo="0";
                global.orderid=null;
                global.orders=new HashMap<String, List<OrderItems>>();
                global.orders.put(global.TableNo,new ArrayList<OrderItems>());
                resetDiplayamount();
                recyclerAdapter1=new OrderAdapter(getActivity());
                orderlist.setAdapter(recyclerAdapter1);

            }
        });



        return view;

    }


    public void menulist() {
        data = new ArrayList<Categories>();
        data.add(new Categories("1", "1.0", "3.00", "Fusion Spring Roll", "http://www.tellusaboutus.com/comments/images/BK-WebComment/BB_WHOPPER-v1.png", "3.00", ""));
        data.add(new Categories("2", "1.0", "5.00", "Soups", "https://timedotcom.files.wordpress.com/2017/08/pizzahutrewards-em-624200530.jpg", "5.00", ""));
        data.add(new Categories("3", "1.0", "8.00", "Veg roll", "http://paypizzapal.com/wp-content/uploads/2014/01/pizza-hut2.jpg", "8.00", ""));
        data.add(new Categories("4", "1.0", "10.00", "Non-veg", "http://www.ndtv.com/cooks/images/moong-dal-samosa-new.jpg", "10.00", ""));
        data.add(new Categories("5", "1.0", "11.00", "Ice-Cream", "https://timedotcom.files.wordpress.com/2017/08/pizzahutrewards-em-624200530.jpg", "11.00", ""));
        data.add(new Categories("6", "1.0", "13.00", "Tomato sauce", "http://paypizzapal.com/wp-content/uploads/2014/01/pizza-hut2.jpg", "13.00", ""));
        data.add(new Categories("7", "1.0", "8.00", "Fusion chicken", "http://www.tellusaboutus.com/comments/images/BK-WebComment/BB_WHOPPER-v1.png", "8.00", ""));
        data.add(new Categories("8", "1.0", "5.00", "Fried Rice", "http://www.tellusaboutus.com/comments/images/BK-WebComment/BB_WHOPPER-v1.png", "5.00", ""));



        for (int i = 0; i < data.size(); i++) {
            Long id=getHelper().insertOrUpdateCategories(data.get(i));
           // data.get(i).setId(Long.parseLong(String.valueOf(i)));
          //  SubCategories subCategories1=new SubCategories( "1.0", "3.00", "Fusion Spring Roll", "http://www.tellusaboutus.com/comments/images/BK-WebComment/BB_WHOPPER-v1.png", "3.00", "",getHelper().getMenuItems().get(i).getId());

           // getHelper().insertOrUpdateSubCategories(subCategories1);
            subCategories=new ArrayList<>();
            subCategories.add(new SubCategories( "1","1.0", "3.00", "Fusion Spring Roll", "http://www.tellusaboutus.com/comments/images/BK-WebComment/BB_WHOPPER-v1.png", "3.00", "",id,"1","11",true));
            subCategories.add(new SubCategories("2","1.0", "5.00", "Soups", "https://timedotcom.files.wordpress.com/2017/08/pizzahutrewards-em-624200530.jpg", "5.00", "",id,"4","5",false));
            subCategories.add(new SubCategories("3", "1.0", "8.00", "Veg roll", "http://paypizzapal.com/wp-content/uploads/2014/01/pizza-hut2.jpg", "8.00", "",id,"4","6",true));
            subCategories.add(new SubCategories( "4","1.0", "10.00", "Non-veg", "http://www.ndtv.com/cooks/images/moong-dal-samosa-new.jpg", "10.00", "",id,"4","10",true));
            subCategories.add(new SubCategories( "5","1.0", "11.00", "Ice-Cream", "https://timedotcom.files.wordpress.com/2017/08/pizzahutrewards-em-624200530.jpg", "11.00", "",id,"2","0",true));
            subCategories.add(new SubCategories("6","1.0", "13.00", "Tomato sauce", "http://paypizzapal.com/wp-content/uploads/2014/01/pizza-hut2.jpg", "13.00", "",id,"3","10",true));
            subCategories.add(new SubCategories( "7","1.0", "8.00", "Fusion chicken", "http://www.tellusaboutus.com/comments/images/BK-WebComment/BB_WHOPPER-v1.png", "8.00", "",id,"1","1",false));
            subCategories.add(new SubCategories("8","1.0", "5.00", "Fried Rice", "http://www.tellusaboutus.com/comments/images/BK-WebComment/BB_WHOPPER-v1.png", "5.00", "",id,"1","1",true));

            for(int l=0;l<subCategories.size();l++)
            {
                getHelper().insertOrUpdateSubCategories(subCategories.get(l));
            }
        }

       /* for(int i=0;i<getHelper().getMenuItems().size();i++)
        {


        }
*/





            // subCategories.get(i).setId(Long.parseLong(String.valueOf(i)));














        if (getHelper().getMenuItems().size() > 0) {
            List<Categories> datas = new ArrayList<>();
            datas = getHelper().getMenuItems();
            int size = datas.size();
            menuListAdapter = new MenuListAdapter(getActivity(), datas);
        }


        LinearLayoutManager llma = new LinearLayoutManager(getActivity());
        llma.setOrientation(LinearLayoutManager.VERTICAL);
        menulist.setLayoutManager(llma);

        menulist.setAdapter(menuListAdapter);
    }

    private void init(View v) {
        //table_edit_selection = (ImageView) v.findViewById(R.id.table_edit_selection);
        //addcheck = (ImageView) v.findViewById(R.id.addcheck);
       // table_data = (LinearLayout) v.findViewById(R.id.table_data);
        cancel_order = (ImageView) v.findViewById(R.id.cancel_order);
        staff_clock = (ImageView) v.findViewById(R.id.staff_clock);
        confirm_order = (ImageView) v.findViewById(R.id.confirm_order);
        tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        tabLayoutHeader = (TabLayout) v.findViewById(R.id.tab_header);
        menulist = (RecyclerView) v.findViewById(R.id.menu_list);
        productlist = (RecyclerView) v.findViewById(R.id.product_list);
        orderlist = (RecyclerView) v.findViewById(R.id.orderlist);
        total = (TextView) v.findViewById(R.id.total);
        clock_in_time = (TextView) v.findViewById(R.id.clockin_time);
        clock_in_Timer = (TextView) v.findViewById(R.id.clock_timer);
        table_no = (TextView) v.findViewById(R.id.table_no);
        split = (Button) v.findViewById(R.id.split);
        order_detail = (Button) v.findViewById(R.id.order_detail);
        payment = (ImageView) v.findViewById(R.id.payment);
        clear=(Button)v.findViewById(R.id.clear);
        subtotal = (TextView) v.findViewById(R.id.subtotal);
        tax = (TextView) v.findViewById(R.id.tax);
        discount= (TextView) v.findViewById(R.id.discount);
        hold=(Button)v.findViewById(R.id.hold);
    }

    private void setupTabLayout() {

        LinearLayout linearLayout;
        GradientDrawable drawable;
        drawable = new GradientDrawable();
        tabLayout.addTab(tabLayout.newTab().setText("Dine-In"), true);
        tabLayout.addTab(tabLayout.newTab().setText("Take Away"));
        tabLayout.addTab(tabLayout.newTab().setText("Delivery"));
        linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        drawable.setColor(Color.GRAY);
        drawable.setSize(1, 1);
        linearLayout.setDividerPadding(10);
        linearLayout.setDividerDrawable(drawable);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.orange));


        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(getResources().getColor(R.color.tab_background_selected), getResources().getColor(R.color.orange));


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getText().toString().equalsIgnoreCase("Dine-in")) {
                    GlobalClass.mOrderType = tab.getText().toString();
                    //table_data.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), GlobalClass.mOrderType, Toast.LENGTH_SHORT).show();
                    // table_edit_selection.setEnabled(true);

                    //initFragments();

                    BookFragments fragments = new BookFragments();
                    loadFragment(fragments);


                } else if (tab.getText().toString().equalsIgnoreCase("Take Away")) {

                    TakeAway fragments = new TakeAway();
                    loadFragment(fragments);


                } else {

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void loadFragment(Fragment fragments) {


        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fadeinact, R.anim.fadeoutact);
        ft.replace(R.id.container, fragments, fragments.getClass().getSimpleName()).commit();


    }


    public void showOrderDialog() {

        // custom dialog
        final Dialog dialog = new Dialog(getActivity(), R.style.AppTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.order_detail_dialog);
        dialog.getWindow().setGravity(Gravity.CENTER);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        DisplayMetrics metrics = getActivity().getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.show();
        dialog.getWindow().setLayout((8 * width) / 10, (8 * height) / 10);

        RecyclerView recyclerViews = (RecyclerView) dialog.findViewById(R.id.order_detail);

        OrderDetailsAdapter adapter = new OrderDetailsAdapter(getActivity(), getHelper().getAllOrder(), dialog);
        LinearLayoutManager llma = new LinearLayoutManager(getActivity());
        llma.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViews.setLayoutManager(llma);
        recyclerViews.setAdapter(adapter);


        dialog.show();


    }

    void logout() {
        global.orders=new HashMap<>();
        Intent i = new Intent(getActivity(), Login.class);
        getActivity().startActivity(i);
        getActivity().finish();
    }

    public void resultFromTable() {

        if (global.TableNo.equalsIgnoreCase("0")) {

        } else {
            table_no.setText("Table No. \n" + global.TableNo);
        }


        Log.i("GlobalOrders", "GlobalOrders" + global.orders.containsKey(global.TableNo));
        if (global.orders.containsKey(global.TableNo)) {







            recyclerAdapter1 = new OrderAdapter(getActivity());
            LinearLayoutManager ll = new LinearLayoutManager(getActivity());
            ll.setOrientation(LinearLayoutManager.VERTICAL);
            orderlist.setLayoutManager(ll);
            orderlist.setAdapter(recyclerAdapter1);


            OrderTotal total = new OrderTotal(getActivity());
            total.totals();

        } else {

            global.orders.put(global.TableNo, new ArrayList<OrderItems>());
            recyclerAdapter1 = new OrderAdapter(getActivity());
            LinearLayoutManager ll = new LinearLayoutManager(getActivity());
            ll.setOrientation(LinearLayoutManager.VERTICAL);
            orderlist.setLayoutManager(ll);
            orderlist.setAdapter(recyclerAdapter1);
            total.setText("0.0");

        }


    }

    void resetDiplayamount()
    {
        total.setText("0.0");
        subtotal.setText("0.0");
        tax.setText("0.0");
        discount.setText("0.0");
    }


    boolean containsTable(List<Tables> list, String tableNo) {
        for (Tables item : list) {
            if (item.table_num.equals(tableNo)) {

                index=list.indexOf(item);
                return true;
            }
        }

        return false;
    }

}
