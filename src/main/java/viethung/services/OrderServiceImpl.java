package viethung.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import viethung.models.*;
import viethung.repositories.*;
import viethung.repositories.impl.*;
import viethung.services.impl.OrderService;

import java.io.IOException;
import java.util.*;

public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepo = new OrderRepositoryImpl();
    private OrderDetailRepository orderDetailRepo = new OrderDetailRepositoryImpl();
    private ProductDetailRepository productDetailRepo = new ProductDetailRepositoryImpl();
    private CustomerRepository customerRepo = new CustomerRepositoryImpl();
    private StaffRepository staffRepo = new StaffRepositoryImpl();

    @Override
    public void initPos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/pos/pos-list.jsp";
        //list đang xử lý
        List<Order> orders = orderRepo.getAllByStatus(0);
        request.setAttribute("view", view);
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/pos/pos-detail.jsp";
        String uuid = UUID.randomUUID().toString();
        Order order = new Order();
        order.setId(uuid);
        order.setCode("HD" + new Random().nextInt());
        order.setDateCreate(new Date());
        order.setStatus(0);
        order.setOrderDetailList(new ArrayList<>());
        orderRepo.insert(order);
        //lấy ra sau khi insert
        order = orderRepo.getByCode(order.getCode());
        //list product
        List<ProductDetail> productDetails = productDetailRepo.getAll();
        request.setAttribute("productDetails", productDetails);

        //list customer
        List<Customer> customers = customerRepo.getAll();
        request.setAttribute("customers", customers);
        //list staff
        List<Staff> staffs = staffRepo.getAll();
        request.setAttribute("staffs", staffs);
        request.setAttribute("order", order);

        request.setAttribute("view", view);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/pos/pos-list.jsp";
        String orderId = request.getParameter("orderId");
        //delete
        orderRepo.delete(orderId);
        //list đang xử lý
        List<Order> orders = orderRepo.getAllByStatus(0);
        request.setAttribute("view", view);
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void detailOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String orderId = request.getParameter("orderId");
        if (orderId != null) {
            //tránh lỗi khi chưa có ID

            //ds order detail
            List<OrderDetail> orderDetails = orderDetailRepo.getByOrderId(orderId);
            request.setAttribute("orderDetails", orderDetails);
            //order
            Order order = orderRepo.getById(orderId);
            request.setAttribute("order", order);
        }
        //list product
        List<ProductDetail> productDetails = productDetailRepo.getAll();
        request.setAttribute("productDetails", productDetails);

        //list customer
        List<Customer> customers = customerRepo.getAll();
        request.setAttribute("customers", customers);
        //list staff
        List<Staff> staffs = staffRepo.getAll();
        request.setAttribute("staffs", staffs);


        String view = "/views/pos/pos-detail.jsp";
        request.setAttribute("view", view);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void updateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //ds order detail
        String orderId = request.getParameter("orderId");
        //lấy thông tin người nhận
        String staffId = request.getParameter("staffId");
        String customerId = request.getParameter("customerId");
        String receiver = request.getParameter("receiver");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");

        //get customer
        Customer customer = customerRepo.getById(customerId);
        //get staff
        Staff staff = staffRepo.getById(staffId);
        //order update
        Order order = new Order();
        order.setCustomer(customer);
        order.setStaff(staff);
        order.setCustomer(customer);
        order.setReceiver(receiver);
        order.setAddress(address);
        order.setPhoneNumber(phoneNumber);
        //do update
        orderRepo.updateOrder(order,orderId);
        String uri = "/pos/detail?orderId=" + orderId;
        response.sendRedirect(uri);
    }
}
