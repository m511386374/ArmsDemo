package www.goodycom.com.armsdemo.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.kongzue.dialog.v2.WaitDialog;

import javax.inject.Inject;

import butterknife.BindView;
import www.goodycom.com.armsdemo.app.base.MySupportFragment;
import www.goodycom.com.armsdemo.di.component.DaggerTestComponent;
import www.goodycom.com.armsdemo.di.module.TestModule;
import www.goodycom.com.armsdemo.mvp.contract.TestContract;
import www.goodycom.com.armsdemo.mvp.presenter.TestPresenter;

import www.goodycom.com.armsdemo.R;
import www.goodycom.com.armsdemo.ui.activity.MainActivity;
import www.goodycom.com.armsdemo.ui.view.BottomBar;
import www.goodycom.com.armsdemo.ui.view.StateButton;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class TestFragment extends MySupportFragment<TestPresenter> implements TestContract.View ,View.OnClickListener{
    @BindView(R.id.text_test)
    StateButton mBottomBar;
    public static TestFragment newInstance() {
        TestFragment fragment = new TestFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerTestComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .testModule(new TestModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_test_one, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mBottomBar.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.text_test:
                _mActivity.startActivity(new Intent(_mActivity, MainActivity.class));
            break;
        }
    }

}
