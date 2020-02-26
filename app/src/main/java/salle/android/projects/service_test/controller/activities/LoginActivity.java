package salle.android.projects.service_test.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import salle.android.projects.service_test.R;
import salle.android.projects.service_test.controller.restapi.callback.UserCallback;
import salle.android.projects.service_test.controller.restapi.manager.UserManager;
import salle.android.projects.service_test.model.User;
import salle.android.projects.service_test.model.UserToken;
import salle.android.projects.service_test.utils.Constants;
import salle.android.projects.service_test.utils.PreferenceUtils;
import salle.android.projects.service_test.utils.Session;

public class LoginActivity extends AppCompatActivity implements UserCallback {

    private EditText etLogin;
    private EditText etPassword;
    private Button btnLogin;


    @Override
    public void onCreate(Bundle savedInstanceSate) {
        super.onCreate(savedInstanceSate);
        setContentView(R.layout.activity_login);
        initViews();
        checkForSavedData();
    }

    private void initViews () {

        etLogin = (EditText) findViewById(R.id.login_user);
        etPassword = (EditText) findViewById(R.id.login_password);

        btnLogin = (Button) findViewById(R.id.login_btn_action);
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Click de Login");
                doLogin(etLogin.getText().toString(), etPassword.getText().toString());
            }
        });
    }

    private void checkForSavedData() {
        if (checkExistingPreferences()) {
            etLogin.setText(PreferenceUtils.getUser(this));
            etPassword.setText(PreferenceUtils.getPassword(this));
        }
    }

    private void doLogin(String username, String userpassword) {
        UserManager.getInstance(getApplicationContext())
                .loginAttempt(username, userpassword, LoginActivity.this);
    }

    private boolean checkExistingPreferences () {
        return PreferenceUtils.getUser(this) != null
                && PreferenceUtils.getPassword(this) != null;
    }


    @Override
    public void onLoginSuccess(UserToken userToken) {
        Session.getInstance(getApplicationContext())
                .setUserToken(userToken);
        PreferenceUtils.saveUser(this, etLogin.getText().toString());
        PreferenceUtils.savePassword(this, etPassword.getText().toString());
        UserManager.getInstance(this).getUserData(etLogin.getText().toString(), this);
    }

    @Override
    public void onLoginFailure(Throwable throwable) {
        Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onRegisterSuccess() {

    }

    @Override
    public void onRegisterFailure(Throwable throwable) {

    }

    @Override
    public void onUserInfoReceived(User userData) {
        Session.getInstance(getApplicationContext())
                .setUser(userData);
        Intent intent= new Intent();
        setResult(Constants.NETWORK.LOGIN_OK,intent);
        finish();
    }

    @Override
    public void onFailure(Throwable throwable) {

    }

}

