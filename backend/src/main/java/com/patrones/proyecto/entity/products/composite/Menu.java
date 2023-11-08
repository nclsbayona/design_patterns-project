package com.patrones.proyecto.entity.products.composite;

import java.util.ArrayList;
import java.util.List;

import com.patrones.proyecto.entity.providers.Provider;


public class Menu implements IProduct {

  private String id;
  private String name;
  private String image;
  private List<IProduct> children;
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
    return children.stream().mapToDouble(IProduct::getPrice).sum();
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
    Menu other = (Menu) obj;
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
    if (children == null) {
      if (other.children != null)
        return false;
    } else if (!children.equals(other.children))
      return false;
    if (provider == null) {
      if (other.provider != null)
        return false;
    } else if (!provider.equals(other.provider))
      return false;
    return true;
  }

  public List<IProduct> getChildren(){
    return this.children;
  }

  public boolean addChildren(IProduct product) {
    boolean result = false;
    try {
      this.children.add(product);
      result = true;
    } catch (Exception e) {

    }
    return result;
  }

  public boolean removeChildren(IProduct product) {
    boolean result = false;
    try {
      this.children.remove(product);
      result = true;
    } catch (Exception e) {

    }
    return result;
  }

  public Menu(String id, String name, String image, Provider provider) {
    this.id = id;
    this.name = name;
    this.image = image;
    this.provider = provider;
    this.children = new ArrayList<>();
  }
}
