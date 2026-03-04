<%-- 
    Document   : addWatchListItem
    Created on : Mar 2, 2026, 3:53:43 PM
    Author     : quyt2
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm Mã Vào Thư Mục</title>
    </head>
    <body>
        <h2>Chọn Mã Cổ Phiếu / Vàng Cần Thêm</h2>

        <form action="MainController" method="POST">
            <input type="hidden" name="action" value="add-item">

            <%-- Giấu cái ID thư mục đi để xíu ném xuống DB --%>
            <input type="hidden" name="watchListId" value="${CURRENT_WATCHLIST_ID}">

            <label>Chọn Mã Tài Sản: </label>
            <select name="assetId" required>
                <option value="" disabled selected>-- Chọn một mã --</option>
                <c:forEach var="asset" items="${LIST_ASSET}">
                    <%-- Value gửi đi là ID, nhưng chữ hiện lên cho user xem là Symbol + Name --%>
                    <option value="${asset.assetId}">[${asset.symbol}] - ${asset.name}</option>
                </c:forEach>
            </select>
            <br><br>

            <button type="submit">Thêm Vào Thư Mục</button>
        </form>

        <br>
        <%-- Quay lại đúng cái thư mục đang xem --%>
        <a href="MainController?action=watchlist-item&selectedId=${CURRENT_WATCHLIST_ID}">Quay Lại Chi Tiết Thư Mục</a>
    </body>
</html>
