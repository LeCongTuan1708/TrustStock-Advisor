<%-- 
    Document   : editCareNote
    Created on : Mar 2, 2026, 6:02:47 PM
    Author     : quyt2
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sửa Nhật Ký</title>
    </head>
    <body>
        <h2>Sửa Nhật Ký Đầu Tư</h2>

        <form action="MainController" method="POST">
            <input type="hidden" name="action" value="edit-care-note">
            
            <input type="hidden" name="noteId" value="${CARE_NOTE.noteId}">

            <label>Chọn Mã Tài Sản: </label>
            <select name="assetId" required>
                <c:forEach var="asset" items="${LIST_ASSET}">
                    <option value="${asset.assetId}" 
                            <c:if test="${asset.assetId == CARE_NOTE.assetId}">selected</c:if> >
                        [${asset.symbol}] - ${asset.name}
                    </option>
                </c:forEach>
            </select>
            <br><br>

            <label>Tiêu đề: </label><br>
            <input type="text" name="title" value="${CARE_NOTE.title}" required style="width: 300px;">
            <br><br>

            <label>Nội dung: </label><br>
            <textarea name="content" rows="5" cols="40" required>${CARE_NOTE.content}</textarea>
            <br><br>

            <button type="submit">Cập Nhật</button>
        </form>

        <br>
        <a href="MainController?action=care-note-list">Hủy bỏ</a>
    </body>
</html>