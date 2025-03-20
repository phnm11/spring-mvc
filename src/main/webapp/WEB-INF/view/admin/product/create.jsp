<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            const avatarFile = $("#thumb");
            avatarFile.change(function (e) {
                const imgUrl = URL.createObjectURL(e.target.files[0]);
                $("#thumbPreview").attr("src", imgUrl);
                $("#thumbPreview").css({"display": "block"});
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
                <h1 class="mt-4">Manage Products</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                    <li class="breadcrumb-item active">Users</li>
                </ol>
                <div class="my-5">
                    <div class="row">
                        <div class="col-md-6 col-12 mx-auto">
                            <h3>Create a product</h3>
                            <hr/>
                            <form:form method="post" action="/admin/product/create" modelAttribute="newProduct"
                                       class="row g-3" enctype="multipart/form-data">
                                <div class="col-md-6">
                                    <label for="name" class="form-label">Name:</label>
                                    <form:input type="text" class="form-control" path="name"/>
                                </div>
                                <div class="col-md-6">
                                    <label for="price" class="form-label">Price:</label>
                                    <form:input type="number" class="form-control" path="price"/>
                                </div>
                                <div>
                                    <label for="detailDesc" class="form-label">Detail description:</label>
                                    <form:input type="text" class="form-control" path="detailDesc"/>
                                </div>
                                <div class="col-md-6">
                                    <label for="shortDesc" class="form-label">Short description:</label>
                                    <form:input type="text" class="form-control" path="shortDesc"/>
                                </div>
                                <div class="col-md-6">
                                    <label for="quantity" class="form-label">Quantity:</label>
                                    <form:input type="number" class="form-control" path="quantity"/>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label">Factory:</label>
                                    <form:select class="form-select" path="factory">
                                        <form:option value="ADMIN">ADMIN</form:option>
                                        <form:option value="USER">USER</form:option>
                                    </form:select>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label">Target:</label>
                                    <form:select class="form-select" path="target">
                                        <form:option value="ADMIN">ADMIN</form:option>
                                        <form:option value="USER">USER</form:option>
                                    </form:select>
                                </div>
                                <div class="col-md-6">
                                    <label for="thumb" class="form-label">Image:</label>
                                    <input class="form-control" type="file" id="thumb" name="productImg"
                                           accept=".png, .jpg, .jpeg"/>
                                </div>
                                <div class="col-12 mb-3">
                                    <img style="max-height: 250px; display: none;" alt="Product thumbnail"
                                         id="thumbPreview"/>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <a href="/admin/user" class="btn btn-success">Back</a>
                                    <button type="submit" class="btn btn-primary">Submit</button>
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
