package viethung.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import viethung.models.Producer;
import viethung.repositories.ProducerRepositoryImpl;
import viethung.repositories.ProductRepositoryImpl;
import viethung.repositories.impl.ProducerRepository;
import viethung.services.impl.ProducerService;
import viethung.utilities.ProducerUtil;

import java.io.IOException;
import java.util.List;

public class ProducerServiceImpl implements ProducerService {
    private ProducerRepository producerRepo = new ProducerRepositoryImpl();

    @Override
    public void initProducer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/product/producer/producer-list.jsp";
        List<Producer> producers = producerRepo.getAll();
        request.setAttribute("view", view);
        request.setAttribute("producers", producers);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void initViewProducer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/product/producer/producer-form.jsp";
        request.setAttribute("view", view);
        request.setAttribute("create", true);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void initDetailProducer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/product/producer/producer-form.jsp";
        String id = request.getParameter("producerId");

        Producer producer = producerRepo.getById(id);

        request.setAttribute("view", view);
        request.setAttribute("create", false);
        request.setAttribute("producer", producer);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void createProducer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/product/producer/producer-form.jsp";
        String code = request.getParameter("code");
        String name = request.getParameter("name");

        Producer producer = new Producer();
        producer.setName(name);
        producer.setCode(code);

        String message = ProducerUtil.validateInsert(producer);
        if (message == null) {
            producer = producerRepo.insert(producer);
        }

        request.setAttribute("view", view);
        request.setAttribute("create", true);
        request.setAttribute("producer", producer);
        request.setAttribute("message", message == null ? "Create Successfully" : message);
        request.setAttribute("status", message == null ? "success" : "danger");
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void updateProducer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/product/producer/producer-form.jsp";
        String id = request.getParameter("producerId");
        String code = request.getParameter("code");
        String name = request.getParameter("name");

        Producer producer = new Producer();
        producer.setName(name);
        producer.setCode(code);

        String message = ProducerUtil.validateUpdate(producer);
        if (message == null) {
            producer = producerRepo.update(producer,id);
        }

        request.setAttribute("view", view);
        request.setAttribute("create", false);
        request.setAttribute("producer", producer);
        request.setAttribute("message", message == null ? "Update Successfully" : message);
        request.setAttribute("status", message == null ? "success" : "danger");
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void deleteProducer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("producerId");
        producerRepo.delete(id);
        response.sendRedirect("/producer");
    }
}
