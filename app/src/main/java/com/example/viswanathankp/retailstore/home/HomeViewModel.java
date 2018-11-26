package com.example.viswanathankp.retailstore.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.example.viswanathankp.retailstore.model.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeViewModel extends ViewModel {

  private MutableLiveData<Map<String, List<Item>>> mItemLiveData;

  public HomeViewModel(){
    mItemLiveData = new MutableLiveData<>();
    getData();
  }

  private void getData() {
    Map<String, List<Item>> expandableListDetail = new HashMap<>();

    List<Item> electronics = new ArrayList<Item>();
    electronics.add(new Item("Microwave oven", "", 200));
    electronics.add(new Item("Television", "", 200));
    electronics.add(new Item("Vaccum Cleaner", "", 200));

    List<Item> furniture = new ArrayList<Item>();
    furniture.add(new Item("Table", "", 200));
    furniture.add(new Item("Chair", "", 200));
    furniture.add(new Item("Almirah", "", 200));

    expandableListDetail.put("ELECTRONICS", electronics);
    expandableListDetail.put("FURNITURE", furniture);

    mItemLiveData.setValue(expandableListDetail);
  }

  public LiveData<Map<String, List<Item>>> getItemLiveData() {
    return mItemLiveData;
  }
}
