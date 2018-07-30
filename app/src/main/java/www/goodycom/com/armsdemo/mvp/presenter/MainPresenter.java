package www.goodycom.com.armsdemo.mvp.presenter;

import android.Manifest;
import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.PermissionUtil;
import com.lzy.okgo.model.Response;

import java.util.List;


import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import www.goodycom.com.armsdemo.mvp.model.Bean.User;
import www.goodycom.com.armsdemo.net.LzyResponse;
import www.goodycom.com.armsdemo.mvp.contract.MainContract;
import www.goodycom.com.armsdemo.mvp.model.Bean.LoginModel;


@ActivityScope
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {
    @Inject
    public RxErrorHandler mErrorHandler;

    @Inject
    public MainPresenter(MainContract.Model model, MainContract.View rootView) {
        super(model, rootView);
    }
    public void requestPermissions(){
        PermissionUtil.requestPermission(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
            }
            @Override
            public void onRequestPermissionFailure(List<String> permissions) {
            }
            @Override
            public void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions) {
                mRootView.showMessage("Request permissons failure");
            }

        }, mRootView.getRxPermissions(), mErrorHandler,Manifest.permission.CAMERA,Manifest.permission.CALL_PHONE);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }
}
