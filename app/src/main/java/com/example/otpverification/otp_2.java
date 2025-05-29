package com.example.otpverification;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otp_2 extends AppCompatActivity {

    EditText input1,input2,input3,input4,input5,input6;
    Button verify_btn;
    String get_otp_backend;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.otp_2);

        verify_btn = findViewById(R.id.verify_button);


        input1 = findViewById(R.id.input_no_1);
        input2 = findViewById(R.id.input_no_2);
        input3 = findViewById(R.id.input_no_3);
        input4 = findViewById(R.id.input_no_4);
        input5 = findViewById(R.id.input_no_5);
        input6 = findViewById(R.id.input_no_6);

        TextView textView = findViewById(R.id.render_mobile_number);
        textView.setText(String.format(
                "+91-%s", getIntent().getStringExtra("mobile")
        ));

        get_otp_backend = getIntent().getStringExtra("backend_otp");
        final ProgressBar progressbar_2 = findViewById(R.id.progress_bar_2);

        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!input1.getText().toString().trim().isEmpty() && !input2.getText().toString().trim().isEmpty() && !input3.getText().toString().trim().isEmpty()
                        && !input4.getText().toString().trim().isEmpty() && !input5.getText().toString().trim().isEmpty() && !input6.getText().toString().trim().isEmpty()) {

                    String enter_code_otp = input1.getText().toString() +
                            input2.getText().toString() +
                            input3.getText().toString() +
                            input4.getText().toString() +
                            input5.getText().toString() +
                            input6.getText().toString();

                    if (get_otp_backend != null) {

                        progressbar_2.setVisibility(view.VISIBLE);

                        verify_btn.setVisibility(view.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                get_otp_backend, enter_code_otp
                        );

                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressbar_2.setVisibility(view.GONE);
                                        verify_btn.setVisibility(view.VISIBLE);


                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(getApplicationContext(), dashboard.class);
                                            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(otp_2.this, "Enter correct otp", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(otp_2.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
                    }
//                    Toast.makeText(otp_2.this, "otp verify", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(otp_2.this, "please enter all number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        numberotpmove();
        
        
        TextView resend_otp = findViewById(R.id.text_resend_otp);
        
        resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                         60,
                        TimeUnit.SECONDS,
                        otp_2.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                


                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                
                                Toast.makeText(otp_2.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String new_backend_otp , @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                
                                get_otp_backend = new_backend_otp;
                                Toast.makeText(otp_2.this, "Otp sended succesfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });

    }




    private void numberotpmove(){

        input1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence , int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence , int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    input2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable charSequence ) {

            }
        });

        input2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence , int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence , int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    input3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable charSequence ) {

            }
        });

        input3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence , int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence , int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    input4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable charSequence ) {

            }
        });

        input4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence , int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence , int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    input5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable charSequence ) {

            }
        });

        input5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence , int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence , int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    input6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable charSequence ) {

            }
        });
    }

}