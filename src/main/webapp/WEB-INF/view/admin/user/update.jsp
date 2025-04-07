<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>LapStore - Admin</title>
    <link href="/css/styles.css" rel="stylesheet"/>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        $(document).ready(() => {
            const avatarFile = $("#avatarFile");
            avatarFile.change(function (e) {
                const imgUrl = URL.createObjectURL(e.target.files[0]);
                $("#avatarPreview").attr("src", imgUrl);
                $("#avatarPreview").css({"display": "block"});
            });
        });
    </script>
</head>
<body class="sb-nav-fixed">
<jsp:include page="../layout/header.jsp"/>
<div id="layoutSidenav">
    <jsp:include page="../layout/sidebar.jsp"/>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Quản lý User</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                    <li class="breadcrumb-item"><a href="/admin/user">User</a></li>
                    <li class="breadcrumb-item active">Cập nhật</li>
                </ol>
                <div class="my-5">
                    <div class="row">
                        <div class="col-md-6 col-12 mx-auto">
                            <h3>Cập nhật user</h3>
                            <hr/>
                            <form:form method="post" action="/admin/user/update" modelAttribute="newUser"
                                       class="row g-3" enctype="multipart/form-data">
                                <div class="mb-3" style="display: none">
                                    <label for="id" class="form-label">ID:</label>
                                    <form:input type="text" class="form-control" path="id"/>
                                </div>
                                <div>
                                    <c:set var="invalidEmail">
                                        <form:errors path="email" cssClass="invalid-feedback"/>
                                    </c:set>
                                    <label for="email" class="form-label">Email:</label>
                                    <form:input type="email"
                                                class="form-control ${not empty invalidEmail ? 'is-invalid' : ''}"
                                                path="email"/>
                                        ${invalidEmail}
                                </div>
                                <div class="col-md-6">
                                    <c:set var="invalidFullName">
                                        <form:errors path="fullName" cssClass="invalid-feedback"/>
                                    </c:set>
                                    <label for="fullName" class="form-label">Họ tên:</label>
                                    <form:input type="text"
                                                class="form-control ${not empty invalidFullName ? 'is-invalid' : ''}"
                                                path="fullName"/>
                                        ${invalidFullName}
                                </div>
                                <div class="col-md-6">
                                    <c:set var="invalidPhone">
                                        <form:errors path="phone" cssClass="invalid-feedback"/>
                                    </c:set>
                                    <label for="phone" class="form-label">Số điện thoại:</label>
                                    <form:input type="text"
                                                class="form-control ${not empty invalidPhone ? 'is-invalid' : ''}"
                                                path="phone"/>
                                        ${invalidPhone}
                                </div>
                                <div>
                                    <label for="address" class="form-label">Địa chỉ:</label>
                                    <form:input type="text" class="form-control" path="address"/>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label">Role:</label>
                                    <form:select class="form-select" path="role.name">
                                        <form:option value="ADMIN">ADMIN</form:option>
                                        <form:option value="USER">USER</form:option>
                                    </form:select>
                                </div>
                                <div class="col-md-6">
                                    <label for="avatarFile" class="form-label">Avatar:</label>
                                    <input class="form-control" type="file" id="avatarFile" name="userAvatar"
                                           accept=".png, .jpg, .jpeg"/>
                                </div>
                                <div class="col-12 mb-3">
                                    <img style="max-height: 250px; display: none;" alt="avatar preview"
                                         id="avatarPreview"/>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <a href="/admin/user" class="btn btn-success">Quay lại</a>
                                    <button type="submit" class="btn btn-primary">Lưu</button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="../layout/footer.jsp"/>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="/js/scripts.js"></script>
</body>
</html>
