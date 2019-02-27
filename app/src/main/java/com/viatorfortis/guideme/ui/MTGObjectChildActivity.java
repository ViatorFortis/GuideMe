package com.viatorfortis.guideme.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.viatorfortis.guideme.R;
import com.viatorfortis.guideme.model.Child;

public class MTGObjectChildActivity extends AppCompatActivity {

    private Child mChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtgobject_child);

        try {
            mChild = getIntent().getParcelableExtra(getString(R.string.mtgobject_child_parcel_key) );
        } catch (NullPointerException e) {
            Toast.makeText(this, "Unable to get point information", Toast.LENGTH_LONG).show();
            finish();

            return;
        }

        ( (TextView) findViewById(R.id.tv_title) ).setText(mChild.getTitle() );
        ( (TextView) findViewById(R.id.tv_desc) ).setText(mChild.getDesc() );
    }
}
