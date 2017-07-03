package com.vc.library.imgbrowse.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.vc.library.imgbrowse.util.BaseUtil;
import com.vc.library.imgbrowse.view.ImgBrowseItemView;

import java.util.List;

/**
 * Created by vc on 2017/7/1.
 */

public class ImgAdapter extends PagerAdapter {


  private Context  mContext;
  private List<String> imgUrls;
  private boolean isCanzoom;

    public ImgAdapter(Context context, List<String> imgUrls,boolean zoom) {
        mContext = context;
        this.imgUrls = imgUrls;
        isCanzoom = zoom;
    }


    @Override
    public int getCount() {
        return imgUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {


        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj;
        if(isCanzoom){
            ImgBrowseItemView imgScannItemView =new ImgBrowseItemView(mContext);

            imgScannItemView.setImgUrl(imgUrls.get(position));

            imgScannItemView.setIonPhotoTagListenter(new ImgBrowseItemView.IonPhotoTagListenter() {
                @Override
                public void OnPhotoTag() {
                    if(mClickListener!=null){
                        mClickListener.photoClick();
                    }
                }
            });



            container.addView(imgScannItemView);

            obj = imgScannItemView;


        }else {
            String url= imgUrls.get(position);
            ImageView imageView =new ImageView(mContext);

            if(BaseUtil.isHttp(url)){
                Glide.with(mContext).load(url).into(new GlideDrawableImageViewTarget(imageView){
                    @Override
                    public void onResourceReady(GlideDrawable arg0, GlideAnimation<? super GlideDrawable> arg1) {
                        super.onResourceReady(arg0, arg1);


                    }
                } );
            }else {
                Glide.with(mContext).load("file://" + url).into(new GlideDrawableImageViewTarget(imageView){
                    @Override
                    public void onResourceReady(GlideDrawable arg0, GlideAnimation<? super GlideDrawable> arg1) {
                        super.onResourceReady(arg0, arg1);


                    }
                } );
            }

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mClickListener!=null){
                        mClickListener.photoClick();
                    }
                }
            });


            container.addView(imageView);
            obj = imageView;

        }

        return obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View)object);

        if (object != null)
            object = null;
    }

    IphotoClickListener mClickListener;

    public void setClickListener(IphotoClickListener clickListener) {
        mClickListener = clickListener;
    }

    public interface IphotoClickListener{

       void photoClick();
   }



}
