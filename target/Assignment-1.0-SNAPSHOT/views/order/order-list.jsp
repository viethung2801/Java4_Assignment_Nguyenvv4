<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 21/07/2023
  Time: 9:48 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="row">
        <div class="col-12 mb-3 mb-lg-5">
            <div class="position-relative card table-nowrap table-card">
                <div class="card-header align-items-center">
                    <h5 class="mb-0">Orders </h5>
                </div>
                <div class="table-responsive">
                    <table class="table mb-0">
                        <thead class="small text-uppercase bg-body text-muted">
                        <tr>
                            <th>Code</th>
                            <th>Customer</th>
                            <th>Staff</th>
                            <th>Create Date</th>
                            <th>Status</th>
                            <th>Total</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="o" items="${orders}">
                            <tr class="align-middle">
                                <td>
                                    <a href="/orders/detail?orderId=${o.id}">${o.code}</a>
                                </td>
                                <td>
                                    <a href="/customer/detail?customerId=${o.customer.id}">${o.customer.firstName} ${o.customer.middleName} ${o.customer.lastName}</a>
                                </td>
                                <td>
                                    <a href="/staff/detail?staffId=${o.staff.id}">${o.staff.firstName} ${o.staff.middleName} ${o.staff.lastName}</a>
                                </td>
                                <td>
                                    <fmt:formatDate value="${o.dateCreate}" var="dateCreate" pattern="yyyy-MM-dd"/>
                                    ${dateCreate}
                                </td>
                                <td>
                                    <c:if test="${o.status == 1}">
                                        <span class="badge bg-success">Complete</span>
                                    </c:if>
                                    <c:if test="${o.status == 0}">
                                        <span class="badge bg-warning">Processing</span>
                                    </c:if>

                                </td>
                                <td>
                                    <fmt:formatNumber var="price" value="${o.getTotalPrice()}" pattern="###,###"/>
                                    ${price} đ
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>
</div>
