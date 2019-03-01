package com.viatorfortis.guideme.ui;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;
import com.viatorfortis.guideme.R;
import com.viatorfortis.guideme.model.FullFormMTGObject;
import com.viatorfortis.guideme.model.MTGObject;
import com.viatorfortis.guideme.rv.MTGObjectChildListAdapter;
import com.viatorfortis.guideme.utils.LoadFullFormMTGObjectByIdTask;
import com.viatorfortis.guideme.vp.MTGObjectFragmentPagerAdapter;

import static com.viatorfortis.guideme.utils.IziTravelObjectUtils.MTGO_CHILD_STORY_NAVIGATION_TYPE;
import static com.viatorfortis.guideme.utils.IziTravelObjectUtils.excludeMtgObjectChildByType;
import static com.viatorfortis.guideme.utils.JsonUtils.parseFullFormMTGObjectListJson;

public class MTGObjectActivity extends AppCompatActivity
        implements LoadFullFormMTGObjectByIdTask.OnTaskCompleteListener,
        MTGObjectChildListAdapter.GridItemClickListener {

    private final String tag = this.getClass().getSimpleName();

    private FullFormMTGObject mFullFormMTGObject;

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtgobject);

        Toolbar appBar = findViewById(R.id.tb_mtgobject);
        setSupportActionBar(appBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        MTGObject mtgObject;

        if (savedInstanceState == null || !savedInstanceState.containsKey("FullFormMTGObject") ) {
            try {
                mtgObject = getIntent().getParcelableExtra(getString(R.string.mtgobject_parcel_key) );
            } catch (NullPointerException e) {
                Toast.makeText(this, "Unable to get MTGObject details", Toast.LENGTH_LONG).show();
                finish();
                return;
            }

            LoadFullFormMTGObjectByIdTask loadFullFormMTGObjectByIdTask = new LoadFullFormMTGObjectByIdTask(this);
            String [] loadParameters = {mtgObject.getUuid(), "en"};
            loadFullFormMTGObjectByIdTask.execute(loadParameters);
        } else {
            mFullFormMTGObject = savedInstanceState.getParcelable("FullFormMTGObject");
            populateUI();
            initializeViewPager();
        }
    }

    private void populateUI() {
        getSupportActionBar().setTitle(mFullFormMTGObject.getType() );

        ((TextView) findViewById(R.id.tv_title)).setText(mFullFormMTGObject.getContentList().get(0).getTitle() );
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        try {
            outState.putParcelable("FullFormMTGObject", mFullFormMTGObject);
        } catch (Exception e) {
            Log.d(e.getClass().getName(), e.getMessage() );
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
            mFullFormMTGObject = parseFullFormMTGObjectListJson(result).get(0);
        } catch (JsonSyntaxException e) {
            Log.d(tag, e.getMessage() );
        }

        excludeMtgObjectChildByType(mFullFormMTGObject.getContentList().get(0).getChildren(), MTGO_CHILD_STORY_NAVIGATION_TYPE);

        populateUI();
        initializeViewPager();
    }

    private void initializeViewPager() {
        PagerTabStrip pagerTabStrip = findViewById(R.id.pts_content);
        pagerTabStrip.setDrawFullUnderline(true);
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.colorPrimaryDark) );
        pagerTabStrip.setTextColor(getResources().getColor(R.color.colorPrimaryDark) );
        //pagerTabStrip.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark) );

        mViewPager = findViewById(R.id.vp_mtgobject_content);
        mPagerAdapter = new MTGObjectFragmentPagerAdapter(getSupportFragmentManager(), mFullFormMTGObject);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(1);
    }


    @Override
    public void onGridItemClick(int itemPosition) {
        Intent intent = new Intent(this, MTGObjectChildActivity.class);
        intent.putExtra(getString(R.string.mtgobject_child_parcel_key), mFullFormMTGObject.getContentList().get(0).getChildren().get(itemPosition) );

        startActivity(intent);
    }
}
