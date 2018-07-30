package www.goodycom.com.armsdemo.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import www.goodycom.com.armsdemo.mvp.contract.TwoContract;
import www.goodycom.com.armsdemo.mvp.model.TwoModel;


@Module
public class TwoModule {
    private TwoContract.View view;

    /**
     * 构建TwoModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public TwoModule(TwoContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    TwoContract.View provideTwoView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    TwoContract.Model provideTwoModel(TwoModel model) {
        return model;
    }
}