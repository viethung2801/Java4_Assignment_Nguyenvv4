<%@ page import="viethung.models.Order" %>
<%@ page import="java.text.Format" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 19/07/2023
  Time: 5:00 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container pb-5 mt-n2 mt-md-n3">
    <c:if test="${not empty sessionScope.message}">
        <p class="alert alert-danger">${sessionScope.message}</p>
        <%-- Xóa thông báo sau khi hiển thị --%>
        <% request.getSession().removeAttribute("message");%>
    </c:if>
    <div class="row">
        <div class="col-9">
            <div class="">
                <h6 class="d-flex flex-wrap justify-content-between align-items-center px-4 py-3 bg-secondary">
                    <span class="text-white">Order Detail</span>
                    <a href="/pos" class="text-white"> >> Back POS App</a>
                </h6>

                <!-- Items-->
                <div class="" style="height: 500px;">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                            <th>Code</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>
                            <th>Action</th>
                            </thead>
                            <tbody>
                            <c:forEach items="${orderDetails}" var="oD">
                                <form action="">
                                    <tr class="">
                                        <td class="col-1">
                                                ${oD.productDetail.product.code}
                                        </td>
                                        <th class="col-4">
                                                ${oD.productDetail.product.name}
                                        </th>
                                        <td class="col-1">
                                            <input class="form-control" name="quantity" type="number" id="quantity"
                                                   min="1" value="${oD.quanltity}">
                                        </td>
                                        <td class="col-2">
                                            <fmt:formatNumber var="price" value="${oD.price}"
                                                              pattern="###,###"></fmt:formatNumber>
                                                ${price} đ
                                        </td>
                                        <td class="col-2">
                                            <fmt:formatNumber var="thanhTien" value="${oD.quanltity * oD.price}"
                                                              pattern="###,###"></fmt:formatNumber>
                                                ${thanhTien} đ
                                        </td>
                                        <td class="col-2">
                                            <button class="btn btn-outline-secondary" formaction="/pos/update-cart?orderId=${order.id}&productDetailId=${oD.productDetail.id}" formmethod="post">
                                                <i class="bi bi-arrow-clockwise"></i>
                                            </button>
                                            <a class="btn btn-outline-danger"
                                               href="/pos/delete-cart?orderId=${order.id}&productDetailId=${oD.productDetail.id}">
                                                <i class="bi bi-trash"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </form>

                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!-- List product -->
            <div class="">
                <h6 class="d-flex flex-wrap justify-content-between align-items-center px-4 py-3 bg-secondary">
                    <span class="text-white">List Product</span>
                </h6>

                <!-- Items-->
                <div class="">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                            <c:forEach var="p" items="${productDetails}">
                                <tr class="">
                                    <th class="col-1">${p.product.code}</th>
                                    <th class="col-4">${p.product.name}</th>
                                    <th class="col-2">${p.category.name}</th>
                                    <th class="col-2">${p.color.name}</th>
                                    <th class="col-1">${p.quantity}</th>
                                    <th class="col-1">${p.price}</th>
                                    <th class="col-1">
                                        <a class="btn btn-outline-success"
                                           href="/pos/add-cart?orderId=${order.id}&productDetailId=${p.id}">
                                            <i class="bi bi-plus"></i>
                                        </a>
                                    </th>
                                </tr>
                            </c:forEach>

                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- Sidebar-->
        <div class="col-xl-3 col-md-4 pt-3 pt-md-0">
            <h2 class="h6 px-4 py-3 bg-secondary text-center text-white">Subtotal</h2>
            <div class="h3 font-weight-semibold text-center py-3">
                <%Order order = (Order) request.getAttribute("order");%>
                <%= order.getId() != null ? new DecimalFormat("###,###").format(order.getTotalPrice()) : 0 %> đ
            </div>
            <hr>

            <form action="">
                <div class="mb-3">
                    <label class="form-label">Customer: </label>
                    <select class="form-select " name="customerId">
                        <c:forEach var="c" items="${customers}">
                            <option value="${c.id}"
                                    <c:if test="${order.customer.id == c.id}">selected</c:if> >${c.firstName} ${c.middleName} ${c.lastName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label">Staff: </label>
                    <select class="form-select " name="staffId">
                        <c:forEach var="s" items="${staffs}">
                            <option value="${s.id}"
                                    <c:if test="${order.staff.id == s.id}">selected</c:if> >${s.firstName} ${s.middleName} ${s.lastName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <labe class="form-label">Name Receiver:</labe>
                    <input type="text" class="form-control" name="receiver" value="${order.receiver}"
                           placeholder="Enter Name Receiver">
                </div>
                <div class="mb-3">
                    <labe class="form-label">Phone Number Receiver:</labe>
                    <input type="text" class="form-control" name="phoneNumber" value="${order.phoneNumber}"
                           placeholder="Enter Phone Number Receiver">
                </div>
                <div class="mb-3">
                    <labe class="form-label">Address Receiver:</labe>
                    <textarea class="form-control" name="address" placeholder="Enter Address Receiver"
                              rows="5">${order.address}</textarea>
                </div>
                <div class="mb-3">
                    <button formaction="/pos/update?orderId=${order.id}" formmethod="post"
                            class="btn btn-danger w-100 h-100">Thêm Người Nhận
                    </button>
                    <a  href="/pos/pay?orderId=${order.id}" class="btn btn-success w-100 h-100">
                        Thanh Toán
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>