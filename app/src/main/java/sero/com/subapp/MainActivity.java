package sero.com.subapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.Clock;

import Factories.RepositoryFactory;
import repositories.Repository;
import sero.com.databases.DB;
import sero.com.entities.Nano;

public class MainActivity extends Activity {
    Repository nanoRepository;

    TextInputEditText textedit;
    FloatingActionButton addnano;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nanoRepository = RepositoryFactory.get(RepositoryFactory.Entities.NANO, this.getApplicationContext());

        textedit = (TextInputEditText) findViewById(R.id.nano_textedit);
        textedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                addnano.setVisibility(View.VISIBLE);
                System.out.println("test");
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        addnano = (FloatingActionButton) findViewById(R.id.addnano_button);
        addnano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
