<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12/07/2023
  Time: 11:26 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${not empty message}">
    <p class="alert alert-${status}">${message}</p>
    <%-- Xóa thông báo sau khi hiển thị --%>
    <% request.removeAttribute("message");%>
</c:if>
<div class="col-md-6">
    <div class="mb-3">
        <h5 class="card-title">Customer Detail</h5>
    </div>
</div>
<form class="">
    <div class="">
        <div class="mb-3">
            <label class="form-label fw-bold">Code:</label>
            <input type="text" class="form-control" name="code" value="${customer.code}" maxlength="20"
                   placeholder="Enter Code">
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">First Name:</label>
            <input type="text" class="form-control" name="firstName" maxlength="50" value="${customer.firstName}"
                   placeholder="Enter First Name">
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Middle Name:</label>
            <input type="text" class="form-control" name="middleName" value="${customer.middleName}"
                   placeholder="Enter Middle Name">
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Last Name:</label>
            <input type="text" class="form-control" name="lastName" value="${customer.lastName}"
                   placeholder="Enter Last Name">
        </div>
        <div class="mb-3">
            <fmt:formatDate var="date" value="${customer.dateBirth}" pattern="yyyy-MM-dd"/>
            <label class="form-label fw-bold">Date Birth:</label>
            <input type="date" class="form-control" name="dateOfBirth" value="${date}"
                   placeholder="Enter Date Birth">
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Phone Number:</label>
            <input type="text" class="form-control" name="phoneNumber" value="${customer.phoneNumber}"
                   placeholder="Enter Phone Number">
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Address:</label>
            <input type="text" class="form-control" name="address" value="${customer.address}"
                   placeholder="Enter Address">
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">City:</label>
            <input type="text" class="form-control" name="city" value="${customer.city}"
                   placeholder="Enter City">
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Country:</label>
            <input type="text" class="form-control" name="country" value="${customer.country}"
                   placeholder="Enter Country">
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Password:</label>
            <input type="text" class="form-control" name="password" value="${customer.password}"
                   placeholder="Enter Password">
        </div>
    </div>

    <div>
        <button formmethod="post" formaction="/customer/create" class="btn btn-primary m-2"
                <c:if test="${!create}">disabled</c:if> >Add
        </button>
        <button formmethod="post" formaction="/customer/update?customerId=${customer.id}" class="btn btn-warning m-2"
                <c:if test="${create}">disabled</c:if>>
            Update
        </button>
        <button formmethod="post" formaction="/customer/delete?customerId=${customer.id}" class="btn btn-danger m-2"
                <c:if test="${create}">disabled</c:if>>
            Delete
        </button>
        <a href="/customer/detail?customerId=${customer.id}"
           class="btn btn-secondary m-2" <c:if test="${create}">disabled</c:if>>Reset
        </a>
    </div>
</form>
