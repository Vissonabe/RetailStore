package com.example.viswanathankp.retailstore.di;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.content.Context;
import com.example.viswanathankp.retailstore.CartApplication;
import com.example.viswanathankp.retailstore.db.CartDao;
import com.example.viswanathankp.retailstore.db.CartDatabase;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.inject.Singleton;

@Module(subcomponents = ViewModelSubComponent.class)
public class AppModule {

  @Singleton
  @Provides
  ViewModelProvider.Factory provideViewModelFactory(
      ViewModelSubComponent.Builder viewModelSubComponent) {
    return new ProjectViewModelFactory(viewModelSubComponent.build());
  }

  @Singleton
  @Provides
  CartDatabase provideDatabase(CartApplication applicationContext) {
    return Room.databaseBuilder(applicationContext,
        CartDatabase.class, "item_database")
        .build();
  }

  @Provides
  CartDao provideCartDao(CartDatabase database) {
    return database.itemDao();
  }

  @Singleton
  @Provides
  Executor provideExecutor() {
    return Executors.newFixedThreadPool(2);
  }
}
