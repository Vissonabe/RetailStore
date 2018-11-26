package com.example.viswanathankp.retailstore.di;

import android.app.Activity;
import com.example.viswanathankp.retailstore.cart.CartActivity;
import com.example.viswanathankp.retailstore.detail.DetailActivity;
import com.example.viswanathankp.retailstore.home.HomeActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
  @ContributesAndroidInjector
  abstract DetailActivity contributeDetailActivity();

  @ContributesAndroidInjector
  abstract CartActivity contributeCartActivity();
}
