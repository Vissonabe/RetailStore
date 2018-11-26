package com.example.viswanathankp.retailstore.di;

import com.example.viswanathankp.retailstore.cart.CartViewModel;
import com.example.viswanathankp.retailstore.detail.DetailViewModel;
import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {
  @Subcomponent.Builder
  interface Builder {
    ViewModelSubComponent build();
  }

  DetailViewModel detailViewModel();
  CartViewModel cartViewModel();
}
