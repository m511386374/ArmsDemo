package www.goodycom.com.armsdemo.ui.adapter;

import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.maning.imagebrowserlibrary.MNImageBrowser;
import com.maning.imagebrowserlibrary.listeners.OnClickListener;

import java.util.ArrayList;
import java.util.List;

import www.goodycom.com.armsdemo.R;
import www.goodycom.com.armsdemo.app.util.GlideImageEngine;
import www.goodycom.com.armsdemo.mvp.model.Bean.LoginModel;

public class UserAdapter extends BaseQuickAdapter<LoginModel, BaseViewHolder> {
    private AppComponent mAppComponent;
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用 Glide,使用策略模式,可替换框架
    private List<LoginModel> data;
    public CountDownTimer countDownTimer;
    private SparseArray<CountDownTimer> countDownMap;
    private ArrayList<String> imageUrls = new ArrayList<>();
    public UserAdapter(@Nullable List<LoginModel> data) {
        super(R.layout.recycle_list, data);
        this.data= data;
        countDownMap = new SparseArray<>();
    }

    @Override
    protected void convert(final BaseViewHolder helper, LoginModel item) {
        if (mAppComponent == null) {
            mAppComponent = ArmsUtils.obtainAppComponentFromContext(mContext);
        }
        mImageLoader = mAppComponent.imageLoader();
        mImageLoader.loadImage(mContext,
                ImageConfigImpl
                        .builder()
                        .url(item.getUrl())
                        .isClearDiskCache(true)
                        .imageView((ImageView) helper.getView(R.id.iv_avatar))
                        .build());
        for (LoginModel image : data) {
            if (!imageUrls.contains(image.getUrl())) {
                imageUrls.add(image.getUrl());
            }
        }
        helper.getView(R.id.iv_avatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MNImageBrowser.with(mContext)
                        .setCurrentPosition(helper.getLayoutPosition())
                        .setImageEngine(new GlideImageEngine(mContext))
                        .setImageList(imageUrls)
                        .setIndicatorHide(false)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(FragmentActivity activity, ImageView view, int position, String url) {
                            }
                        })
                        .show(helper.getView(R.id.iv_avatar));
            }
        });

    }

}
