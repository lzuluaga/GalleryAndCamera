package com.cedesistemas.cameraandgallery;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class Permissions {

    public static boolean isGrantedPermissions(Context context, String permissionType){
        int permission = ActivityCompat.checkSelfPermission(context, permissionType);

        return permission == PackageManager.PERMISSION_GRANTED;
    }

    public static void verifyPermissions(Activity activity, String[] permissionType){
        ActivityCompat.requestPermissions(activity, permissionType, 3);
    }
}
