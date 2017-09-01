package com.dexeldesigns.postheta.model;

import android.widget.TextView;

/**
 * Created by Creative IT Works on 02-May-17.
 */

public class Tables {
    public String table_id;
    public TextView table_number;
    public String tablePosition;
    public String iscombine;
    public String table_num;

    public TextView getTable_number() {
        return table_number;
    }

    public void setTable_number(TextView table_number) {
        this.table_number = table_number;
    }

    public String getTablePosition() {
        return tablePosition;
    }

    public void setTablePosition(String tablePosition) {
        this.tablePosition = tablePosition;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected;
public Tables(String table_num)
{
this.table_num=table_num;

}
    public Tables(String table_id,String tablePosition,TextView table_number,String table_num,String iscombine)
    {

        this.table_number=table_number;
        this.table_id=table_id;
        this.tablePosition=tablePosition;
        this.iscombine=iscombine;
        this.table_num=table_num;
    }
}
