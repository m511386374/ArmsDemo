package www.goodycom.com.armsdemo.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import www.goodycom.com.armsdemo.mvp.contract.HomeContract;
import www.goodycom.com.armsdemo.mvp.contract.MainContract;
import www.goodycom.com.armsdemo.mvp.model.Bean.LoginModel;
import www.goodycom.com.armsdemo.net.JsonConvert;
import www.goodycom.com.armsdemo.net.LzyResponse;


@ActivityScope
public class HomeModel extends BaseModel implements HomeContract.Model {
    public static final String API_BASE_URL = "http://gank.io/api/data/";
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public HomeModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }


    @Override
    public Observable<Response<LzyResponse<List<LoginModel>>>> getDate(String code , int count,int page) {
        return OkGo.<LzyResponse<List<LoginModel>>>get(API_BASE_URL+code+"/"+count+""+"/"+page+"")//
                .cacheKey("MainModel")
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .converter(new JsonConvert<LzyResponse<List<LoginModel>>>(){})//
                .adapt(new ObservableResponse<LzyResponse<List<LoginModel>>>());

    }
}