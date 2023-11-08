package com.patrones.proyecto.entity.products.composite;

import com.patrones.proyecto.entity.providers.Provider;

public interface IProduct {
  public String getId();

  public String getName();

  public Double getPrice();

  public String getImage();

  public Provider getProvider();

  public boolean equals(Object obj);
}
