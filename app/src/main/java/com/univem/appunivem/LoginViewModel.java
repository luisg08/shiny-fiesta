package com.univem.appunivem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.univem.appunivem.model.User;
import com.univem.appunivem.repositories.Repositories;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> errMsg = new MutableLiveData<>();
    private Context context;
    LoginActivity lgnActivity = new LoginActivity();

    public void init(Context context){
        this.context = context;
    }

    public void login(){
        errMsg.setValue("");
        if(getUsername().getValue().isEmpty() || getPassword().getValue().isEmpty()){
            errMsg.setValue("Username ou Senha vazio");
        }
        if(errMsg.getValue().isEmpty()){
            User user = Repositories.getInstance().login(getUsername().getValue(),getPassword().getValue());
            if(getUsername().getValue() != null && getPassword().getValue() != null){
                errMsg.setValue("");
                Toast.makeText(context, "Login sucesso", Toast.LENGTH_SHORT).show();

                saveLogin();

                Intent abrirHome = new Intent(context.getApplicationContext(), HomeActivity.class);
                abrirHome.putExtra("username", getUsername().getValue());
                context.startActivity(abrirHome);


            }else{
                errMsg.setValue("Incorreto Username ou Senha");
            }
        }
    }


    public void saveLogin() {
        SharedPreferences sharedPref = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("isLogin", true);
        editor.putString("username", String.valueOf(getUsername().getValue()));
        editor.commit();
    }

    public MutableLiveData<String> getUsername(){
        if(username.getValue() == null)
            username.setValue("");
        return username;
    }

    public MutableLiveData<String> getPassword(){
        if(password.getValue() == null){
            password.setValue("");
        }
        return password;
    }
}