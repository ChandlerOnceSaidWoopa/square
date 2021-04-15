package com.square;


import android.Manifest;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import android.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

public class MainActivity extends Activity implements LoginCallbacks {

    private boolean isAuth = false;
    Login loginFrag = Login.newInstance();
    SwipeDetector mainFrag = SwipeDetector.newInstance();
    FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 123);
        }
        setContentView(R.layout.activity_main);

        fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.Continer,loginFrag).commit();

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 123:
                onCreate(null);
        }
    }

    @Override
    public void onSuccess(String UserName) {
        fm.beginTransaction().replace(R.id.Continer,mainFrag).commit();
    }

}