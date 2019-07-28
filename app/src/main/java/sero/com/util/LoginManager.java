package sero.com.util;

import android.content.Context;
import android.content.SharedPreferences;

import sero.com.data.entities.User;

public class LoginManager {

    public static void login(User user, Context context){
        getSp(context).edit()
                .putBoolean("logged", true)
                .putLong("phone", user.getPhone())
                .putString("mail", user.getMail())
                .putString("firstname", user.getFirstname())
                .putString("lastname", user.getLastname())
                .commit();
    }

    public static void logout(Context context){
        getSp(context).edit().clear();
    }

    public static boolean exist(Context context){
        return getSp(context).contains("logged");
    }

    public static SharedPreferences getSp(Context context){
        return context.getSharedPreferences("login", Context.MODE_PRIVATE);
    }
}
