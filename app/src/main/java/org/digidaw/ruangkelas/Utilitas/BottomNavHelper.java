package org.digidaw.ruangkelas.Utilitas;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import org.digidaw.ruangkelas.Absensi.AbsensiActivity;
import org.digidaw.ruangkelas.Chat.ChatActivity;
import org.digidaw.ruangkelas.Home.MainActivity;
import org.digidaw.ruangkelas.Profile.ProfileActivity;
import org.digidaw.ruangkelas.R;
import org.digidaw.ruangkelas.Student.StudentActivity;

/**
 * Created by STURMBANNFUEHRER on 29/10/2017.
 */

public class BottomNavHelper {
    private static final String TAG = "BottomNavHelper";

    public static void setupBottomNavView(BottomNavigationViewEx navBottom){
        Log.d(TAG, "settupBottomNavView: setting up BottomNavView");
        navBottom.enableShiftingMode(false);
        navBottom.enableAnimation(false);
        navBottom.enableItemShiftingMode(false);
        navBottom.setTextVisibility(false);
        navBottom.setIconSize(30, 30);
    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        Intent intentHome = new Intent(context, MainActivity.class);
                        context.startActivity(intentHome);
                        break;
                    case R.id.ic_messaging:
                        Intent intentMessaging = new Intent(context, ChatActivity.class);
                        context.startActivity(intentMessaging);
                        break;
                    case R.id.ic_student:
                        Intent intentStudent = new Intent(context, StudentActivity.class);
                        context.startActivity(intentStudent);
                        break;
                    case R.id.ic_absensi:
                        Intent intentAbsensi = new Intent(context, AbsensiActivity.class);
                        context.startActivity(intentAbsensi);
                        break;
                    case R.id.ic_account_info:
                        Intent intentAccInfo = new Intent(context, ProfileActivity.class);
                        context.startActivity(intentAccInfo);
                        break;
                }

                return false;
            }
        });
    }
}
