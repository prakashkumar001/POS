package com.dexeldesigns.postheta.model;

import com.dexeldesigns.postheta.common.CustomerInfo;

/**
 * Created by Creative IT Works on 16-May-17.
 */

public class TakeAway {
    public String member;
    public String takeaway_number;
    public String tablePosition;
    public CustomerInfo customerInfo;


    public String getTakeaway_number() {
        return takeaway_number;
    }

    public void setTakeaway_number(String takeaway_number) {
        this.takeaway_number = takeaway_number;
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

    public TakeAway(String tablePosition, String takeaway_number, CustomerInfo customerInfo)
    {

        this.takeaway_number=takeaway_number;
        this.tablePosition=tablePosition;
        this.customerInfo=customerInfo;
    }

}
