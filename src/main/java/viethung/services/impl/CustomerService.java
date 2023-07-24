package viethung.services.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public interface CustomerService {
    void initCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void initViewCreateCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void initDetailCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void createCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException, ParseException;
    void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, ServletException, IOException;
    void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
