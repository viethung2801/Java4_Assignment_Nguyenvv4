package viethung.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import viethung.models.Store;
import viethung.services.StoreServiceImpl;
import viethung.services.impl.StoreService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "StoreServlet", value = {"/store","/store/detail","/store/create","/store/update","/store/delete"})
public class StoreServlet extends HttpServlet {
    private StoreService storeService = new StoreServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/store")){
            storeService.initStore(request,response);
        }else if (uri.equals("/store/create")){
            storeService.initViewCreateStore(request,response);
        }else if (uri.equals("/store/detail")){
            storeService.initDetailStore(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/store/create")){
            try {
                storeService.createStore(request,response);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }else if (uri.equals("/store/update")){
            try {
                storeService.updateStore(request,response);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }else if (uri.equals("/store/delete")){
            storeService.deleteStore(request,response);
        }
    }
}
