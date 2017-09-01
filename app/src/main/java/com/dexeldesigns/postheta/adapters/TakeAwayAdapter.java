package com.dexeldesigns.postheta.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.dexeldesigns.postheta.MainActivity;
import com.dexeldesigns.postheta.R;
import com.dexeldesigns.postheta.common.CustomerInfo;
import com.dexeldesigns.postheta.common.GlobalClass;
import com.dexeldesigns.postheta.model.TakeAway;

import java.util.ArrayList;

/**
 * Created by Creative IT Works on 16-May-17.
 */

public class TakeAwayAdapter extends RecyclerView.Adapter<TakeAwayAdapter.MyViewHolder> {


    Activity context;
    ArrayList<TakeAway> tableList;
    GlobalClass global;
    CustomerInfo info;


    public TakeAwayAdapter(Activity context, ArrayList<TakeAway> tableList) {

        this.context = context;
        this.tableList = tableList;
        global = new GlobalClass();

    }

    @Override
    public TakeAwayAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.takeaway_item, parent, false);

        return new TakeAwayAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TakeAwayAdapter.MyViewHolder holder, final int position) {

        holder.title.setText("TakeAway \n" + tableList.get(position).takeaway_number);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                global.TakeAwayno = String.valueOf(position + 1);
                if (info != null) {
                    global.select_takeaway.add(new TakeAway(String.valueOf(position), global.TakeAwayno, info));

                } else {
                    info = new CustomerInfo("", "", "");
                    global.select_takeaway.add(new TakeAway(String.valueOf(position), global.TakeAwayno, info));

                }

                ((MainActivity) context).loadFragmentsfromTakeAway(global.TakeAwayno);


            }


        });
        holder.user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showuser_info_dialog(position);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
               /* global.TakeAwayno="";
                tableList.get(position).setSelected(false);
                holder.title.setBackgroundColor(0);
                notifyDataSetChanged();
*/

                for (int i = 0; i < global.select_takeaway.size(); i++) {
                    if (global.select_takeaway.get(i).getTablePosition().equals(String.valueOf(position))) {

                        global.select_takeaway.remove(i);
                    }

                }
                tableList.get(position).setSelected(false);

                holder.title.setBackgroundResource(R.drawable.editbox);
                notifyDataSetChanged();
                return false;
            }
        });


        if (global.select_takeaway.size() > 0) {
            for (int i = 0; i < global.select_takeaway.size(); i++) {


                if (position == Integer.parseInt(global.select_takeaway.get(i).tablePosition)) {
                    holder.title.setBackgroundColor(context.getResources().getColor(R.color.orange));
                } else {
                    tableList.get(position).setSelected(false);
                }

            }
        }

    }

    private void showuser_info_dialog(final int positions) {

        // custom dialog
        final Dialog dialog = new Dialog(context, R.style.DialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.customerinfo);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.show();
        dialog.getWindow().setLayout((6 * width) / 10, (6 * height) / 10);

        final EditText name = (EditText) dialog.findViewById(R.id.name);
        final EditText email = (EditText) dialog.findViewById(R.id.email);
        final EditText phonenumber = (EditText) dialog.findViewById(R.id.phone);

        final Button clear = (Button) dialog.findViewById(R.id.clear);
        Button ok = (Button) dialog.findViewById(R.id.ok);
        final Button cancel = (Button) dialog.findViewById(R.id.cancel);



        for (int i = 0; i < global.select_takeaway.size(); i++) {


            if (positions == Integer.parseInt(global.select_takeaway.get(i).tablePosition)) {
                name.setText(global.select_takeaway.get(i).customerInfo.name);
                email.setText(global.select_takeaway.get(i).customerInfo.address);
                phonenumber.setText(global.select_takeaway.get(i).customerInfo.phone_no);

            }

        }



        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info = new CustomerInfo(name.getText().toString(), phonenumber.getText().toString(),email.getText().toString());

                if(global.select_takeaway.size()>0)
                {
                    for (int i = 0; i < global.select_takeaway.size(); i++) {


                        if (positions == Integer.parseInt(global.select_takeaway.get(i).tablePosition)) {
                            global.select_takeaway.set(i, new TakeAway(String.valueOf(positions), global.TakeAwayno, info));

                        } else {
                            global.select_takeaway.add(new TakeAway(String.valueOf(positions), global.TakeAwayno, info));

                        }

                    }
                }else
                {
                    global.select_takeaway.add(new TakeAway(String.valueOf(positions), global.TakeAwayno, info));

                }


                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        name.setText("");
                        phonenumber.setText("");
                        email.setText("");
                    }
                });


                notifyDataSetChanged();
                dialog.dismiss();
            }
        });


        dialog.show();


    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageView user;
        public Spinner no_person;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.table_number);
            user = (ImageView) view.findViewById(R.id.user);

        }
    }


}
