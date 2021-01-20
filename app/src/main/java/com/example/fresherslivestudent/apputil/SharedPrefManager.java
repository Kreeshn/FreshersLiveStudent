package com.example.fresherslivestudent.apputil;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private Context context;
    private SharedPreferences sharedPreferences;

    public SharedPrefManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("pref_file_key", context.MODE_PRIVATE);
    }
    public boolean isStudentLogin(){
        return sharedPreferences.getBoolean("isStudentlogin", false);
    }
    public void updateStudentLoginStatus(Boolean status){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isStudentlogin", status);
        editor.apply();
    }

    public void saveidofStudent(int sid){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("idofstudent",sid);
        editor.apply();
    }
    public void saveEmailofStudent(String email){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("emailofstudent",email);
        editor.apply();
    }
    public void saveNameofStudent(String name){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nameofstudent", name);
        editor.apply();
    }
    public String getNameofStudent(){
        return sharedPreferences.getString("nameofstudent","unknown");
    }
    public String getEmailofStudent(){
        return sharedPreferences.getString("emailofstudent","unknown");
    }
    public int getIdofStudent(){
        return sharedPreferences.getInt("idofstudent", -1);
    }
}
