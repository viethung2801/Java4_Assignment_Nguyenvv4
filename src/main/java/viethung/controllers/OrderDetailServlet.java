package viethung.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import viethung.services.OrderDetailServiceImpl;
import viethung.services.impl.OrderDetailService;

import java.io.IOException;

@WebServlet(name = "OrderDetailServlet", value = {"/orders", "/orders/detail"})
public class OrderDetailServlet extends HttpServlet {
    private OrderDetailService orderDetailService = new OrderDetailServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/orders")) {
            orderDetailService.initOrderList(request,response);
        }else if (uri.equals("/orders/detail")) {
            orderDetailService.initOrderDetail(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
