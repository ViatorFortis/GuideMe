package com.viatorfortis.guideme.rv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viatorfortis.guideme.R;
import com.viatorfortis.guideme.model.Child;

import java.util.ArrayList;

public class MTGObjectChildListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final ArrayList<Child> mChildList;

    private GridItemClickListener mGridItemClickListener;

    public interface GridItemClickListener {
        void onGridItemClick(int itemPosition);
    }


    public MTGObjectChildListAdapter(ArrayList<Child> childList, GridItemClickListener gridItemClickListener) {
        mChildList = childList;
        mGridItemClickListener = gridItemClickListener;
    }

    @Override

    public int getItemCount() {
        return mChildList.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext() )
                .inflate(R.layout.viewholder_mtgo_child, parent, false);

        return new ChildViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ( (ChildViewHolder) holder).populate(mChildList.get(position) );
    }

    class ChildViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private final TextView mTitleTextView;

        public ChildViewHolder(View itemView) {
            super(itemView);

            mTitleTextView = itemView.findViewById(R.id.tv_title);
            itemView.setOnClickListener(this);
        }

        void populate(Child child) {
            mTitleTextView.setText(child.getTitle() );
        }

        @Override
        public void onClick(View v) {

            mGridItemClickListener.onGridItemClick(getAdapterPosition() );
        }
    }
}
