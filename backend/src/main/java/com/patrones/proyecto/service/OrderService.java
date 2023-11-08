package com.patrones.proyecto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.patrones.proyecto.entity.orders.dtos.OrderDTO;
import com.patrones.proyecto.entity.products.ProductTypes;
import com.patrones.proyecto.service.persistance.IPersistance;

@Service
public class OrderService {
  private final IPersistance persistanceService;

  public OrderService(IPersistance persistanceService) {
    this.persistanceService = persistanceService;
  }

  public List<OrderDTO> getOrders() {
    return this.persistanceService.getOrders().stream().map(OrderDTO::new).collect(Collectors.toList());
  }

  public OrderDTO getOrder(String id) {
    try {
      return new OrderDTO(this.persistanceService.getOrder(id));
    } catch (Exception e) {
    }
    return null;
  }

  public OrderDTO createOrder(String client_id) {
    String order_id = this.persistanceService.getNextOrderID();
    if (this.persistanceService.save(order_id, client_id))
      return new OrderDTO(this.persistanceService.getOrder(order_id));
    else
      return null;
  }

  public OrderDTO addSimpleProductToOrder(String orderId, String simpleProductId) {
    this.persistanceService.addProductToOrder(orderId, simpleProductId, ProductTypes.SimpleProduct);
    return new OrderDTO(this.persistanceService.getOrder(orderId));
  }

  public OrderDTO addMenuToOrder(String order_id, String menu_id) {
    this.persistanceService.addProductToOrder(order_id, menu_id, ProductTypes.Menu);
    return new OrderDTO(this.persistanceService.getOrder(order_id));
  }

  public OrderDTO payOrder(String order_id) {
    // Payment check
    OrderDTO order = new OrderDTO(this.persistanceService.payOrder(order_id));
    // Notification
    new Thread(() -> {
      try {
        System.out.println("Cooking ...");
        Thread.sleep(5000l);
        System.out.println("Notification sent to client with id -> "+order.getClient_id());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }).start();
    // Done
    return order;
  }
}
