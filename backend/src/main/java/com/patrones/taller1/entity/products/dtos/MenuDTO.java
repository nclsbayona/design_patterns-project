package com.patrones.taller1.entity.products.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.patrones.taller1.entity.products.composite.Menu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDTO {
  private String id;
  private String name;
  private String image;
  private List<ProductDTO> children;
  private String provider_name;

  public MenuDTO(Menu menu){
    this.id = menu.getId();
    this.name = menu.getName();
    this.image = menu.getImage();
    this.provider_name = menu.getProvider().getName();
    this.children = menu.getChildren().stream().map(ProductDTO::new).collect(Collectors.toList());
  }
}
