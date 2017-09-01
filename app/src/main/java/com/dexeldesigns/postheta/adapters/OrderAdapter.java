package com.dexeldesigns.postheta.adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.dexeldesigns.postheta.R;
import com.dexeldesigns.postheta.common.GlobalClass;
import com.dexeldesigns.postheta.db_tables.model.OrderItems;
import com.dexeldesigns.postheta.db_tables.model.Orders;
import com.dexeldesigns.postheta.fragments.Home;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import static com.dexeldesigns.postheta.fragments.Home.orderlist;
import static com.dexeldesigns.postheta.helper.Helper.getHelper;


/**
 * Created by Creative IT Works on 01-Aug-17.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    Context context;
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




    public OrderAdapter(Context context) {

        this.context=context;
        global=new GlobalClass();
        loader=ImageLoader.getInstance();
        order=global.orders.get(global.TableNo);
        for(int i=0;i<order.size();i++)
        {
            if(order.get(i).getQuantity().equalsIgnoreCase("0.0"))
            {
                order.remove(i);
            }
        }


    }

    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)  {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orders_item, parent, false);

        return new OrderAdapter.MyViewHolder(itemView);
    }




    @Override
    public void onBindViewHolder(final OrderAdapter.MyViewHolder holder, final int position) {

       /* if(order.get(position).getStatus().equalsIgnoreCase("void"))
        {
            order.remove(position);
        }*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                lastCheckPosition=position;
                notifyDataSetChanged();
                showDialog(position);

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


            if(order.get(position).getStatus().equalsIgnoreCase("void")) {
                holder.title.setText( order.get(position).getTitle());
                holder.eachitem.setText( order.get(position).getPrice());
                holder.qty.setText( order.get(position).getQuantity());
                holder.total.setText( order.get(position).getTotal_price_row());
                holder.title.setTextColor(context.getResources().getColor(R.color.orange));
                holder.eachitem.setTextColor(context.getResources().getColor(R.color.orange));
                holder.qty.setTextColor(context.getResources().getColor(R.color.orange));
                holder.total.setTextColor(context.getResources().getColor(R.color.orange));

                holder.layout_bg.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_light));

            }else
            {
                holder.title.setText(order.get(position).getTitle());
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

    }



    @Override
    public int getItemCount() {
        return  order.size();
    }



    public void showDialog(final int positions) {
        // custom dialog
        final Dialog dialog = new Dialog(context, R.style.AppTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_quantity_product_dialog);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.show();
        dialog.getWindow().setLayout((6 * width) / 10, (6 * height) / 10);

        final EditText quants = (EditText) dialog.findViewById(R.id.quantity);
        final EditText ingredients = (EditText) dialog.findViewById(R.id.ingredients);
        ImageView image = (ImageView) dialog.findViewById(R.id.product_image);
        ImageView plus = (ImageView) dialog.findViewById(R.id.plus);
        ImageView minus = (ImageView) dialog.findViewById(R.id.minus);
        ImageView confirm = (ImageView) dialog.findViewById(R.id.confirm);
        ImageView cancel = (ImageView) dialog.findViewById(R.id.cancel);
        Button voids = (Button) dialog.findViewById(R.id.voids);
        TextView product_name = (TextView) dialog.findViewById(R.id.menu_item);//title

        TextView title = (TextView) dialog.findViewById(R.id.title);
        // Bitmap b=loader.loadImageSync(order.get(position).getImageUrl());
        //image.setImageBitmap(b);
        product_name.setText(order.get(positions).getTitle());
        title.setText(order.get(positions).getTitle());
        quants.setText(order.get(positions).getQuantity());
        //ingredients.setText(global.orders.get(String.valueOf(mainposition+1)).get(position).getInformation());

        // Log.i("IIIIIIIIIIIIIII","IIIIIIIIIIIIIII"+order.get(position).getInformation());


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* if(order.get(positions).getStatus().equalsIgnoreCase("Kitchen"))
                {
                    Toast.makeText(context,"you cant edit your order",Toast.LENGTH_SHORT).show();
                }else*/ if(order.get(positions).getStatus().equalsIgnoreCase("void")) {

                }else
                {

                    double totalprice = 0.0;
                    String qty = quants.getText().toString();
                    String information = ingredients.getText().toString();

                    Log.i("Position", "Position" + positions);


                    order.get(positions).setQuantity(qty);
                    totalprice=Double.parseDouble(qty)*Double.parseDouble(order.get(positions).getPrice());
                    order.get(positions).setTotal_price_row(String.valueOf(totalprice));
                    // global.orders.get(String.valueOf(mainposition+1)).get(position).setInformation(information);
                    Home.recyclerAdapter1 = new OrderAdapter(context);
                    orderlist.setAdapter(Home.recyclerAdapter1);

                }




                //  notifyDataSetChanged();
                dialog.dismiss();
                notifyDataSetChanged();


            }
        });

        voids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





               /* order.remove(positions);
                recyclerAdapter1 = new OrderAdapter(context);
                orderlist.setAdapter(recyclerAdapter1);
                recyclerAdapter1.notifyDataSetChanged();
                dialog.dismiss();*/


               if(global.orderid!=null)
               {
                   Orders orderbyid=getHelper().getOrderById(global.orderid);


                       List<OrderItems> orderItemsList=getHelper().getOrderItems(global.orderid);

                       Log.i("OOOOOOOOOOOOOOOOO","OOOOOOOOOOO"+orderItemsList);
                       for(OrderItems orderItems:orderItemsList)
                       {
                           if(orderItems.getProduct_id().equalsIgnoreCase(order.get(positions).product_id) && orderItems.getId()==(order.get(positions).getId()))
                           {
                              double total_quantity= Double.parseDouble(order.get(positions).getQuantity());
                               double void_quantity= Double.parseDouble(quants.getText().toString());
                               double update_quantity=total_quantity-void_quantity;
                               orderItems.setStatus("void");
                               orderItems.setQuantity(String.valueOf(update_quantity));
                               orderItems.setVoid_quantity(String.valueOf(void_quantity));
                               getHelper().getDaoSession().update(orderItems);
                               orderbyid.setIsContainsvoid(true);
                               getHelper().getDaoSession().update(orderbyid);

                           }
                       }
                   Home.recyclerAdapter1 = new OrderAdapter(context);
                   orderlist.setAdapter(Home.recyclerAdapter1);

               }




                notifyDataSetChanged();


               dialog.dismiss();
                  // getHelper().getDaoSession().update();






            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();


                // lastCheckPosition=-1;

               /* holder.title.setTextColor(context.getResources().getColor(android.R.color.black));
                holder.eachitem.setTextColor(context.getResources().getColor(android.R.color.black));
                holder.qty.setTextColor(context.getResources().getColor(android.R.color.black));
                holder.total.setTextColor(context.getResources().getColor(android.R.color.black));
                holder.layout_bg.setBackgroundResource(android.R.color.transparent);*/
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(order.get(positions).getStatus().equalsIgnoreCase("Kitchen") || order.get(positions).getStatus().equalsIgnoreCase("void") )
                {
                    if(order.get(positions).getQuantity().equalsIgnoreCase(quants.getText().toString()))
                    {

                    }else
                    {
                        double quant = Double.parseDouble(quants.getText().toString());
                        quant = quant + 1;
                        quants.setText(String.valueOf(quant));
                    }
                    Toast.makeText(context,"you cant add more your order",Toast.LENGTH_SHORT).show();
                }else {
                    double quant = Double.parseDouble(quants.getText().toString());
                    quant = quant + 1;
                    quants.setText(String.valueOf(quant));
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                if(order.get(positions).getStatus().equalsIgnoreCase("Kitchen"))
                {
                    Toast.makeText(context,"you cant edit your order",Toast.LENGTH_SHORT).show();
                }else*/
                    double quant = Double.parseDouble(quants.getText().toString());
                    if (quant == 1) {

                    } else {
                        quant = quant - 1;
                        quants.setText(String.valueOf(quant));

                    }



            }
        });


        dialog.show();
    }

}

