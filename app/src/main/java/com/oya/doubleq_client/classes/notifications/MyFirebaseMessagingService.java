//package com.oya.doubleq_client.classes.notifications;
//
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Color;
//import android.media.RingtoneManager;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Handler;
//import android.os.Looper;
//import android.util.Log;
//
//import androidx.annotation.RequiresApi;
//import androidx.core.app.NotificationCompat;
//
//import com.google.firebase.messaging.FirebaseMessagingService;
//import com.google.firebase.messaging.RemoteMessage;
//
//import com.oya.doubleq_client.R;
//import com.oya.doubleq_client.classes.cons;
//import com.oya.doubleq_client.ui.a_splash.Splash;
//
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Random;
//
////TODO ******************NEW *************************
//public class MyFirebaseMessagingService extends FirebaseMessagingService {
//
//    private static final String NOTIFICATION_ID_EXTRA = "notificationId";
//    private static final String IMAGE_URL_EXTRA = "imageUrl";
//    private static final String ADMIN_CHANNEL_ID = "admin_channel";
//    private NotificationManager notificationManager;
//    private String TAG = "MyFirebaseMessagingService";
//    public static EventListener listener;
//
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        Intent notificationIntent = new Intent(getApplicationContext(), Splash.class);
////TODO        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        SharedPreferences sp = getSharedPreferences(cons.SH_UserData, MODE_PRIVATE);
//        String token = sp.getString(cons.token, "");
//        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        final PendingIntent pendingIntent = PendingIntent.getActivity(this,
//                0 /* Request code */, notificationIntent,
//                PendingIntent.FLAG_ONE_SHOT);
//        //You should use an actual ID instead
//        int notificationId = new Random().nextInt(60000);
//        Intent likeIntent = new Intent(this, LikeService.class);
//        likeIntent.putExtra(NOTIFICATION_ID_EXTRA, notificationId);
//        likeIntent.putExtra(IMAGE_URL_EXTRA, remoteMessage.getData().get("image-url"));
//        PendingIntent likePendingIntent = PendingIntent.getService(this,
//                notificationId + 1, likeIntent, PendingIntent.FLAG_ONE_SHOT);
//
//
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);//TYPE_NOTIFICATION
//
//        notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            setupChannels();
//        }
//        if (!token.equals("")) {
//            Log.d(TAG, "token: "+token);
//            NotificationCompat.Builder notificationBuilder =
//                    new NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
////                        .setLargeIcon(bitmap)
//                            .setSmallIcon(R.drawable.ic_logo)
//                            .setContentTitle(remoteMessage.getNotification().getTitle())//"You have new notification"//remoteMessage.getData().get("title")
////                        .setStyle(new NotificationCompat.BigPictureStyle()
////                                .setSummaryText(remoteMessage.getData().get("message"))
////                                .bigPicture(bitmap))/*Notification with Image*/
//                            .setContentText(remoteMessage.getNotification().getBody())
//                            .setAutoCancel(true)
//                            .setSound(defaultSoundUri)
////                        .addAction(R.drawable.ic_favorite_true,
////                                getString(R.string.notification_add_to_cart_button),likePendingIntent)
//                            .setContentIntent(pendingIntent);
//            notificationManager.notify(notificationId, notificationBuilder.build());
//        }
//
//        Handler handler = new Handler(Looper.getMainLooper());
//        handler.post(() -> {
//            if (listener != null)
//                listener.onNotificationReceived();
//
//        });
////        try{
////            MainActivity.showPopUp_Notification("",remoteMessage.getData().get("content"));
////            Log.e(TAG, "content: "+remoteMessage.getData().get("content"));
////        }catch (Exception e){
////            Log.e(TAG, "error: "+e.getMessage());
////        }
//
//    }
//
//    public Bitmap getBitmapfromUrl(String imageUrl) {
//        try {
//            URL url = new URL(imageUrl);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.connect();
//            InputStream input = connection.getInputStream();
//            return BitmapFactory.decodeStream(input);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void setupChannels() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence adminChannelName = getString(R.string.notifications_admin_channel_name);
//            String adminChannelDescription = getString(R.string.notifications_admin_channel_description);
//
//            NotificationChannel adminChannel;
//            adminChannel = new NotificationChannel(ADMIN_CHANNEL_ID, adminChannelName, NotificationManager.IMPORTANCE_HIGH);
//            adminChannel.setDescription(adminChannelDescription);
//            adminChannel.enableLights(true);
//            adminChannel.setLightColor(Color.RED);
//            adminChannel.enableVibration(true);
//            if (notificationManager != null) {
//                notificationManager.createNotificationChannel(adminChannel);
//            }
//        }
//
//    }
//
//    public interface EventListener {
//        void onNotificationReceived();
//    }
//}
