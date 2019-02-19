package com.cedesistemas.cameraandgallery;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    private ImageView camera;
    private ImageView gallery;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        camera = findViewById(R.id.camera);
        gallery = findViewById(R.id.gallery);
        recyclerView = findViewById(R.id.recyclerViewPhotos);

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGallery();
            }
        });
    }

    private void showGallery() {
        if (Permissions.isGrantedPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            openGallery();
        }else{
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
            Permissions.verifyPermissions(this, permissions);
        }
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            // el request code puede tener cualquier valor entero
            startActivityForResult(intent, Build.VERSION_CODES.KITKAT);
        } else {
            String[] type = {"image/*"};
            intent.putExtra(Intent.EXTRA_MIME_TYPES, type);
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            // el request code puede tener cualquier valor entero
            startActivityForResult(intent, Constants.GALLERY);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Build.VERSION_CODES.KITKAT) {
            resultGalleryKitKatLess(data.getData());
        }

        if (requestCode == Constants.GALLERY) {
            resultGalleryKitKatHigher(data);
        }

    }

    private void resultGalleryKitKatHigher(Intent data) {
        ClipData clipData = data.getClipData();

        if (clipData != null) {
            for (int i = 0; i < clipData.getItemCount(); i++) {
                grantUriPermission(getPackageName(), clipData.getItemAt(i).getUri(), Intent.FLAG_GRANT_READ_URI_PERMISSION);
                clipData.getItemAt(i).getUri();
            }
        }
    }

    private void resultGalleryKitKatLess(Uri data) {
        grantUriPermission(getPackageName(), data, Intent.FLAG_GRANT_READ_URI_PERMISSION);
        data.toString();
    }
}
