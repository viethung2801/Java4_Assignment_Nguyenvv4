package viethung.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import viethung.services.OrderDetailServiceImpl;
import viethung.services.impl.OrderDetailService;

import java.io.IOException;

@WebServlet(name = "PosServlet", value = {"/pos/add-cart", "/pos/delete-cart", "/pos/update-cart","/pos/pay"})
public class PosServlet extends HttpServlet {
    private OrderDetailService orderDetailService = new OrderDetailServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/pos/add-cart")) {
            orderDetailService.addCart(request, response);
        } else if (uri.equals("/pos/delete-cart")) {
            orderDetailService.deleteCart(request, response);
        } else if (uri.equals("/pos/pay")) {
            orderDetailService.pay(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/pos/update-cart")) {
            orderDetailService.updateCart(request, response);
        }
    }
}
