package com.dexeldesigns.postheta.payment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dexeldesigns.postheta.MainActivity;
import com.dexeldesigns.postheta.R;
import com.dexeldesigns.postheta.fragments.Home;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by Creative IT Works on 02-Aug-17.
 */

public class CashFragment extends Fragment implements View.OnClickListener {
    DecimalFormat df = new DecimalFormat("#.##");
    private float amtPaid,expAmt,paidAmt, balAmt;
    private TextView givenAmt,changeAmt,totalPymt,txtPaidAmt, txtBalAmt,total;
    private Button btnHundred, btnFifty, btnTwenty, btnTen, btnFive, btnTwo, btnOne;
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDot, btnClr, btnBckSpc;
    private Button btnPayPartial,btnPayFull;
    double getBalanceToPay;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cash_fragment, container, false);
        Bundle bundle = this.getArguments();
        initui(view);
        if(bundle != null){
            // handle your code here.
            getBalanceToPay=Double.parseDouble(bundle.getString("Totalpayment"));
            //Toast.makeText(getActivity(),String.valueOf(getBalanceToPay),Toast.LENGTH_SHORT).show();
            total.setText(String.valueOf(getBalanceToPay));

        }



        return view;

    }
   void initui(View view)
    {
        givenAmt = (TextView) view.findViewById(R.id.givenAmt);
        changeAmt = (TextView) view.findViewById(R.id.changeAmt);
        total = (TextView) view.findViewById(R.id.total);
        btnHundred = (Button)  view.findViewById(R.id.hundredDollar);
        btnFifty = (Button)  view.findViewById(R.id.fiftyDollar);
        btnTwenty = (Button)  view.findViewById(R.id.twentyDollar);
        btnTen = (Button)  view.findViewById(R.id.tenDollar);
        btnFive = (Button)  view.findViewById(R.id.fiveDollar);
        btnTwo = (Button)  view.findViewById(R.id.twoDollar);
        btnOne = (Button)  view.findViewById(R.id.oneDollar);


        btn0 = (Button)  view.findViewById(R.id.zero);
        btn1 = (Button)  view.findViewById(R.id.one);
        btn2 = (Button)  view.findViewById(R.id.two);
        btn3 = (Button)  view.findViewById(R.id.three);
        btn4 = (Button)  view.findViewById(R.id.four);
        btn5 = (Button)  view.findViewById(R.id.five);
        btn6 = (Button)  view.findViewById(R.id.six);
        btn7 = (Button)  view.findViewById(R.id.seven);
        btn8 = (Button)  view.findViewById(R.id.eight);
        btn9 = (Button)  view.findViewById(R.id.nine);
        btnDot = (Button)  view.findViewById(R.id.dot);
        btnClr = (Button)  view.findViewById(R.id.clearBtn);
        btnBckSpc = (Button)  view.findViewById(R.id.backSpace);

        btnPayFull=(Button) view.findViewById(R.id.btnPayAmountDue);
        btnPayFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payAmountDue();
            }
        });

        btnHundred.setOnClickListener(this);
        btnFifty.setOnClickListener(this);
        btnTwenty.setOnClickListener(this);
        btnTen.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnOne.setOnClickListener(this);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnBckSpc.setOnClickListener(this);
        btnClr.setOnClickListener(this);
        df.setRoundingMode(RoundingMode.CEILING);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.hundredDollar:
                amountPaid(100);
                break;
            case R.id.fiftyDollar:
                amountPaid(50);
                break;
            case R.id.twentyDollar:
                amountPaid(20);
                break;
            case R.id.tenDollar:
                amountPaid(10);
                break;
            case R.id.fiveDollar:
                amountPaid(5);
                break;
            case R.id.twoDollar:
                amountPaid(2);
                break;
            case R.id.oneDollar:
                amountPaid(1);
                break;
            default:
                amountPaid(((Button) v).getText().toString());

        }

    }
    private void amountPaid(float amount)
    {
        double change,balanceToPay;
        balanceToPay=getBalanceToPay;
        //Only accept payment if this way.
        if(amtPaid<getBalanceToPay) {
            amtPaid += amount;
            givenAmt.setText(String.format("%.2f", amtPaid));
            printChange();

        }

    }
    private void printChange() {
        double balanceToPay=getBalanceToPay;
        double change;
        if(amtPaid>=balanceToPay){
            change=amtPaid-balanceToPay;
            changeAmt.setText(String.format( "%.2f", Math.abs(change)));
        }

    }

    private void payAmountDue() {
        ((MainActivity) getActivity()).logout();
    }

    //Method to enter all the input from Numpad
    private void amountPaid(String amount)
    {
        String amt = givenAmt.getText().toString().trim();

        if(amount.equalsIgnoreCase("CE"))
        {
            amt="";
            amtPaid = 0;
            givenAmt.setText("");
            changeAmt.setText("");
        }
        else if (amount.equalsIgnoreCase("."))
        {
            if(amt !=null && amt.length() > 0 && !amt.contains("."))
            {
                givenAmt.setText(amt+".");
            }
        }
        else if (amount.equalsIgnoreCase("bck"))
        {
            String strAmt = givenAmt.getText().toString().trim();
            if(strAmt.length() > 0)
            {
                givenAmt.setText(strAmt.substring(0, strAmt.length() - 1));
                amt=amt.substring(0,strAmt.length()-1);
            }

//            if(givenAmt.getText().toString().length() > 0)
//                amtPaid = Float.parseFloat(givenAmt.getText().toString());
//            else
//                amtPaid = 0.0f;

        }
        else
        {
            if(amtPaid<getBalanceToPay) {
                boolean valid = true;
                if (amt.contains(".") && !amt.endsWith(".")) {
                    if (amt.split("\\.")[1].length() > 1) {
                        Toast.makeText(getActivity(), "No more decmial values", Toast.LENGTH_LONG).show();
                        valid = false;
                    }
                }
                if (valid) {
                    amt += amount;
                    givenAmt.setText(amt);
                }
            }

        }

        if(amt.length() > 0 ) {

            if(amt.contains(".")) {
                if(amt.endsWith("."))
                    amtPaid = Float.parseFloat(amt.substring(0, amt.length() - 1));
                else
                    amtPaid=Float.parseFloat(amt);
            }
            else
                amtPaid=Float.parseFloat(amt);

            printChange();



        }



    }


}
