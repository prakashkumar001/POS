package com.dexeldesigns.postheta.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dexeldesigns.postheta.R;

/**
 * Created by Creative IT Works on 18-Sep-17.
 */

public class FavouritesPage extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.favourites_page, container, false);

        return view;

    }

    }
