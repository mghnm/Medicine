package hkr.da216a.medicine.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import hkr.da216a.medicine.R;

public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = RegisterActivity.class.getSimpleName();

    private TextInputLayout emailEditTextLayout;
    private TextInputEditText emailEditText;

    private TextInputLayout passwordEditTextLayout;
    private TextInputEditText passwordEditText;

    private TextInputLayout repeatPasswordEditTextLayout;
    private TextInputEditText repeatPasswordEditText;

    private Button backButton;
    private TextView termsOfServiceTextView;
    private CheckBox termsOfServiceCheckBox;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initializeLocalVariables();
        setupInputErrorCheckingForAllEditTexts();
        setupClickListeners();
    }

    private void initializeLocalVariables() {
        this.emailEditTextLayout = findViewById(R.id.register_email_edit_text_layout);
        this.emailEditText = findViewById(R.id.register_email_edit_text);

        this.passwordEditTextLayout = findViewById(R.id.register_password_edit_text_layout);
        this.passwordEditText = findViewById(R.id.register_password_edit_text);

        this.repeatPasswordEditTextLayout = findViewById(R.id.register_repeat_password_edit_text_layout);
        this.repeatPasswordEditText = findViewById(R.id.register_repeat_password_edit_text);

        this.backButton = findViewById(R.id.back_button);
        this.termsOfServiceTextView = findViewById(R.id.register_terms_of_service_text_link);
        this.termsOfServiceCheckBox = findViewById(R.id.register_terms_of_service_checkbox);
        this.registerButton = findViewById(R.id.register_register_button);
    }

    private void setupInputErrorCheckingForAllEditTexts() {
        emailEditText.addTextChangedListener(LoginUtils.clearLayoutErrorOnUpdateTextWatcher(emailEditTextLayout));
        emailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!LoginUtils.isValidEmail(emailEditText.getText())) {
                        emailEditTextLayout.setError("Please enter a valid Email");
                    }
                }
            }
        });

        passwordEditText.addTextChangedListener(
                LoginUtils.clearLayoutErrorOnUpdateTextWatcher(passwordEditTextLayout));
        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (LoginUtils.isValidPassword(passwordEditText.getText())) {
                        passwordEditTextLayout.setError("Must contain:" +
                                " Minimum 8 characters, a capital letter," +
                                " a lower-case letter and a number." +
                                "\nSpecial characters allowed: -_@$!%*?&");
                    }
                }
            }
        });

        repeatPasswordEditText.addTextChangedListener(
                LoginUtils.clearLayoutErrorOnUpdateTextWatcher(repeatPasswordEditTextLayout));
        repeatPasswordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (LoginUtils.matchesText(passwordEditText.getText(),
                            repeatPasswordEditText.getText())) {
                        repeatPasswordEditTextLayout.setError("Your passwords must match");
                    }
                }
            }
        });
    }

    private void setupClickListeners() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        termsOfServiceTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: clicked terms of service");
                showTermsAndConditionsPopupWindow(v);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), LoginActivity.class));
            }
        });
    }

    private void registerUser() {
        //todo php
        //termsOfServiceCheckBox
    }

    private void showTermsAndConditionsPopupWindow(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.popup_terms_of_service, null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
