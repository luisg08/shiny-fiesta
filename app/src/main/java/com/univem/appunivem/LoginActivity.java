package com.univem.appunivem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.univem.appunivem.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String teste = (checkIsLogin() ? "true": "false");
        Log.v("checkislogin", teste);

        if (checkIsLogin()) {
            homeStart(localGetUsername());
            return;
        }
            binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
            loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
            binding.setLoginViewModel(loginViewModel);
            binding.setLifecycleOwner(this);
            loginViewModel.init(this);
    }

    public void homeStart(String username) {
        Intent abrirHome = new Intent(this.getApplicationContext(), HomeActivity.class);
        abrirHome.putExtra("username", username);
        startActivity(abrirHome);

        finish();
    }

    public boolean checkIsLogin() {
        SharedPreferences sharedPref = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        Log.d("BOO", (sharedPref.getAll()).toString());
        Log.v("isLogin", Boolean.toString(sharedPref.getBoolean("isLogin", false)));
        return (sharedPref.getBoolean("isLogin", false));
    }

    public String localGetUsername() {
        SharedPreferences sharedPref = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        return (sharedPref.getString("username", ""));
    }
}