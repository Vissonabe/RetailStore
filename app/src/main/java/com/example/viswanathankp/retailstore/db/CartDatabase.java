package com.example.viswanathankp.retailstore.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {CartItem.class}, version = 1)
public abstract class CartDatabase extends RoomDatabase {
  public abstract CartDao itemDao();

  private static volatile CartDatabase INSTANCE;

  public static CartDatabase getDatabase(final Context context) {
    if (INSTANCE == null) {
      synchronized (CartDatabase.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
              CartDatabase.class, "item_database")
              .build();
        }
      }
    }
    return INSTANCE;
  }
}
