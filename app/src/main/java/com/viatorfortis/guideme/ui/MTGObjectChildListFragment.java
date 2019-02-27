package com.viatorfortis.guideme.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viatorfortis.guideme.R;
import com.viatorfortis.guideme.model.Child;
import com.viatorfortis.guideme.rv.MTGObjectChildListAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MTGObjectChildListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MTGObjectChildListFragment extends Fragment {

    private ArrayList<Child> mChildList;

    private static final String CHILD_LIST_PARCEL_KEY = "child_list_parcel";

    private MTGObjectChildListAdapter.GridItemClickListener mOnGridItemClickListener;


    public MTGObjectChildListFragment() {
        // Required empty public constructor
    }

    public static MTGObjectChildListFragment newInstance(List<Child> childList) {
        MTGObjectChildListFragment fragment = new MTGObjectChildListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(CHILD_LIST_PARCEL_KEY, (ArrayList<Child>) childList);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MTGObjectChildListAdapter.GridItemClickListener) {
            mOnGridItemClickListener = (MTGObjectChildListAdapter.GridItemClickListener) context;
        } else {
            throw new ClassCastException(context.toString() +
                    getResources().getString(R.string.cast_to_interface_exception_message, MTGObjectChildListAdapter.GridItemClickListener.class.getName() ) );
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(CHILD_LIST_PARCEL_KEY) ) {
            mChildList = getArguments().getParcelableArrayList(CHILD_LIST_PARCEL_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mtgobject_child_list, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.rv_mtgobject_child_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        MTGObjectChildListAdapter mtgObjectChildListAdapter = new MTGObjectChildListAdapter(mChildList, mOnGridItemClickListener);
        recyclerView.setAdapter(mtgObjectChildListAdapter);

        return rootView;
    }

}
