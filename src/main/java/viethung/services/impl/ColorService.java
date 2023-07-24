package viethung.services.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface ColorService {
    void initColor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void initViewColor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void initDetailColor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void createColor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException;
    void updateColor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException;
    void deleteColor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
