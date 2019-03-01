package com.viatorfortis.guideme.ui;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.viatorfortis.guideme.R;
import com.viatorfortis.guideme.model.Child;

import static com.viatorfortis.guideme.utils.IziTravelApi.buildStoryImageUri;

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

//        Toolbar appBar = findViewById(R.id.tb_mtgobject_child);
//        setSupportActionBar(appBar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
//            getSupportActionBar().setTitle(mChild.getType() );
//        }

        setSupportActionBar((Toolbar) findViewById(R.id.tb_mtgobject_child) );

        if (getSupportActionBar() != null) {
            ActionBar actionBar = getSupportActionBar();
            //actionBar.setDisplayOptions();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setCustomView(R.layout.actionbar_title);
            ( (TextView) findViewById(R.id.ab_title) ).setText(mChild.getTitle() );
        }


        ( (TextView) findViewById(R.id.tv_title) ).setText(mChild.getTitle() );
        ( (TextView) findViewById(R.id.tv_desc) ).setText(mChild.getDesc() );

        ImageView pointImageView = findViewById(R.id.iv_point_image);
        Picasso.with(this)
                .load(buildStoryImageUri(mChild.getContentProvider().getUuid(), mChild.getImages().get(0).getUuid(), "800x600") )
                .into(pointImageView);

        ( (TextView) findViewById(R.id.tv_content_provider) ).setText(mChild.getContentProvider().getName() );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
