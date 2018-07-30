package www.goodycom.com.armsdemo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.utils.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.kongzue.dialog.v2.WaitDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import www.goodycom.com.armsdemo.R;
import www.goodycom.com.armsdemo.app.base.MySupportFragment;
import www.goodycom.com.armsdemo.di.component.DaggerHomeComponent;
import www.goodycom.com.armsdemo.di.module.HomeModule;
import www.goodycom.com.armsdemo.mvp.contract.HomeContract;
import www.goodycom.com.armsdemo.mvp.model.Bean.LoginModel;
import www.goodycom.com.armsdemo.mvp.presenter.HomePresenter;
import www.goodycom.com.armsdemo.ui.adapter.UserAdapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;

public class HomeFragment extends MySupportFragment<HomePresenter> implements HomeContract.View ,BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Inject
    RecyclerView.LayoutManager mLayoutManager;
    @Inject
    RecyclerView.Adapter mAdapter;
    @Inject
    List<LoginModel> mUsers;
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerHomeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_user, container, false);
    }


    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initRecyclerView();
        mRecyclerView.setAdapter(mAdapter);
        ((UserAdapter)mAdapter).setOnItemClickListener(this);

    }

    private void initRecyclerView() {
//        mSwipeRefreshLayout.setOnRefreshListener(this);
        ArmsUtils.configRecyclerView(mRecyclerView, mLayoutManager);
    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        // todo,当该Fragment对用户可见时
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void showLoading() {
        WaitDialog.show(getContext(), "载入中...").setCanCancel(true);
    }

    @Override
    public void hideLoading() {
        WaitDialog.dismiss();
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
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }


}