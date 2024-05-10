package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatapp.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterView extends AppCompatActivity {
    private boolean passwordShowing= false;
    private boolean passwordShowingSubmit= false;
    private FirebaseAuth auth;
    EditText txtEmail,txtFullName,txtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);

        final EditText txtEmail = findViewById(R.id.txtEmail);
        final EditText txtFullName=findViewById(R.id.txtFullName);
        final RelativeLayout signInByPhone = findViewById(R.id.signInByPhone);
        final EditText txtPassword= findViewById(R.id.txtPassword);
        final EditText txtPasswordSubmit = findViewById(R.id.txtPasswordSubmit);
        final ImageView passwordIcon = findViewById(R.id.showHideBtn);
        final ImageView passwordIconSubmit = findViewById(R.id.showHideBtn2);
        auth = FirebaseAuth.getInstance();
        final AppCompatButton signUpBtn = findViewById(R.id.signupEmailAndPasswordBtn);
        final TextView signInBtn = findViewById(R.id.signInBtnDirector);

        setupUI(findViewById(R.id.rootLayout));
        TextWatcher passwordWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Code here will execute before text changes
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Code here will execute as the text is being changed
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkPasswordsAndAdjustButton(txtPassword, txtPasswordSubmit, signUpBtn);
            }
        };

        // Apply the same TextWatcher to both password fields
        txtPassword.addTextChangedListener(passwordWatcher);
        txtPasswordSubmit.addTextChangedListener(passwordWatcher);
        signInByPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passwordShowing){
                    passwordShowing=false;
                    txtPassword.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.show_pass_icon);
                }else{
                    passwordShowing=true;
                    txtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.hide_password_icon);
                }
                txtPassword.setSelection(txtPassword.length());
            }
        });
        passwordIconSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passwordShowingSubmit){
                    passwordShowingSubmit=false;
                    txtPasswordSubmit.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    passwordIconSubmit.setImageResource(R.drawable.show_pass_icon);
                }else{
                    passwordShowingSubmit=true;
                    txtPasswordSubmit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIconSubmit.setImageResource(R.drawable.hide_password_icon);
                }
                txtPassword.setSelection(txtPassword.length());
            }
        });
        signUpBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                createUser();
                return true; // Trả về true để chỉ định rằng sự kiện được xử lý hoàn toàn
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void checkPasswordsAndAdjustButton(EditText txtPassword, EditText txtPasswordSubmit, AppCompatButton button) {
        if (!txtPassword.getText().toString().equals(txtPasswordSubmit.getText().toString())) {
            button.setEnabled(false);
            button.setAlpha(0.5f); // Make button appear blurred or faded
        } else {
            button.setEnabled(true);
            button.setAlpha(1.0f); // Restore button appearance
        }
    }
    private void setupUI(View view) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(RegisterView.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView != null) {
            inputMethodManager.hideSoftInputFromWindow(
                    currentFocusedView.getWindowToken(), 0);
        }
    }

    private void createUser() {
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        String name = txtFullName.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            txtEmail.setError("Email cannot be empty");
            txtEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            txtPassword.setError("Password cannot be empty");
            txtPassword.requestFocus();
        } else {
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // User is successfully created, now add to database
                                User user = new User("", name, "ok", "offline", email);
                                addUserToDatabase(user);
                                Toast.makeText(RegisterView.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterView.this, LoginView.class));
                            } else {
                                // Handle failures
                                Log.e("RegistrationError", "Registration failed: ", task.getException());
                                Toast.makeText(RegisterView.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    private void addUserToDatabase(User user) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            usersRef.child(userId).setValue(user);
        } else {
            Log.e("FirebaseError", "The user is not logged in or registration failed.");
        }
    }
}