package com.oya.doubleq_client.data.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.oya.doubleq_client.classes.cons;
import com.oya.doubleq_client.ui.a_login.LoginActivity;

public class CheckStatusCode {
//    public CheckCode(Context context,int code) {
//        this.context = context;
//        this.code = code;
//    }

    public void checkCode(Context context, int code, String msg){
//        MessageDialog dialog = new MessageDialog();
        SharedPreferences sp = context.getSharedPreferences(cons.SH_UserData, Context.MODE_PRIVATE);
        String token = sp.getString(cons.token,"");

        SharedPreferences.Editor ep;
        Activity activity = (Activity) context;
        Intent intent = new Intent(context, LoginActivity.class);
        String content;
        switch (code) {
//            case cons.CODE_TOKEN_EXPIRED:
            case cons.CODE_TOKEN_NOT_AVAILABLE:
//                content = context.getString(R.string.account_not_available);
                ep = sp.edit();
                ep.putInt(cons.id, 0);
                ep.apply();
                activity.finishAffinity();
                context.startActivity(intent);
//                dialog.showDialog(context,content);
                break;
            case cons.CODE_USER_BLOCKED:
//                content = context.getString(R.string.account_blocked);
                ep = sp.edit();
                ep.putInt(cons.id, 0);
                ep.apply();
                activity.finishAffinity();
                context.startActivity(intent);
//                dialog.showDialog(context,content);
                break;
            case cons.CODE_USER_NOT_VERIFIED:
//                content = context.getString(R.string.not_verified);
                ep = sp.edit();
                ep.putInt(cons.id, 0);
                ep.apply();
                activity.finishAffinity();
                context.startActivity(intent);
//                dialog.showDialog(context,content);
                break;
//            case cons.CODE_PRICES_MAY_CHANGE:
//                dialog.showDialog(context,msg);
//                break;
            default:
                if (token.equals("")){
//                    content = context.getString(R.string.account_not_available);
                    ep = sp.edit();
                    ep.putInt(cons.id, 0);
                    ep.apply();
                    activity.finishAffinity();
                    context.startActivity(intent);
//                    dialog.showDialog(context,content);
                }
        }
    }
}
