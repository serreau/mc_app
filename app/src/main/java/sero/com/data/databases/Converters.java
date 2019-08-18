package sero.com.data.databases;

import android.arch.persistence.room.TypeConverter;
import android.net.Uri;

import java.util.Date;

public class Converters {

    @TypeConverter
    public static Uri toUri(String value) {
        return value == null ? null : Uri.parse(value);
    }

    @TypeConverter
    public static String toString(Uri value) {
        return value == null ? null : value.getPath().toString();
    }
}
