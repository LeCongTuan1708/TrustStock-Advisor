<%-- 
    Document   : login
    Created on : Jan 31, 2026, 2:54:28 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
        integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
        crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
        <div class="row d-flex justify-content-center align-items-center vh-100">
            <div class="col-md-4">
                <form action="MainController" class="card shadow p-4 bg-white">
                    <label for="" class="form-check-label text-center fs-3"><b>Sign in</b></label>
                    <label for="" class="mt-2 mb-2">Username</label>
                    <input type="text" name="actio" class=" form-control p-2" placeholder="Enter username...">
                    <label for="" class="mt-2 mb-2">Password</label>
                    <input type="password" name="matKhau" class=" form-control p-2" placeholder="Enter password...">
                    <button class="btn btn-dark mt-3" type="submit" name="action" value="login">Sign in
                    </button>
                    <p class="text-center pt-3 m-0 text-black">Chưa có tài khoản?  <a href="signup.jsp" class="fs-5" style=" color: black">Đăng ký</a></p>
                </form>
            </div>
        </div>  
    </div>
    </body>
</html>
