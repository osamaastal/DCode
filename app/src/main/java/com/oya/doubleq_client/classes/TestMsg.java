package com.oya.doubleq_client.classes;

import android.content.Context;
import android.widget.Toast;

public class TestMsg {
    private static boolean isActive = true;

    public static void show(Context context, String msg, int period) { //0:SHORT , 1:LONG
        if (isActive) {
            if (period == 0)
                Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, "" + msg, Toast.LENGTH_LONG).show();
        }
    }
}
