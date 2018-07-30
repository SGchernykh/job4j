<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" pageEncoding="utf-8" session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Add Task</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
    <form action="./create" method="post"  enctype="multipart/form-data">
        <div class="form-group" style="align-content: center">
            <div class="col-md-10" style="padding-top: 50px">

                <div class="row" style="text-align: center">
                    <div class="col-md-10">
                        <label for="title">Заголовок</label>
                        <input type="text" class="form-control" id="title" name="title" required autofocus>
                    </div>
                </div>
                <div class="row" style="text-align: center">
                    <div class="col-md-10">
                        <label for="description" style="font-weight: bold">Описание</label>
                        <textarea class="form-control" id="description" name="description" required></textarea>
                    </div>
                </div>
                <div class="form-group" style="margin-top: 20px">
                    <input type="submit" class="btn btn-info"/>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>