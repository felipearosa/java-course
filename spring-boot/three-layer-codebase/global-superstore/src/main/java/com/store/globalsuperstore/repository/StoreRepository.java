package com.store.globalsuperstore.repository;

import java.util.ArrayList;
import java.util.List;

import com.store.globalsuperstore.Item;

public class StoreRepository {
  List<Item> items = new ArrayList<>();

  public List<Item> getItems(){
    return this.items;
  }

  public Item getItem(int index){
    return this.items.get(index);
  }

  public void setItem(int index, Item item){
    this.items.set(index, item);
  }

  public void addItem(Item item){
    this.items.add(item);
  }
}
