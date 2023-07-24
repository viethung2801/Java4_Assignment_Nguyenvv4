package viethung.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import viethung.services.ProducerServiceImpl;
import viethung.services.impl.ProducerService;

import java.io.IOException;

@WebServlet(name = "ProducerServlet", value = {"/producer","/producer/detail","/producer/create","/producer/update","/producer/delete"})
public class ProducerServlet extends HttpServlet {
    private ProducerService producerService = new ProducerServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/producer")) {
            producerService.initProducer(request, response);
        } else if (uri.equals("/producer/create")) {
            producerService.initViewProducer(request, response);
        } else if (uri.contains("/producer/detail")) {
            producerService.initDetailProducer(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/producer/create")) {
            producerService.createProducer(request, response);
        } else if (uri.contains("/producer/update")) {
            producerService.updateProducer(request, response);
        }else if (uri.contains("/producer/delete")) {
            producerService.deleteProducer(request, response);
        }
    }
}
