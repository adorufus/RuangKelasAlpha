package org.digidaw.ruangkelas.Map;

/**
 * Created by STURMBANNFUEHRER on 17/11/2017.
 */

public class User {

    private String username, status;

    public User(){

    }

    public User(String username, String status){
        this.username = username;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
