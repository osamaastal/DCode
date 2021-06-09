package com.oya.doubleq_client.classes;

import android.content.Context;
import android.content.SharedPreferences;

import com.oya.doubleq_client.pojo.user.User;
import com.oya.doubleq_client.pojo.user.UserLocal;

public final class SharedPref {

    public static String getToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences(cons.SH_UserData, Context.MODE_PRIVATE);
        return sp.getString(cons.token, "");
    }

    public static String getId(Context context) {
        SharedPreferences sp = context.getSharedPreferences(cons.SH_UserData, Context.MODE_PRIVATE);
        return sp.getString(cons.id, "");
    }
    public static void setId(Context context,String id) {
        SharedPreferences sp = context.getSharedPreferences(cons.SH_UserData, Context.MODE_PRIVATE);
        SharedPreferences.Editor ep = sp.edit();
        ep.putString(cons.id,id);
        ep.apply();
    }

    public static void setGuestToken(String token, Context context) {
        SharedPreferences sp = context.getSharedPreferences(cons.SH_UserData, Context.MODE_PRIVATE);
        SharedPreferences.Editor ep = sp.edit();
        ep.putString(cons.token, token);
        ep.putBoolean(cons.isGuest, true);
        ep.apply();
    }

    public static void setUserData(User user, Context context) {
        try {
            SharedPreferences sp = context.getSharedPreferences(cons.SH_UserData, Context.MODE_PRIVATE);
            SharedPreferences.Editor ep = sp.edit();
            ep.putString(cons.token, user.getToken());
            ep.putString(cons.address, user.getAddress());
            ep.putBoolean(cons.isVerify, user.isVerify());
            ep.putBoolean(cons.isDeleted, user.isDeleted());
            ep.putBoolean(cons.isGuest, false);
            ep.putBoolean(cons.isEnableNotifications, user.isEnableNotifications());
            ep.putString(cons.id, user.getId());
            ep.putString(cons.name, user.getFullName());
            ep.putString(cons.email, user.getEmail());
            ep.putString(cons.mobile, user.getPhoneNumber());
            ep.putString(cons.image, user.getImage());
            ep.putFloat(cons.lat, (float) user.getLat());
            ep.putFloat(cons.lng, (float) user.getLng());
            ep.putBoolean(cons.isGuest, true);
            ep.apply();
        } catch (Exception e) {
            TestMsg.show(context, "SharedPref: " + e.getMessage(), 0);
        }

    }

    public static UserLocal getUserData(Context context) {
        UserLocal userLocal = new UserLocal();
        SharedPreferences sp = context.getSharedPreferences(cons.SH_UserData, Context.MODE_PRIVATE);
        userLocal.setToken(sp.getString(cons.token, ""));
        userLocal.setAddress(sp.getString(cons.address, ""));
        userLocal.setVerify(sp.getBoolean(cons.isVerify, false));
        userLocal.setDeleted(sp.getBoolean(cons.isDeleted, false));
        userLocal.setGuest(sp.getBoolean(cons.isGuest, false));
        userLocal.setEnableNotifications(sp.getBoolean(cons.isEnableNotifications, false));
        userLocal.setId(sp.getString(cons.id, ""));
        userLocal.setFullName(sp.getString(cons.name, ""));
        userLocal.setEmail(sp.getString(cons.email, ""));
        userLocal.setPhoneNumber(sp.getString(cons.mobile, ""));
        userLocal.setImage(sp.getString(cons.image, ""));
        userLocal.setLat(sp.getFloat(cons.lat, 0));
        userLocal.setLng(sp.getFloat(cons.lng, 0));
        return userLocal;
    }


    public static String getLanguage(Context context) {
        SharedPreferences sp = context.getSharedPreferences(cons.SH_UserData, Context.MODE_PRIVATE);
        return sp.getString(cons.language, cons.EN);
    }
    public static void setFirstTime(Context context, boolean isFirstTime) {
        SharedPreferences sp = context.getSharedPreferences(cons.SH_IsFirstTime, Context.MODE_PRIVATE);
        SharedPreferences.Editor  ep = sp.edit();
        ep.putBoolean(cons.isFirstTime,isFirstTime);
        ep.apply();
    }
    public static boolean getFirstTime(Context context) {
        SharedPreferences sp = context.getSharedPreferences(cons.SH_IsFirstTime, Context.MODE_PRIVATE);
        return sp.getBoolean(cons.isFirstTime,true);
    }
}
