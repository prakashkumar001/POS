package com.dexeldesigns.postheta;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.dexeldesigns.postheta.common.GlobalClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Creative IT Works on 20-Apr-17.
 */

public class Login extends AppCompatActivity {
    EditText enter_mpin;
    ImageView i1, i2, i3, i4;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pin);
        i1 = (ImageView) findViewById(R.id.imageview_circle1);
        i2 = (ImageView) findViewById(R.id.imageview_circle2);
        i3 = (ImageView) findViewById(R.id.imageview_circle3);
        i4 = (ImageView) findViewById(R.id.imageview_circle4);

        enter_mpin = (EditText) findViewById(R.id.editText_enter_mpin);
        enter_mpin.requestFocus();
        enter_mpin.setInputType(InputType.TYPE_CLASS_NUMBER);
        enter_mpin.setFocusableInTouchMode(true);

        enter_mpin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("", "onKey: screen key pressed");
                if(enter_mpin.getText().toString().length()==1)
                {
                    i1.setImageResource(R.drawable.pin_circle_select);
                    i2.setImageResource(R.drawable.pin_circle);
                    i3.setImageResource(R.drawable.pin_circle);
                    i4.setImageResource(R.drawable.pin_circle);

                }else if(enter_mpin.getText().toString().length()==2)
                {
                    i1.setImageResource(R.drawable.pin_circle_select);
                    i2.setImageResource(R.drawable.pin_circle_select);
                    i3.setImageResource(R.drawable.pin_circle);
                    i4.setImageResource(R.drawable.pin_circle);

                }else if(enter_mpin.getText().toString().length()==3)
                {
                    i1.setImageResource(R.drawable.pin_circle_select);
                    i2.setImageResource(R.drawable.pin_circle_select);
                    i3.setImageResource(R.drawable.pin_circle_select);
                    i4.setImageResource(R.drawable.pin_circle);

                }else if(enter_mpin.getText().toString().length()==4)
                {
                    i1.setImageResource(R.drawable.pin_circle_select);
                    i2.setImageResource(R.drawable.pin_circle_select);
                    i3.setImageResource(R.drawable.pin_circle_select);
                    i4.setImageResource(R.drawable.pin_circle_select);


                }else {
                    i1.setImageResource(R.drawable.pin_circle);
                    i2.setImageResource(R.drawable.pin_circle);
                    i3.setImageResource(R.drawable.pin_circle);
                    i4.setImageResource(R.drawable.pin_circle);

                }

            }
        });
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();


        finish();
    }
}
