package viethung.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import viethung.services.StaffServiceImpl;
import viethung.services.impl.StaffService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "StaffServlet", value = {"/staff", "/staff/detail", "/staff/create", "/staff/update", "/staff/delete"})
public class StaffServlet extends HttpServlet {
    private StaffService staffService = new StaffServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/staff")){
            staffService.initStaff(request,response);
        }else if (uri.equals("/staff/create")){
            staffService.initViewCreateStaff(request,response);
        }else if (uri.equals("/staff/detail")){
            staffService.initDetailStaff(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/staff/create")){
            try {
                staffService.createStaff(request,response);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }else if (uri.equals("/staff/update")){
            try {
                staffService.updateStaff(request,response);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }else if (uri.equals("/staff/delete")){
            staffService.deleteStaff(request,response);
        }
    }
}
