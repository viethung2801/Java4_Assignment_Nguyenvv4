package viethung.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import viethung.services.PositionServiceImpl;
import viethung.services.impl.PositionService;

import java.io.IOException;

@WebServlet(name = "PositionServlet", value = {"/position","/position/detail","/position/create","/position/update","/position/delete"})
public class PositionServlet extends HttpServlet {
    private PositionService positionService = new PositionServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/position")) {
            positionService.initPosition(request, response);
        } else if (uri.equals("/position/create")) {
            positionService.initViewPosition(request, response);
        } else if (uri.contains("/position/detail")) {
            positionService.initDetailPosition(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/position/create")) {
            positionService.createPosition(request, response);
        } else if (uri.contains("/position/update")) {
            positionService.updatePosition(request, response);
        }else if (uri.contains("/position/delete")) {
            positionService.deletePosition(request, response);
        }
    }
}
