package com.oya.doubleq_client.classes.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.oya.doubleq_client.R;


public class LoadingDialog {
    /**
     * Function to display simple Alert Dialog
     *
     * @param context - application context
     * - pass null if you don't want icon
     */
    private Dialog popup;

    public void showDialog(Context context) {
        if (popup == null) {
            popup = new Dialog(context);
            popup.setContentView(R.layout.popup_loading);
            popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            popup.setCancelable(false);
        }
        popup.show();
    }

    public void dismiss() {
        popup.dismiss();
    }
}
