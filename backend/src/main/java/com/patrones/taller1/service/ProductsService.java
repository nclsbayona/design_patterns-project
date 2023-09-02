package com.patrones.taller1.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.patrones.taller1.entity.products.dtos.MenuDTO;
import com.patrones.taller1.entity.products.dtos.ProductDTO;
import com.patrones.taller1.entity.products.dtos.SimpleProductDTO;
import com.patrones.taller1.service.persistance.IPersistance;

@Service
public class ProductsService {

  private final IPersistance persistanceService;

  public List<ProductDTO> getProducts(){
    return this.persistanceService.getProducts().stream().map(ProductDTO::new).collect(Collectors.toList());
  }

  public ProductsService(IPersistance persistanceService) {
    this.persistanceService = persistanceService;
  }

  public SimpleProductDTO getSimpleProduct(String id) {
    try {
      return new SimpleProductDTO(this.persistanceService.getSimpleProduct(id));
    } catch (Exception e) {
    }
    return null;
  }

  public MenuDTO getMenu(String id) {
    try {
      return new MenuDTO(this.persistanceService.getMenu(id));
    } catch (Exception e) {
    }
    return null;
  }
}
