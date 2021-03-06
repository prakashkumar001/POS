package com.dexeldesigns.postheta.adapters;

/**
 * Created by Creative IT Works on 21-Apr-17.
 */

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.dexeldesigns.postheta.R;
import com.dexeldesigns.postheta.Utils.OrderTotal;
import com.dexeldesigns.postheta.common.GlobalClass;
import com.dexeldesigns.postheta.db_tables.model.OrderItems;
import com.dexeldesigns.postheta.db_tables.model.Product;
import com.dexeldesigns.postheta.db_tables.model.SubCategories;
import com.dexeldesigns.postheta.fragments.Home;
import com.dexeldesigns.postheta.helper.ItemTouchHelperAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.dexeldesigns.postheta.helper.Helper.getHelper;


public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder>  {

    ImageLoader loader;
    GlobalClass global;
    Activity context;
    double d;
    double totalvalue = 0.0;
    private List<SubCategories> moviesList=new ArrayList<>();


    public ProductListAdapter(Activity context, List<SubCategories> moviesList) {
        loader = ImageLoader.getInstance();

        this.moviesList = moviesList;
        this.context = context;
        global = new GlobalClass();
        //global.selectedProduct=new ArrayList<>();


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.produt_items, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.loadimage) // resource or drawable
                .showImageForEmptyUri(R.drawable.loadimage) // resource or drawable
                .showImageOnFail(R.drawable.loadimage) // resource or drawable
                .resetViewBeforeLoading(false)  // default
                .delayBeforeLoading(10)
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .build();

        holder.title.setText(moviesList.get(position).getTitle());
       loader.displayImage(moviesList.get(position).getImageUrl(), holder.product_image, options);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(global.orderid!=null)
                {
                    if(getHelper().getOrderById(global.orderid).getPayment_status().equalsIgnoreCase("Paid"))
                    {
                        Toast.makeText(context,"Please generate a new order",Toast.LENGTH_SHORT).show();

                    }else
                    {
                        if (global.orders.containsKey(global.TableNo)) {

                            if(containsProduct(global.orders.get(global.TableNo),moviesList.get(position).getSubCategoryId()))
                            {
                                Toast.makeText(context,"Already Added",Toast.LENGTH_SHORT).show();
                            }else
                            {
                                global.orders.get(global.TableNo).add(new OrderItems(moviesList.get(position).getSubCategoryId(), moviesList.get(position).quantity, moviesList.get(position).price, moviesList.get(position).title, moviesList.get(position).imageUrl, moviesList.get(position).totalPricerow,"",new Date().toString(),moviesList.get(position).discountQtyapplyfor,moviesList.get(position).discountforparticularItems,moviesList.get(position).isoveralldiscountavailable));

                            }



                        } else {
                            double discount=  calculateDiscountForItem(moviesList.get(position));

                            double totalprice=discount+Double.parseDouble(moviesList.get(position).totalPricerow);

                            global.orders.get("0").add(new OrderItems(moviesList.get(position).getSubCategoryId(), moviesList.get(position).quantity, moviesList.get(position).price, moviesList.get(position).title, moviesList.get(position).imageUrl, String.valueOf(totalprice),"",new Date().toString(),moviesList.get(position).discountQtyapplyfor,moviesList.get(position).discountforparticularItems,moviesList.get(position).isoveralldiscountavailable));


                        }

                        Toast.makeText(context,moviesList.get(position).getCategory_id().toString(),Toast.LENGTH_SHORT).show();

                        orderDetail();
                    }
                }else
                {
                    if (global.orders.containsKey(global.TableNo)) {

                        if(containsProduct(global.orders.get(global.TableNo),moviesList.get(position).getSubCategoryId()))
                        {
                            Toast.makeText(context,"Already Added",Toast.LENGTH_SHORT).show();
                        }else
                        {
                            global.orders.get(global.TableNo).add(new OrderItems(moviesList.get(position).getSubCategoryId(), moviesList.get(position).quantity, moviesList.get(position).price, moviesList.get(position).title, moviesList.get(position).imageUrl, moviesList.get(position).totalPricerow,"",new Date().toString(),moviesList.get(position).discountQtyapplyfor,moviesList.get(position).discountforparticularItems,moviesList.get(position).isoveralldiscountavailable));

                        }



                    } else {
                        double discount=  calculateDiscountForItem(moviesList.get(position));

                        double totalprice=discount+Double.parseDouble(moviesList.get(position).totalPricerow);

                        global.orders.get("0").add(new OrderItems(moviesList.get(position).getSubCategoryId(), moviesList.get(position).quantity, moviesList.get(position).price, moviesList.get(position).title, moviesList.get(position).imageUrl, String.valueOf(totalprice),"",new Date().toString(),moviesList.get(position).discountQtyapplyfor,moviesList.get(position).discountforparticularItems,moviesList.get(position).isoveralldiscountavailable));


                    }

                    Toast.makeText(context,moviesList.get(position).getCategory_id().toString(),Toast.LENGTH_SHORT).show();

                    orderDetail();
                }





            }
        });





    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }






    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public LinearLayout layout_bg;
        public ImageView product_image;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.menu_item);
            layout_bg = (LinearLayout) view.findViewById(R.id.laybg);
            product_image = (ImageView) view.findViewById(R.id.product_image);

        }


    }
    public void orderDetail() {


        Home.recyclerAdapter1 = new OrderAdapter(context);
        Home.orderlist.setAdapter(Home.recyclerAdapter1);
        Home.recyclerAdapter1.notifyDataSetChanged();
        OrderTotal total=new OrderTotal(context);
        total.totals();

        totalvalue = 0.0;




    }
    boolean containsProduct(List<OrderItems> list, String productid) {
        for (OrderItems item : list) {
            if (item.product_id.equals(productid) && item.getStatus().equalsIgnoreCase("")) {

                return true;
            }
        }

        return false;
    }

    public double calculateDiscountForItem(SubCategories product)
    {
        Double discount_amount=0.0 ;

        if(product.discountQtyapplyfor.equalsIgnoreCase(product.quantity))
        {
             discount_amount=Double.parseDouble(product.price)*(Double.parseDouble(product.discountforparticularItems)/100);

        }else
        {

        }

        return discount_amount;
    }

}