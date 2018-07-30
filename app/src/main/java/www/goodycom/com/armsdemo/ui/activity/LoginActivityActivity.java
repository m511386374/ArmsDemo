package www.goodycom.com.armsdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import www.goodycom.com.armsdemo.di.component.DaggerLoginActivityComponent;
import www.goodycom.com.armsdemo.di.module.LoginActivityModule;
import www.goodycom.com.armsdemo.mvp.contract.LoginActivityContract;
import www.goodycom.com.armsdemo.mvp.presenter.LoginActivityPresenter;

import www.goodycom.com.armsdemo.R;


import static com.jess.arms.utils.Preconditions.checkNotNull;


public class LoginActivityActivity extends BaseActivity<LoginActivityPresenter> implements LoginActivityContract.View {
    @BindView(R.id.btn)
    Button btn;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginActivityComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .loginActivityModule(new LoginActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ARouter.getInstance().build(MAIN_DETAIL)
//                        .withSerializable(EXTRA_DETAIL, bean)
//                        .navigation();
            }
        });
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
        finish();
    }
}
