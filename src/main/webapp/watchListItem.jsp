<%-- 
    Document   : watchListItem
    Created on : Mar 2, 2026, 10:24:20 AM
    Author     : quyt2
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chi Tiết Thư Mục</title>
    </head>
    <body>
        <h2>Danh sách Tài Sản trong Thư Mục</h2>

        <table border="1">
            <thead>
                <tr>
                    <th>Loại</th>
                    <th>Mã (Symbol)</th>
                    <th>Tên Tài Sản</th>
                    <th>Ngày Thêm</th>
                    <th>Hành Động</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty LIST_WATCHLIST_ITEM}">
                        <tr>
                            <td colspan="5" style="text-align: center; color: red;">
                                Thư mục này chưa có tài sản nào!
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="item" items="${LIST_WATCHLIST_ITEM}">
                            <tr>
                                <td>${item.asset.type}</td> 
                                <td><strong>${item.asset.symbol}</strong></td> 
                                <td>${item.asset.name}</td> 

                                <td>${item.addedAt}</td> 
                                <td>
                                    <a href="MainController?action=remove-item&itemId=${item.itemId}&watchListId=${CURRENT_WATCHLIST_ID}" 
                                       onclick="return confirm('Bạn có chắc chắn muốn xóa mã này khỏi thư mục không?');">Xóa</a>                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
        <a href="MainController?action=show-add-item&watchListId=${CURRENT_WATCHLIST_ID}">
            <button>+ Thêm Mã Tài Sản</button>
        </a>
        <br>
        <a href="MainController?action=watch-list">
            <button>Quay Lại Danh Sách Thư Mục</button>
        </a>
        
        

    </body>
</html>