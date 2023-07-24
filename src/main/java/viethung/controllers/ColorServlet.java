package viethung.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import viethung.services.ColorServiceImpl;
import viethung.services.impl.ColorService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "ColorServlet", value = {"/color","/color/detail","/color/create","/color/update","/color/delete"})
public class ColorServlet extends HttpServlet {
    private ColorService colorService = new ColorServiceImpl();
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String uri = request.getRequestURI();
         if (uri.equals("/color")) {
             colorService.initColor(request, response);
         } else if (uri.equals("/color/create")) {
             colorService.initViewColor(request, response);
         } else if (uri.contains("/color/detail")) {
             colorService.initDetailColor(request, response);
         }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/color/create")) {
            try {
                colorService.createColor(request, response);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (uri.equals("/color/update")) {
            try {
                colorService.updateColor(request, response);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (uri.equals("/color/delete")) {
            colorService.deleteColor(request, response);
        }
    }
}
