package com.patrones.proyecto.service.persistance;

import java.util.List;

import com.patrones.proyecto.entity.orders.Order;
import com.patrones.proyecto.entity.products.ProductTypes;
import com.patrones.proyecto.entity.products.composite.IProduct;
import com.patrones.proyecto.entity.products.composite.Menu;
import com.patrones.proyecto.entity.products.composite.SimpleProduct;

public interface IPersistance {
  public boolean save(IProduct product);
  public boolean save(String order_id, String client_id);
  public Order addProductToOrder(String order_id, String product_id, ProductTypes type);
  public List<IProduct> getProducts();
  public List<Order> getOrders();
  public SimpleProduct getSimpleProduct(String id);
  public Menu getMenu(String id);
  public Order getOrder(String id);
  public String getNextOrderID();
  public Order payOrder(String id);
}
