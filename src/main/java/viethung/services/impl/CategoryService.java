package viethung.services.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface CategoryService {
    void initCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void initCreateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void initDetailCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void createCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException;
    void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException;
    void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
