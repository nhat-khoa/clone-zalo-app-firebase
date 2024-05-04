package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class StartActivity extends AppCompatActivity {
    private Button btn_loginWithGG;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN = 20;
    private FirebaseUser firebaseUser;

    EditText signupName,signupEmail,signupUsername,signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        signupButton=findViewById(R.id.signup_btn);
        signupEmail=findViewById(R.id.signup_email);
        signupUsername=findViewById(R.id.signup_username);
        signupPassword=findViewById(R.id.signup_pass);
        signupName=findViewById(R.id.signup_name);
        loginRedirectText=findViewById(R.id.loginRedirectText);




        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        btn_loginWithGG = findViewById(R.id.btn_loginWithGG);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        reference = database.getReference("user");
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String userName = signupUsername.getText().toString();
                String password = signupPassword.getText().toString();

                HelperClass helperClass = new HelperClass(name,email,userName,password);
                reference.child(name).setValue(helperClass);

                Toast.makeText(StartActivity.this,"Signup Successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StartActivity.this, LoginScreen.class);
                startActivity(intent);
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, LoginScreen.class);
                startActivity(intent);
            }
        });
        //login by GG
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btn_loginWithGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();
            }
        });
    }

    private void googleSignIn() {
        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                fireBaseAuth(account.getIdToken());
            } catch (Exception e) {
                Toast.makeText(StartActivity.this, e.getMessage()+"e.getMessage() loi o day", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void fireBaseAuth(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            // kiểm tra xem user có tồn tại trong db hay chưa, nếu chưa thì lưu thông tin user lại
                            database.getReference().child("users").child(user.getUid()).get()
                                    .addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                                        @Override
                                        public void onSuccess(DataSnapshot dataSnapshot) {
                                            if (!dataSnapshot.exists()) {
                                                HashMap<String, Object> map = new HashMap<>();
                                                map.put("id", user.getUid());
                                                map.put("name", user.getDisplayName());
                                                map.put("profile", user.getPhotoUrl().toString());
                                                map.put("email", user.getEmail());
                                                map.put("status", "offline");
                                                database.getReference().child("users").child(user.getUid()).setValue(map);
                                            }
                                            Intent intent = new Intent(StartActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}