package com.example.viswanathankp.retailstore.model;

import org.parceler.Parcel;

@Parcel
public class Item {

  int mId;
  String mName;
  String mImgUrl;
  float mPrice;

  public Item(){
  }

  public Item(String name, String imgUrl, float price){
    mName = name;
    mImgUrl = imgUrl;
    mPrice = price;
  }

  public Item(int id, String name, String imgUrl, float price){
    mId = id;
    mName = name;
    mImgUrl = imgUrl;
    mPrice = price;
  }

  public int getId() {
    return mId;
  }

  public String getName() {
    return mName;
  }

  public String getImgUrl() {
    return mImgUrl;
  }

  public float getPrice() {
    return mPrice;
  }
}
