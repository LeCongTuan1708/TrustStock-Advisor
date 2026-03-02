<%-- 
    Document   : addCareNote
    Created on : Mar 2, 2026, 5:33:24 PM
    Author     : quyt2
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm Nhật Ký Đầu Tư</title>
    </head>
    <body>
        <h2>Viết Nhật Ký Đầu Tư Mới (Care Note)</h2>

        <%-- Form này sẽ chĩa súng về thằng AddCareNoteController --%>
        <form action="MainController" method="POST">
            <input type="hidden" name="action" value="add-care-note">

            <label>Chọn Mã Tài Sản (Ticker): </label>
            <select name="assetId" required>
                <option value="" disabled selected>-- Chọn một mã --</option>
                <c:forEach var="asset" items="${LIST_ASSET}">
                    <option value="${asset.assetId}">[${asset.symbol}] - ${asset.name}</option>
                </c:forEach>
            </select>
            <br><br>

            <label>Tiêu đề: </label><br>
            <input type="text" name="title" required style="width: 300px;">
            <br><br>

            <label>Nội dung: </label><br>
            <textarea name="content" rows="5" cols="40" required></textarea>
            <br><br>

            <button type="submit">Lưu Nhật Ký</button>
        </form>

        <br>
        <a href="MainController?action=care-note-list">
            <button>Quay Lại Danh Sách Nhật Ký</button>
        </a>
    </body>
</html>