package viethung.services.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface ProducerService {
    void initProducer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void initViewProducer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void initDetailProducer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void createProducer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void updateProducer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void deleteProducer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
