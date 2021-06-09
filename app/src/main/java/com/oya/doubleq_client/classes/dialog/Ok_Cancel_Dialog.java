package com.oya.doubleq_client.classes.dialog;//package com.oya.doubleq.classes.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import android.widget.TextView;

import com.oya.doubleq_client.R;


public class Ok_Cancel_Dialog {
    /**
     * Function to display simple Alert Dialog
     *
     * @param context - application context
     * - pass null if you don't want icon
     */
    private Dialog popup;

    public void showDialog(Context context, String msg,EventListener listener,int action_type) {
        if (popup==null){
            popup = new Dialog(context);
            popup.setContentView(R.layout.popup_ok_cancel);
            popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            popup.setCancelable(true);
        }
        popup.show();
        TextView msgTv = popup.findViewById(R.id.msgTv);
        Button okBtn = popup.findViewById(R.id.okBtn);
        Button cancelBtn = popup.findViewById(R.id.cancelBtn);
        msgTv.setText(msg);
        cancelBtn.setOnClickListener(v -> {
            dismiss();
        });
        okBtn.setOnClickListener(v -> {
            listener.okAction(action_type);
            dismiss();
        });
    }

    public void dismiss() {
        popup.dismiss();
    }
    public interface EventListener{
        void okAction(int action_type);
    }
}
