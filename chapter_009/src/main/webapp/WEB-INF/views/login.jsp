<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Sign In</title>
</head>
<body>
<div class="container">
    <div class="col col-md-4">
        <h2>Sign In</h2>
        <form name="login" action="./login" method="post" style="width: 300px">
            <div class="form-group">
                <label for="login">Login:</label>
                <input type="text" class="form-control" id="login" placeholder="Enter login" name="login" autofocus>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
            </div>
            <button type="submit" class="btn btn-info">Sign In</button>
            <a href="./register" class="btn btn-info" style="float: right">Create account</a>
        </form>
    </div>
</div>
</body>
</html>