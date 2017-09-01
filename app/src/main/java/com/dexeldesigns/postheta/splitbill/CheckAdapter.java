package com.dexeldesigns.postheta.splitbill;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dexeldesigns.postheta.R;
import com.dexeldesigns.postheta.common.GlobalClass;
import com.dexeldesigns.postheta.db_tables.model.OrderItems;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Creative IT Works on 01-Aug-17.
 */

public class CheckAdapter extends RecyclerView.Adapter<CheckAdapter.MyViewHolder> {

        Activity context;
        int lastCheckPosition=-1;
        GlobalClass global;
        ImageLoader loader;
        List<OrderItems> order;





public class MyViewHolder extends RecyclerView.ViewHolder  {
    public TextView title,qty,eachitem,total;
    public LinearLayout layout_bg;

    public MyViewHolder(View view) {
        super(view);
        title = (TextView) view.findViewById(R.id.menu_item);
        qty = (TextView) view.findViewById(R.id.qty);
        eachitem = (TextView) view.findViewById(R.id.eachitem);
        total = (TextView) view.findViewById(R.id.total);
        layout_bg = (LinearLayout) view.findViewById(R.id.laybg);

    }


}




    public CheckAdapter(Activity context, List<OrderItems> order) {

        this.context=context;
        global=new GlobalClass();
        loader= ImageLoader.getInstance();
        this.order=order;


    }

    @Override
    public CheckAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)  {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orders_item, parent, false);

        return new CheckAdapter.MyViewHolder(itemView);
    }




    @Override
    public void onBindViewHolder(final CheckAdapter.MyViewHolder holder, final int position) {



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lastCheckPosition=position;
                notifyDataSetChanged();
                SplitFragment.splitmenu=order.get(position);
               /* if(!order.get(position).getQuantity().equalsIgnoreCase("1"))
                {
                    showDialog(position);
                }else {

                }*/
            }
        });




        if(lastCheckPosition==position)
        {
            holder.title.setText( order.get(position).getTitle());
            holder.eachitem.setText( order.get(position).getPrice());
            holder.qty.setText( order.get(position).getQuantity());
            holder.total.setText( order.get(position).getTotal_price_row());
            holder.layout_bg.setBackgroundColor(context.getResources().getColor(R.color.tab_background_selected));
            holder.title.setTextColor(context.getResources().getColor(R.color.orange));
            holder.eachitem.setTextColor(context.getResources().getColor(R.color.orange));
            holder.qty.setTextColor(context.getResources().getColor(R.color.orange));
            holder.total.setTextColor(context.getResources().getColor(R.color.orange));



        }else {
            holder.title.setText( order.get(position).getTitle());
            holder.eachitem.setText( order.get(position).getPrice());
            holder.qty.setText( order.get(position).getQuantity());
            holder.total.setText( order.get(position).getTotal_price_row());
            holder.title.setTextColor(context.getResources().getColor(android.R.color.black));
            holder.eachitem.setTextColor(context.getResources().getColor(android.R.color.black));
            holder.qty.setTextColor(context.getResources().getColor(android.R.color.black));
            holder.total.setTextColor(context.getResources().getColor(android.R.color.black));
            holder.layout_bg.setBackgroundResource(android.R.color.transparent);

        }

    }



    @Override
    public int getItemCount() {
        return  order.size();
    }





}