package com.patrones.proyecto.entity.products.factorymethod;

import com.patrones.proyecto.entity.products.composite.SimpleProduct;
import com.patrones.proyecto.entity.providers.Provider;

public class SimpleProductCreator implements IProductCreator {

  @Override
  public SimpleProduct createProduct(String id, String name, String image, Provider provider) {
    return new SimpleProduct(id, name, image, provider);
  }

}
