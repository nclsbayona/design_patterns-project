package com.patrones.taller1.entity.orders;

import java.util.ArrayList;
import java.util.List;

import com.patrones.taller1.entity.products.composite.IProduct;

public class Order {
  private String id;
  private String client_id;
  private List<IProduct> products;

  public Order(String id, String client_id) {
    this.id = id;
    this.client_id = client_id;
    this.products = new ArrayList<>();
  }

  public Double getPrice() {
    return products.stream().mapToDouble(IProduct::getPrice).sum();
  }

  public String getId() {
    return this.id;
  }

  public String getClient_id() {
    return this.client_id;
  }

  public List<IProduct> getProducts(){
    return products;
  }

  public void addProduct(IProduct product){
    this.products.add(product);
  }

  public void removeProduct(IProduct product){
    this.products.remove(product);
  }
}
