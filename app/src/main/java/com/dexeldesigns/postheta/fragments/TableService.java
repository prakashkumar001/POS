package com.dexeldesigns.postheta.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dexeldesigns.postheta.MainActivity;
import com.dexeldesigns.postheta.R;
import com.dexeldesigns.postheta.Utils.WSUtils;
import com.dexeldesigns.postheta.common.GlobalClass;

import com.dexeldesigns.postheta.db_tables.model.OrderItems;
import com.dexeldesigns.postheta.db_tables.model.TableDetail;
import com.dexeldesigns.postheta.model.Tables;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.dexeldesigns.postheta.helper.Helper.getHelper;


/**
 * Created by Creative IT Works on 13-Jul-17.
 */

public class TableService extends Fragment {
    ImageView img;
    TextView number;
    RelativeLayout linearLayout1;
    ImageView admin;
    ArrayList<ImageView> imageViewssids;
    String spinselection;
    ArrayAdapter<String> adapter;
    GlobalClass global;
    int globalindex = -1;
    Spinner spinner;
    Button submit,refresh;
    TableDetail tabledata;
    LinearLayout toplay;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.waiter_table_select, container, false);
        init(view);
        global = new GlobalClass();
        global.tableDetails =getHelper().getDaoSession().loadAll(TableDetail.class);
        imageViewssids = new ArrayList<ImageView>();
        toplay.setVisibility(View.VISIBLE);

        showExistingLayout();
        spinAdapter();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);


        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(global.staffType)) {
                    global.staffType = "admin";
                    admin.setImageResource(R.drawable.ok);
                    spinner.setEnabled(true);
                    showExistingLayout();
                } else {
                    global.staffType = "";
                    spinner.setEnabled(false);
                    admin.setImageResource(R.drawable.edit);
                    showExistingLayout();
                }


            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tabledata!=null)
                {
                   // dbHelper.update_table(tabledata);


                    getHelper().getDaoSession().update(tabledata);
                }
                try {
                    postToServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTableStructfromServer();
            }
        });

        return view;
    }

    private void getTableStructfromServer() {
        class SyncServer extends AsyncTask<String,Void,String>
        {
            ArrayList<TableDetail> data;
            ProgressDialog dialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog=new ProgressDialog(getActivity());
                dialog.setMessage("Loading...");
                dialog.show();
            }

            @Override
            protected String doInBackground(String[] params) {

                String response = null;
                response = new WSUtils().getResultFromHttpRequest("http://192.168.1.16/pos/get_table_structure.php","GET",new HashMap<String,String>());
                Log.i("JSON","JSON"+response);
                data=new ArrayList<>();




                return response;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                try {
                    JSONArray array=new JSONArray(result);

                    getHelper().getDaoSession().deleteAll(TableDetail.class);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);
                        String img_name=object.getString("img_name");
                        String imgid=object.getString("imgid");
                        String rotation=object.getString("rotation");
                        String tableX=object.getString("tableX");
                        String tableY=object.getString("tableY");
                        String table_no=object.getString("table_no");

                        TableDetail detail=new TableDetail(imgid,table_no,tableX,tableY,img_name,rotation);

                          Long id= getHelper().getDaoSession().insertOrReplace(detail);
                            detail.setId(id);




                    }
                    showExistingLayout();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();

            }
        }new SyncServer().execute();

    }

    private void addTable() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        String imgname = "";
        img = new ImageView(getActivity());
        number = new TextView(getActivity());
        number.setHint("Enter table number");
        number.setHintTextColor(Color.BLUE);
        if (spinselection.equalsIgnoreCase("2")) {
            img.setImageResource(R.drawable.twoseater);
            imgname = "2";
        } else if (spinselection.equalsIgnoreCase("4")) {
            img.setImageResource(R.drawable.fourseater);
            imgname = "4";
        } else if (spinselection.equalsIgnoreCase("6")) {
            img.setImageResource(R.drawable.sixseater);
            imgname = "6";
        } else if (spinselection.equalsIgnoreCase("8")) {
            img.setImageResource(R.drawable.eightseater);
            imgname = "8";
        }


        img.setId(global.tableDetails.size());
        number.setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout ll4 = new LinearLayout(getActivity());

