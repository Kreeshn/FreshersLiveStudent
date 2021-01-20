package com.example.fresherslivestudent.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fresherslivestudent.R;
import com.example.fresherslivestudent.model.StudentLoginResponse;
import com.example.fresherslivestudent.retrofitutil.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentRegistrationActivity extends AppCompatActivity {
    private EditText etFname, etLname, etDOB, etContact, etEmail, etPass;
    RadioGroup radioGroup;
    RadioButton radioButton;
    private TextView textLogin;
    Button btnReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        etFname = (EditText) findViewById(R.id.etfName);
        etLname = (EditText) findViewById(R.id.etlName);
        etDOB = (EditText) findViewById(R.id.etDOB);
        etContact = (EditText) findViewById(R.id.etcontact);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        radioGroup = (RadioGroup) findViewById(R.id.rgStudent);
        textLogin = (TextView) findViewById(R.id.textLogin);

        btnReg = (Button) findViewById(R.id.btnReg);
        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentRegistrationActivity.this, StudentLoginActivity.class));
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performReg();
            }
        });
    }
    public  void performReg(){
        String fname = etFname.getText().toString().trim();
        String lnamae = etLname.getText().toString().trim();
        String dob = etDOB.getText().toString().trim();
        String contanct = etContact.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPass.getText().toString().trim();
        int radioid = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(radioid);
        String gender = radioButton.getText().toString().trim();

        Call<StudentLoginResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .studentReg(fname,lnamae,dob,gender,contanct,email,password);

        call.enqueue(new Callback<StudentLoginResponse>() {
            @Override
            public void onResponse(Call<StudentLoginResponse> call, Response<StudentLoginResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getError().equals("200")){
                        Toast.makeText(StudentRegistrationActivity.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(StudentRegistrationActivity.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(StudentRegistrationActivity.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(StudentRegistrationActivity.this, StudentLoginActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<StudentLoginResponse> call, Throwable t) {
                Toast.makeText(StudentRegistrationActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}