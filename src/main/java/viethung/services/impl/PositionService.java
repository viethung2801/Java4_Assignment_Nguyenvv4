package viethung.services.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface PositionService {
    void initPosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void initViewPosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void initDetailPosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void createPosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void updatePosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void deletePosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
