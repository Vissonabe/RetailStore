package com.example.viswanathankp.retailstore;

import android.app.Activity;
import android.app.Application;
import com.example.viswanathankp.retailstore.di.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import javax.inject.Inject;

public class CartApplication extends Application implements HasActivityInjector {

  @Inject
  DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

  @Override
  public AndroidInjector<Activity> activityInjector() {
    return dispatchingAndroidInjector;
  }

  public CartApplication(){
    DaggerAppComponent.builder()
        .application(this)
        .build().inject(this);
  }
}
