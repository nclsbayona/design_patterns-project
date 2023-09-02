package com.patrones.taller1.entity.products.factorymethod;

import com.patrones.taller1.entity.products.composite.IProduct;
import com.patrones.taller1.entity.providers.Provider;

public interface IProductCreator {
  public IProduct createProduct(String id, String name, String image, Provider provider);
}
