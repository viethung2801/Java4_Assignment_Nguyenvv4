package viethung.repositories.impl;

import viethung.models.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> getAll();
    List<Order> getAllByStatus(int status);
    Order insert(Order order);
    Order getById(String orderId);
    Order getByCode(String orderCode);
    String nextCode();
    void delete(String orderId);
    void pay(String orderId);
    void updateOrder(Order order,String orderId);
}
