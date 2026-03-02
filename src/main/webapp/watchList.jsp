<%-- 
    Document   : watchList
    Created on : Mar 1, 2026, 10:13:05 PM
    Author     : quyt2
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WatchList của tui</title>
    </head>
    <body>
        <h2>Danh sách WatchList</h2>


        <table border="1">
            <thead>
                <tr>
                    <th>Tên Thư Mục</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${LIST_WATCHLIST}">
                    <tr>
                        <td>${item.name}</td>
                        <td>
                            <a href="MainController?action=watchlist-item&selectedId=${item.watchListId}">Xem chi tiết</a> |
                            <a href="MainController?action=show-edit-watchlist&id=${item.watchListId}&oldName=${item.name}">Sửa tên</a> |
                            <a href="MainController?action=remove-watchlist&watchListId=${item.watchListId}" 
                               onclick="return confirm('Cảnh báo: Xóa thư mục này là mất sạch cổ phiếu bên trong. Bạn có chắc không?');"
                               style="color: red;">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="MainController?action=show-add-watchlist">
            <button>+ Tạo Thư Mục Mới</button>
        </a>
        <a href="MainController?action=user-dash-board">
            <button>về trang chủ</button>
        </a>
    </body>
</html>