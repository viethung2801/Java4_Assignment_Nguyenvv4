package viethung.services.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface StaffService {
    void initStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void initViewCreateStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void initDetailStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void createStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException;
    void updateStaff(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, ServletException, IOException;
    void deleteStaff(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
