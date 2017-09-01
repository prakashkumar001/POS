package com.dexeldesigns.postheta.helper;

/**
 * Created by Creative IT Works on 05-May-17.
 */

public interface ItemTouchHelperViewHolder {
    /**
     * Implementations should update the item view to indicate it's active state.
     */
    void onItemSelected();


    /**
     * state should be cleared.
     */
    void onItemClear();
}