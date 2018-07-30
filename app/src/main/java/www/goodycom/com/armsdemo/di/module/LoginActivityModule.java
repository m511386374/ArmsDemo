package www.goodycom.com.armsdemo.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import www.goodycom.com.armsdemo.mvp.contract.LoginActivityContract;
import www.goodycom.com.armsdemo.mvp.model.LoginActivityModel;


@Module
public class LoginActivityModule {
    private LoginActivityContract.View view;

    /**
     * 构建LoginActivityModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public LoginActivityModule(LoginActivityContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    LoginActivityContract.View provideLoginActivityView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    LoginActivityContract.Model provideLoginActivityModel(LoginActivityModel model) {
        return model;
    }
}