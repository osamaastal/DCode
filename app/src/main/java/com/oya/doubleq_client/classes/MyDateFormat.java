package com.oya.doubleq_client.classes;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MyDateFormat {
    private static final String TAG = "MyDateFormat";
//    public static String formatDate_Time(String dt_date, String dt_time) {
//        if (dt_date == null || dt_date.equals(""))
//            return "";
//        if (dt_time == null || dt_time.equals(""))
//            return "";
//        String time = dt_date.split(" ")[0] + " " + dt_time;
//        String inputPattern = "yyyy-MM-dd kk:mm:ss";
//        String outputPattern = "dd/MMM h:mm a";
//        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.US);
//        inputFormat.setTimeZone(TimeZone.getTimeZone("Asia/Riyadh"));
//        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.US);
//        outputFormat.setTimeZone(TimeZone.getDefault());
//
//        Date date = null;
//        String str = null;
//
//        try {
//            date = inputFormat.parse(time);
//            str = outputFormat.format(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return str;
//    }

    public static String formatDate_Time(String dt_date) {
        if (dt_date == null || dt_date.equals(""))
            return "";
        dt_date = dt_date.replace("T", " ");
        String inputPattern = "yyyy-MM-dd kk:mm:ss";
        String outputPattern = "dd MMM yyyy - h:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.US);
        inputFormat.setTimeZone(TimeZone.getTimeZone("Asia/Riyadh"));
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.US);
        outputFormat.setTimeZone(TimeZone.getDefault());

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(dt_date);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }


    public static String formatDate(String dt_date) {
        if (dt_date == null || dt_date.equals(""))
            return "";
//        String time = dt_date.split(" ")[0];
        dt_date = dt_date.replace("T", " ");
        String inputPattern = "yyyy-MM-dd kk:mm:ss";
        String outputPattern = "dd MMM yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.US);
        inputFormat.setTimeZone(TimeZone.getTimeZone("Asia/Riyadh"));
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.US);
        outputFormat.setTimeZone(TimeZone.getDefault());
        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(dt_date);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static long formatTimeToLong(String dt_date) {
        if (dt_date == null || dt_date.equals(""))
            return 0;
        dt_date = dt_date.replace("T", " ");
        String inputPattern = "yyyy-MM-dd kk:mm:ss";
//        String outputPattern = "dd MMMM yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.US);
        inputFormat.setTimeZone(TimeZone.getTimeZone("Asia/Riyadh"));
//        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.US);
//        outputFormat.setTimeZone(TimeZone.getDefault());
        Date date = null;
        String str = null;
        long millis;
        try {
            date = inputFormat.parse(dt_date);
            millis = date.getTime() ;//millis
//            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            millis = 0;
            Log.d(TAG, "formatDate_Time: "+e.getMessage());
        }
        return millis;
    }

    public static String formatTime(String dt_time) {
        if (dt_time == null || dt_time.equals(""))
            return "";
        String inputPattern = "kk:mm:ss";
        String outputPattern = "h:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.US);
        inputFormat.setTimeZone(TimeZone.getTimeZone("Asia/Riyadh"));
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.US);
        outputFormat.setTimeZone(TimeZone.getDefault());
        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(dt_time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String periodTime(long millis_period) {
        int min = 0;
        int sec = 0;
        String time;
        if (millis_period > 60000) {
            min = (int) millis_period / 60000;
            sec = (int) (millis_period - (min * 60000)) / 1000;
            time = min + ":" + sec;
        } else {
            sec = (int) millis_period / 1000;
            time = "0" + ":" + sec;
        }

        String inputPattern = "mm:ss";
        String outputPattern = "mm:ss";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.US);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.US);
        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getCurrentTime(String format) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        return df.format(c.getTime());
    }
    public static String convertLongToTime(String format, long time_millis) {
        SimpleDateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        return df.format(time_millis);
    }
}
