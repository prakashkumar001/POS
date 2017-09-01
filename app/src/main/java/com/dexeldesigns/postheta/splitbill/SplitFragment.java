package com.dexeldesigns.postheta.splitbill;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dexeldesigns.postheta.R;
import com.dexeldesigns.postheta.common.GlobalClass;
import com.dexeldesigns.postheta.db_tables.model.OrderItems;
import com.dexeldesigns.postheta.payment.PaymentFragment;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Creative IT Works on 01-Aug-17.
 */

public class SplitFragment extends Fragment {
    public static OrderItems menu;
    public static OrderItems splitmenu;
    RecyclerView orderlist, checklist;
    ImageView right, left;
    Button pay;
    GlobalClass global;
    ImageLoader loader;
    SplitOrderAdapter recyclerAdapter1;
    List<OrderItems> checklistdata,order;
    String quantity;
    CheckAdapter checkadapter;
    int index=-1;
    String getQuantity;
    double totalvalue=0.0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.split_page, container, false);
        global = new GlobalClass();

        order=new ArrayList<>();
        order=global.orders.get(global.TableNo);
        loader = ImageLoader.getInstance();
        init(view);
        checklistdata = new ArrayList<>();
        intialiselist();
       /* menu=null;
        splitmenu=null;*/
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    quantity = menu.getQuantity();
                    showDialogorder();

            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    quantity = splitmenu.getQuantity();
                    showdialogsplit();

            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                global.paytype="split";
                splitcheckTotal();

            }
        });
        return view;

    }

    private void intialiselist() {
        recyclerAdapter1 = new SplitOrderAdapter(getActivity(), order);
        LinearLayoutManager ll = new LinearLayoutManager(getActivity());
        ll.setOrientation(LinearLayoutManager.VERTICAL);
        orderlist.setLayoutManager(ll);
        orderlist.setAdapter(recyclerAdapter1);

        checkadapter = new CheckAdapter(getActivity(), checklistdata);
        LinearLayoutManager lls = new LinearLayoutManager(getActivity());
        lls.setOrientation(LinearLayoutManager.VERTICAL);
        checklist.setLayoutManager(lls);
        checklist.setAdapter(checkadapter);

    }


    void init(View view) {
        orderlist = (RecyclerView) view.findViewById(R.id.orderlist);
        checklist = (RecyclerView) view.findViewById(R.id.checklist);
        right = (ImageView) view.findViewById(R.id.right);
        pay = (Button) view.findViewById(R.id.pay);
        left = (ImageView) view.findViewById(R.id.left);
    }

    public void showDialogorder() {
        // custom dialog
        final Dialog dialog = new Dialog(getActivity(), R.style.DialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.decrease_quantity_split);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        DisplayMetrics metrics = getActivity().getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.show();
        dialog.getWindow().setLayout((8 * width) / 10, (8 * height) / 10);
        final EditText quants = (EditText) dialog.findViewById(R.id.quantity);
        final EditText ingredients = (EditText) dialog.findViewById(R.id.ingredients);
        ImageView image = (ImageView) dialog.findViewById(R.id.product_image);
        ImageView minus = (ImageView) dialog.findViewById(R.id.minus);
        ImageView confirm = (ImageView) dialog.findViewById(R.id.confirm);
        ImageView cancel = (ImageView) dialog.findViewById(R.id.cancel);
        TextView product_name = (TextView) dialog.findViewById(R.id.menu_item);//title

        TextView title = (TextView) dialog.findViewById(R.id.title);
        Bitmap b = loader.loadImageSync(menu.getImageUrl());
        image.setImageBitmap(b);
        product_name.setText(menu.getTitle());
        title.setText(menu.getTitle());
        quants.setText(menu.getQuantity());
        ingredients.setText(menu.getInformation());

        Log.i("IIIIIIIIIIIIIII", "IIIIIIIIIIIIIII" + menu.getInformation());


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qty = quants.getText().toString();
                double menu_quants = Double.parseDouble(quantity) - Double.parseDouble(qty);
                double total_row_prices = Double.parseDouble(qty) * Double.parseDouble(menu.getPrice());
                OrderItems m = new OrderItems(menu.getProduct_id(), String.valueOf(qty) , menu.getPrice(),menu.getTitle(), menu.getImageUrl(), String.valueOf(total_row_prices),menu.getStatus(), menu.getOrder_items_time());

                if(containsProduct(checklistdata,menu.product_id))
                {
                    double quan=Double.parseDouble(getQuantity)+Double.parseDouble(qty);
                    m.setQuantity(String.valueOf(quan));
                   double totalprice=quan*Double.parseDouble(menu.getPrice());
                   m.setTotal_price_row(String.valueOf(totalprice));

                    checklistdata.set(index,m);

                }else {
                    checklistdata.add(m);
                }



                if (menu_quants == 0.0) {
                    order.remove(menu);
                } else {
                    menu.setQuantity(String.valueOf(menu_quants));
                    double total_row_price = Double.parseDouble(menu.getQuantity()) * Double.parseDouble(menu.getPrice());
                    menu.setTotal_price_row(String.valueOf(total_row_price));

                }


                checkadapter = new CheckAdapter(getActivity(), checklistdata);
                checklist.setAdapter(checkadapter);

                recyclerAdapter1.lastCheckPosition = -1;

                recyclerAdapter1.notifyDataSetChanged();
                menu=null;
                index=-1;
                getQuantity="0.0";

                dialog.dismiss();


            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();


            }
        });


        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    public void showdialogsplit()
    {

        final Dialog dialog = new Dialog(getActivity(), R.style.AppTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.decrease_quantity_split);
        dialog.getWindow().setGravity(Gravity.CENTER);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        DisplayMetrics metrics = getActivity().getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.show();
        dialog.getWindow().setLayout((8 * width) / 10, (8 * height) / 10);

            final EditText quants = (EditText) dialog.findViewById(R.id.quantity);
            final EditText ingredients = (EditText) dialog.findViewById(R.id.ingredients);
            ImageView image = (ImageView) dialog.findViewById(R.id.product_image);
            ImageView minus = (ImageView) dialog.findViewById(R.id.minus);
            ImageView confirm = (ImageView) dialog.findViewById(R.id.confirm);
            ImageView cancel = (ImageView) dialog.findViewById(R.id.cancel);
            TextView product_name = (TextView) dialog.findViewById(R.id.menu_item);//title

            TextView title = (TextView) dialog.findViewById(R.id.title);
            Bitmap b = loader.loadImageSync(splitmenu.getImageUrl());
            image.setImageBitmap(b);
            product_name.setText(splitmenu.getTitle());
            title.setText(splitmenu.getTitle());
            quants.setText(splitmenu.getQuantity());
            ingredients.setText(splitmenu.getInformation());

            Log.i("IIIIIIIIIIIIIII", "IIIIIIIIIIIIIII" + splitmenu.getInformation());


            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String qty = quants.getText().toString();
                    double menu_quants = Double.parseDouble(quantity) - Double.parseDouble(qty);
                    double total_row_prices = Double.parseDouble(qty) * Double.parseDouble(splitmenu.getPrice());
                    OrderItems m = new OrderItems(splitmenu.getProduct_id(), String.valueOf(qty) , splitmenu.getPrice(),splitmenu.getTitle(), splitmenu.getImageUrl(), String.valueOf(total_row_prices),splitmenu.getStatus(), splitmenu.getOrder_items_time());
                    if(containsProduct(order,splitmenu.product_id))
                    {

                        double quan=Double.parseDouble(getQuantity)+Double.parseDouble(qty);
                        m.setQuantity(String.valueOf(quan));
                        double totalprice=quan*Double.parseDouble(splitmenu.getPrice());
                        m.setTotal_price_row(String.valueOf(totalprice));

                        order.set(index,m);
                    }else {
                        order.add(m);
                    }

                    if (menu_quants == 0.0) {
                       checklistdata.remove(splitmenu);
                    } else {
                        splitmenu.setQuantity(String.valueOf(menu_quants));
                        double total_row_price = Double.parseDouble(splitmenu.getQuantity()) * Double.parseDouble(splitmenu.getPrice());
                        splitmenu.setTotal_price_row(String.valueOf(total_row_price));

                    }


                    recyclerAdapter1 = new SplitOrderAdapter(getActivity(), order);
                    orderlist.setAdapter(recyclerAdapter1);

                    checkadapter.lastCheckPosition = -1;

                    checkadapter.notifyDataSetChanged();
                    splitmenu=null;
                    index=-1;
                    getQuantity="0.0";
                    dialog.dismiss();


                }
            });


            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();


                }
            });


            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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


    boolean containsProduct(List<OrderItems> list, String product_id) {
        for (OrderItems item : list) {
            if (item.product_id.equals(product_id)) {
                 index=list.indexOf(item);
                getQuantity=item.getQuantity();
                return true;
            }
        }

        return false;
    }
    private void loadFragment(Fragment fragments) {


        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fadeinact, R.anim.fadeoutact);
        ft.replace(R.id.container, fragments, fragments.getClass().getSimpleName()).commit();


    }

   void  splitcheckTotal()
    {
        double d=0.0;
        double totalvalue=0.0;


            for(int k=0;k<checklistdata.size();k++)
            {

                String total=checklistdata.get(k).getTotal_price_row();
                d=d+Double.parseDouble(total);

            }

        totalvalue=totalvalue+d;

        Toast.makeText(getActivity(),String.valueOf(totalvalue),Toast.LENGTH_SHORT).show();

        PaymentFragment fragment=new PaymentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Totalpayment",String.valueOf(totalvalue)); // Put anything what you want
        fragment.setArguments(bundle);
        loadFragment(fragment);

    }


}
