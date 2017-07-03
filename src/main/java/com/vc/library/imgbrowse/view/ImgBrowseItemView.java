package com.vc.library.imgbrowse.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.vc.library.imgbrowse.R;
import com.vc.library.imgbrowse.util.BaseUtil;

/**
 * Created by vc on 2017/7/1.
 */

public class ImgBrowseItemView extends FrameLayout {

   private PhotoView mPhotoView;

   private ProgressBar mProgressBar;

   private PhotoViewAttacher attacher;


   private IonPhotoTagListenter mIonPhotoTagListenter;


   private String imgUrl;

    public void setIonPhotoTagListenter(IonPhotoTagListenter ionPhotoTagListenter) {
        mIonPhotoTagListenter = ionPhotoTagListenter;
    }

    public ImgBrowseItemView(@NonNull Context context) {
        this(context,null);
    }

    public ImgBrowseItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ImgBrowseItemView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }


    private void initView(final Context context){

        View view =  inflate(context, R.layout.imageview_photoview,this);

        mPhotoView = (PhotoView) view.findViewById(R.id.imageview_photoview);

        mProgressBar= (ProgressBar) view.findViewById(R.id.imageview_progressBar);

        attacher =new PhotoViewAttacher(mPhotoView);

        attacher.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {

                 if(mIonPhotoTagListenter!=null){
                     mIonPhotoTagListenter.OnPhotoTag();
                 }

            }
        });

    }


    public void setImgUrl(String url){


        if(BaseUtil.isHttp(url)){
            Glide.with(getContext()).load(url).into(new GlideDrawableImageViewTarget(mPhotoView){
                @Override
                public void onResourceReady(GlideDrawable arg0, GlideAnimation<? super GlideDrawable> arg1) {
                    super.onResourceReady(arg0, arg1);
                    attacher.update();
                    mProgressBar.setVisibility(GONE);

                }
            } );
        }else {
            Glide.with(getContext()).load("file://" + url).into(new GlideDrawableImageViewTarget(mPhotoView){
                @Override
                public void onResourceReady(GlideDrawable arg0, GlideAnimation<? super GlideDrawable> arg1) {
                    super.onResourceReady(arg0, arg1);
                    attacher.update();
                    mProgressBar.setVisibility(GONE);

                }
            } );
        }

    }




    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();




    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }



    public interface  IonPhotoTagListenter{

        void OnPhotoTag();
    }


}
