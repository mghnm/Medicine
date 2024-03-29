package hkr.da216a.medicine.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import hkr.da216a.medicine.database.DatabaseFacade;
import hkr.da216a.medicine.database.DatabaseHandler;
import hkr.da216a.medicine.R;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = LoginActivity.class.getSimpleName();

    private TextInputLayout personalNumberEditTextLayout;
    private TextInputEditText personalNumberEditText;

    private TextInputLayout passwordEditTextLayout;
    private TextInputEditText passwordEditText;

    private Button loginButton;

    private TextView haveNoAccountTextView;
    private TextView haveNoAccountTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeLocalVariables();
        setupInputErrorCheckingForAllEditTexts();
        setupClickListeners();
    }

    private void initializeLocalVariables() {
        this.personalNumberEditTextLayout = findViewById(R.id.login_personal_number_text_layout);
        this.personalNumberEditText = findViewById(R.id.login_personal_number_text);

        this.passwordEditTextLayout = findViewById(R.id.loging_password_text_layout);
        this.passwordEditText = findViewById(R.id.login_password_text);

        this.loginButton = findViewById(R.id.login_login_button);

        this.haveNoAccountTextView = findViewById(R.id.login_have_no_account_text);
        this.haveNoAccountTextView2 = findViewById(R.id.login_have_no_account_text_2);
    }

    private void setupInputErrorCheckingForAllEditTexts() {
        personalNumberEditText.addTextChangedListener(LoginUtils.clearLayoutErrorOnUpdateTextWatcher(personalNumberEditTextLayout));
        personalNumberEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (LoginUtils.isValidEmail(personalNumberEditText.getText())) { //todo toString() ?
                        personalNumberEditTextLayout.setError("Please enter a valid Email");
                    }
                }
            }
        });

        passwordEditText.addTextChangedListener(LoginUtils.clearLayoutErrorOnUpdateTextWatcher(passwordEditTextLayout));
        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (LoginUtils.isValidPassword(passwordEditText.getText())) {
                        passwordEditTextLayout.setError("Please enter a valid Password");

                    }
                }
            }
        });
    }

    private void setupClickListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        haveNoAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });

        haveNoAccountTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });
    }

    private void openRegisterActivity() {
        Intent registerActivityIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerActivityIntent);
    }

    private void loginUser() {
        //TODO validity check for inputs
        String personalNumber = personalNumberEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        DatabaseFacade.loginWithCredentials(LoginActivity.this, personalNumber, password);

        personalNumberEditText.setText("");
        passwordEditText.setText("");
    }

    public void openMainScene() {

    }

}
