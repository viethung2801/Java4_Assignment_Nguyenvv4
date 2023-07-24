package viethung.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import viethung.models.Category;
import viethung.repositories.CategoryRepositoryImpl;
import viethung.repositories.impl.CategoryRepository;
import viethung.services.impl.CategoryService;
import viethung.utilities.CategoryUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepo = new CategoryRepositoryImpl();
    private CategoryUtil categoryUtil = new CategoryUtil();
    @Override
    public void initCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/product/category/category-list.jsp";
        List<Category> categories = categoryRepo.getAll();
        request.setAttribute("view", view);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    public void initCreateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/product/category/category-form.jsp";


        request.setAttribute("create", true);
        request.setAttribute("view", view);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void initDetailCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/product/category/category-form.jsp";
        String categoryId = request.getParameter("categoryId");
        Category category = categoryRepo.getById(categoryId);
        request.setAttribute("create", false);
        request.setAttribute("view", view);
        request.setAttribute("category", category);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void createCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        String view = "/views/product/category/category-form.jsp";
        Category category = new Category();
        //get category form request
        BeanUtils.copyProperties(category, request.getParameterMap());

        //validate
        String message = categoryUtil.validateInsert(category);

        if (message == null){
            Category categoryInsert = categoryRepo.insert(category);
        }


        request.setAttribute("create", true);
        request.setAttribute("view", view);
        request.setAttribute("message", message != null ? message : "Create successfully");
        request.setAttribute("status", message != null ? "danger" : "success");
        request.setAttribute("category", category);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        String view = "/views/product/category/category-form.jsp";
        String categoryId = request.getParameter("categoryId");

        //get category form request
        Category category = new Category();
        BeanUtils.copyProperties(category, request.getParameterMap());

        //validate
        String message = categoryUtil.validateUpdate(category);


        if (message == null){
            Category categoryInsert = categoryRepo.update(category,categoryId);
        }


        request.setAttribute("create", false);
        request.setAttribute("view", view);
        request.setAttribute("message", message != null ? message : "Update successfully");
        request.setAttribute("status", message != null ? "danger" : "success");
        request.setAttribute("category", category);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/product/category/category-form.jsp";
        String categoryId = request.getParameter("categoryId");

        Category category = categoryRepo.delete(categoryId);

        request.setAttribute("create", false);
        request.setAttribute("view", view);
        request.setAttribute("message", category != null ? "Delete successfully" : "Delete fail");
        request.setAttribute("status", category != null ? "success" : "danger");
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }
}
