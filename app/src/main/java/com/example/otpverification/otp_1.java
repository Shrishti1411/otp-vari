package com.example.otpverification;

import static com.google.firebase.Firebase.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otp_1 extends AppCompatActivity {


    EditText enter_number;
    Button get_otp_btn;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_1);



//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.otp_1);


        enter_number = findViewById(R.id.mobile_number);
        get_otp_btn = findViewById(R.id.otp_button);
        ProgressBar progressBar = findViewById(R.id.progress_bar_1);



        get_otp_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!enter_number.getText().toString().trim().isEmpty()){
                    if ((enter_number.getText().toString().trim()).length() == 10){







                        progressBar.setVisibility(view.VISIBLE);
                        get_otp_btn.setVisibility(view.INVISIBLE);



//                        PhoneAuthOptions options =
//                                PhoneAuthOptions.newBuilder(mAuth)
//                                        .setPhoneNumber("+91" + enter_number.getText().toString())
//                                        .setTimeout(60L, TimeUnit.SECONDS)
//                                        .setActivity(this)
//                                        .setCallbacks(mCallBack)
//                                        .build();
//                        PhoneAuthProvider.verifyPhoneNumber(options);



                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + enter_number.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                otp_1.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


                                        progressBar.setVisibility(view.GONE);
                                        get_otp_btn.setVisibility(view.VISIBLE);


                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {

                                        progressBar.setVisibility(view.GONE);
                                        get_otp_btn.setVisibility(view.VISIBLE);
                                        Toast.makeText(otp_1.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        progressBar.setVisibility(view.GONE);
                                        get_otp_btn.setVisibility(view.VISIBLE);
                                        Intent intent = new Intent(getApplicationContext(),otp_2.class);
                                        intent.putExtra("mobile",enter_number.getText().toString());
                                        intent.putExtra("backend_otp",s);
                                        startActivity(intent);
                                    }
                                }
                        );
                        Intent intent = new Intent(getApplicationContext(),otp_2.class);
                        intent.putExtra("mobile",enter_number.getText().toString());
                        startActivity(intent);


                    } else{
                        Toast.makeText(otp_1.this, "Please Enter correct number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(otp_1.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}