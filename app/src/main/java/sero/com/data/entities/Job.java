package sero.com.data.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Job")
public class Job {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title1;
    private String title2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    @Override
    public String toString() {
        return "id : "+id+", title1 : "+title1+", title2 : "+title2;
    }
}
