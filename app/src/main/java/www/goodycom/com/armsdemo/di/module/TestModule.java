package www.goodycom.com.armsdemo.di.module;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.di.scope.FragmentScope;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

import www.goodycom.com.armsdemo.mvp.contract.TestContract;
import www.goodycom.com.armsdemo.mvp.model.Bean.LoginModel;
import www.goodycom.com.armsdemo.mvp.model.TestModel;
import www.goodycom.com.armsdemo.ui.adapter.UserAdapter;


@Module
public class TestModule {
    private TestContract.View view;

    /**
     * 构建TestModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public TestModule(TestContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    TestContract.View provideTestView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    TestContract.Model provideTestModel(TestModel model) {
        return model;
    }

}