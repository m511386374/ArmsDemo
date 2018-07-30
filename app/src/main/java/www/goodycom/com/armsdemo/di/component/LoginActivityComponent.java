package www.goodycom.com.armsdemo.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import www.goodycom.com.armsdemo.di.module.LoginActivityModule;

import com.jess.arms.di.scope.ActivityScope;

import www.goodycom.com.armsdemo.ui.activity.LoginActivityActivity;

@ActivityScope
@Component(modules = LoginActivityModule.class, dependencies = AppComponent.class)
public interface LoginActivityComponent {
    void inject(LoginActivityActivity activity);
}