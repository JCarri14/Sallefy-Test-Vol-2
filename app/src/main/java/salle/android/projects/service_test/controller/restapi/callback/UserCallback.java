package salle.android.projects.service_test.controller.restapi.callback;

import salle.android.projects.service_test.model.User;
import salle.android.projects.service_test.model.UserToken;

public interface UserCallback extends FailureCallback {
    void onLoginSuccess(UserToken userToken);
    void onLoginFailure(Throwable throwable);
    void onRegisterSuccess();
    void onRegisterFailure(Throwable throwable);
    void onUserInfoReceived(User userData);
}
