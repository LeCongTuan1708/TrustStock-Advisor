<%-- 
    Document   : addWatchList
    Created on : Mar 2, 2026, 9:47:50 AM
    Author     : quyt2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tạo Thư Mục</title>
    </head>
    <body>
        <h2>Tạo Thư Mục Theo Dõi Mới</h2>

        <form action="MainController" method="POST">
            <%-- Chìa khóa báo cho MainController biết là tao muốn lưu data --%>
            <input type="hidden" name="action" value="add-watchlist">

            <label>Tên Thư Mục Mới:</label>
            <%-- Đặt tên biến là txtWatchListName để xíu nữa Servlet tóm cổ nó --%>
            <input type="text" name="txtWatchListName" placeholder="Nhập tên thư mục..." required="required">

            <button type="submit">Lưu Thư Mục</button>
        </form>

        <br>
        <a href="MainController?action=watch-list">Quay lại danh sách</a>
    </body>
</html>