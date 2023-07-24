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
<form class="border">
    <div class="">
        <div class="mb-3">
            <label class="form-label fw-bold">Code:</label>
            <input type="text" class="form-control" name="code" value="${productDetail.product.code}"
                   placeholder="Enter Code" required>
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Name:</label>
            <input type="text" class="form-control" name="name" value="${productDetail.product.name}"
                   placeholder="Enter Name" required>
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Category:</label>
            <select class="form-select" name="categoryId">
                <c:forEach var="c" items="${categories}">
                    <option value="${c.id}" <c:if test="${productDetail.category.id == c.id}">selected</c:if> >${c.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label fw-bold">Producer:</label>
            <select class="form-select" name="producerId">
                <c:forEach var="p" items="${producers}">
                    <option value="${p.id}" <c:if test="${productDetail.producer.id == p.id}">selected</c:if> >${p.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Color:</label>
            <select class="form-select" name="colorId">
                <c:forEach var="c" items="${colors}">
                    <option value="${c.id}" <c:if test="${productDetail.color.id == c.id}">selected</c:if> >${c.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Warranty year:</label>
            <input type="number" class="form-control" min="0" step="1" name="warrantyYear" value="${productDetail.warrantyYear}"
                   placeholder="Enter Warranty year" >
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Quantity:</label>
            <input type="number" class="form-control" min="0" step="1" name="quantity" value="${productDetail.quantity}"
                   placeholder="Enter Quantity" required>
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Cost:</label>
            <input type="number" class="form-control" min="0" name="cost" value="${productDetail.cost}"
                   placeholder="Enter Cost" required>
        </div>
        <div class="mb-3">
            <label class="form-label fw-bold">Price:</label>
            <input type="number" class="form-control" min="0" name="price" value="${productDetail.price}"
                   placeholder="Enter Price" required>
        </div>
        <div class="col-12">
            <div class="mb-3">
                <label class="form-label fw-bold">Description:</label>
                <textarea class="form-control" name="description" rows="5"
                          placeholder="Enter Description">${productDetail.description}</textarea>
            </div>
        </div>
        <div>

            <button formmethod="post" formaction="/product/create" class="btn btn-primary m-2"
                    <c:if test="${!create}">disabled</c:if> >Add
            </button>
            <button formmethod="post" formaction="/product/update?productDetailId=${productDetail.id}" class="btn btn-warning m-2"
                    <c:if test="${create}">disabled</c:if>>
                Update
            </button>
            <button formmethod="post" formaction="/product/delete?productDetailId=${productDetail.id}" class="btn btn-danger m-2"
                    <c:if test="${create}">disabled</c:if>>
                Delete
            </button>
            <a href="/product/detail?productDetailId=?${productDetail.id}"
                    class="btn btn-secondary m-2" <c:if test="${create}">disabled</c:if>>Reset
            </a>
        </div>
    </div>
</form>
