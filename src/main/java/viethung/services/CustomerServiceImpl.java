package viethung.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import viethung.models.Category;
import viethung.models.Customer;
import viethung.repositories.CustomerRepositoryImpl;
import viethung.repositories.impl.CustomerRepository;
import viethung.services.impl.CustomerService;
import viethung.utilities.CustomerUltil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepo = new CustomerRepositoryImpl();

    @Override
    public void initCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/customer/customer-list.jsp";

        List<Customer> customers = customerRepo.getAll();

        request.setAttribute("view", view);
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void initViewCreateCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/customer/customer-form.jsp";


        request.setAttribute("view", view);
        request.setAttribute("create", true);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void initDetailCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/customer/customer-form.jsp";
        String id = request.getParameter("customerId");
        Customer customer = customerRepo.getById(id);

        request.setAttribute("view", view);
        request.setAttribute("create", false);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void createCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException, ParseException {
        String view = "/views/customer/customer-form.jsp";

        //lấy về ngày sinh
        String dateString = request.getParameter("dateOfBirth");
        Date dateBirth = null;
        try {
             dateBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        }catch (Exception e){

        }
        //lấy về customer
        Customer customer = new Customer();
        BeanUtils.copyProperties(customer, request.getParameterMap());
        customer.setDateBirth(dateBirth);
        //validate
        String message = CustomerUltil.validateInsert(customer);
        if (message == null) {
            customer = customerRepo.insert(customer);
        }
        request.setAttribute("view", view);
        request.setAttribute("create", true);
        request.setAttribute("customer", customer);
        request.setAttribute("status", message == null ? "success":"danger");
        request.setAttribute("message", message == null ? "Create successfully":message);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, ServletException, IOException {
        String view = "/views/customer/customer-form.jsp";
        String customerId = request.getParameter("customerId");
        //lấy về ngày sinh
        String dateString = request.getParameter("dateOfBirth");
        Date dateBirth = null;
        try {
            dateBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        }catch (Exception e){

        }
        //lấy về customer
        Customer customer = new Customer();
        BeanUtils.copyProperties(customer, request.getParameterMap());
        customer.setDateBirth(dateBirth);
        //validate
        String message = CustomerUltil.validateUpdate(customer);
        if (message == null) {
            customer = customerRepo.update(customer,customerId);
        }
        request.setAttribute("view", view);
        request.setAttribute("create", false);
        request.setAttribute("customer", customer);
        request.setAttribute("status", message == null ? "success":"danger");
        request.setAttribute("message", message == null ? "Update successfully":message);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String customerId = request.getParameter("customerId");
        customerRepo.delete(customerId);
        response.sendRedirect("/customer");
    }
}
