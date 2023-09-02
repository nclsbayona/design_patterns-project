package com.patrones.taller1.entity.products.factorymethod;

import com.patrones.taller1.entity.products.composite.Menu;
import com.patrones.taller1.entity.providers.Provider;

public class MenuCreator implements IProductCreator {

  @Override
  public Menu createProduct(String id, String name, String image, Provider provider) {
    return new Menu(id, name, image, provider);
  }

}
