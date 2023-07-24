package viethung.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import viethung.models.Color;
import viethung.repositories.ColorRepositoryImpl;
import viethung.repositories.impl.ColorRepository;
import viethung.services.impl.ColorService;
import viethung.utilities.ColorUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ColorServiceImpl implements ColorService {
    private ColorRepository colorRepo = new ColorRepositoryImpl();

    @Override
    public void initColor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/product/color/color-list.jsp";
        List<Color> colors = colorRepo.getAll();
        request.setAttribute("view", view);
        request.setAttribute("colors", colors);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void initViewColor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/product/color/color-form.jsp";

        request.setAttribute("view", view);
        request.setAttribute("create", true);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void initDetailColor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/product/color/color-form.jsp";
        String colorId = request.getParameter("colorId");
        Color color = colorRepo.getById(colorId);
        if (color == null) {
            response.sendRedirect("/color");
            return;
        }
        request.setAttribute("view", view);
        request.setAttribute("create", false);
        request.setAttribute("color", color);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void createColor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        String view = "/views/product/color/color-form.jsp";
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        Color color = new Color();
        color.setCode(code);
        color.setName(name);

        String message = ColorUtil.validateInsert(color);
        if (message == null) {
            color = colorRepo.insert(color);
        }

        request.setAttribute("view", view);
        request.setAttribute("create", true);
        request.setAttribute("message", message == null ? "Create successfully" : message);
        request.setAttribute("status", message == null ? "success" : "danger");
        request.setAttribute("color", color);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void updateColor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        String view = "/views/product/color/color-form.jsp";
        String id = request.getParameter("colorId");
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        Color color = new Color();
        color.setCode(code);
        color.setName(name);

        String message = ColorUtil.validateUpdate(color);
        if (message == null) {
            color = colorRepo.update(color, id);
        }

        request.setAttribute("view", view);
        request.setAttribute("create", true);
        request.setAttribute("message", message == null ? "Update successfully" : message);
        request.setAttribute("status", message == null ? "success" : "danger");
        request.setAttribute("color", color);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void deleteColor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/product/color/color-form.jsp";
        String id = request.getParameter("colorId");
        System.out.println(id);
         colorRepo.delete(id);
        response.sendRedirect("/color");
    }
}
