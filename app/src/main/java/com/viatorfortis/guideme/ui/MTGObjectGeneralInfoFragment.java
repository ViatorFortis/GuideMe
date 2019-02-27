package com.viatorfortis.guideme.ui;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viatorfortis.guideme.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MTGObjectGeneralInfoFragment extends Fragment {

    private String mDescription;

    public static MTGObjectGeneralInfoFragment newInstance(String description) {
        MTGObjectGeneralInfoFragment fragment = new MTGObjectGeneralInfoFragment();
        Bundle arguments = new Bundle();
        arguments.putString("mtgobject_description", description);
        fragment.setArguments(arguments);
        return fragment;
    }

    public MTGObjectGeneralInfoFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDescription = getArguments().getString("mtgobject_description");
        Log.d("Fragment", String.valueOf(mDescription) );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mtgobject_general_info, null);

        TextView descriptionTextView = view.findViewById(R.id.tv_description);

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
            descriptionTextView.setText(Html.fromHtml(mDescription, Html.FROM_HTML_MODE_COMPACT));
        } else {
            descriptionTextView.setText(Html.fromHtml(mDescription));
        }

        return view;
    }
}
