<%@ page language="java" contentType="text/html;charset=UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Main Page</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" charset="utf-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" charset="utf-8"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" charset="utf-8"></script>
    <script>
        function find() {
            var text = document.getElementById("text").value;
            var result = "";
            $.ajax({
                url: './desc',
                data: {text: text},
                success: function (data) {
                    result = fillRow(data);
                    document.getElementById("table_body").innerHTML += result;
                }
            });
        }
        function fillRow(data) {
            $("#table_body").empty();
            var row = "";
            $.each(data, function (i, task) {
                row += "<tr><td class='table-td'>" + task + "</td></tr>";
        })
            return row;
        };


    </script>

</head>
<body>
<div class="container">
    <div style="margin-top: 30px">
        <label for="text">Find</label>
        <input type="text" class="form-control" id="text" name="text" required autofocus>
        <input type="submit" class="btn btn-info" style="width: 40%" onclick="find()" value="find"/>
        <a href="./" class="btn btn-info" style="width: 40%; float: right">Return</a>
    </div>
</div>
<div class="container" style="margin-top: 30px">
    <div class="col-md-12">
        <table class="table">
            <thead>
            <tr>
                <th class="table-td">Description</th>
            </tr>
            </thead>
            <tbody id="table_body"></tbody>
        </table>
    </div>
</div>
</body>
</html>