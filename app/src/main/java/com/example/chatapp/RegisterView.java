package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class RegisterView extends AppCompatActivity {
    private boolean passwordShowing= false;
    private boolean passwordShowingSubmit= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);

        final EditText txtEmail = findViewById(R.id.txtEmail);
        final EditText txtFullName=findViewById(R.id.txtFullName);
        final EditText txtPassword= findViewById(R.id.txtPassword);
        final EditText txtPasswordSubmit = findViewById(R.id.txtPasswordSubmit);
        final ImageView passwordIcon = findViewById(R.id.showHideBtn);
        final ImageView passwordIconSubmit = findViewById(R.id.showHideBtn2);

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
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
}