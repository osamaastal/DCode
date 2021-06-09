package com.oya.doubleq_client.classes;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.snackbar.Snackbar;
import com.oya.doubleq_client.R;

public class MyLocationManager implements MyPermissionManager.MyPermissionListener {

    private final Context context;
    private final EventListener listener;
    private double lat=0, lng=0;
    private final View root;

    public MyLocationManager(Context context, EventListener listener, View root) {// root for snakeBar
        this.context = context;
        this.listener = listener;
        this.root = root;
    }

    /***********************Current Location***************/
    private FusedLocationProviderClient fusedLocationClient;

    public void getCurrentLocation() {
        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //   TODO: ActivityCompat#requestPermissions
            MyPermissionManager osPermissionManager = new MyPermissionManager();
            osPermissionManager.checkLocationPermission(context, this);
            return;
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener((Activity) context, location -> {
                    if (location != null) {
                        lat = location.getLatitude();
                        lng = location.getLongitude();
                        LatLng latLng = new LatLng(lat,lng);
                        listener.onLocationReady(latLng);
                        TestMsg.show(context, lat + " - " + lng, 0);
                    } else {
                        listener.onLocationReady(null);
                        Toast.makeText(context, "location = null", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onGranted() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener((Activity) context, location -> {
                    if (location != null) {
                        lat = location.getLatitude();
                        lng = location.getLongitude();
                        LatLng latLng = new LatLng(lat,lng);
                        listener.onLocationReady(latLng);
                        TestMsg.show(context, lat + " - " + lng, 0);
                    } else {
                        listener.onLocationReady(null);
                        TestMsg.show(context, "location = null", 0);
                    }
                });
    }

    @Override
    public void onDenied() {
        Snackbar snackbar = Snackbar
                .make(root, R.string.access_location_is_required, Snackbar.LENGTH_LONG)
                .setAction(R.string.allow, view -> getCurrentLocation());

        snackbar.show();
    }

    public interface EventListener{
        void onLocationReady(LatLng latLng);
    }
}
