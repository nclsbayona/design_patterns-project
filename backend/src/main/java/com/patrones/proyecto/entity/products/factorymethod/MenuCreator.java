package com.patrones.proyecto.entity.products.factorymethod;

import com.patrones.proyecto.entity.products.composite.Menu;
import com.patrones.proyecto.entity.providers.Provider;

public class MenuCreator implements IProductCreator {

  @Override
  public Menu createProduct(String id, String name, String image, Provider provider) {
    return new Menu(id, name, image, provider);
  }

}
