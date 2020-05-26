package com.example.nested;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by sy-02 on 2018-04-12.
 */

public abstract class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private static final String TAG = RecyclerViewScrollListener.class.getSimpleName();


    int visibleItemCount, totalItemCount;
    RecyclerViewPositionHelper mRecyclerViewHelper;

    private int totalDy = 0;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

        totalDy += dy;
        super.onScrolled(recyclerView, dx, dy);
        mRecyclerViewHelper = RecyclerViewPositionHelper.createHelper(recyclerView);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mRecyclerViewHelper.getItemCount();
        onScrolled(totalDy);
    }

    public abstract void onScrolled(int dy);
}