package salle.android.projects.service_test.controller.restapi.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import salle.android.projects.service_test.model.UserLogin;
import salle.android.projects.service_test.model.UserToken;

public interface UserTokenService {

    @POST("authenticate")
    Call<UserToken> loginUser(@Body UserLogin login);

}
