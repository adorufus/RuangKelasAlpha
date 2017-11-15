package org.digidaw.ruangkelas.Home;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import org.digidaw.ruangkelas.Utilitas.BottomNavHelper;
import org.digidaw.ruangkelas.R;
import org.digidaw.ruangkelas.Utilitas.SectionPagerAdapter;
import org.digidaw.ruangkelas.Utilitas.StartActivity;

public class MainActivity extends AppCompatActivity {

    //create firebase auth variable
    private FirebaseAuth mAuth;

    private Context mContext = MainActivity.this;
    private static final int ACTIVITY_NUM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate the FirebaseAuth()
        mAuth = FirebaseAuth.getInstance();

        setBottomNavBar();
        setupViewPager();
    }

    /**
     * Setting firebase
     */

    @Override
    public void onStart() {
        super.onStart();
        //cek jika user login, maka update Tampilan
        FirebaseUser curUser = mAuth.getCurrentUser();

        if(curUser == null){
            sendToStart();
        }
    }

    private void sendToStart(){
        Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(startIntent);
    }

    /**
     * untuk menambahkan 3 tab: Map, Home, Notifikasi
     */
    private void setupViewPager(){
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MapFragment());
        adapter.addFragment(new MainFragment());
        adapter.addFragment(new NotificationFragment());
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(0);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_map);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dashboard);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_notification);
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
