package com.store.globalsuperstore.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.store.globalsuperstore.Item;
import com.store.globalsuperstore.service.StoreService;

@Controller
public class StoreController {

  StoreService storeService = new StoreService();

  @GetMapping("/")
  public String getForm(Model model, @RequestParam(required = false) String id) {
    model.addAttribute("item", storeService.getItemById(id));
    return "form";
  }

  @PostMapping("/submitItem")
  public String handleSubmit(@Valid Item item, BindingResult result, RedirectAttributes redirectAttributes) {
    return storeService.validateInputs(item, result, redirectAttributes);
  }

  @GetMapping("/inventory")
  public String getInventory(Model model) {
    model.addAttribute("items", storeService.getItems());
    return "inventory";
  }

}
