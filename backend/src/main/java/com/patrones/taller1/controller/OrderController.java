package com.patrones.taller1.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.patrones.taller1.entity.orders.dtos.OrderDTO;
import com.patrones.taller1.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }
  
  @GetMapping
  public List<OrderDTO> getOrders(){
    return this.orderService.getOrders();
  }

  @GetMapping("/{id}")
  public OrderDTO getOrder(@PathVariable String id){
    return this.orderService.getOrder(id);
  }

  @PostMapping
  public OrderDTO createOrder(@RequestParam String client_id){
    return this.orderService.createOrder(client_id);
  }

  @PutMapping("/{order_id}/simple/{simple_id}")
  public OrderDTO addSimpleProductToOrder (@PathVariable("order_id")String order_id, @PathVariable("simple_id") String simpleProduct_id){
    return this.orderService.addSimpleProductToOrder(order_id, simpleProduct_id);
  }

  @PutMapping("/{order_id}/menu/{menu_id}")
  public OrderDTO addMenuToOrder (@PathVariable("order_id")String order_id, @PathVariable("menu_id") String menu_id){
    return this.orderService.addMenuToOrder(order_id, menu_id);
  }

  @PutMapping("/{order_id}")
  public OrderDTO payOrder (@PathVariable("order_id")String order_id){
    return this.orderService.payOrder(order_id);
  }
}
