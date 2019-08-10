package sero.com.util;

import android.content.Context;
import android.content.SharedPreferences;

import sero.com.data.entities.User;

public abstract class LoginManager {
    private static User connecteduser;

    public static void login(User user, Context context){
        getSp(context).edit()
                .putBoolean("logged", true)
                .putString("login", ""+user.getPhone())
                .putString("mail", user.getMail())
                .putString("firstname", user.getFirstname())
                .putString("lastname", user.getLastname())
                .putString("password", user.getPassword())
                .commit();
        connecteduser = user;
    }

    public static void logout(Context context){
        getSp(context).edit().clear().apply();
    }

    public static boolean isConnected(Context context){
        return getSp(context).contains("logged");
    }

    public static SharedPreferences getSp(Context context){
        return context.getSharedPreferences("user", Context.MODE_PRIVATE);
    }

    public static User getUser(Context context) {
        if (connecteduser == null) {
            connecteduser = new User();
            connecteduser.setPhone(getSp(context).getString("login", ""));
            connecteduser.setMail(getSp(context).getString("mail", "error mail"));
            connecteduser.setFirstname(getSp(context).getString("firstname", "error firstname"));
            connecteduser.setLastname(getSp(context).getString("lastname", "error lastname"));
            connecteduser.setPassword(getSp(context).getString("password", "error password"));
        }
        return connecteduser;
    }
}
