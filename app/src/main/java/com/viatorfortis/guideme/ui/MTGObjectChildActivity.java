package com.viatorfortis.guideme.ui;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;
import com.squareup.picasso.Picasso;
import com.viatorfortis.guideme.R;
import com.viatorfortis.guideme.model.Child;
import com.viatorfortis.guideme.model.FullFormMTGObject;
import com.viatorfortis.guideme.model.Image;
import com.viatorfortis.guideme.utils.LoadFullFormMTGObjectByIdTask;

import java.util.List;

import static com.viatorfortis.guideme.utils.IziTravelApi.buildStoryImageUri;
import static com.viatorfortis.guideme.utils.JsonUtils.parseFullFormMTGObjectListJson;

public class MTGObjectChildActivity extends AppCompatActivity
        implements LoadFullFormMTGObjectByIdTask.OnTaskCompleteListener {

    private final String tag = this.getClass().getSimpleName();

    private Child mChild;
    private FullFormMTGObject mFullFormChildMTGObject;

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

        LoadFullFormMTGObjectByIdTask loadFullFormMTGObjectByIdTask = new LoadFullFormMTGObjectByIdTask(this);
        String [] loadParameters = {mChild.getUuid(), "en"};
        loadFullFormMTGObjectByIdTask.execute(loadParameters);

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
        ( (TextView) findViewById(R.id.tv_content_provider) ).setText(mChild.getContentProvider().getName() );
    }

    private void loadImages() {
        List<Image> imageList = mFullFormChildMTGObject.getContentList().get(0).getImages();

        ImageView pointImageView = findViewById(R.id.iv_point_image);
        Picasso.with(this)
                .load(buildStoryImageUri(mFullFormChildMTGObject.getContentProvider().getUuid(), imageList.get(0).getUuid(), "800x600") )
                .into(pointImageView);

        if (imageList.size() > 1) {
            cacheImagesExceptFirst();
        }
    }

    private void cacheImagesExceptFirst() {
        List<Image> imageList = mFullFormChildMTGObject.getContentList().get(0).getImages();

        for (int i = 1; i < imageList.size(); i++) {
            Picasso.with(this)
                    .load(buildStoryImageUri(mFullFormChildMTGObject.getContentProvider().getUuid(), imageList.get(i).getUuid(), "800x600") )
                    .fetch();
        }
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

    @Override
    public void onTaskComplete(String result) {
        try {
            mFullFormChildMTGObject = parseFullFormMTGObjectListJson(result).get(0);
        } catch (JsonSyntaxException e) {
            Log.d(tag, e.getMessage() );
        }

        List <Image> imageList = mFullFormChildMTGObject.getContentList().get(0).getImages();
        if (imageList != null
                && imageList.size() > 0) {
            loadImages();
        }
    }
}
