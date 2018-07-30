package www.goodycom.com.armsdemo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.ToastUtils;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import me.yokeyword.fragmentation.BuildConfig;
import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import www.goodycom.com.armsdemo.R;
import www.goodycom.com.armsdemo.app.base.MySupportActivity;
import www.goodycom.com.armsdemo.app.base.MySupportFragment;
import www.goodycom.com.armsdemo.di.component.DaggerMainComponent;
import www.goodycom.com.armsdemo.di.module.MainModule;
import www.goodycom.com.armsdemo.mvp.contract.MainContract;
import www.goodycom.com.armsdemo.mvp.presenter.MainPresenter;
import www.goodycom.com.armsdemo.ui.fragment.HomeFragment;
import www.goodycom.com.armsdemo.ui.fragment.TestFragment;
import www.goodycom.com.armsdemo.ui.view.BottomBar;
import www.goodycom.com.armsdemo.ui.view.BottomBarTab;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MainActivity extends MySupportActivity<MainPresenter> implements MainContract.View ,CommonTitleBar.OnTitleBarListener {
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    @BindView(R.id.titlebar)
    CommonTitleBar titlebar;
    @Inject
    RxPermissions mRxPermissions;
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    private double firstTime = 0;
    private MySupportFragment[] mFragments = new MySupportFragment[3];
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.requestPermissions();
        titlebar.setListener(this);
          initBottomBar();

    }
    public void initBottomBar(){
        mBottomBar.addItem(new BottomBarTab(this, R.drawable.ic_home_black_24dp,"首页"))
                .addItem(new BottomBarTab(this, R.drawable.ic_friends,"福利"))
                .addItem(new BottomBarTab(this, R.drawable.ic_dashboard_black_24dp,"收藏")
                );

        MySupportFragment firstFragment = findFragment(HomeFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = HomeFragment.newInstance();
            mFragments[SECOND] = HomeFragment.newInstance();
            mFragments[THIRD] = TestFragment.newInstance();
            loadMultipleRootFragment(R.id.main_frame, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD]);
        } else {
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(HomeFragment.class);
            mFragments[THIRD] = findFragment(TestFragment.class);
        }
        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }
            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
    @Override
    public void showLoading() {
//
    }

    @Override
    public void hideLoading() {
    }


    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ToastUtils.showLongToast(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public RxPermissions getRxPermissions() {
        return mRxPermissions;
    }

    @Override
    public void onClicked(View v, int action, String extra) {
        if (action == CommonTitleBar.ACTION_LEFT_TEXT) {
            ToastUtils.showLongToast("111111111111");
           finish();

        }
    }
    public void startBrotherFragment(SupportFragment targetFragment) {
        start(targetFragment);
    }
    @Override
    public void onBackPressedSupport() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            ArmsUtils.snackbarText("再按一次退出程序");
            firstTime = secondTime;
        } else {
            ArmsUtils.exitApp();
        }
    }
}
