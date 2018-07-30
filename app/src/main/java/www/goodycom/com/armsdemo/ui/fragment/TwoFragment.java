package www.goodycom.com.armsdemo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import www.goodycom.com.armsdemo.app.base.MySupportFragment;
import www.goodycom.com.armsdemo.di.component.DaggerTwoComponent;
import www.goodycom.com.armsdemo.di.module.TwoModule;
import www.goodycom.com.armsdemo.mvp.contract.TwoContract;
import www.goodycom.com.armsdemo.mvp.presenter.TwoPresenter;

import www.goodycom.com.armsdemo.R;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class TwoFragment extends MySupportFragment<TwoPresenter> implements TwoContract.View {

    public static TwoFragment newInstance() {
        TwoFragment fragment = new TwoFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerTwoComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .twoModule(new TwoModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {

    }

}
