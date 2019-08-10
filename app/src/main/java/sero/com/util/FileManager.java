package sero.com.util;

import android.net.Uri;
import android.os.Environment;

public final class FileManager {

    public  static Uri getProfilImage() {
        return Uri.parse(Environment.getExternalStorageDirectory().toString() + "/profile.png");
    }
}
