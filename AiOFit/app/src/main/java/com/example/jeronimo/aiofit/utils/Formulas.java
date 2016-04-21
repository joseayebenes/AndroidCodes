package com.example.jeronimo.aiofit.utils;

/**
 * Created by JoseAntonio on 21/04/2016.
 */
public class Formulas {


    public double getIMC(double peso_kg, double altura_m){
        return peso_kg/(altura_m*altura_m);
    }

    public double getGrasa_yuhasz(boolean isHombre, double abdominal, double supraliaco, double subescapular, double tricipital, double cuadricipital, double peroneal){
        double porcentajeGraso=0;
        double sumaPlieges = abdominal+supraliaco+subescapular+tricipital+cuadricipital+peroneal;
        if(isHombre){
              porcentajeGraso = 3.64 + sumaPlieges*0.097;
        }else{
            porcentajeGraso = 4.56 + sumaPlieges*0.143;
        }
        return porcentajeGraso;
    }

}
