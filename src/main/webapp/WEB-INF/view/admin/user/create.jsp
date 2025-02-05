<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Create User</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    </head>
    <body>
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-6 col-12 mx-auto">
                    <h1>Create a user</h1>
                    <hr />
                    <form:form method="post" action="/admin/user/create" modelAttribute="newUser">
                        <div class="mb-3">
                            <label for="email" class="form-label">Email:</label>
                            <form:input type="email" class="form-control" path="email" />
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password:</label>
                            <form:input type="password" class="form-control" path="password" />
                        </div>
                        <div class="mb-3">
                            <label for="phoneNumber" class="form-label">Phone number:</label>
                            <form:input type="text" class="form-control" path="phone" />
                        </div>
                        <div class="mb-3">
                            <label for="fullName" class="form-label">Full name:</label>
                            <form:input type="text" class="form-control" path="fullName" />
                        </div>
                        <div class="mb-3">
                            <label for="address" class="form-label">Address:</label>
                            <form:input type="text" class="form-control" path="address" />
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
