package com.patrones.proyecto.entity.products.dtos;

import com.patrones.proyecto.entity.products.composite.SimpleProduct;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleProductDTO {
  private String id;
  private String name;
  private String image;
  private Double price;
  private String provider_name;

  public SimpleProductDTO(SimpleProduct simple) {
    this.id = simple.getId();
    this.name = simple.getName();
    this.image = simple.getImage();
    this.price = simple.getPrice();
    this.provider_name = simple.getProvider().getName();
  }
}
