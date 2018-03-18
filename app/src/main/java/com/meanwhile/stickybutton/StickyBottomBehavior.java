package com.meanwhile.stickybutton;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import static android.content.ContentValues.TAG;
import static android.view.View.SCROLL_AXIS_VERTICAL;

/**
 *
 */
public class StickyBottomBehavior extends CoordinatorLayout.Behavior {

        private static final String TAG = "StickyBottomBehavior";

        private Integer mInitialMargin = 100;


    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {

//        if (mInitialMargin == null) {
//            mInitialMargin = ((ViewGroup.MarginLayoutParams) child.getLayoutParams()).leftMargin;
//        }

        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //Log.d(TAG, "onDependentViewChanged()");
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public void onDependentViewRemoved(CoordinatorLayout parent, View child, View dependency) {
        super.onDependentViewRemoved(parent, child, dependency);
        //Log.d(TAG, "onDependentViewRemoved()");
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

        updateMargin(coordinatorLayout, child);
    }

    private void updateMargin(CoordinatorLayout coordinatorLayout, View child) {
        Log.d(TAG, "coord - left: " + coordinatorLayout.getLeft() + "," + "right:" + coordinatorLayout.getRight()+ ",bottom: " + coordinatorLayout.getBottom());
        Log.d(TAG, "child - left: " + child.getLeft() + "," + "right:" + child.getRight() + ", bottom: " + child.getBottom());

        int coordBottom = coordinatorLayout.getBottom();
        int childBottom = child.getBottom();

        int diff = coordBottom - childBottom;
        Log.d(TAG, "diff: " + diff);
        Log.d(TAG, "initalMargin: " + mInitialMargin);
        //if ( diff < mInitialMargin) {

            //CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            //lp.leftMargin = coordBottom - childBottom;
            //lp.rightMargin = coordBottom - childBottom;
            //child.setLayoutParams(lp);
            //child.requestLayout();
            //child.setMinimumWidth(coordinatorLayout.getWidth() - (coordBottom - childBottom));
            //((ViewGroup.MarginLayoutParams)child.getLayoutParams()).setMargins(diff, 0, diff, 0);
            //((ViewGroup.LayoutParams)child.getLayoutParams()).width = coordinatorLayout.getWidth() - diff;
            //child.setLeft(coordinatorLayout.getLeft() + diff);
            //child.setRight(coordinatorLayout.getRight() - diff);

            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
            layoutParams.leftMargin = Math.min(Math.abs(diff), mInitialMargin);
            layoutParams.rightMargin = Math.min(Math.abs(diff), mInitialMargin);
            child.setLayoutParams(layoutParams);

        //}
        Log.d(TAG, "---");
    }


    @Override
    public boolean onNestedFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View
            child, @NonNull View target, float velocityX, float velocityY, boolean consumed) {

        //TODO this is not being called!!!

        Log.d(TAG, "fling!");
        updateMargin(coordinatorLayout, child);


        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY,
                consumed);
    }
}
