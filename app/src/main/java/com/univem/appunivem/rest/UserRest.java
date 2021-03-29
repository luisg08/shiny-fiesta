package com.univem.appunivem.rest;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.univem.appunivem.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRest {
    private static final UserRest ourInstance = new UserRest();;

    private MutableLiveData<User> mutableLiveUserData= new MutableLiveData<>();

    public static UserRest getInstance(){
        return ourInstance;
    }

    public LiveData<User> login(String username, String password){

        User user = new User(username,password);

        Call<User> call = ApiClient.getInstance().getLogin(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                mutableLiveUserData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("userrest", "onFailure: failed to login");
            }
        });

        return mutableLiveUserData;
    }
}
