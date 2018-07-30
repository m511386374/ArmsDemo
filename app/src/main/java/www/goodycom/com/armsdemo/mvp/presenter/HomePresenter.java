package www.goodycom.com.armsdemo.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.lzy.okgo.model.Response;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import www.goodycom.com.armsdemo.mvp.contract.HomeContract;
import www.goodycom.com.armsdemo.mvp.contract.MainContract;
import www.goodycom.com.armsdemo.mvp.model.Bean.LoginModel;
import www.goodycom.com.armsdemo.net.LzyResponse;


@ActivityScope
public class HomePresenter extends BasePresenter<HomeContract.Model, HomeContract.View> {

    @Inject
    List<LoginModel> mUsers;
    @Inject
    RecyclerView.Adapter mAdapter;

    @Inject
    public HomePresenter(HomeContract.Model model, HomeContract.View rootView) {
        super(model, rootView);
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        getUserDate("福利", 10, 1);
    }
    public void getUserDate(String code , int count,int page) {
        mModel.getDate(code,count,page)
                .subscribeOn(Schedulers.io())//
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                       mRootView.showLoading();
                    }
                })
                .map(new Function<Response<LzyResponse<List<LoginModel>>>, List<LoginModel>>() {
                    @Override
                    public List<LoginModel> apply(@NonNull Response<LzyResponse<List<LoginModel>>> lzyResponseResponse) throws Exception {
                        return lzyResponseResponse.body().results;
                    }
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .observeOn(AndroidSchedulers.mainThread())  //
                .subscribe(new Observer<List<LoginModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(List<LoginModel> loginModels) {
                        mRootView.hideLoading();
                        mUsers.addAll(loginModels);
                        mAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();            //请求失败
                        mRootView.showMessage("请求失败");
                        mRootView.hideLoading();
                    }
                    @Override
                    public void onComplete() {
                        mRootView.hideLoading();
                    }
                });

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mUsers=null;
        this.mAdapter=null;
    }
}
