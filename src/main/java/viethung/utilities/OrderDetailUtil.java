package viethung.utilities;

import viethung.models.Order;
import viethung.models.OrderDetail;
import viethung.repositories.OrderDetailRepositoryImpl;
import viethung.repositories.impl.OrderDetailRepository;

public class OrderDetailUtil {
    public  static OrderDetailRepository orderDetailRepo = new OrderDetailRepositoryImpl();
    public static void insert(OrderDetail orderDetail){

        //kiểm tra xem có orderDetail trong db chưa
        //chưa có sẽ trả về null và thực hiện insert
        OrderDetail check = orderDetailRepo.getById(orderDetail.getOder().getId(),orderDetail.getProductDetail().getId());
        if (check != null){
            orderDetail.setQuanltity(check.getQuanltity() + 1);
            orderDetailRepo.update(orderDetail,orderDetail.getOder().getId(),orderDetail.getProductDetail().getId());

        }else {
            orderDetail.setQuanltity(1);
            orderDetailRepo.insert(orderDetail);
        }
    }

    public static String validate(Order order){
        if (order.getCustomer() == null){
            return "Fail! Please add customer.";
        }
        if (order.getStaff() == null){
            return "Fail! Please add staff.";
        }if (order.getReceiver() == null || order.getReceiver().trim().equals("")){
            return "Fail! Please add receiver.";
        }if (order.getPhoneNumber() == null || order.getPhoneNumber().trim().equals("")){
            return "Fail! Please add phone number.";
        }if (order.getAddress() == null || order.getAddress().trim().equals("")){
            return "Fail! Please add address.";
        }
        return null;
    }
}
