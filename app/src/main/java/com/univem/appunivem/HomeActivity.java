package com.univem.appunivem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.univem.appunivem.model.User;
import com.univem.appunivem.repositories.Repositories;

public class HomeActivity extends AppCompatActivity {

    Button logout;
    LoginActivity lgnActivity = new LoginActivity();
    LoginViewModel lgnViewModel = new LoginViewModel();

    private Context context;

    public void init(Context context){
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logout = findViewById(R.id.buttonLogout);
        String username = getIntent().getStringExtra("username");

        Log.d("username", username);
        
        ((TextView)findViewById(R.id.textView1)).setText(username);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                voltarLogin();
            }
        });
    }

    public void voltarLogin(){
        logout();
        Intent voltarLogin = new Intent(this, LoginActivity.class);
        startActivity(voltarLogin);
    }

    public void logout(){
        SharedPreferences sharedPref = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("isLogin", false);
        editor.putString("username", "");
        Log.d("logout", "passou aqui");
        editor.commit();
    }

}