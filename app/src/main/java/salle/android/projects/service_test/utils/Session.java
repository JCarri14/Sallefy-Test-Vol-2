package salle.android.projects.service_test.utils;

import android.content.Context;

import salle.android.projects.service_test.model.Track;
import salle.android.projects.service_test.model.User;
import salle.android.projects.service_test.model.UserRegister;
import salle.android.projects.service_test.model.UserToken;

public class Session {

    public static Session sSession;
    private static Object mutex = new Object();

    private Context mContext;

    private User mUser;
    private UserToken mUserToken;

    private Track mTrack;

    public static Session getInstance(Context context) {
        Session result = sSession;
        if (result == null) {
            synchronized (mutex) {
                result = sSession;
                if (result == null)
                    sSession = result = new Session();
            }
        }
        return result;
    }

    private Session() {}

    public Session(Context context) {
        this.mContext = context;
        this.mUserToken = null;
    }

    public void resetValues() {
        mUserToken = null;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public UserToken getUserToken() {
        return mUserToken;
    }

    public void setUserToken(UserToken userToken) {
        this.mUserToken = userToken;
    }
}
