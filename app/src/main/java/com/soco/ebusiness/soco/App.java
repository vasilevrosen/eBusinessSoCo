package com.soco.ebusiness.soco;

import android.app.Application;
import android.util.Log;
import android.widget.Button;

import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.ParseGeoPoint;
/**
 * Created by Rosen on 27.05.2015.
 */

public class App extends Application {

    private static boolean loginstate = false;
    private static String login_text;




    public static boolean get_loginstate() {
        return loginstate;
    }

    public static void setloginstate(boolean loginstate) {
        App.loginstate = loginstate;
    }



    @Override
    public void onCreate() {

        super.onCreate();
        //FacebookSdk.sdkInitialize(this.getApplicationContext());
        ParseObject.registerSubclass(Rezept.class);
        ParseObject.registerSubclass(Event.class);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "r5kFDsKD0dBiQpdiSxSxpQkiIAy1ytBUV1olpd0U", "C8XCXf6LQp2GBYfedTlaDnLFjQWctB7yc0DzZ2L5");
        ParseFacebookUtils.initialize(this.getApplicationContext());
        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
            loginstate = true;
        } else {
            loginstate= false;
            // show the signup or login screen
        }

    }
    public String islogin(){
        if(loginstate){
            login_text= getString(R.string.login);
            return login_text;
        } else {

            login_text= getString(R.string.logout);
            return login_text;
        }

    }

}