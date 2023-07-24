package viethung.services.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface StoreService {
    void initStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void initViewCreateStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void initDetailStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void createStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException;
    void updateStore(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, ServletException, IOException;
    void deleteStore(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
