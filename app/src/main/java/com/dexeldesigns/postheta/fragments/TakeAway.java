package com.dexeldesigns.postheta.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dexeldesigns.postheta.R;
import com.dexeldesigns.postheta.Utils.Utility;
import com.dexeldesigns.postheta.adapters.TakeAwayAdapter;
import com.dexeldesigns.postheta.common.CustomerInfo;
import com.dexeldesigns.postheta.common.GlobalClass;

import java.util.ArrayList;

/**
 * Created by Creative IT Works on 18-Jul-17.
 */

public class TakeAway extends Fragment {

    RecyclerView table_list;
    TakeAwayAdapter tableAdapter;
    GlobalClass global;
    ArrayList<com.dexeldesigns.postheta.model.TakeAway> tables;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.table_view,container,false);

        table_list=(RecyclerView)v.findViewById(R.id.table);

        global=new GlobalClass();
        tables=new ArrayList<com.dexeldesigns.postheta.model.TakeAway>();

        tables.add(new com.dexeldesigns.postheta.model.TakeAway("","1",new CustomerInfo("","","")));
        tables.add(new  com.dexeldesigns.postheta.model.TakeAway("","2",new CustomerInfo("","","")));
        tables.add(new  com.dexeldesigns.postheta.model.TakeAway("","3",new CustomerInfo("","","")));
        tables.add(new  com.dexeldesigns.postheta.model.TakeAway("","4",new CustomerInfo("","","")));
        tables.add(new  com.dexeldesigns.postheta.model.TakeAway("","5",new CustomerInfo("","","")));
        tables.add(new  com.dexeldesigns.postheta.model.TakeAway("","6",new CustomerInfo("","","")));
        tables.add(new  com.dexeldesigns.postheta.model.TakeAway("","7",new CustomerInfo("","","")));
        tables.add(new  com.dexeldesigns.postheta.model.TakeAway("","8",new CustomerInfo("","","")));
        tables.add(new  com.dexeldesigns.postheta.model.TakeAway("","9",new CustomerInfo("","","")));
        tables.add(new  com.dexeldesigns.postheta.model.TakeAway("","10",new CustomerInfo("","","")));
        tables.add(new  com.dexeldesigns.postheta.model.TakeAway("","11",new CustomerInfo("","","")));
        tables.add(new  com.dexeldesigns.postheta.model.TakeAway("","12",new CustomerInfo("","","")));
        tables.add(new  com.dexeldesigns.postheta.model.TakeAway("","13",new CustomerInfo("","","")));
        tables.add(new  com.dexeldesigns.postheta.model.TakeAway("","14",new CustomerInfo("","","")));
        tables.add(new  com.dexeldesigns.postheta.model.TakeAway("","15",new CustomerInfo("","","")));
        tables.add(new  com.dexeldesigns.postheta.model.TakeAway("","16",new CustomerInfo("","","")));
        tables.add(new  com.dexeldesigns.postheta.model.TakeAway("","17",new CustomerInfo("","","")));
        tables.add(new  com.dexeldesigns.postheta.model.TakeAway("","18",new CustomerInfo("","","")));
        tables.add(new  com.dexeldesigns.postheta.model.TakeAway("","19",new CustomerInfo("","","")));
        tables.add(new  com.dexeldesigns.postheta.model.TakeAway("","20",new CustomerInfo("","","")));

        int columns= Utility.calculateNoOfColumns(getActivity());
        tableAdapter=new TakeAwayAdapter(getActivity(),tables);
        GridLayoutManager llms  = new GridLayoutManager(getActivity(),4);
        llms.setOrientation(GridLayoutManager.VERTICAL);
        table_list.setLayoutManager(llms);
        table_list.setAdapter(tableAdapter);

        return v;
    }


}
