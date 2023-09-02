package com.patrones.taller1.entity.products.factorymethod;

import com.patrones.taller1.entity.products.composite.SimpleProduct;
import com.patrones.taller1.entity.providers.Provider;

public class SimpleProductCreator implements IProductCreator {

  @Override
  public SimpleProduct createProduct(String id, String name, String image, Provider provider) {
    return new SimpleProduct(id, name, image, provider);
  }

}
