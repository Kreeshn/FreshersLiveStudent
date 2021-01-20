package com.example.fresherslivestudent.retrofitutil;

import com.example.fresherslivestudent.model.StudentLoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("studentLogin.php")
    Call<StudentLoginResponse> studentLogin(
            @Field("email") String email,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("studentReg.php")
    Call<StudentLoginResponse> studentReg(
            @Field("fname") String fname,
            @Field("lname") String lname,
            @Field("dob") String dob,
            @Field("gender") String gender,
            @Field("contact") String contact,
            @Field("email") String email,
            @Field("password") String password
    );
}
