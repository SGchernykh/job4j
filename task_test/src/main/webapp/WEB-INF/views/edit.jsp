<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="utf-8" session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Add new Task</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(getData());
        function getData() {
            $.ajax({
                success: function () {
                    document.getElementById("title").value = '${title}';
                    document.getElementById("description").value = '${description}';
                }
            });
        }
    </script>
</head>
<body>
<div class="container">
    <form action="./edit" method="post" enctype="multipart/form-data">
        <div class="form-group" style="align-content: center">
            <div class="col-md-10" style="padding-top: 50px">
                <div class="row" style="text-align: center">
                    <div class="col-md-10">
                        <input type="hidden" name="id" id='id' value="${id}">
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
                <div>
                    <p><input name="radio" type="radio" value="1" checked>TODO</p>
                    <p><input name="radio" type="radio" value="2">In Progress</p>
                    <p><input name="radio" type="radio" value="3">Done</p>
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