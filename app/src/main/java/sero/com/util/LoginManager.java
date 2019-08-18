package sero.com.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import sero.com.data.entities.User;

public abstract class LoginManager {

    public static void login(String login, Context context){
        getSp(context).edit()
                .putString("login", ""+login)
                .commit();
    }

    public static void logout(Context context){
        getSp(context).edit().clear().apply();
    }

    public static boolean isConnected(Context context){
        return getSp(context).contains("login");
    }

    public static SharedPreferences getSp(Context context){
        return context.getSharedPreferences("user", Context.MODE_PRIVATE);
    }

}
