package viethung.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import viethung.models.*;
import viethung.repositories.*;
import viethung.repositories.impl.*;
import viethung.services.impl.ProductDetailService;
import viethung.utilities.ProductDetailUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ProductDetailServiceImpl implements ProductDetailService {
    private ProductRepository productRepo = new ProductRepositoryImpl();
    private ProductDetailRepository productDetailRepo = new ProductDetailRepositoryImpl();
    private ColorRepository colorRepo = new ColorRepositoryImpl();
    private ProducerRepository producerRepo = new ProducerRepositoryImpl();
    private CategoryRepository categoryRepo = new CategoryRepositoryImpl();

    @Override
    public void initProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/product/product/product-list.jsp";
        List<ProductDetail> productDetails = productDetailRepo.getAll();
        request.setAttribute("view", view);
        request.setAttribute("productDetails", productDetails);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void initDetailProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/product/product/product-form.jsp";
        String productDetailId = request.getParameter("productDetailId");
        //product detail
        ProductDetail productDetail = productDetailRepo.getById(productDetailId);
        //list category
        List<Category> categories = categoryRepo.getAll();
        //list color
        List<Color> colors = colorRepo.getAll();
        //list producer
        List<Producer> producers = producerRepo.getAll();
        request.setAttribute("view", view);
        request.setAttribute("create", false);
        request.setAttribute("productDetail", productDetail);
        request.setAttribute("categories", categories);
        request.setAttribute("colors", colors);
        request.setAttribute("producers", producers);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void initViewCreateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/views/product/product/product-form.jsp";
        //list category
        List<Category> categories = categoryRepo.getAll();
        //list color
        List<Color> colors = colorRepo.getAll();
        //list producer
        List<Producer> producers = producerRepo.getAll();

        //send attribute
        request.setAttribute("view", view);
        request.setAttribute("create", true);
        request.setAttribute("categories", categories);
        request.setAttribute("colors", colors);
        request.setAttribute("producers", producers);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        String view = "/views/product/product/product-form.jsp";

        //get product
        Product product = new Product();
        BeanUtils.copyProperties(product, request.getParameterMap());

        //get category
        String categoryId = request.getParameter("categoryId");
        Category category = categoryRepo.getById(categoryId);

        //get producer
        String producerId = request.getParameter("producerId");
        Producer producer = producerRepo.getById(producerId);

        //get color
        String colorId = request.getParameter("colorId");
        Color color = colorRepo.getById(colorId);

        //get productdetail
        ProductDetail productDetail = new ProductDetail();
        BeanUtils.copyProperties(productDetail, request.getParameterMap());

        //merge product detail
        productDetail.setProduct(product);
        productDetail.setProducer(producer);
        productDetail.setCategory(category);
        productDetail.setColor(color);
        //validate
        String message = ProductDetailUtil.validateInsert(productDetail);
        if (message == null) {
            productDetail = productDetailRepo.insert(productDetail);
        }

        //list category
        List<Category> categories = categoryRepo.getAll();
        //list color
        List<Color> colors = colorRepo.getAll();
        //list producer
        List<Producer> producers = producerRepo.getAll();

        //send attribute
        request.setAttribute("view", view);
        request.setAttribute("create", true);
        request.setAttribute("categories", categories);
        request.setAttribute("colors", colors);
        request.setAttribute("producers", producers);
        request.setAttribute("productDetail", productDetail);
        request.setAttribute("status", message == null ? "success" : "danger");
        request.setAttribute("message", message == null ? "Create successfully" : message);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        String view = "/views/product/product/product-form.jsp";
        //get productDetailId
        String productDetailId = request.getParameter("productDetailId");
        //get productId tránh lỗi khi cập nhật với id null thì sẽ insert
        ProductDetail productDetailTemp = productDetailRepo.getById(productDetailId);
        String productId = productDetailTemp.getProduct().getId();
        //get product
        Product product = new Product();
        BeanUtils.copyProperties(product, request.getParameterMap());
        product.setId(productId);
        //get category
        String categoryId = request.getParameter("categoryId");
        Category category = categoryRepo.getById(categoryId);

        //get producer
        String producerId = request.getParameter("producerId");
        Producer producer = producerRepo.getById(producerId);

        //get color
        String colorId = request.getParameter("colorId");
        Color color = colorRepo.getById(colorId);

        //get productdetail
        ProductDetail productDetail = new ProductDetail();
        BeanUtils.copyProperties(productDetail, request.getParameterMap());

        //merge product detail
        productDetail.setProduct(product);
        productDetail.setProducer(producer);
        productDetail.setCategory(category);
        productDetail.setColor(color);
        //validate
        String message = ProductDetailUtil.validateUpdate(productDetail);
        if (message == null) {
            productDetail = productDetailRepo.update(productDetail,productDetailId);
        }

        //list category
        List<Category> categories = categoryRepo.getAll();
        //list color
        List<Color> colors = colorRepo.getAll();
        //list producer
        List<Producer> producers = producerRepo.getAll();

        //send attribute
        request.setAttribute("view", view);
        request.setAttribute("create", false);
        request.setAttribute("categories", categories);
        request.setAttribute("colors", colors);
        request.setAttribute("producers", producers);
        request.setAttribute("productDetail", productDetail);
        request.setAttribute("status", message == null ? "success" : "danger");
        request.setAttribute("message", message == null ? "Update successfully" : message);
        request.getRequestDispatcher("/views/layout/index.jsp").forward(request, response);
    }

    @Override
    public void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //get productDetailId
        String productDetailId = request.getParameter("productDetailId");
        productDetailRepo.delete(productDetailId);
        response.sendRedirect("/product");
    }
}
