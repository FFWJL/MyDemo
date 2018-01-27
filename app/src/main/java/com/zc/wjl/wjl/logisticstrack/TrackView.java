package com.zc.wjl.wjl.logisticstrack;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ScrollView;

import com.zc.wjl.wjl.R;

/**
 * @author Wjl.
 * @date 2018\1\26 0026
 */

public class TrackView extends View {
    private View mView;
    private Context mContext;
    private OrderTrackView mOrderTrackView;
    private FixedRecyclerView mRecycler;
    private LinearLayoutManager mManager;

    public TrackView(Context context) {
        this(context, null);
    }

    public TrackView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TrackView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        mView = LayoutInflater.from(context).inflate(R.layout.logistics_track, null);
        mOrderTrackView = mView.findViewById(R.id.track_view);
        mRecycler = mView.findViewById(R.id.recycler);
        mRecycler.setHasFixedSize(true);
        checkViewParent(context, mRecycler);
    }

    private void checkViewParent(Context context, FixedRecyclerView recyclerView) {
        ViewParent parentView = getParent();
        if (null != parentView) {
            View parent = (View) parentView;
            if (parent instanceof ScrollView) {
                setNested(true, context);
                recyclerView.setNestedScrollingEnabled(false);
            } else {
                checkViewParent(context, recyclerView);
            }
        } else {
            setNested(false, context);
        }
    }

    private void setNested(boolean nested, Context context) {
        if (nested) {
            mManager = new LinearLayoutManager(context) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
        } else {
            mManager = new LinearLayoutManager(context);
        }
        mRecycler.setLayoutManager(mManager);
    }

    class TrackAdapter extends RecyclerView.Adapter {
        private int itemId;

        public TrackAdapter(int itemId) {
            this.itemId = itemId;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TrackVH(LayoutInflater.from(mContext).inflate(itemId, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    class TrackVH extends RecyclerView.ViewHolder {

        public TrackVH(View itemView) {
            super(itemView);
        }
    }
}
