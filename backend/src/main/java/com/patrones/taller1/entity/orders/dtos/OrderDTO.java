package com.patrones.taller1.entity.orders.dtos;

import java.util.ArrayList;
import java.util.List;

import com.patrones.taller1.entity.orders.Order;
import com.patrones.taller1.entity.products.composite.IProduct;
import com.patrones.taller1.entity.products.composite.Menu;
import com.patrones.taller1.entity.products.composite.SimpleProduct;
import com.patrones.taller1.entity.products.dtos.MenuDTO;
import com.patrones.taller1.entity.products.dtos.SimpleProductDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
  private String id;
  private String client_id;
  private List<SimpleProductDTO> products;
  private List<MenuDTO> menus;

  public OrderDTO(Order order) {
    this.id = order.getId();
    this.client_id = order.getClient_id();
    this.products = new ArrayList<>();
    this.menus = new ArrayList<>();
    System.out.println(order.getProducts().size());
    for (IProduct product : order.getProducts())
      if ((product instanceof SimpleProduct))
        this.products.add(new SimpleProductDTO((SimpleProduct) product));
      else
        this.menus.add(new MenuDTO((Menu) product));
  }
}
