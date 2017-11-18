package org.digidaw.ruangkelas.Map;

import android.graphics.Bitmap;
import java.text.DecimalFormat;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.digidaw.ruangkelas.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private String username;

    DatabaseReference locations;

    Double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Referensikan ke firebase dulu
        locations = FirebaseDatabase.getInstance().getReference("Locations");

        //Dapatkan intent
        if(getIntent() != null){
            username = getIntent().getStringExtra("username");
            lat = getIntent().getDoubleExtra("lat", 0);
            lng = getIntent().getDoubleExtra("lng", 0);
        }

        if(!TextUtils.isEmpty(username))
            loadLocatioinForThisUser(username);
    }

    private void loadLocatioinForThisUser(String username) {
        Query user_location = locations.orderByChild("username").equalTo(username);

        user_location.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapShot:dataSnapshot.getChildren()) {
                    Tracking tracking = postSnapShot.getValue(Tracking.class);

                    //tambahkan penanda untuk lokasi siswa
                    LatLng studentLocation = new LatLng(Double.parseDouble(tracking.getLat()),
                            Double.parseDouble(tracking.getLng()));

                    //Buat lokasi dari koordinat user
                    Location currentUser = new Location("");
                    currentUser.setLatitude(lat);
                    currentUser.setLongitude(lng);

                    //Buat lokasi dari koordinat Siswa
                    Location student = new Location("");
                    student.setLatitude(Double.parseDouble(tracking.getLat()));
                    student.setLongitude(Double.parseDouble(tracking.getLng()));

                    //tambahkan penanda didalam map
                    mMap.addMarker(new MarkerOptions().position(studentLocation)
                            .title(tracking.getUsername())
                            .snippet("Distance " + new DecimalFormat("#.#").format(distance(currentUser, student)))
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 12.0f));
                }

                //buat penanda untuk user yang login di device ini
                LatLng current = new LatLng(lat, lng);
                mMap.addMarker(new MarkerOptions().position(current).title(FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private double distance(Location currentUser, Location student) {
        double theta = currentUser.getLongitude() - student.getLongitude();
        double dist = Math.sin(deg2rad(currentUser.getLatitude()))
                * Math.sin(deg2rad(student.getLatitude()))
                * Math.cos(deg2rad(currentUser.getLatitude()))
                * Math.cos(deg2rad(student.getLatitude()))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double rad2deg(double rad) {
        return (rad * 180  / Math.PI);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


    }
}
