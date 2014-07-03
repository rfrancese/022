package com.example.shadowduel;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

public class LocalStorage extends Activity {
    public static final String PREFS_NAME = "storage_var";
private String user;
private String pass;
private String username;
private String password;
	@Override
protected void onCreate(Bundle savedInstanceState) {
	
}
public LocalStorage(){
	load();
		
	}
    public LocalStorage(String user,String pass){
    	this.user=user;
    	this.pass=pass;
    	save();
    }
    private void save(){
        SharedPreferences u = getSharedPreferences(PREFS_NAME, 0);
       SharedPreferences p = getSharedPreferences(PREFS_NAME, 1);
       SharedPreferences.Editor editoru = u.edit();
       editoru.putString("username", user);
       editoru.commit();

       SharedPreferences.Editor editorp = p.edit();
       editorp.putString("password", pass);
       editorp.commit();
    }
    private void load(){
        SharedPreferences u = getSharedPreferences(PREFS_NAME, 0);
        username = u.getString("username", user);
       SharedPreferences p = getSharedPreferences(PREFS_NAME, 1);
      password = p.getString("password", pass);
    }
    public String getUsername(){
    	return username;
    }
    public String getPassword(){
    	return password;
    }
}
