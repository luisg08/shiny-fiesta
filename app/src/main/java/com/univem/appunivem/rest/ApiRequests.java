package com.univem.appunivem.rest;

import com.univem.appunivem.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiRequests {

    @POST("/login")
    Call<User> getLogin(@Body User user);

}
