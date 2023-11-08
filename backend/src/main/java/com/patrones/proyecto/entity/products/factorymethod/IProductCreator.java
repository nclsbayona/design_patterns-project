package com.patrones.proyecto.entity.products.factorymethod;

import com.patrones.proyecto.entity.products.composite.IProduct;
import com.patrones.proyecto.entity.providers.Provider;

public interface IProductCreator {
  public IProduct createProduct(String id, String name, String image, Provider provider);
}
