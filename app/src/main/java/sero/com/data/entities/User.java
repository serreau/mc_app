package sero.com.data.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "User")
public class User {
    @NonNull
    @PrimaryKey
    private String phone;
    private String password;
    private String mail;
    private String firstname;
    private String lastname;
    private String image ;

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        //return image;
        return "https://img5.goodfon.com/original/2880x1800/a/36/tochki-linii-soedineniia.jpg";
    }

    public void setImage(String image) {
        this.image = image;
    }
}
