package com.vc.library.imgbrowse.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by vc on 2017/7/3.
 */

public class ImgBrowseViewPager extends ViewPager {

    public ImgBrowseViewPager(Context context) {
        super(context);
    }

    public ImgBrowseViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        try {
            return super.onInterceptTouchEvent(event);
        } catch (IllegalArgumentException e) {

            return false;
        } catch (ArrayIndexOutOfBoundsException e) {

            return false;
        }
    }

}
