package viethung.services.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface OrderDetailService {
    void addCart(HttpServletRequest request, HttpServletResponse response) throws IOException;
    void updateCart(HttpServletRequest request, HttpServletResponse response) throws IOException;
    void deleteCart(HttpServletRequest request, HttpServletResponse response) throws IOException;
    void pay(HttpServletRequest request, HttpServletResponse response) throws IOException;
    void initOrderList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
    void initOrderDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;


}
