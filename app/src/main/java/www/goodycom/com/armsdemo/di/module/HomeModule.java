package www.goodycom.com.armsdemo.di.module;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.jess.arms.di.scope.ActivityScope;
import java.util.ArrayList;
import java.util.List;
import dagger.Module;
import dagger.Provides;
import www.goodycom.com.armsdemo.mvp.contract.HomeContract;
import www.goodycom.com.armsdemo.mvp.model.Bean.LoginModel;
import www.goodycom.com.armsdemo.mvp.model.HomeModel;
import www.goodycom.com.armsdemo.ui.adapter.UserAdapter;


@Module
public class HomeModule {
    private HomeContract.View view;

    /**
     * 构建MainModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public HomeModule(HomeContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    HomeContract.View provideHomeView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    HomeContract.Model provideHomeModel(HomeModel model) {
        return model;
    }
    @ActivityScope
    @Provides
    List<LoginModel> provideUserList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    RecyclerView.Adapter provideUserAdapter(List<LoginModel> list){
        return new UserAdapter(list);
    }

    @ActivityScope
    @Provides
    RecyclerView.LayoutManager provideLayoutManager() {
        return new GridLayoutManager(view.getActivity(), 2);
    }
}