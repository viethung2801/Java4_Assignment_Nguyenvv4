<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="">
    <div class="mb-3">
        <h5 class="card-title">Store Detail </h5>
    </div>
</div>
<form class="border" >
    <div class="">
        <div class="mb-3">
            <label class="form-label fw-bold">Code:</label>
            <input type="text" class="form-control" name="code" value="${store.code}" placeholder="Enter Code" >
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Name:</label>
            <input type="text" class="form-control" name="name" value="${store.name}"
                   placeholder="Enter Name" >
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Addres:</label>
            <input type="text" class="form-control" name="address" value="${store.address}"
                   placeholder="Enter Address" >
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">City:</label>
            <input type="text" class="form-control"  name="city" value="${store.city}"
                   placeholder="Enter City" >
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Country:</label>
            <input type="text" class="form-control"  name="country" value="${store.country}"
                   placeholder="Enter Country" >
        </div>
    </div>
    <div>
        <button formmethod="post" formaction="/store/create" class="btn btn-primary m-2"
                <c:if test="${!create}">disabled</c:if> >Add
        </button>
        <button formmethod="post" formaction="/store/update?storeId=${store.id}" class="btn btn-warning m-2"
                <c:if test="${create}">disabled</c:if>>
            Update
        </button>
        <button formmethod="post" formaction="/store/delete?storeId=${store.id}" class="btn btn-danger m-2"
                <c:if test="${create}">disabled</c:if>>
            Delete
        </button>
        <a href="/store/detail?storeId=${store.id}"
                class="btn btn-secondary m-2" <c:if test="${create}">disabled</c:if>>Reset
        </a>
    </div>
</form>
