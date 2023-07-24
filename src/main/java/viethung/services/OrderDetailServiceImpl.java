package viethung.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import viethung.models.Order;
import viethung.models.OrderDetail;
import viethung.models.ProductDetail;
import viethung.repositories.OrderDetailRepositoryImpl;
import viethung.repositories.OrderRepositoryImpl;
import viethung.repositories.ProductDetailRepositoryImpl;
import viethung.repositories.impl.OrderDetailRepository;
import viethung.repositories.impl.OrderRepository;
import viethung.repositories.impl.ProductDetailRepository;
import viethung.services.impl.OrderDetailService;
import viethung.utilities.OrderDetailUtil;

import java.io.IOException;
import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {
    private OrderRepository orderRepo = new OrderRepositoryImpl();
    private ProductDetailRepository productDetailRepo = new ProductDetailRepositoryImpl();
    private OrderDetailRepository orderDetailRepo = new OrderDetailRepositoryImpl();
    @Override
    public void addCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String orderId = request.getParameter("orderId");
        String productDetailId = request.getParameter("productDetailId");

        //get order
        Order order = orderRepo.getById(orderId);
        //get product detail
        ProductDetail productDetail = productDetailRepo.getById(productDetailId);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOder(order);
        orderDetail.setProductDetail(productDetail);
        orderDetail.setQuanltity(1);
        orderDetail.setPrice(productDetail.getPrice());
        //thực hiện insert
        OrderDetailUtil.insert(orderDetail);
        response.sendRedirect("/pos/detail?orderId="+orderId);
    }

    @Override
    public void updateCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String orderId = request.getParameter("orderId");
        String productDetailId = request.getParameter("productDetailId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        //do update quantity
        orderDetailRepo.updateQuantity(orderId,productDetailId,quantity);
        System.out.println(quantity);
        response.sendRedirect("/pos/detail?orderId="+orderId);
    }

    @Override
    public void deleteCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String orderId = request.getParameter("orderId");
        String productDetailId = request.getParameter("productDetailId");
        //do remove
        orderDetailRepo.delete(orderId,productDetailId);
        response.sendRedirect("/pos/detail?orderId="+orderId);
    }

    @Override
    public void pay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String orderId = request.getParameter("orderId");
        Order order = orderRepo.getById(orderId);
        //validate
        String message = OrderDetailUtil.validate(order);
        if (message == null){
            //thực hiện thanh thoán
            orderRepo.pay(orderId);
            response.sendRedirect("/pos");
            return;
        }
        //lỗi sẽ trả về trang hiện tại
        request.getSession().setAttribute("message",message);
        response.sendRedirect("/pos/detail?orderId="+orderId);
    }

    @Override
    public void initOrderList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String view = "/views/order/order-list.jsp";
        List<Order> orders = orderRepo.getAll();
        request.setAttribute("orders",orders);
        request.setAttribute("view", view);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void initOrderDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String view = "/views/order/order-detail.jsp";
        String orderId = request.getParameter("orderId");
        Order order = orderRepo.getById(orderId);
        request.setAttribute("view", view);
        request.setAttribute("order", order);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

}
