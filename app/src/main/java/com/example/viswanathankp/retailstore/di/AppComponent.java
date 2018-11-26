package com.example.viswanathankp.retailstore.di;

import com.example.viswanathankp.retailstore.CartApplication;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.HasActivityInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class, ActivityModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent {

  @Component.Builder
  interface Builder{
    @BindsInstance
    Builder application(CartApplication application);
    AppComponent build();
  }

  void inject(CartApplication application);
}
