package viethung.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import viethung.models.Position;
import viethung.repositories.PositionRepositoryImpl;
import viethung.repositories.impl.PositionRepository;
import viethung.services.impl.PositionService;
import viethung.utilities.PositionUtil;

import java.io.IOException;
import java.util.List;

public class PositionServiceImpl implements PositionService {
    private PositionRepository positionRepo = new PositionRepositoryImpl();

    @Override
    public void initPosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/position/position-list.jsp";
        List<Position> positions = positionRepo.getAll();
        request.setAttribute("view", view);
        request.setAttribute("positions", positions);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void initViewPosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/position/position-form.jsp";

        request.setAttribute("view", view);
        request.setAttribute("create", true);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void initDetailPosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/position/position-form.jsp";
        String id = request.getParameter("positionId");
        Position position = positionRepo.getById(id);

        request.setAttribute("view", view);
        request.setAttribute("create", false);
        request.setAttribute("position", position);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void createPosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/position/position-form.jsp";
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        Position position = new Position();
        position.setName(name);
        position.setCode(code);
        String message = PositionUtil.validateInsert(position);
        if (message == null) {
            position = positionRepo.insert(position);
        }

        request.setAttribute("view", view);
        request.setAttribute("create", true);
        request.setAttribute("message", message == null ? "Create successfully" : message);
        request.setAttribute("status", message == null ? "success" : "danger");
        request.setAttribute("position", position);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void updatePosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/position/position-form.jsp";
        String id = request.getParameter("positionId");
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        Position position = new Position();
        position.setName(name);
        position.setCode(code);
        String message = PositionUtil.validateUpdate(position);
        if (message == null) {
            position = positionRepo.update(position, id);
        }

        request.setAttribute("view", view);
        request.setAttribute("create", false);
        request.setAttribute("message", message == null ? "Update successfully" : message);
        request.setAttribute("status", message == null ? "success" : "danger");
        request.setAttribute("position", position);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void deletePosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("positionId");
        positionRepo.delete(id);
        response.sendRedirect("/position");
    }
}
