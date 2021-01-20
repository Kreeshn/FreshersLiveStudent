package com.example.fresherslivestudent.model;

public class StudentLoginResponse {
    private int sid;
    private String fname;
    private String lname;
    private String email;
    public String error;
    public String message;

    public StudentLoginResponse(int sid, String fname, String lname, String email, String error, String message) {
        this.sid = sid;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.error = error;
        this.message = message;
    }

    public int getSid() {
        return sid;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
