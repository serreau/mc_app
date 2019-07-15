package sero.com.subapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Factories.RepositoryFactory;
import repositories.Repository;
import sero.com.entities.Nano;

public class MainActivity extends Activity {
    Repository nanoRepository;

    TextInputEditText textedit;
    FloatingActionButton addnano;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nanoRepository = RepositoryFactory.get(RepositoryFactory.Entities.NANO, this.getApplicationContext());

        textedit = (TextInputEditText) findViewById(R.id.textedit_home);
        addnano = (FloatingActionButton) findViewById(R.id.button_home);
        listview = (ListView) findViewById(R.id.list_home);


        textedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        addnano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nano n = new Nano();
                n.setName(textedit.getText().toString());
                nanoRepository.insert(n);
                Toast t = Toast.makeText( getApplicationContext(), "test", Toast.LENGTH_SHORT );
                t.show();
                System.out.println(nanoRepository.getAll().toString());
            }
        });

    }
}
