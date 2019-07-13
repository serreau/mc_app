package sero.com.subapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import Factories.RepositoryFactory;
import repositories.Repository;
import sero.com.entities.Nano;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository nanoRepository = RepositoryFactory.get(RepositoryFactory.Entities.NANO, this.getApplicationContext());
        Nano nano = new Nano();
        nano.setName("Mon nano");
        nano.setDescription("Ma description de mon nano");
        nano.setAuthor("Anthony");
        nano.setLocation("Aix en Provence");
        nanoRepository.insert(nano);

        TextView tv = (TextView) findViewById(R.id.test_db);
        tv.setText(nano.toString());
    }
}
