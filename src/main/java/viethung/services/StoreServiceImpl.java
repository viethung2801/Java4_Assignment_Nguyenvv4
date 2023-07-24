package viethung.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import viethung.models.Store;
import viethung.repositories.StoreRepositoryImpl;
import viethung.repositories.impl.StoreRepository;
import viethung.services.impl.StoreService;
import viethung.utilities.StoreUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class StoreServiceImpl implements StoreService {
    private StoreRepository storeRepo = new StoreRepositoryImpl();
    @Override
    public void initStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/store/store-list.jsp";
        List<Store> stores = storeRepo.getAll();
        request.setAttribute("view", view);
        request.setAttribute("stores", stores);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void initViewCreateStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/store/store-form.jsp";
        request.setAttribute("view", view);
        request.setAttribute("create", true);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void initDetailStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/store/store-form.jsp";
        String storeId= request.getParameter("storeId");
        Store store = storeRepo.getById(storeId);
        request.setAttribute("view", view);
        request.setAttribute("create", false);
        request.setAttribute("store", store);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void createStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        String view = "/views/store/store-form.jsp";

        Store store = new Store();
        BeanUtils.copyProperties(store,request.getParameterMap());

        String message = StoreUtil.validateInsert(store);
        if (message == null){
            store = storeRepo.insert(store);
        }

        request.setAttribute("view", view);
        request.setAttribute("create", true);
        request.setAttribute("store", store);
        request.setAttribute("message", message == null ? "Create successfully": message);
        request.setAttribute("status", message == null ? "success": "danger");
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void updateStore(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, ServletException, IOException {
        String view = "/views/store/store-form.jsp";
        String id = request.getParameter("storeId");

        Store store = new Store();
        BeanUtils.copyProperties(store,request.getParameterMap());

        String message = StoreUtil.validateUpdate(store);
        if (message == null){
            store = storeRepo.update(store,id);
        }

        request.setAttribute("view", view);
        request.setAttribute("create", false);
        request.setAttribute("store", store);
        request.setAttribute("message", message == null ? "Update successfully": message);
        request.setAttribute("status", message == null ? "success": "danger");
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void deleteStore(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("storeId");
        storeRepo.delete(id);
        response.sendRedirect("/store");
    }
}
