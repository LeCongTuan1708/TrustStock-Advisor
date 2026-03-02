<%-- 
    Document   : careNoteList
    Created on : Mar 2, 2026, 5:13:00 PM
    Author     : quyt2
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nhật Ký Đầu Tư</title>
    </head>
    <body>
        <h2>Danh sách Nhật Ký Đầu Tư (CareNote)</h2>

        <table border="1">
            <thead>
                <tr>
                    <th>Mã Tài Sản</th>
                    <th>Tiêu đề</th>
                    <th>Nội dung</th>
                    <th>Ngày viết</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${empty LIST_CARENOTE}">
                    <tr>
                        <td colspan="5" style="text-align: center; color: gray;">
                            M chưa có dòng nhật ký nào. Bấm tạo mới đi!
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="item" items="${LIST_CARENOTE}">
                        <tr>
                            <%-- LƯU Ý: Chỗ này t đang giả sử m dùng Composition (item.asset.symbol) 
                                 giống bên WatchListItem, nếu m xài biến khác thì tự sửa lại nha --%>
                            <td><strong>${item.asset.symbol}</strong></td>
                            <td>${item.title}</td>
                            <td>${item.content}</td>
                            <td>${item.createdAt}</td>
                            <td>
                                <%-- Nút Sửa --%>
                                <a href="MainController?action=show-edit-care-note&noteId=${item.noteId}">Sửa</a> |

                                <%-- Nút Xóa --%>
                                <a href="MainController?action=remove-care-note&noteId=${item.noteId}" 
                                   onclick="return confirm('Bạn có chắc chắn muốn xóa dòng nhật ký này không?');"
                                   style="color: red;">Xóa</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>

    <br>
    <a href="MainController?action=show-add-care-note">
        <button>+ Viết Nhật Ký Mới</button>
    </a>
    <a href="MainController?action=user-dash-board">
        <button>Về trang chủ</button>
    </a>
</body>
</html>
