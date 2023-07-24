package viethung.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import viethung.models.Position;
import viethung.models.Staff;
import viethung.models.Store;
import viethung.repositories.PositionRepositoryImpl;
import viethung.repositories.StaffRepositoryImpl;
import viethung.repositories.StoreRepositoryImpl;
import viethung.repositories.impl.PositionRepository;
import viethung.repositories.impl.StaffRepository;
import viethung.repositories.impl.StoreRepository;
import viethung.services.impl.StaffService;
import viethung.utilities.StaffUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StaffServiceImpl implements StaffService {
    private StaffRepository staffRepo = new StaffRepositoryImpl();
    private StoreRepository storeRepo = new StoreRepositoryImpl();
    private PositionRepository positionRepo = new PositionRepositoryImpl();
    @Override
    public void initStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/staff/staff-list.jsp";
        List<Staff> staffs = staffRepo.getAll();
        request.setAttribute("view", view);
        request.setAttribute("staffs", staffs);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void initViewCreateStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/staff/staff-form.jsp";
        //get List position
        List<Position> positions = positionRepo.getAll();
        //get List store
        List<Store> stores = storeRepo.getAll();

        request.setAttribute("view", view);
        request.setAttribute("create", true);
        request.setAttribute("positions", positions);
        request.setAttribute("stores", stores);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void initDetailStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/staff/staff-form.jsp";
        //get staff
        String id = request.getParameter("staffId");
        Staff staff = staffRepo.getById(id);
        //get List position
        List<Position> positions = positionRepo.getAll();
        //get List store
        List<Store> stores = storeRepo.getAll();

        request.setAttribute("view", view);
        request.setAttribute("create", false);
        request.setAttribute("staff", staff);
        request.setAttribute("positions", positions);
        request.setAttribute("stores", stores);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void createStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        String view = "/views/staff/staff-form.jsp";
        String dateString = request.getParameter("dateOfBirth");
        String positionId = request.getParameter("positionId");
        String storeId = request.getParameter("storeId");
        //chuyển đổi date từ string sang date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateBirth = null;
        try {
            dateBirth = dateFormat.parse(dateString);
        }catch (Exception e){
            e.printStackTrace();
        }
        //lấy cửa hàng
        Store store = storeRepo.getById(storeId);
        //lấy chức vụ
        Position position = positionRepo.getById(positionId);
        //lấy ra dối tượng cần tạo
        Staff staff = new Staff();
        BeanUtils.copyProperties(staff,request.getParameterMap());
        staff.setDateBirth(dateBirth);
        staff.setPosition(position);
        staff.setStore(store);

        //validate
        String message = StaffUtil.validateInsert(staff);
        if (message == null){
            staff = staffRepo.insert(staff);
        }


        //get List position
        List<Position> positions = positionRepo.getAll();
        //get List store
        List<Store> stores = storeRepo.getAll();
        //tạo staff
        request.setAttribute("view", view);
        request.setAttribute("create", true);
        request.setAttribute("staff", staff);
        request.setAttribute("positions", positions);
        request.setAttribute("stores", stores);
        request.setAttribute("message", message == null ? "Create successfully" : message);
        request.setAttribute("status", message == null ? "success" : "danger");
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void updateStaff(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, ServletException, IOException {
        String view = "/views/staff/staff-form.jsp";
        String staffId = request.getParameter("staffId");
        String dateString = request.getParameter("dateOfBirth");
        String positionId = request.getParameter("positionId");
        String storeId = request.getParameter("storeId");
        //chuyển đổi date từ string sang date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateBirth = null;
        try {
            dateBirth = dateFormat.parse(dateString);
        }catch (Exception e){
            e.printStackTrace();
        }
        //lấy cửa hàng
        Store store = storeRepo.getById(storeId);
        //lấy chức vụ
        Position position = positionRepo.getById(positionId);
        //lấy ra dối tượng cần tạo
        Staff staff = new Staff();
        BeanUtils.copyProperties(staff,request.getParameterMap());
        staff.setDateBirth(dateBirth);
        staff.setPosition(position);
        staff.setStore(store);

        //validate
        String message = StaffUtil.validateUpdate(staff);
        if (message == null){
            staff = staffRepo.update(staff,staffId);
        }


        //get List position
        List<Position> positions = positionRepo.getAll();
        //get List store
        List<Store> stores = storeRepo.getAll();
        //tạo staff
        request.setAttribute("view", view);
        request.setAttribute("create", false);
        request.setAttribute("staff", staff);
        request.setAttribute("positions", positions);
        request.setAttribute("stores", stores);
        request.setAttribute("message", message == null ? "Update successfully" : message);
        request.setAttribute("status", message == null ? "success" : "danger");
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void deleteStaff(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String staffId = request.getParameter("staffId");
        staffRepo.delete(staffId);
        response.sendRedirect("/staff");
    }
}
