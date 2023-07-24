<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12/07/2023
  Time: 11:22 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row align-items-center">
    <div class="col-md-6">
        <div class="mb-3">
            <h5 class="card-title">Store List </h5>
        </div>
    </div>
    <div class="col-md-6">
        <div class="d-flex flex-wrap align-items-center justify-content-end gap-2 mb-3">
            <div>
                <a href="/store/create" class="btn btn-primary">
                    <i class="bx bx-plus me-1"></i> Add New
                </a>
            </div>

        </div>
    </div>
</div>
<div class="row">
    <div class="table-responsive">
        <table class="table project-list-table table-nowrap align-middle table-borderless">
            <thead>
            <tr>
                <th scope="col">Code</th>
                <th scope="col">Name</th>
                <th scope="col">Address</th>
                <th scope="col">City</th>
                <th scope="col">Country</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="o" items="${stores}">
                <tr>
                    <td>${o.code}</td>
                    <td>${o.name}</td>
                    <td>${o.address}</td>
                    <td>${o.city}</td>
                    <td>${o.country}</td>
                    <td>
                        <a href="/store/detail?storeId=${o.id}" class="px-2 text-primary"><i
                                class="bi bi-pencil"></i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
