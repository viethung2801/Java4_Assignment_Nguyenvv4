package viethung.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import viethung.services.ProductDetailServiceImpl;
import viethung.services.impl.ProductDetailService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "ProductDeatilServlet", value = {"/product","/product/detail","/product/create","/product/update","/product/delete"})
public class ProductDetailServlet extends HttpServlet {
    private ProductDetailService productDetailService = new ProductDetailServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/product")){
            productDetailService.initProduct(request,response);
        }else if (uri.equals("/product/create")){
            productDetailService.initViewCreateProduct(request,response);
        }else if (uri.equals("/product/detail")){
            productDetailService.initDetailProduct(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/product/create")){
            try {
                productDetailService.createProduct(request,response);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }else if (uri.equals("/product/update")){
            try {
                productDetailService.updateProduct(request,response);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }else if (uri.equals("/product/delete")){
            productDetailService.deleteProduct(request,response);
        }
    }
}
