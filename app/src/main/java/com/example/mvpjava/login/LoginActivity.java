package com.example.mvpjava.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.mvpjava.R;
import com.example.mvpjava.home.HomeActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private ProgressBar progressBar;
    private EditText mUsername,mPassword;
    private Button mLogin;
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress);
        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mLogin = findViewById(R.id.button);


        mPresenter=new LoginPresenter(this,new LoginInteractor());

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateCredentials();

            }
        });
    }



    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        mUsername.setError("Invalid User Name");
    }

    @Override
    public void setPasswordError() {
        mPassword.setError("Invalid password");
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
    private void validateCredentials() {
        mPresenter.validateCredentials(mUsername.getText().toString(), mPassword.getText().toString());
    }
}
