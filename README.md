# ImgScann
一个图片浏览的library
快速实现图片的浏览

用法：
1.添加ImgBrowseViewLayout到浏览的activity
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:vc="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    >


    <com.vc.library.imgbrowse.view.ImgBrowseViewLayout
        android:id="@+id/img_scann_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        vc:indicator_type="TEXT"
        vc:can_zoom="false"
        />


</RelativeLayout>

indicator_type  有两种的类型一种是文字（TEXT ）一种小圆点（POINT）   

can_zoom      表示浏览的图片是否可以缩放默认不缩放 false     true 表示可以缩放

2.在浏览图片的activity 界面 实现ImgBrowseViewLayout.IphotoClick 

  imagePosition =	getIntent().getIntExtra(ScannConfig.INDEX, 0);  // 图片的位置
  list= getIntent().getStringArrayListExtra(ScannConfig.IMGS);     //图片集合
  mImgScannViewLayout = (ImgBrowseViewLayout) findViewById(R.id.img_scann_layout);
  mImgScannViewLayout.setData(list,imagePosition);  //设置数据
  mImgScannViewLayout.setIphotoClick(this);

3.效果图

