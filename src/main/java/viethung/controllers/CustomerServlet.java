package viethung.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import viethung.services.CustomerServiceImpl;
import viethung.services.impl.CustomerService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

@WebServlet(name = "CustomerServlet", value = {"/customer","/customer/detail","/customer/create","/customer/update","/customer/delete"})
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/customer")){
            customerService.initCustomer(request,response);
        }else if (uri.equals("/customer/create")){
            customerService.initViewCreateCustomer(request,response);
        }else if (uri.equals("/customer/detail")){
            customerService.initDetailCustomer(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/customer/create")){
            try {
                customerService.createCustomer(request,response);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if (uri.equals("/customer/update")){
            try {
                customerService.updateCustomer(request,response);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }else if (uri.equals("/customer/delete")){
            customerService.deleteCustomer(request,response);
        }
    }
}
