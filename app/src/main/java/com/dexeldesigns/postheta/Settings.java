package com.dexeldesigns.postheta;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dexeldesigns.postheta.db_tables.model.Discount;
import com.dexeldesigns.postheta.db_tables.model.GST;
import com.dexeldesigns.postheta.fragments.Home;

import static com.dexeldesigns.postheta.helper.Helper.getHelper;

/**
 * Created by Creative IT Works on 10-Aug-17.
 */

public class Settings extends Fragment {
    EditText gstValue, discount;
    Button submit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings, container, false);
        discount = (EditText) view.findViewById(R.id.discount);
        gstValue = (EditText) view.findViewById(R.id.gst);
        submit = (Button) view.findViewById(R.id.submit);

        if (getHelper().getGST() != null) {
            gstValue.setText(getHelper().getGST().getGstamount());
        }


        if (getHelper().getDiscount() != null) {
            discount.setText(getHelper().getDiscount().getDiscountValue());
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gstvalue = gstValue.getText().toString();
                String discountvalue = discount.getText().toString();

               /* if(getHelper().getGST()!=null)
                {
                    GST gstdata=getHelper().getGST();
                    gstdata.setGstamount(gstvalue);
                    getHelper().getDaoSession().update(gstdata);
                }else
                {*/

                Discount discount = new Discount();
                discount.setId(Long.parseLong("1"));
                discount.setDiscountValue(discountvalue);
                getHelper().getDaoSession().insertOrReplace(discount);


                GST gstdata = new GST();
                gstdata.setId(Long.parseLong("1"));
                gstdata.setGstamount(gstvalue);
                getHelper().getDaoSession().insertOrReplace(gstdata);

                //  }

                Home fragments = new Home();
                loadFragment(fragments);


            }
        });

        return view;
    }

    public void loadFragment(Fragment fragments) {


        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fadeinact, R.anim.fadeoutact);
        ft.replace(R.id.container, fragments, fragments.getClass().getSimpleName()).commit();

    }

}
