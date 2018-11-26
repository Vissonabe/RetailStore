package com.example.viswanathankp.retailstore.db;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface CartDao {

  @Insert(onConflict = REPLACE)
  void save(CartItem user);

  @Query("SELECT * FROM item_table WHERE id = :cartId")
  LiveData<CartItem> load(int cartId);

  @Query("SELECT * from item_table")
  LiveData<List<CartItem>> getAllItems();

  @Query("DELETE FROM item_table WHERE id = :cartId")
  void deleteItem(int cartId);
}
