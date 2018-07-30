package www.goodycom.com.armsdemo.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import www.goodycom.com.armsdemo.di.module.MainModule;
import www.goodycom.com.armsdemo.ui.activity.MainActivity;
import www.goodycom.com.armsdemo.ui.fragment.HomeFragment;

import com.jess.arms.di.scope.ActivityScope;
@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}