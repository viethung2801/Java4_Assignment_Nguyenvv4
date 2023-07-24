<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12/07/2023
  Time: 11:26 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<c:if test="${not empty message}">
    <p class="alert alert-${status}">${message}</p>
    <%-- Xóa thông báo sau khi hiển thị --%>
    <% request.removeAttribute("message");%>
</c:if>
<div class="col-md-6">
    <div class="mb-3">
        <h5 class="card-title">Staff Detail</h5>
    </div>
</div>
<form class="">
    <div class="">
        <div class="mb-3">
            <label class="form-label fw-bold">Code:</label>
            <input type="text" class="form-control" name="code" value="${staff.code}" maxlength="20"
                   placeholder="Enter Code">
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">First Name:</label>
            <input type="text" class="form-control" name="firstName" maxlength="50" value="${staff.firstName}"
                   placeholder="Enter Video Views">
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Middle Name:</label>
            <input type="text" class="form-control" name="middleName" value="${staff.middleName}"
                   placeholder="Enter Video Views">
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Last Name:</label>
            <input type="text" class="form-control" name="lastName" value="${staff.lastName}"
                   placeholder="Enter Video Views">
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Gender:</label>
            <div class="form-check form-check-inline">
                <label class="form-check-label">
                    <input class="form-check-input" type="radio" name="gender" value="Male" <c:if test="${staff.gender == 'Male'}">checked</c:if> >Male
                </label>
            </div>
            <div class="form-check form-check-inline">
                <label class="form-check-label">
                    <input class="form-check-input" type="radio" name="gender"  value="Female" <c:if test="${staff.gender == 'Female'}">checked</c:if>>Female
                </label>
            </div>
        </div>

        <div class="mb-3">
            <fmt:formatDate var="date" value="${staff.dateBirth}" pattern="yyyy-MM-dd" />
            <label class="form-label fw-bold">Date Birth:</label>
            <input type="date" class="form-control" name="dateOfBirth" value="${date}"
                   placeholder="Enter Date Birth" >
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Phone Number:</label>
            <input type="text" class="form-control" name="phoneNumber" value="${staff.phoneNumber}"
                   placeholder="Enter Phone Number">
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Password:</label>
            <input type="text" class="form-control" name="password" value="${staff.password}"
                   placeholder="Enter Password">
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Position:</label>
            <select class="form-select" name="positionId">
                <c:forEach var="o" items="${positions}">
                    <option value="${o.id}" <c:if test="${o.id == staff.position.id}">selected</c:if> >${o.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Store:</label>
            <select class="form-select" name="storeId">
                <c:forEach var="o" items="${stores}">
                    <option value="${o.id}" <c:if test="${o.id == staff.store.id}">selected</c:if> >${o.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Status:</label>
            <div class="form-check form-check-inline">
                <label class="form-check-label">
                    <input class="form-check-input" type="radio" name="status" value="1" <c:if test="${staff.status == 1}">checked</c:if> >Working
                </label>
            </div>
            <div class="form-check form-check-inline">
                <label class="form-check-label">
                    <input class="form-check-input" type="radio" name="status"  value="0" <c:if test="${staff.status == 0}">checked</c:if>>Worked
                </label>
            </div>
        </div>
        <div class="col-12">
            <div class="mb-3">
                <label class="form-label fw-bold">Address:</label>
                <textarea class="form-control" name="address" rows="5"
                          placeholder="Enter Video Description">${staff.address}</textarea>
            </div>
        </div>
    </div>

    <div>
        <button formmethod="post" formaction="/staff/create" class="btn btn-primary m-2"
                <c:if test="${!create}">disabled</c:if> >Add
        </button>
        <button formmethod="post" formaction="/staff/update?staffId=${staff.id}" class="btn btn-warning m-2"
                <c:if test="${create}">disabled</c:if>>
            Update
        </button>
        <button formmethod="post" formaction="/staff/delete?staffId=${staff.id}" class="btn btn-danger m-2"
                <c:if test="${create}">disabled</c:if>>
            Delete
        </button>
        <a formaction="/staff/detail?staffId=${staff.id}"
                class="btn btn-secondary m-2" <c:if test="${create}">disabled</c:if>>Reset
        </a>
    </div>
</form>
