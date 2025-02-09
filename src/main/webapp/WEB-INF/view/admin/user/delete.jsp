<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Delete User ${id}</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    </head>
    <body>
        <div class="container mt-5">
            <div class="row">
                <div class="col-12 mx-auto">
                    <div class="d-flex justify-content-between">
                        <h3>Delete user with id: ${id}</h3>
                    </div>
                    <hr />
                    <div class="alert alert-danger" role="alert">Are you sure to delete this user?</div>
                    <div class="d-flex justify-content-between">
                        <a href="/admin/user" class="btn btn-success">Back</a>
                        <form:form action="/admin/user/delete" method="post" modelAttribute="newUser">
                            <div class="mb-3" style="display: none">
                                <label for="id" class="form-label">ID:</label>
                                <form:input type="text" class="form-control" value="${id}" path="id" />
                            </div>
                            <button class="btn btn-danger">Confirm</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
