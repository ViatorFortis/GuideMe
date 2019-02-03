package com.viatorfortis.guideme.rv;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viatorfortis.guideme.R;
import com.viatorfortis.guideme.model.MTGObject;
import com.viatorfortis.guideme.model.Region;

import java.util.ArrayList;

public class SearchResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int REGION_VIEW_TYPE_ID = 0;
    private final int MTGO_VIEW_TYPE_ID = 1;

    private final ArrayList<Region> mRegionList;
    private final ArrayList<MTGObject> mMTGObjectList;

    private final GridItemClickListener mGridItemClickListener;

    public interface GridItemClickListener {
        void onGridItemClick(Object object);
    }

    public SearchResultAdapter(ArrayList<Region> regionList, ArrayList<MTGObject> MTGObjectList, GridItemClickListener gridItemClickListener) {
        mRegionList = regionList;
        mMTGObjectList = MTGObjectList;
        mGridItemClickListener = gridItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mRegionList.size() + mMTGObjectList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mRegionList.size()) {
            return REGION_VIEW_TYPE_ID;
        } else {
            return MTGO_VIEW_TYPE_ID;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        RecyclerView.ViewHolder viewHolder;

        if (viewType == REGION_VIEW_TYPE_ID) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.viewholder_region, parent, false);
            viewHolder = new RegionViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(parent.getContext() )
                    .inflate(R.layout.viewholder_mtgo, parent, false);
            viewHolder = new MTGOViewHolder(itemView);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RegionViewHolder) {
            ( (RegionViewHolder) holder).populate(mRegionList.get(position));
        } else {
            ( (MTGOViewHolder) holder).populate(mMTGObjectList.get(position - mRegionList.size() ) );
        }
    }

    class RegionViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private final TextView mTypeTextView;
        private final TextView mTitleTextView;

        RegionViewHolder(View itemView) {
            super(itemView);

            mTypeTextView = itemView.findViewById(R.id.tv_region_type);
            mTitleTextView = itemView.findViewById(R.id.tv_region_title);

            itemView.setOnClickListener(this);
        }

        void populate(Region region) {
            mTypeTextView.setText(region.getType() );
            mTitleTextView.setText(region.getTitle() );
        }

        @Override
        public void onClick(View v) {
            mGridItemClickListener.onGridItemClick(mRegionList.get(getAdapterPosition() ) );
        }
    }

    class MTGOViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private final TextView mTypeTextView;
        private final TextView mTitleTextView;

        MTGOViewHolder(View itemView) {
            super(itemView);

            mTypeTextView = itemView.findViewById(R.id.tv_mtgo_type);
            mTitleTextView = itemView.findViewById(R.id.tv_mtgo_title);

            itemView.setOnClickListener(this);
        }

        void populate(MTGObject MTGObject) {
            mTypeTextView.setText(MTGObject.getType() );
            mTitleTextView.setText(MTGObject.getTitle() );
        }

        @Override
        public void onClick(View v) {
            mGridItemClickListener.onGridItemClick(mMTGObjectList.get(getAdapterPosition() - mRegionList.size() ) );
        }
    }

    public void addRegionList(ArrayList<Region> regionList) {
        mRegionList.addAll(regionList);
        notifyDataSetChanged();
    }

    public void addMTGObjectList(ArrayList<MTGObject> mtgObjectList) {
        mMTGObjectList.addAll(mtgObjectList);
        notifyDataSetChanged();
    }

    public void clear() {
        mRegionList.clear();
        mMTGObjectList.clear();
        this.notifyDataSetChanged();
    }

    public ArrayList<Region> getRegionList() {
        return mRegionList;
    }

    public ArrayList<MTGObject> getMTGObjectList() {
        return mMTGObjectList;
    }
}
