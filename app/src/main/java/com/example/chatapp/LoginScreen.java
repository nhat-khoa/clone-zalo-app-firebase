package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginScreen extends AppCompatActivity {
    EditText loginUsername,loginPassword;
    Button loginButton;
    TextView signupRedirectText;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_pass);
        signupRedirectText = findViewById(R.id.SignupRedirectText);
        loginButton = findViewById(R.id.login_btn);
        auth = FirebaseAuth.getInstance();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateUsername()| !validatePassword()){

                }else{
                    checkUser();
                }
            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginScreen.this,StartActivity.class);
                startActivity(intent);
            }
        });
    }

    public Boolean validateUsername(){
        String val = loginUsername.getText().toString();
        if(val.isEmpty()){
            loginUsername.setError("Username cannot be empty");
            return false;
        }else{
            loginUsername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = loginPassword.getText().toString();
        if(val.isEmpty()){
            loginPassword.setError("Username cannot be empty");
            return false;
        }else{
            loginPassword.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String userUsername= loginUsername.getText().toString().trim();
        String userPassword= loginPassword.getText().toString().trim();

        if(TextUtils.isEmpty(userUsername)){
            loginUsername.setError("Email cannot be empty");
            loginUsername.requestFocus();
        }else if(TextUtils.isEmpty(userPassword)){
            loginPassword.setError("Password cannot be empty");
            loginPassword.requestFocus();
        }else{
            auth.signInWithEmailAndPassword(userUsername,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginScreen.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginScreen.this,MainActivity.class));
                    }else{
                        Toast.makeText(LoginScreen.this, "Login Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}