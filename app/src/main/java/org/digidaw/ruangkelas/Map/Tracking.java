package org.digidaw.ruangkelas.Map;

/**
 * Created by STURMBANNFUEHRER on 17/11/2017.
 */

public class Tracking {
    private String username, uid, lat, lng;
    public Tracking(){

    }

    public Tracking(String username, String uid, String lat, String lng){
        this.username = username;
        this.uid = uid;
        this.lat = lat;
        this.lng = lng;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
