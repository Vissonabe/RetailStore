package com.example.viswanathankp.retailstore.detail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import com.example.viswanathankp.retailstore.repo.Repository;
import com.example.viswanathankp.retailstore.db.CartItem;
import com.example.viswanathankp.retailstore.model.Item;
import javax.inject.Inject;

public class DetailViewModel extends ViewModel {

  private Repository mRepository;

  @Inject
  public DetailViewModel(Repository repo) {
    mRepository = repo;
  }

  public void saveItemToCart(Item item){
    mRepository.saveItemToCart(itemToCartItem(item));
  }

  public void removeItemFromCart(Item item){
    mRepository.removeItemFromCart(itemToCartItem(item));
  }

  private CartItem itemToCartItem(Item item){
    CartItem cart = new CartItem();
    if(item.getId() > 0) {
      cart.setId(item.getId());
    }
    cart.setName(item.getName());
    cart.setImgUrl(item.getImgUrl());
    cart.setPrice(item.getPrice());
    return cart;
  }
}
