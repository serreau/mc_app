package sero.com.data.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;
import android.support.annotation.NonNull;

@Entity(tableName = "Session")
public class Session {
    @NonNull
    @PrimaryKey
    private String login;

    public String getPhone() {
        return this.login;
    }

    public void setPhone(String login) {
        this.login = login;
    }
}
