<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 21/07/2023
  Time: 2:32 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<String> labels = (List<String>) request.getAttribute("labels");
    List<Integer> dataPoints = (ArrayList<Integer>) request.getAttribute("values");
    // Chuyển danh sách thành một chuỗi JSON để sử dụng trong JavaScript
    String dataPointsJson = new Gson().toJson(dataPoints);
    String labelsJson = new Gson().toJson(labels);

%>
<div>
    <form action="/thong-ke" class="row" method="get">
        <div class="col-4">
            <div class="form-check-inline">
                <label class="form-check-label me-3">Từ:</label>
                <input class="" type="date" name="fromDate" value="${fromDate}">
            </div>
        </div>
        <div class="col-4">
            <div class="form-check-inline">
                <label class="form-check-label me-3">Đến:</label>
                <input class="" type="date" name="toDate" value="${toDate}">
            </div>
        </div>
        <div class="col-4">
            <button class="btn btn-primary">Search</button>
        </div>
    </form>
</div>
<canvas id="myChart"></canvas>
<script>
    // Lấy dữ liệu biểu đồ từ biến đã chuẩn bị trước đó
    var labelList = <%= labelsJson %>;
    var dataPoints = <%= dataPointsJson %>;

    // Vẽ biểu đồ cột
    var ctx = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labelList,
            datasets: [{
                label: 'Doanh thu',
                data: dataPoints,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>
