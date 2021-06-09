package com.oya.doubleq_client.classes;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.loader.content.CursorLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

public class MyImageManager {

    private static final String TAG = "MyImageManager";
    public static Bitmap getResizedBitmap(Bitmap image, int MAX_SIZE) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio >= 1.9) {//
            width = (int) (MAX_SIZE * 1.5);
            height = (int) (width / bitmapRatio);
        } else if (bitmapRatio > 1) {
            width = MAX_SIZE;
            height = (int) (width / bitmapRatio);
        } else if (bitmapRatio > 0.5) {
            height = MAX_SIZE;
            width = (int) (height * bitmapRatio);
        } else {
            height = (int) (MAX_SIZE * 1.5);
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
    public static String getRealPathFromURI(Context context, Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
    public static File getFileFromBitmap(Bitmap bitmap) {
        File file = new File(Environment.getExternalStorageDirectory()
                + File.separator + Calendar.getInstance().getTimeInMillis());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 60, byteArrayOutputStream);
        byte[] bitmapData = byteArrayOutputStream.toByteArray();
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bitmapData);
            fileOutputStream.flush();
            fileOutputStream.close();
            return file;
        } catch (Exception e) {
            return null;
        }
    }

//    public static Bitmap decodeResource(Resources res, int resId, int dstWidth, int dstHeight)
//    {
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeResource(res, resId, options);
//        options.inJustDecodeBounds = false;
//
//        options.inSampleSize = calculateSampleSize(options.outWidth, options.outHeight, dstWidth,
//                dstHeight);
//
//        options = new BitmapFactory.Options();
//        //May use null here as well. The funciton may interpret the pre-used options variable in ways hard to tell.
//        Bitmap unscaledBitmap = BitmapFactory.decodeResource(res, resId, options);
//
//        if(unscaledBitmap == null)
//        {
//            Log.e("ERR","Failed to decode resource - " + resId + " " + res.toString());
//            return null;
//        }
//
//        return unscaledBitmap;
//    }



}
