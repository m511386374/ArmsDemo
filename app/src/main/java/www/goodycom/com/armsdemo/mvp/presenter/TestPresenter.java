package www.goodycom.com.armsdemo.mvp.presenter;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;
import com.lzy.okgo.model.Response;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import www.goodycom.com.armsdemo.mvp.contract.TestContract;
import www.goodycom.com.armsdemo.mvp.model.Bean.LoginModel;
import www.goodycom.com.armsdemo.net.LzyResponse;


@FragmentScope
public class TestPresenter extends BasePresenter<TestContract.Model, TestContract.View> {

    @Inject
    public TestPresenter(TestContract.Model model, TestContract.View rootView) {
        super(model, rootView);
    }

}
