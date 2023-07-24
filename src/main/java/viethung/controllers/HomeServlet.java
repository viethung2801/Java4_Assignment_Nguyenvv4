package viethung.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import viethung.models.Category;
import viethung.repositories.ThongKeRepositoryImpl;
import viethung.repositories.impl.ThongKeRepository;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "HomeServlet", value = {"/home", "/thong-ke"})
public class HomeServlet extends HttpServlet {
    private ThongKeRepository thongKeRepo = new ThongKeRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/home")) {
            String view = "/views/home/home.jsp";
            LocalDate toDate = LocalDate.now();
            LocalDate fromDate = toDate.minusDays(7);
            //lấy dữ liệu để hiển thị
            List<Object[]> objects = thongKeRepo.getDoanhThuByDate(fromDate.toString(), toDate.toString());

            //labels
            List<String> labels = listLabel(objects);
            //values
            List<Integer> values = listValue(objects);

            request.setAttribute("view", view);
            request.setAttribute("toDate", toDate);
            request.setAttribute("fromDate", fromDate);
            request.setAttribute("labels", labels);
            request.setAttribute("values", values);
            request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
        } else if (uri.equals("/thong-ke")) {
            String view = "/views/home/home.jsp";
            String fromDateString = request.getParameter("fromDate");
            String toDateString = request.getParameter("toDate");
            //lấy dữ liệu để hiển thị
            List<Object[]> objects = thongKeRepo.getDoanhThuByDate(fromDateString, toDateString);

            //labels
            List<String> labels = listLabel(objects);
            //values
            List<Integer> values = listValue(objects);

            request.setAttribute("view", view);
            request.setAttribute("toDate", fromDateString);
            request.setAttribute("fromDate", toDateString);
            request.setAttribute("labels", labels);
            request.setAttribute("values", values);
            request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private List<String> listLabel(List<Object[]> objects) {
        List<String> strings = new ArrayList<>();
        objects.forEach(o -> {
            strings.add(o[0].toString());
        });

        return strings;
    }

    private List<Integer> listValue(List<Object[]> objects) {
        List<Integer> integers = new ArrayList<>();
        objects.forEach(o -> {
            integers.add(Integer.parseInt(o[1].toString()));
        });
        return integers;
    }
}
