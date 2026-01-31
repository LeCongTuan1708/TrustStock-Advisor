<%-- 
    Document   : signup
    Created on : Jan 31, 2026, 3:01:44 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.min.js"
                integrity="sha384-G/EV+4j2dNv+tEPo3++6LCgdCROaejBqfUeNjuKAiuXbjrxilcCdDz6ZAVfHWe1Y"
        crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
            <div class="row d-flex justify-content-center align-items-center vh-100">
                <div class="col-md-4">
                    <form action="MainController" class="card shadow p-4 bg-white">
                        <label for="" class="form-check-label text-center fs-3"><b>Sign up</b></label>
                        <label for="" class="mt-2 mb-2">Username</label>
                        <input type="text" name="tenDangNhap" class=" form-control p-2" placeholder="Enter username...">
                        <label for="" class="mt-2 mb-2">Password</label>
                        <input type="password" name="matKhau" class=" form-control p-2" placeholder="Enter password...">
                        <label for="" class="mt-2 mb-2">Re-enter password</label>
                        <input type="password" name="matKhau" class=" form-control p-2" placeholder="Enter password...">
                        <label for="" class="mt-2 mb-2">Email</label>
                        <input type="email" name="matKhau" class=" form-control p-2" placeholder="Enter email...">
                        <button class="btn btn-dark mt-3" type="submit" name="action" value="login">Sign up
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
