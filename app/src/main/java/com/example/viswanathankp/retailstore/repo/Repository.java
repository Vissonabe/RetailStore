package com.example.viswanathankp.retailstore.repo;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import com.example.viswanathankp.retailstore.db.CartDao;
import com.example.viswanathankp.retailstore.db.CartDatabase;
import com.example.viswanathankp.retailstore.db.CartItem;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class Repository {

  private static Repository sRepository = null;
  private CartDao itemDao;
  private Executor executor;

  @Inject
  public Repository(CartDao dao, Executor extor){
    itemDao = dao;
    executor = extor;
  }

  //public static Repository getInstance(Context appContext){
  //  if(sRepository == null){
  //    sRepository = new Repository(appContext);
  //  }
  //  return sRepository;
  //}

  public void saveItemToCart(CartItem item){
    executor.execute(() -> itemDao.save(item));
  }

  public void removeItemFromCart(CartItem item){
    executor.execute(() -> itemDao.deleteItem(item.getId()));
  }

  public LiveData<List<CartItem>> getAllCartItems(){
    return itemDao.getAllItems();
  }
}
