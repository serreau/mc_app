package sero.com.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import sero.com.data.entities.User;

public abstract class SharedPreferencesHelper {

    public static void login(String login, Context context){
        getSp(context).edit()
                .putString("login", login)
                .apply();
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

    public static void setKanban(String kanban, Context context){
        getSp(context).edit()
                .putString("kanban", kanban)
                .apply();
    }

    public static String  getKanban(Context context){
        return getSp(context).getString("kanban", "public");
    }

    public static String getlogin(Context context){
        return context.getSharedPreferences("user", Context.MODE_PRIVATE).getString("login", "error_login");
    }

}
