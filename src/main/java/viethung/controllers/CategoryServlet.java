package viethung.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import viethung.services.CategoryServiceImpl;
import viethung.services.impl.CategoryService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "CategoryServlet", value = {
        "/category",
        "/category/detail", "/category/create",
        "/category/update", "/category/delete"})
public class CategoryServlet extends HttpServlet {
    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/category")) {
            categoryService.initCategory(request, response);
        } else if (uri.equals("/category/create")) {
            categoryService.initCreateCategory(request, response);
        } else if (uri.equals("/category/detail")) {
            categoryService.initDetailCategory(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/category/create")) {
            try {
                categoryService.createCategory(request, response);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (uri.equals("/category/update")) {
            try {
                categoryService.updateCategory(request, response);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (uri.equals("/category/delete")) {
            categoryService.deleteCategory(request, response);
        }
    }
}
