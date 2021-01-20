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
}
