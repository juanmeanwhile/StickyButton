package com.meanwhile.stickybutton;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import static android.view.View.SCROLL_AXIS_VERTICAL;

/**
 *
 */
public class StickyBottomBehavior extends CoordinatorLayout.Behavior {

    private static final String TAG = "StickyBottomBehavior";

    private Integer mNotStickyMargin;
    private int mAnchorId;

    public StickyBottomBehavior(int anchorId, int notStickyMargin) {
        mAnchorId = anchorId;
        mNotStickyMargin = notStickyMargin;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull
            View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return (axes == SCROLL_AXIS_VERTICAL);
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View
            child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);

        View anchor = coordinatorLayout.findViewById(mAnchorId);
        int[] anchorLocation = new int[2];
        anchor.getLocationInWindow(anchorLocation);

        int coordBottom = coordinatorLayout.getBottom();

        //vertical position, cannot scroll over the bottom of the coordinator layout
        child.setY(Math.min(anchorLocation[1], coordBottom - child.getHeight()));

        //Margins depend on the distance to the bottom
        int diff = Math.max(coordBottom - anchorLocation[1] - child.getHeight(), 0);
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
        layoutParams.leftMargin = Math.min(diff, mNotStickyMargin);
        layoutParams.rightMargin = Math.min(diff, mNotStickyMargin);
        child.setLayoutParams(layoutParams);

    }



}
