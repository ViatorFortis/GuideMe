package com.viatorfortis.guideme.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.viatorfortis.guideme.R;
import com.viatorfortis.guideme.model.MTGObject;

public class MTGObjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtgobject);



        MTGObject mtgObject;

        try {
            mtgObject = getIntent().getParcelableExtra(getString(R.string.mtgobject_parcel_key) );
        } catch (NullPointerException e) {
            Toast.makeText(this, "Unable to get MTGObject details", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        Toolbar appBar = findViewById(R.id.tb_mtgobject);
        setSupportActionBar(appBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(mtgObject.getType() );
        }

        TextView titleTextView = findViewById(R.id.tv_title);
        titleTextView.setText(mtgObject.getTitle());
    }
}
