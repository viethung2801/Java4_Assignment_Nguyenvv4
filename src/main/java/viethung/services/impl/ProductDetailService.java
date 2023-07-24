package viethung.services.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface ProductDetailService {
    void initProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void initDetailProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void initViewCreateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException;
    void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException;
    void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
