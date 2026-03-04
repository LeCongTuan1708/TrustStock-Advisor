<%-- 
    Document   : editWatchList
    Created on : Mar 1, 2026, 11:19:12 PM
    Author     : quyt2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đổi Tên Thư Mục</title>
    </head>
    <body>
        <h2>Đổi Tên Thư Mục</h2>

        <form action="MainController" method="POST">
            <input type="hidden" name="action" value="edit-watchlist">
            <input type="hidden" name="txtWatchListId" value="${param.id}">

            <label>Tên thư mục mới:</label>
            <input type="text" name="txtNewName" value="${param.oldName}" required="required">

            <button type="submit">Lưu Thay Đổi</button>
        </form>

        <br>
        <a href="MainController?action=watch-list">Quay lại</a>
    </body>
</html>
