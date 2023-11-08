package com.patrones.proyecto.entity.products.composite;

import com.patrones.proyecto.entity.providers.Provider;
import lombok.Builder;

@Builder
public class SimpleProduct implements IProduct {
  private String id;
  private String name;
  private String image;
  private Double price;
  private Provider provider;

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Double getPrice() {
    return this.price;
  }

  @Override
  public String getImage() {
    return this.image;
  }

  @Override
  public Provider getProvider() {
    return this.provider;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SimpleProduct other = (SimpleProduct) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (image == null) {
      if (other.image != null)
        return false;
    } else if (!image.equals(other.image))
      return false;
    if (price == null) {
      if (other.price != null)
        return false;
    } else if (!price.equals(other.price))
      return false;
    if (provider == null) {
      if (other.provider != null)
        return false;
    } else if (!provider.equals(other.provider))
      return false;
    return true;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public SimpleProduct(String id, String name, String image, Provider provider) {
    this.id = id;
    this.name = name;
    this.image = image;
    this.provider = provider;
    this.price = 0.0;
  }

  
  public SimpleProduct(String id, String name, String image, Double price, Provider provider) {
    this.id = id;
    this.name = name;
    this.image = image;
    this.price = price;
    this.provider = provider;
  }
}
