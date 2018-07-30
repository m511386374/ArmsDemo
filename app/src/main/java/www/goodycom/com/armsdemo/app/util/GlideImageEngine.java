package www.goodycom.com.armsdemo.app.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.maning.imagebrowserlibrary.ImageEngine;

import www.goodycom.com.armsdemo.R;

//Glide
public class GlideImageEngine implements ImageEngine {
    private AppComponent mAppComponent;
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用 Glide,使用策略模式,可替换框架
    private Context context;
    public GlideImageEngine(Context context) {
       this.context= context;
    }

    @Override
    public void loadImage(Context context, String url, ImageView imageView) {
        if (mAppComponent == null) {
            mAppComponent = ArmsUtils.obtainAppComponentFromContext(context);
        }
        mImageLoader = mAppComponent.imageLoader();
        mImageLoader.loadImage(context,
                ImageConfigImpl
                        .builder()
                        .url(url)
                        .isClearDiskCache(true)
                        .imageView(imageView)
                        .build());
    }

}
