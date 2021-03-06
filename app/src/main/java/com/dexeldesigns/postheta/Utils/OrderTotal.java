package com.dexeldesigns.postheta.Utils;

import android.content.Context;
import android.util.Log;

import com.dexeldesigns.postheta.common.GlobalClass;
import com.dexeldesigns.postheta.db_tables.model.Discount;
import com.dexeldesigns.postheta.db_tables.model.GST;
import com.dexeldesigns.postheta.fragments.Home;

import static com.dexeldesigns.postheta.helper.Helper.getHelper;

/**
 * Created by Creative IT Works on 16-Jun-17.
 */

public class OrderTotal {
    Context context;
    GlobalClass globalClass;

    public OrderTotal(Context context)
    {
        this.context=context;
        globalClass=new GlobalClass();
    }

    double totalvalue=0.0;
    double totalvaluewithoutdiscount=0.0;
    public  void totals()
    {

        double d=0.0;
        double d1=0.0;

        if(globalClass.orders.containsKey(globalClass.TableNo))
        {


                for(int k=0;k<globalClass.orders.get(globalClass.TableNo).size();k++)
                {
/*
                    if(globalClass.orders.get(globalClass.TableNo).get(k).getIsoveralldiscountavailable()==true)
                    {*/
                        String total=globalClass.orders.get(globalClass.TableNo).get(k).getTotal_price_row();
                        d=d+Double.parseDouble(total);
                   /* }else
                    {
                        String totals=globalClass.orders.get(globalClass.TableNo).get(k).getTotal_price_row();
                        d1=d1+Double.parseDouble(totals);
                    }*/


                }
            totalvalue=d;
            totalvaluewithoutdiscount=d;

        }







        if(totalvalue==0.0)
        {
            Home.subtotal.setText(String.valueOf(totalvaluewithoutdiscount));
        }else
        {
            Home.subtotal.setText(String.valueOf(totalvalue));

        }

        if(getHelper().getGST()!=null || getHelper().getDiscount()!=null)
        {
            if(getHelper().getGST()!=null && getHelper().getDiscount()!=null)
            {
                GST gstvalue=getHelper().getGST();
                Discount discountvalue=getHelper().getDiscount();

                Double discount_amount=totalvalue*(Double.parseDouble(discountvalue.getDiscountValue())/100);
                Home.discount.setText(String.format("%.2f",discount_amount));


                Double gstamount=totalvalue*(Double.parseDouble(gstvalue.getGstamount())/100);
                Home.tax.setText(String.format("%.2f",gstamount));

                Double total=totalvalue+gstamount-discount_amount;


                Home.total.setText(String.format("%.2f", total));
            }else
            {
                if(getHelper().getGST()!=null || getHelper().getDiscount()!=null)
                {

                    if(getHelper().getGST()!=null)
                    {
                        GST gstvalue=getHelper().getGST();

                        Double gstamount=totalvalue*(Double.parseDouble(gstvalue.getGstamount())/100);
                        Home.tax.setText(String.format("%.2f",gstamount));

                        Double total=totalvalue+gstamount;

                        Home.total.setText(String.format("%.2f", total));
                    }else
                    {
                        Discount discountvalue=getHelper().getDiscount();

                        Double discount_amount=totalvalue*(Double.parseDouble(discountvalue.getDiscountValue())/100);
                        Home.discount.setText(String.format("%.2f",discount_amount));


                        Double total=totalvalue-discount_amount;

                        total=total+totalvaluewithoutdiscount;

                        Home.total.setText(String.format("%.2f", total));
                    }

                }else
                {

                }

            }

        }

    }


}

