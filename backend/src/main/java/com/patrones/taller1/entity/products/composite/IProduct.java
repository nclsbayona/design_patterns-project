package com.patrones.taller1.entity.products.composite;

import com.patrones.taller1.entity.providers.Provider;

public interface IProduct {
  public String getId();

  public String getName();

  public Double getPrice();

  public String getImage();

  public Provider getProvider();

  public boolean equals(Object obj);
}
