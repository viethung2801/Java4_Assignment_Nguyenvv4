package viethung.repositories.impl;

import viethung.models.OrderDetail;

import java.util.List;

public interface OrderDetailRepository {
    List<OrderDetail> getAll();
    List<OrderDetail> getByOrderId(String orderId);
    OrderDetail insert(OrderDetail orderDetail);
    OrderDetail update(OrderDetail orderDetail,String orderId,String productDetailId);
    void updateQuantity(String orderId,String productDetailId,int quantity);
    void delete(String orderId,String productDetailId);
    OrderDetail getById(String orderId,String productDetailId);
}
