package www.goodycom.com.armsdemo.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import www.goodycom.com.armsdemo.di.module.TestModule;

import com.jess.arms.di.scope.FragmentScope;

import www.goodycom.com.armsdemo.ui.fragment.TestFragment;

@FragmentScope
@Component(modules = TestModule.class, dependencies = AppComponent.class)
public interface TestComponent {
    void inject(TestFragment fragment);
}