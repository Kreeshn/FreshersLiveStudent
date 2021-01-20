package com.example.fresherslivestudent.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fresherslivestudent.R;
import com.example.fresherslivestudent.apputil.SharedPrefManager;
import com.example.fresherslivestudent.model.StudentLoginResponse;
import com.example.fresherslivestudent.retrofitutil.RetrofitClient;

import java.time.chrono.MinguoChronology;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentLoginActivity extends AppCompatActivity {
    EditText etEmail, etPassword;
    TextView tvNewUser;
   Button btnlogin;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        sharedPrefManager = new SharedPrefManager(this);
        etEmail = (EditText) findViewById(R.id.etEmailLogin);
        etPassword = (EditText) findViewById(R.id.etPassLogin);
        btnlogin = (Button) findViewById(R.id.btnStdLogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performStdLogin();
            }
        });
        tvNewUser = (TextView) findViewById(R.id.textNewUser);
        tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentLoginActivity.this,StudentRegistrationActivity.class));
            }
        });
    }
    public void performStdLogin(){
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        Call<StudentLoginResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .studentLogin(email,password);
        call.enqueue(new Callback<StudentLoginResponse>() {
            @Override
            public void onResponse(Call<StudentLoginResponse> call, Response<StudentLoginResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getError().equals("200")){
                        sharedPrefManager.updateStudentLoginStatus(true);
                        sharedPrefManager.saveidofStudent(response.body().getSid());
                        String fullname= response.body().getFname()+" "+response.body().getLname();
                        sharedPrefManager.saveNameofStudent(fullname);
                        sharedPrefManager.saveEmailofStudent(response.body().getEmail());
                        Intent intent = new Intent(StudentLoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(StudentLoginActivity.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(StudentLoginActivity.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(StudentLoginActivity.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StudentLoginResponse> call, Throwable t) {
                Toast.makeText(StudentLoginActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void onStart() {
        super.onStart();
        if(sharedPrefManager.isStudentLogin()){
            Intent intent = new Intent(StudentLoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}