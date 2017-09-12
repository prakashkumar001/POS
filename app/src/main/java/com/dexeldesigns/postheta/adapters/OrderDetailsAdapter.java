package com.dexeldesigns.postheta.adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.dexeldesigns.postheta.R;
import com.dexeldesigns.postheta.Utils.OrderTotal;
import com.dexeldesigns.postheta.common.GlobalClass;
import com.dexeldesigns.postheta.db_tables.model.OrderItems;
import com.dexeldesigns.postheta.db_tables.model.Orders;
import com.dexeldesigns.postheta.fragments.Home;

import java.util.List;

import static com.dexeldesigns.postheta.helper.Helper.getHelper;


/**
 * Created by Creative IT Works on 21-Jun-17.
 */

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.MyViewHolder> {


    Context context;
    int lastCheckPosition = -1;
    GlobalClass globalClass;
    List<Orders> order_detail;
    Dialog dialog;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tablenumber,status,type,progress;
        public LinearLayout layout_bg;

        public MyViewHolder(View view) {
            super(view);
            tablenumber = (TextView) view.findViewById(R.id.tablenumber);
            status = (TextView) view.findViewById(R.id.status);
            type = (TextView) view.findViewById(R.id.type);
            progress = (TextView) view.findViewById(R.id.progress);

            layout_bg = (LinearLayout) view.findViewById(R.id.laybg);

        }
    }


    public OrderDetailsAdapter(Context context, List<Orders> order_detail, Dialog dialog) {

        this.context = context;
        globalClass=new GlobalClass();
        this.order_detail=order_detail;
        this.dialog=dialog;
    }

    @Override
    public OrderDetailsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_detail_list_item, parent, false);

        return new OrderDetailsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderDetailsAdapter.MyViewHolder holder, final int position) {



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                globalClass.orderid=order_detail.get(position).getId();
                if(order_detail.get(position).getTable_no()==null)
                {

                    globalClass.TableNo="0";

                }


                List<OrderItems> orderItems=getHelper().getOrderItems(globalClass.orderid);

                Log.i("ORDER","ORDER"+ globalClass.orderid);
                globalClass.orders.put(globalClass.TableNo,orderItems);

                Home.recyclerAdapter1=new OrderAdapter(context);
                Home.orderlist.setAdapter(Home.recyclerAdapter1);
                notifyDataSetChanged();
                OrderTotal total=new OrderTotal(context);
                total.totals();


                dialog.dismiss();



            }
        });


        if (lastCheckPosition == position) {
            holder.tablenumber.setText(order_detail.get(position).getTable_no());
            //holder.progress.setText(order_detail.get(position).());
            holder.status.setText(order_detail.get(position).getOrderStatus());
            holder.type.setText(order_detail.get(position).getOrderType());
            holder.layout_bg.setBackgroundResource(R.color.tab_background_unselected);


        } else {
            holder.tablenumber.setText(order_detail.get(position).getTable_no());
            //holder.progress.setText(order_detail.get(position).progress);
            holder.status.setText(order_detail.get(position).getOrderStatus());
            holder.type.setText(order_detail.get(position).getOrderType());
            holder.layout_bg.setBackgroundResource(android.R.color.transparent);

        }

    }


    @Override
    public int getItemCount() {
        return order_detail.size();
    }



}
