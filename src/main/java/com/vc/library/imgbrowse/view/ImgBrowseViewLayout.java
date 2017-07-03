package com.vc.library.imgbrowse.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vc.library.imgbrowse.R;
import com.vc.library.imgbrowse.adapter.ImgAdapter;

import java.util.List;

/**
 * Created by vc on 2017/7/1.
 */

public class ImgBrowseViewLayout extends RelativeLayout {

   private ImgBrowseViewPager mViewPager;

   private LinearLayout ponintMainLayout;

   private LayoutParams pointMainLayparams;

   private int indicatorType ;

   private final  int TEXT_TYPE= 0;

   private final  int  POINT_TYPE=1;

   private  TextView textIndicator;

   private boolean isCanzoom=false;

    public ImgBrowseViewLayout(Context context) {
        this(context,null);
    }

    public ImgBrowseViewLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ImgBrowseViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ImgBrowseViewLayout);
        indicatorType = typedArray.getInt(R.styleable.ImgBrowseViewLayout_indicator_type,POINT_TYPE);
        isCanzoom= typedArray.getBoolean(R.styleable.ImgBrowseViewLayout_can_zoom,false);  // default can not zoom
        typedArray.recycle();
        addLayout(context);
    }


    private void addLayout(Context context){

        setOverScrollMode(OVER_SCROLL_NEVER);

        // add viewpager
        mViewPager =new ImgBrowseViewPager(context);

        addView(mViewPager,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));

       //add point layout
        RelativeLayout pointLayout =new RelativeLayout(context);

        pointLayout.setPadding(0,10,0,10);


        LayoutParams  params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        addView(pointLayout,params);


        if(indicatorType==POINT_TYPE){

            ponintMainLayout= new LinearLayout(context);

            ponintMainLayout.setOrientation(LinearLayout.HORIZONTAL);


            pointMainLayparams  =new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);

            pointMainLayparams.addRule(RelativeLayout.CENTER_HORIZONTAL);

            pointLayout.addView(ponintMainLayout,pointMainLayparams);


        }else {

            textIndicator =new TextView(context);

            pointLayout.addView(textIndicator);

        }


    }


    public void setData(List<String> imgs,int position ){

         ImgAdapter mImgAdapter =new ImgAdapter(getContext(),imgs,isCanzoom);

         mViewPager.setAdapter(mImgAdapter);

        CharSequence text=  getContext().getString(R.string.text_indicator, position+1,mViewPager.getAdapter().getCount());

        if(indicatorType==POINT_TYPE){
             if(imgs.size()>1){
                 addPoints(imgs);
             }
             selectPoint(position);

        }else {
            textIndicator.setText(text);
        }


        mImgAdapter.setClickListener(new ImgAdapter.IphotoClickListener() {
            @Override
            public void photoClick() {

                if(mIphotoClick!=null){
                    mIphotoClick.photoClick();
                }

            }
        });


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(indicatorType==POINT_TYPE){
                    selectPoint(position);

                }else {
                    CharSequence text= getContext().getString(R.string.text_indicator,position+ 1,mViewPager.getAdapter().getCount());

                    textIndicator.setText(text);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        mViewPager.setCurrentItem(position);

    }


    private void addPoints(List<String> imgs){

        ponintMainLayout.removeAllViews();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(10,10,10,10);

        for (int i = 0; i <imgs.size() ; i++) {

            ImageView imageView =new ImageView(getContext());
            imageView.setLayoutParams(params);

            imageView.setImageResource(R.drawable.img_point_sl);

            ponintMainLayout.addView(imageView);

        }

        selectPoint(0);
    }



    private void selectPoint(int currentPosition){

        for (int i = 0; i <ponintMainLayout.getChildCount() ; i++) {

            ponintMainLayout.getChildAt(i).setEnabled(false);
        }
        ponintMainLayout.getChildAt(currentPosition).setEnabled(true);
    }

    IphotoClick mIphotoClick;

    public void setIphotoClick(IphotoClick iphotoClick) {
        mIphotoClick = iphotoClick;
    }

    public interface  IphotoClick{

        void photoClick();

    }




}
