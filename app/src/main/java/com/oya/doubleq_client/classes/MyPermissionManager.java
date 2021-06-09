package com.oya.doubleq_client.classes;

import android.Manifest;
import android.content.Context;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class MyPermissionManager {

    public void checkLocationPermission(Context context, MyPermissionListener listener) {
//        MultiplePermissionsListener snackbarMultiplePermissionsListener =
//                SnackbarOnAnyDeniedMultiplePermissionsListener.Builder
//                        .with(view, R.string.access_location_is_nedded)
//                        .withOpenSettingsButton(R.string.settings)
//                        .withCallback(new Snackbar.Callback() {
//                            @Override
//                            public void onShown(Snackbar snackbar) {
//                                // Event handler for when the given Snackbar is visible
//                            }
//
//                            @Override
//                            public void onDismissed(Snackbar snackbar, int event) {
//                                // Event handler for when the given Snackbar has been dismissed
//                            }
//                        })
//                        .build();
//        MultiplePermissionsListener multiplePermissionsListener = new MultiplePermissionsListener() {
//            @Override
//            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
//
//            }
//
//            @Override
//            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
//                permissionToken.continuePermissionRequest();
//            }
//        };
//        MultiplePermissionsListener compositePermissionsListener = new CompositeMultiplePermissionsListener(snackbarMultiplePermissionsListener, multiplePermissionsListener);

        /** single permission*/
//        Dexter.withContext(context)
//                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
//                .withListener(new PermissionListener() {
//                    @Override
//                    public void onPermissionGranted(PermissionGrantedResponse response) {
//                        getCurrentLocation();
//                    }
//
//                    @Override
//                    public void onPermissionDenied(PermissionDeniedResponse response) {
//                        Toast.makeText(context, "Denied", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
//                        token.continuePermissionRequest();
//                    }
//                }).check();
        /** multi permission*/
        Dexter.withContext(context)
                .withPermissions(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                if (multiplePermissionsReport.areAllPermissionsGranted()) { //Granted
                    listener.onGranted();
                } else { //Denied
                    listener.onDenied();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        })
//                .onSameThread()
                .check();
    }
    public void checkGalleryPermission(Context context, MyPermissionListener listener) {

        /** single permission*/
//        Dexter.withContext(context)
//                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                .withListener(new PermissionListener() {
//                    @Override
//                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//                        listener.onGranted();
//                    }
//
//                    @Override
//                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
//                        listener.onDenied();
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
//                        permissionToken.continuePermissionRequest();
//                    }
//                }).check();
        /** multi permission*/
        Dexter.withContext(context)
                .withPermissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                if (multiplePermissionsReport.areAllPermissionsGranted()) { //Granted
                    listener.onGranted();
                } else { //Denied
                    listener.onDenied();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        })
//                .onSameThread()
                .check();
    }

    public interface MyPermissionListener {
        void onGranted();

        void onDenied();
    }
}
