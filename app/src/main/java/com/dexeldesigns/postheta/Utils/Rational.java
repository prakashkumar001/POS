package com.dexeldesigns.postheta.Utils;

public class Rational {

    private int num, denom;

    public Rational(double d) {
        String s = String.valueOf(d);
        int digitsDec = s.length() - 1 - s.indexOf('.');
        int denom = 1;
        for (int i = 0; i < digitsDec; i++) {
            d *= 10;    
            denom *= 10;
        }

        int num = (int) Math.round(d);
        int g = gcd(num, denom);
        this.num = num / g;
        this.denom = denom /g;
    }
    public Rational() {

    }
    public Rational(int num, int denom) {
        this.num = num;
        this.denom = denom;
    }

    public String toString() {
        return String.valueOf(num) + "/" + String.valueOf(denom);
    }
    public double decimalValue(String values) {
        double d;
        if(values.contains("/"))
        {

            String quants[]=values.split("/");
            double num=Double.parseDouble(quants[0]);
            double denom=Double.parseDouble(quants[1]);

             d=num / denom;

        }else {
            d = Double.parseDouble(values);
        }

        return d;
    }

    public static int gcd(int num, int denom) {

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Rational(1.5));
    }
}