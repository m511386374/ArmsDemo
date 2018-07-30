package www.goodycom.com.armsdemo.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import www.goodycom.com.armsdemo.di.module.TwoModule;

import com.jess.arms.di.scope.FragmentScope;

import www.goodycom.com.armsdemo.ui.fragment.TwoFragment;

@FragmentScope
@Component(modules = TwoModule.class, dependencies = AppComponent.class)
public interface TwoComponent {
    void inject(TwoFragment fragment);
}