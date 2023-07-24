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
<div class="col-md-6">
    <div class="mb-3">
        <h5 class="card-title">Position Detail </h5>
    </div>
</div>
<form class=" border" >
    <div class="">
        <div class="mb-3">
            <label class="form-label">Code:</label>
            <input type="text" class="form-control" name="code" value="${position.code}" placeholder="Enter Code">
        </div>
        <div class="mb-3">
            <label class="form-label">Name:</label>
            <input type="text" class="form-control" name="name" value="${position.name}"
                   placeholder="Enter Name">
        </div>
    </div>
    <div>
        <button formmethod="post" formaction="/position/create" class="btn btn-primary m-2"
                <c:if test="${!create}">disabled</c:if> >Add
        </button>
        <button formmethod="post" formaction="/position/update?positionId=${position.id}" class="btn btn-warning m-2"
                <c:if test="${create}">disabled</c:if>>
            Update
        </button>
        <button formmethod="post" formaction="/position/delete?positionId=${position.id}" class="btn btn-danger m-2"
                <c:if test="${create}">disabled</c:if>>
            Delete
        </button>
        <a href="/position/detail?positionId=${position.id}"
           class="btn btn-secondary m-2" <c:if test="${create}">disabled</c:if>>Reset
        </a>
    </div>
</form>
