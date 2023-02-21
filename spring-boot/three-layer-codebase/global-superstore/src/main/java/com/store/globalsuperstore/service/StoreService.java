package com.store.globalsuperstore.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.store.globalsuperstore.Constants;
import com.store.globalsuperstore.Item;
import com.store.globalsuperstore.repository.StoreRepository;

import jakarta.validation.Valid;

@Service
public class StoreService {

  StoreRepository storeRepo;

  @Autowired
  public StoreService (StoreRepository storeRepo){
    this.storeRepo = storeRepo;
  }

  public List<Item> getItems(){
    return storeRepo.getItems();
  }

  public Item getItemById(String id) {
    int index = getIndexFromId(id);
    if (index == Constants.NOT_FOUND){
      return new Item();
    } else {
      return storeRepo.getItem(index);
    }
  }

  public int getIndexFromId(String id) {
    for (int i = 0; i < getItems().size(); i++) {
      if (getItems().get(i).getId().equals(id))
        return i;
    }
    return Constants.NOT_FOUND;
  }

  public String validateInputs(@Valid Item item, BindingResult result, RedirectAttributes redirectAttributes){
    if (item.getPrice() < item.getDiscount()) {
      result.rejectValue("price", "", "Price cannot be less than discount");
    }
    if (result.hasErrors())
      return "form";
    int index = getIndexFromId(item.getId());
    String status = Constants.SUCCESS_STATUS;
    if (index == Constants.NOT_FOUND) {
      storeRepo.addItem(item);
    } else if (within5Days(item.getDate(), storeRepo.getItem(index).getDate())) {
      storeRepo.setItem(index, item);
    } else {
      status = Constants.FAILED_STATUS;
    }
    redirectAttributes.addFlashAttribute("status", status);
    return "redirect:/inventory";
  }

  public boolean within5Days(Date newDate, Date oldDate) {
    long diff = Math.abs(newDate.getTime() - oldDate.getTime());
    return (int) (TimeUnit.MILLISECONDS.toDays(diff)) <= 5;
  }

}
