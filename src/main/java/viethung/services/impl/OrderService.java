package viethung.services.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface OrderService {
    void initPos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void detailOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void updateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
