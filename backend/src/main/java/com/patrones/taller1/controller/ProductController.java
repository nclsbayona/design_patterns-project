package com.patrones.taller1.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrones.taller1.entity.products.dtos.MenuDTO;
import com.patrones.taller1.entity.products.dtos.ProductDTO;
import com.patrones.taller1.entity.products.dtos.SimpleProductDTO;
import com.patrones.taller1.service.ProductsService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductsService productsService;

  public ProductController(ProductsService productsService) {
    this.productsService = productsService;
  }

  @GetMapping
  public List<ProductDTO> getProducts(){
    return this.productsService.getProducts();
  }

  @GetMapping("/simple/{id}")
  public SimpleProductDTO getProduct(@PathVariable String id){
    return this.productsService.getSimpleProduct(id);
  }

  @GetMapping("/menu/{id}")
  public MenuDTO getMenu(@PathVariable String id){
    return this.productsService.getMenu(id);
  }
  
}
