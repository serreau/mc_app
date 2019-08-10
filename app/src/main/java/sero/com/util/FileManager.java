package sero.com.util;

import android.net.Uri;
import android.os.Environment;

public final class FileManager {

    public  static Uri getProfilImage() {
        return Uri.parse(Environment.getExternalStorageDirectory().toString() + "/profile.png");
    }



    /*public static Uri chargeProfilImage(Uri uri) throws FileNotFoundException {
        String profile = Environment.getExternalStorageDirectory().toString() + "/profile.png";
        FileOutputStream out = new FileOutputStream(profile);

        File f = new File(uri.getPath());
        InputStream in = new FileInputStream(f);
        Bitmap b = BitmapFactory.decodeStream(in);

        b.compress(Bitmap.CompressFormat.PNG, 100, out);

        File tmp = new File(out.toString());
        return Uri.fromFile(tmp);
    }*/
}