// ... other methods
        ll4.setGravity(Gravity.CENTER);
        ll4.addView(number);
        number.setTextColor(Color.BLACK);
        TableDetail detail=new TableDetail(String.valueOf(global.tableDetails.size()), number.getText().toString(), "0.0", "0.0", imgname, "0");

        Long id=getHelper().getDaoSession().insertOrReplace(detail);
        detail.setId(id);

        img.setLayoutParams(layoutParams);
        ll4.setLayoutParams(layoutParams);
        linearLayout1.addView(img);
        linearLayout1.addView(ll4);

        if (global.staffType.equalsIgnoreCase("admin")) {


            //img.setOnClickListener(new adminClickListenser(img, number));
            showDialog(img,number);
            img.setOnTouchListener(new ChoiceTouchListener(number,imgname,id));


        }


    }

    private void spinAdapter() {

        ArrayList<String> spindata = new ArrayList<>();
        spindata.add("Select");
        spindata.add("2");
        spindata.add("4");
        spindata.add("6");
        spindata.add("8");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, spindata);
        spinner.setAdapter(adapter);
        spinner.setEnabled(false);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinselection = parent.getItemAtPosition(position).toString();

                if (spinselection.equalsIgnoreCase("Select")) {

                } else {
                    addTable();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void init(View v) {
        admin = (ImageView) v.findViewById(R.id.admin);
        submit=(Button)v.findViewById(R.id.submit);
        linearLayout1 = (RelativeLayout) v.findViewById(R.id.linear);
        spinner = (Spinner) v.findViewById(R.id.spinner);
        refresh= (Button) v.findViewById(R.id.refresh);
        toplay= (LinearLayout) v.findViewById(R.id.toplay);

    }

    private void showExistingLayout() {
        float density = getContext().getResources().getDisplayMetrics().density;




        linearLayout1.removeAllViews();
        linearLayout1.requestLayout();
        global.tableDetails = new ArrayList<>();
        global.tableDetails = getHelper().getDaoSession().loadAll(TableDetail.class);
        if (global.tableDetails.size() == 0) {


            global.tableDetails = new ArrayList<>();
            global.tableDetails = getHelper().getDaoSession().loadAll(TableDetail.class);

        } else {



            for (int i = 0; i < global.tableDetails.size(); i++) {
                TableDetail detail = global.tableDetails.get(i);


                float x = Float.valueOf(detail.tableX);
                float y = Float.parseFloat(detail.tableY);
                int id = Integer.parseInt(detail.imgid);

                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                img = new ImageView(getActivity());
                img.setImageResource(getImageName(detail.img_name));
                img.setRotation(Float.valueOf(detail.rotation));
                number = new TextView(getActivity());
                number.setText(global.tableDetails.get(i).table_no);
                number.setGravity(Gravity.CENTER);
                number.setTextColor(Color.BLACK);

                LinearLayout ll4 = new LinearLayout(getActivity());

                ll4.setGravity(Gravity.CENTER);
                ll4.addView(number);


                layoutParams.leftMargin = Math.round(x*density);
                layoutParams.topMargin = Math.round(y*density);
                layoutParams.alignWithParent = true;

                img.setId(id);
                img.setLayoutParams(layoutParams);
                img.setRotation(Float.valueOf(detail.rotation));

                // number.setLayoutParams(layoutParams);

                ll4.setLayoutParams(layoutParams);
                linearLayout1.addView(img);
                linearLayout1.addView(ll4);
                imageViewssids.add(img);


                selectTableReserve(imageViewssids, img, number);


                if (global.staffType.equalsIgnoreCase("admin")) {


                    //img.setOnClickListener(new adminClickListenser(img, number));
                    // doubleClickListener(img,number);
                    img.setOnTouchListener(new ChoiceTouchListener(number,detail.img_name,detail.getId()));


                }


            }

            global.tableDetails = new ArrayList<>();
            global.tableDetails = getHelper().getDaoSession().loadAll(TableDetail.class);

            if (global.select_tables.size() > 0) {
                for (int ii = 0; ii < global.select_tables.size(); ii++) {


                    for (int k = 0; k < imageViewssids.size(); k++) {
                        if (global.select_tables.get(ii).getTablePosition().equals(String.valueOf(imageViewssids.get(k).getId()))) {
                            if (global.select_tables.get(ii).iscombine.equals("true")) {
                               // imageViewssids.get(k).setBackgroundColor(Color.TRANSPARENT);
                               // imageViewssids.get(k).setBackgroundColor(Color.parseColor("#D3D3D3"));
                                imageViewssids.get(k).setPressed(true);

                            } else {
                                //imageViewssids.get(k).setBackgroundColor(Color.TRANSPARENT);
                                //imageViewssids.get(k).setBackgroundColor(Color.parseColor("#7FFF4081"));
                                imageViewssids.get(k).setPressed(true);
                            }

                            final ImageView image = imageViewssids.get(k);
                            final TextView textView = global.select_tables.get(ii).getTable_number();


                            image.setOnLongClickListener(new View.OnLongClickListener() {
                                @Override
                                public boolean onLongClick(View v) {


                                    global.orders.remove(textView.getText().toString());

                                    if (contains(global.select_tables, textView.getText().toString())) {

                                        if (global.select_tables.get(globalindex).iscombine.equals("true")) {
                                            Toast.makeText(getActivity(), "You cant remove combine table", Toast.LENGTH_SHORT).show();


                                        } else {


                                            global.select_tables.remove(globalindex);
                                            image.setPressed(false);
                                            globalindex = -1;
                                        }


                                    }


                                    return true;
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    public int getImageName(String imgname) {
        switch (Integer.parseInt(imgname)) {
            case 2:
                return R.drawable.two_s_selector;
            case 4:
                return R.drawable.four_s_selector;

            case 6:
                return R.drawable.six_s_selector;

            case 8:
                return R.drawable.eight_s_selector;


            default:
                return 0;


        }


    }

    boolean contains(ArrayList<Tables> list, String product_id) {
        for (Tables item : list) {
            if (item.table_num.equals(product_id)) {

                globalindex = list.indexOf(item);
                return true;
            }
        }

        return false;
    }

    public void selectTableReserve(final ArrayList<ImageView> imageViewsss, final ImageView imageViews, final TextView textView) {
        imageViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ids = imageViews.getId();
                for (int i = 0; i < imageViewsss.size(); i++) {

                    if (imageViewsss.get(i).getId() == ids) {


                        if (contains(global.select_tables, textView.getText().toString())) {

                            global.TableNo = textView.getText().toString();

                            ((MainActivity) getActivity()).loadFragmentsfromTable(global.TableNo);


                        } else {
                           // imageViewsss.get(i).setBackgroundColor(Color.TRANSPARENT);
                           // imageViewsss.get(i).setBackgroundColor(Color.parseColor("#7FFF4081"));
                            imageViewsss.get(i).setPressed(true);

                            global.select_tables.add(new Tables(String.valueOf(i), String.valueOf(imageViewsss.get(i).getId()), textView, textView.getText().toString(), ""));
                            global.TableNo = textView.getText().toString();



                            if(global.orders.get("0").size()>0)
                            {
                                global.orders.put(global.TableNo,global.orders.get("0"));
                                global.orders.put("0",new ArrayList<OrderItems>());
                            }


                            ((MainActivity) getActivity()).loadFragmentsfromTable(global.TableNo);


                        }


                    }


                }
            }
        });


        imageViews.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                for (int i = 0; i < global.select_tables.size(); i++) {
                    if (global.select_tables.get(i).iscombine.equals("true")) {
                        Toast.makeText(getActivity(), "You cant remove combine table", Toast.LENGTH_SHORT).show();

                    } else {
                        imageViews.setPressed(false);
                        if (global.select_tables.get(i).tablePosition.equals(String.valueOf(imageViews.getId()))) {
                            global.select_tables.remove(i);
                        }

                    }

                }

                return false;
            }
        });


    }


    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }


    public void showDialog(final ImageView image, final TextView textView) {
        // custom dialog
        final Dialog dialog = new Dialog(getActivity(), R.style.DialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.remove_table);

        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        dialog.getWindow().setLayout((6 * width) / 10, (6 * height) / 10);

        ImageView cancel = (ImageView) dialog.findViewById(R.id.delete);
        Button rotate = (Button) dialog.findViewById(R.id.rotate);

        final EditText tablenumber = (EditText) dialog.findViewById(R.id.tablenumber);
        Button submit = (Button) dialog.findViewById(R.id.ok);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                textView.setGravity(Gravity.CENTER);
                global.tableDetails = getHelper().getDaoSession().loadAll(TableDetail.class);
                if(TextUtils.isEmpty(tablenumber.getText().toString()))
                {
                    tablenumber.setError("Please Enter Table number");

                }else {

                   if(containsTable(global.tableDetails,tablenumber.getText().toString()))
                    {
                        Toast.makeText(getActivity(),"Already Table number Available",Toast.LENGTH_SHORT).show();
                    }else
                   {
                       for (int i = 0; i < global.tableDetails.size(); i++) {
                           TableDetail detail = global.tableDetails.get(i);

                           if (detail.imgid.equals(String.valueOf(image.getId()))) {



                               detail.table_no = tablenumber.getText().toString();

                               getHelper().getDaoSession().update(detail);
                               //dbHelper.update_table(detail);
                           }


                       }
                       textView.setText(tablenumber.getText().toString());
                   }


                    dialog.dismiss();
                }


            }
        });


        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < global.tableDetails.size(); i++) {


                    if (global.tableDetails.get(i).imgid.equals(String.valueOf(image.getId()))) {

                        float r = Float.valueOf(global.tableDetails.get(i).rotation);
                        if (r == 360) {
                            r = 0;
                            r = r + 90;
                        } else {
                            r = r + 90;
                        }

                        image.setRotation(r);
                        global.tableDetails.get(i).rotation = String.valueOf(r);
                       // dbHelper.update_table(global.tableDetails.get(i));
                        getHelper().getDaoSession().update(global.tableDetails.get(i));

                    }


                }
                linearLayout1.invalidate();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayout1.removeView(image);
                linearLayout1.removeView(textView);


                for (int i = 0; i < global.tableDetails.size(); i++) {


                    if (global.tableDetails.get(i).imgid.equals(String.valueOf(image.getId()))) {

                       // dbHelper.deleteTable(global.tableDetails.get(i));
                        getHelper().getDaoSession().delete(global.tableDetails.get(i));
                        global.tableDetails.remove(i);


                    }

                }

                linearLayout1.invalidate();


            }
        });

        dialog.show();
    }

    private final class ChoiceTouchListener implements View.OnTouchListener {

        private static final int NONE = 0;
        private static final int DRAG = 1;
        private static final int ZOOM = 2;
        private static final long DOUBLE_CLICK_TIME_DELTA = 300;//milliseconds
        RelativeLayout.LayoutParams parms;
        int startwidth;
        int startheight;
        float dx = 0, dy = 0, x = 0, y = 0;
        float angle = 0;
        float scalediff;
        long lastClickTime = 0;
        TextView number;
        private int mode = NONE;
        private float oldDist = 1f;
        private float d = 0f;
        private float newRot = 0f;
        String imgname;
        Long id;

        public ChoiceTouchListener(TextView number,String img_name,Long id) {
            this.number = number;
            this.imgname=img_name;
            this.id=id;

        }


        @Override
        public boolean onTouch(View v, MotionEvent event) {
            final ImageView view = (ImageView) v;

            //((BitmapDrawable) view.getDrawable()).setAntiAlias(true);
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    parms = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    startwidth = parms.width;
                    startheight = parms.height;
                    dx = event.getRawX() - parms.leftMargin;
                    dy = event.getRawY() - parms.topMargin;
                    mode = DRAG;


                    long clickTime = System.currentTimeMillis();
                    if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                        lastClickTime = 0;

                        showDialog(view, number);
                    }

                    lastClickTime = clickTime;

                    break;

                case MotionEvent.ACTION_POINTER_DOWN:
                    oldDist = spacing(event);
                    if (oldDist > 10f) {
                        mode = ZOOM;
                    }

                    d = rotation(event);

                    break;
                case MotionEvent.ACTION_UP:

                    break;

                case MotionEvent.ACTION_POINTER_UP:
                    mode = NONE;

                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mode == DRAG) {


                        view.getParent().requestDisallowInterceptTouchEvent(true);
                        // view.getParent().requestDisallowInterceptTouchEvent(true);

                        x = event.getRawX();
                        y = event.getRawY();

                        parms.leftMargin = (int) (x - dx);
                        parms.topMargin = (int) (y - dy);


                        parms.rightMargin = 0;
                        parms.bottomMargin = 0;

                        //temporary update to db

                      /*  for (int i = 0; i < global.tableDetails.size(); i++) {

                            TableDetail detail = global.tableDetails.get(i);

                            String id = detail.imgid;


                            if (id.equals(String.valueOf(view.getId()))) {

                                detail.tableX = String.valueOf(view.getX());
                                detail.tableY = String.valueOf(view.getY());
                                detail.rotation = String.valueOf(view.getRotation());
                                detail.imgid = String.valueOf(id);

                                dbHelper.update_table(detail);

                            }


                        }*/
                      tabledata=new TableDetail(String.valueOf(view.getId()),number.getText().toString(),String.valueOf(view.getX()),String.valueOf(view.getY()),imgname,String.valueOf(view.getRotation()));
                        tabledata.setId(id);
                        view.setLayoutParams(parms);

                    } else if (mode == ZOOM) {

                        if (event.getPointerCount() == 2) {

                            newRot = rotation(event);
                            float r = newRot - d;
                            angle = r;

                            x = event.getRawX();
                            y = event.getRawY();
                            view.animate().rotationBy(angle).setDuration(0).setInterpolator(new LinearInterpolator()).start();

                            parms.leftMargin = (int) ((x - dx) + scalediff);
                            parms.topMargin = (int) ((y - dy) + scalediff);

                            parms.rightMargin = 0;
                            parms.bottomMargin = 0;
                            parms.rightMargin = parms.leftMargin + (5 * parms.width);
                            parms.bottomMargin = parms.topMargin + (10 * parms.height);


/*

                            for (int i = 0; i < global.tableDetails.size(); i++) {

                                TableDetail detail = global.tableDetails.get(i);
                                String id = detail.imgid;
                                if (id.equals(String.valueOf(view.getId()))) {
                                    detail.tableX = String.valueOf(view.getX());
                                    detail.tableY = String.valueOf(view.getY());
                                    detail.imgid = String.valueOf(id);
                                    detail.rotation = String.valueOf(view.getRotation());
                                    dbHelper.update_table(detail);
                                }

                            }
*/
                            tabledata=new TableDetail(String.valueOf(view.getId()),number.getText().toString(),String.valueOf(view.getX()),String.valueOf(view.getY()),imgname,String.valueOf(view.getRotation()));

                            tabledata.setId(id);
                            view.setLayoutParams(parms);

                        }
                    }


                    break;
            }

            linearLayout1.invalidate();
            return true;

        }

    }

   public void postToServer() throws IOException {



         class uploadServer extends AsyncTask
         {

             @Override
             protected Object doInBackground(Object[] params) {
                 Gson gson=new Gson();

                 String data=gson.toJson(getHelper().getDaoSession().loadAll(TableDetail.class));

                 String response = null;
                 try {
                     response = new WSUtils().responsedetailsfromserver("http://192.168.1.16/pos/post_table_structure.php",data);
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
                 Log.i("JSON","JSON"+response);
                 return null;
             }
         }new uploadServer().execute();




    }
    boolean containsTable(List<TableDetail> list, String table) {
        for (TableDetail item : list) {
            if (item.table_no.equals(table)) {

                return true;
            }
        }

        return false;
    }


}
