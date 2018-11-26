package com.example.viswanathankp.retailstore.cart;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import com.example.viswanathankp.retailstore.model.Item;
import com.example.viswanathankp.retailstore.repo.Repository;
import com.example.viswanathankp.retailstore.db.CartItem;
import java.util.List;
import javax.inject.Inject;

public class CartViewModel extends ViewModel {

  private final Repository mRepository;

  @Inject
  public CartViewModel(Repository repo) {
    mRepository = repo;
  }

  public LiveData<List<CartItem>> getAllCartItems(){
    return mRepository.getAllCartItems();
  }

  public Item getItem(CartItem item){
    return new Item(item.getId(), item.getName(), item.getImgUrl(), item.getPrice());
  }

  public float getTotalPrice(List<CartItem> cartItems){
    float total = 0f;
    for (CartItem it : cartItems) {
      total += it.getPrice();
    }
    return total;
  }
}
