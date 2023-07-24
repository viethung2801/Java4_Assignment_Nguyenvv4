<%@ page import="viethung.models.Order" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 19/07/2023
  Time: 4:59 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="row align-items-center">
        <div class="col-md-6">
            <div class="mb-3">

            </div>
        </div>
        <div class="col-md-6">
            <div class="d-flex flex-wrap align-items-center justify-content-end gap-2 mb-3">
                <div>
                    <a href="/pos/create" class="btn btn-primary">
                        <i class="bx bx-plus me-1"></i> Add New
                    </a>
                </div>

            </div>
        </div>
    </div>
    <div class="row">
        <c:forEach var="o" items="${orders}">
            <div class="col-6 border rounded-2 p-2 mt-3">
                <div class="row">
                    <div class="col-9">
                        <h4>${o.code}</h4>
                        <p class="text-secondary">Customer: ${o.customer.firstName} ${o.customer.middleName} ${o.customer.lastName}</p>
                        <p class="text-secondary">Staff: ${o.staff.firstName} ${o.staff.middleName} ${o.staff.lastName}</p>
                    </div>
                    <div class="col-3 float-end">
                        <a href="/pos/detail?orderId=${o.id}" class="btn btn-success">
                            <i class="bi bi-pencil"></i>
                        </a>
                        <a href="/pos/delete?orderId=${o.id}" class="btn btn-danger">
                            <i class="bi bi-x-circle"></i>
                        </a>
                    </div>
                </div>
                <hr>
                <ul class="list-inline">
                    <li>
                        Date created: ${o.dateCreate}
                    </li>
                    <li>
                        Status: Đang xử lý
                    </li>
                    <li>
                        <fmt:formatNumber var="total" value="${o.getTotalPrice()}" pattern="###,###"></fmt:formatNumber>
                        Total Price:${total}
                    </li>
                </ul>
            </div>
        </c:forEach>
    </div>
</div>
