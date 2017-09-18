package com.dexeldesigns.postheta.adapters;

/**
 * Created by Creative IT Works on 21-Apr-17.
 */


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.dexeldesigns.postheta.R;
import com.dexeldesigns.postheta.db_tables.model.Product;
import com.dexeldesigns.postheta.fragments.Home;
import com.dexeldesigns.postheta.helper.ItemTouchHelperAdapter;
import com.dexeldesigns.postheta.helper.SimpleItemTouchHelperCallback;

import java.util.Collections;
import java.util.List;

import static com.dexeldesigns.postheta.helper.Helper.getHelper;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.MyViewHolder> implements ItemTouchHelperAdapter {

    private List<Product> moviesList;
    ProductListAdapter productListAdapter;
    Activity context;
    int lastCheckPosition=-1;
    ProgressDialog dialog;
    ItemTouchHelper mItemTouchHelper;

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(moviesList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        notifyItemChanged(fromPosition);
        notifyItemChanged(toPosition);

        getHelper().getDaoSession().deleteAll(Product.class);

        loadMenuItems();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public LinearLayout layout_bg;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.menu_item);
            layout_bg = (LinearLayout) view.findViewById(R.id.laybg);

        }
    }


    public MenuListAdapter(Activity context, List<Product> moviesList) {
        this.moviesList = moviesList;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_list_iem, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new ProgressDialog(context);
                dialog.setMessage("Loading....");
                dialog.show();


                final int SPLASH_DISPLAY_TIME = 2000;
                new Handler().postDelayed(new Runnable() {
                    public void run() {



                      loadData();


                    }
                }, SPLASH_DISPLAY_TIME);



                lastCheckPosition=position;
                notifyDataSetChanged();
            }
        });





        if(lastCheckPosition==position)
        {
            holder.title.setText(moviesList.get(position).getTitle());
            holder.title.setTextColor(context.getResources().getColor(R.color.orange));
            holder.layout_bg.setBackgroundResource(R.color.tab_background_unselected);


        }else {
           holder.title.setText(moviesList.get(position).getTitle());
            holder.title.setTextColor(context.getResources().getColor(android.R.color.black));
            holder.layout_bg.setBackgroundResource(android.R.color.transparent);

        }

    }



    @Override
    public int getItemCount() {
        return moviesList.size();
    }


    public void loadData()
    {
      class loadTask extends AsyncTask<String,Void,String>
       {

           @Override
           protected String doInBackground(String... params) {

               String result="";

               return result;
           }

           @Override
           protected void onPostExecute(String aVoid) {
               super.onPostExecute(aVoid);
               dialog.dismiss();
               productListAdapter=new ProductListAdapter(context,moviesList);
               GridLayoutManager llms  = new GridLayoutManager(context, 3);
               llms.setOrientation(GridLayoutManager.VERTICAL);
               Home.productlist.setLayoutManager(llms);
               ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(productListAdapter);
               mItemTouchHelper = new ItemTouchHelper(callback);
               mItemTouchHelper.attachToRecyclerView(Home.productlist);
               Home.productlist.setAdapter(productListAdapter);
           }
       }new loadTask().execute();
    }

   void loadMenuItems()
    {
        for (int i = 0; i < moviesList.size(); i++) {
        getHelper().insertOrUpdateProduct(moviesList.get(i));
            moviesList.get(i).setId(Long.parseLong(String.valueOf(i)));


    }

    }
}