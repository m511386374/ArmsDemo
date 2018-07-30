package www.goodycom.com.armsdemo.di.module;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jess.arms.di.scope.ActivityScope;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

import www.goodycom.com.armsdemo.mvp.contract.MainContract;
import www.goodycom.com.armsdemo.mvp.model.Bean.LoginModel;
import www.goodycom.com.armsdemo.mvp.model.Bean.User;
import www.goodycom.com.armsdemo.mvp.model.MainModel;
import www.goodycom.com.armsdemo.ui.adapter.UserAdapter;


@Module
public class MainModule {
    private MainContract.View view;

    /**
     * 构建MainModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MainModule(MainContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MainContract.View provideMainView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MainContract.Model provideMainModel(MainModel model) {
        return model;
    }


    @ActivityScope
    @Provides
    RxPermissions provideRxPermissions() {
        return new RxPermissions(view.getActivity());
    }

}