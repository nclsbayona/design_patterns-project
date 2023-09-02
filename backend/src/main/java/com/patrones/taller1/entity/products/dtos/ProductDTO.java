package com.patrones.taller1.entity.products.dtos;

import com.patrones.taller1.entity.products.composite.IProduct;
import com.patrones.taller1.entity.products.composite.SimpleProduct;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
  private String id;
  private String name;
  private String image;
  private Double price;
  private String provider_name;
  private String type;

  public ProductDTO(IProduct product){
    this.id = product.getId();
    this.name = product.getName();
    this.image=product.getImage();
    this.price=product.getPrice();
    this.provider_name = product.getProvider().getName();
    this.type = (product instanceof SimpleProduct) ? "Simple Product" : "Menu";
  }
}
