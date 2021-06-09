package com.oya.doubleq_client.classes.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import android.widget.TextView;

import com.oya.doubleq_client.R;
import com.oya.doubleq_client.ui.a_login.LoginActivity;


public class LoginDialog {
    /**
     * Function to display simple Alert Dialog
     *
     * @param context - application context
     * - pass null if you don't want icon
     */
    private Dialog popup;

    public void showAlertDialog(Context context) {
        if (popup == null) {
            popup = new Dialog(context);
            popup.setContentView(R.layout.popup_msg);
            popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            popup.setCancelable(true);
        }

        popup.show();
        Button loginBtn = popup.findViewById(R.id.okBtn);
        TextView msgTv = popup.findViewById(R.id.msgTv);
        msgTv.setText(R.string.please_login_to_use_this_feature);
        loginBtn.setText(R.string.sign_in);
        loginBtn.setOnClickListener(v -> {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            dismiss();
        });
    }

    public void dismiss() {
        popup.dismiss();
    }
}
