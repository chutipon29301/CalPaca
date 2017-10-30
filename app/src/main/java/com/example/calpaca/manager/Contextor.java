package com.example.calpaca.manager;

import android.content.Context;

/**
 * Created by admin on 30/10/2017 AD.
 */

public class Contextor {
    private static Contextor instance;
    private Context context;

    public static Contextor getInstance() {
        if (instance == null) {
            instance = new Contextor();
        }
        return instance;
    }

    public Context getContext() { 
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
