package sero.com.subapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import sero.com.dao.NanosDao;
import sero.com.databases.DB;
import sero.com.entities.Nanos;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB db = DB.getInstance(this.getApplicationContext());
        NanosDao nanosDao = db.nanosDao();
        Nanos nano = new Nanos();
        nano.setName("Mon nano");
        nano.setDescription("Ma description de mon nano");
        nano.setAuthor("Anthony");
        nano.setLocation("Aix en Provence");
        nanosDao.insert(nano);

        TextView tv = (TextView) findViewById(R.id.test_db);
        tv.setText(nano.toString());
    }
}
