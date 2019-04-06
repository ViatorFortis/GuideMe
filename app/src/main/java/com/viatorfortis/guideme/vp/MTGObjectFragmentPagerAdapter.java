package com.viatorfortis.guideme.vp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.viatorfortis.guideme.model.FullFormMTGObject;
import com.viatorfortis.guideme.ui.MTGOMapFragment;
import com.viatorfortis.guideme.ui.MTGObjectChildListFragment;
import com.viatorfortis.guideme.ui.MTGObjectGeneralInfoFragment;

public class MTGObjectFragmentPagerAdapter extends FragmentPagerAdapter {

    private FullFormMTGObject mFullFormMTGObject;

    public MTGObjectFragmentPagerAdapter(FragmentManager fm, FullFormMTGObject fullFormMTGObject) {
        super(fm);

        mFullFormMTGObject = fullFormMTGObject;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = MTGObjectGeneralInfoFragment.newInstance(mFullFormMTGObject.getContentList().get(0).getDesc() );
                break;
            case 1:
                fragment = MTGObjectChildListFragment.newInstance(mFullFormMTGObject.getContentList().get(0).getChildren() );
                break;
            case 2:
                fragment = MTGOMapFragment.newInstance(mFullFormMTGObject);
        }

        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;

        switch (position) {
            case 0:
                title = "Description";
                break;
            case 1:
                title = "Points";
                break;
            case 2:
                title = "Map";
        }

        return title;
    }
}
