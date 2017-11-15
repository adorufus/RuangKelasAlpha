package org.digidaw.ruangkelas.Chat;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import org.digidaw.ruangkelas.Utilitas.BottomNavHelper;
import org.digidaw.ruangkelas.R;

/**
 * Created by STURMBANNFUEHRER on 29/10/2017.
 */

public class ChatActivity extends AppCompatActivity {
    private static final String TAG = "ChatActivity";
    private static final int ACTIVITY_NUM = 1;
    private Context mContext = ChatActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreateLayout: started.");

        setBottomNavBar();
    }

    private void setBottomNavBar(){
        BottomNavigationViewEx navBottom = (BottomNavigationViewEx) findViewById(R.id.bottom_nav_view);
        BottomNavHelper.setupBottomNavView(navBottom);
        BottomNavHelper.enableNavigation(mContext, navBottom);

        Menu menu = navBottom.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
