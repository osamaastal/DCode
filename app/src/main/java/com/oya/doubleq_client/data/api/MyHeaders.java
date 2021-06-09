package com.oya.doubleq_client.data.api;

import android.content.Context;
import android.content.SharedPreferences;


import com.oya.doubleq_client.classes.cons;

import java.util.HashMap;

public class MyHeaders {
    public static HashMap<String, String> get_token_lang(Context context){
        HashMap<String, String> header = new HashMap<>();
        SharedPreferences sp = context.getSharedPreferences(cons.SH_UserData, Context.MODE_PRIVATE);
        String token = sp.getString(cons.token, "");
        String language = sp.getString(cons.language, cons.EN);
        header.put("token",  token);
        header.put("Accept-Language", language);
        return header;
    }
    public static HashMap<String, String> get_token(Context context){
        HashMap<String, String> header = new HashMap<>();
        SharedPreferences sp = context.getSharedPreferences(cons.SH_UserData, Context.MODE_PRIVATE);
        String token = sp.getString(cons.token, "");
        header.put("token",  token);
        return header;
    }
    public static HashMap<String, String> get_lang(Context context){
        HashMap<String, String> header = new HashMap<>();
        SharedPreferences sp = context.getSharedPreferences(cons.SH_UserData, Context.MODE_PRIVATE);
        String language = sp.getString(cons.language, cons.EN);
        header.put("Accept-Language", language);
        return header;
    }

}
