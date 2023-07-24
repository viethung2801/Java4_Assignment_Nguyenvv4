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
<div class="container-xl px-4 mt-4">
    <div class="row">
        <div class="col-xl-4">
            <!-- Profile picture card-->
            <div class="card mb-4 mb-xl-0">
                <div class="card-header">Receiver Detail</div>
                <div class="card-body text-center">
                    <!-- Profile picture image-->
                    <img class="img-account-profile rounded-circle mb-2"
                         src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="" width="200px">
                    <form>
                        <div class="mb-3">
                            <label class="form-label">Receiver Name</label>
                            <input type="text" class="form-control" value="${order.receiver}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Receiver Phone Number</label>
                            <input type="text" class="form-control" value="${order.phoneNumber}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Receiver Address</label>
                            <input type="text" class="form-control" value="${order.address}">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-xl-8">
            <!-- Account details card-->
            <div class="card mb-4">
                <div class="card-header">Order Details</div>
                <div class="card-body">
                    <!-- Form Group (username)-->
                    <div class="mb-3">
                        <label class="small mb-1">Order Code</label>
                        <input class="form-control" type="text" value="${order.code}">
                    </div>
                    <form>
                        <!-- Form Row-->
                        <div class=" row gx-3 mb-3">
                            <!-- Form Group (first name)-->
                            <div class="col-md-6">
                                <label class="small mb-1">Create Date</label>
                                <fmt:formatDate value="${order.dateCreate}" var="dateCreate" pattern="yyyy-MM-dd"></fmt:formatDate>
                                <input class="form-control" type="date" value="${dateCreate}">
                            </div>
                            <!-- Form Group (last name)-->
                            <div class="col-md-6">
                                <label class="small mb-1">Pay Date</label>
                                <fmt:formatDate value="${order.datePay}" var="datePay" pattern="yyyy-MM-dd"></fmt:formatDate>
                                <input type="date" class="form-control" value="${datePay}">
                            </div>
                        </div>
                        <!-- Form Row        -->
                        <div class="row gx-3 mb-3">
                            <!-- Form Group (organization name)-->
                            <div class="col-md-6">
                                <label class="small mb-1">Ship Date</label>
                                <fmt:formatDate value="${order.dateShip}" var="dateShip" pattern="yyyy-MM-dd"></fmt:formatDate>
                                <input class="form-control" type="date" value="${dateShip}">
                            </div>
                            <!-- Form Group (location)-->
                            <div class="col-md-6">
                                <label class="small mb-1">Receive Date</label>
                                <fmt:formatDate value="${order.dateReceive}" var="dateReceive" pattern="yyyy-MM-dd"></fmt:formatDate>
                                <input class="form-control" type="date" value="${dateReceive}">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="card mb-4">
                <div class="card-header ">Product List</div>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <th>Code</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                        </thead>
                        <tbody>
                        <c:forEach var="p" items="${order.orderDetailList}">
                            <tr class="">
                                <td class="col-1">
                                    <a href="/product/detail?productDetailId=${p.productDetail.id}">${p.productDetail.product.code}</a>

                                </td>
                                <td class="col-4">
                                    <a href="/product/detail?productDetailId=${p.productDetail.id}">${p.productDetail.product.name}</a>
                                </td>
                                <td class="col-1">
                                        ${p.quanltity}
                                </td>
                                <td class="col-2">
                                    <fmt:formatNumber var="price" value="${p.price}" pattern="###,###"/>
                                        ${p.price} đ
                                </td>
                                <td class="col-2">
                                    <fmt:formatNumber var="total" value="${p.price * p.quanltity}" pattern="###,###"/>
                                        ${total} đ
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
                <fmt:formatNumber var="totalMoney" value="${order.getTotalPrice()}" pattern="###,###"/>
                <div class="card-footer text-end pe-5 "> <span class="fw-bold me-2">Total:</span> ${totalMoney} đ</div>
            </div>
        </div>
    </div>
</div>