package viethung.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import viethung.services.OrderServiceImpl;
import viethung.services.impl.OrderService;

import java.io.IOException;

@WebServlet(name = "POSServlet", value = {
        "/pos","/pos/create","/pos/detail","/pos/delete","/pos/update"})
public class OrderServlet extends HttpServlet {
    private OrderService orderService = new OrderServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/pos")){
            orderService.initPos(request,response);
        }else if (uri.equals("/pos/create")){
            orderService.createOrder(request,response);
        }else if (uri.equals("/pos/detail")){
            orderService.detailOrder(request,response);
        }else if (uri.equals("/pos/delete")){
           orderService.deleteOrder(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/pos/update")){
            orderService.updateOrder(request,response);
        }
    }
}
