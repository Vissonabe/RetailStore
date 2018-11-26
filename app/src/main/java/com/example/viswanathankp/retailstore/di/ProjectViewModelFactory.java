package com.example.viswanathankp.retailstore.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.util.ArrayMap;
import com.example.viswanathankp.retailstore.cart.CartViewModel;
import com.example.viswanathankp.retailstore.detail.DetailViewModel;
import java.util.Map;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ProjectViewModelFactory implements ViewModelProvider.Factory {
  private final ArrayMap<Class, Callable<? extends ViewModel>> creators;

  @Inject
  public ProjectViewModelFactory(ViewModelSubComponent viewModelSubComponent) {
    creators = new ArrayMap<>();
    creators.put(CartViewModel.class, () -> viewModelSubComponent.cartViewModel());
    creators.put(DetailViewModel.class, () -> viewModelSubComponent.detailViewModel());
  }

  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    Callable<? extends ViewModel> creator = creators.get(modelClass);
    if (creator == null) {
      for (Map.Entry<Class, Callable<? extends ViewModel>> entry : creators.entrySet()) {
        if (modelClass.isAssignableFrom(entry.getKey())) {
          creator = entry.getValue();
          break;
        }
      }
    }
    if (creator == null) {
      throw new IllegalArgumentException("Unknown model class " + modelClass);
    }
    try {
      return (T) creator.call();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
