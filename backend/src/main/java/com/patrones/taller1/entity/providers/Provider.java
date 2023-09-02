package com.patrones.taller1.entity.providers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Provider {
  private String id;
  private String name;
  private String image;
  public Provider(String id, String name, String image) {
    this.id = id;
    this.name = name;
    this.image = image;
  }
  
}
