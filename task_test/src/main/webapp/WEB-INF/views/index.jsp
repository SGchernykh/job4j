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
        $(loadAdverts());
        function loadAdverts() {
            var result = "";
            $.ajax({
                url: './list',
                success: function (data) {
                    result = fillRow(data);
                    document.getElementById("table_body").innerHTML += result;
                }
            });
        }
        function fillRow(data) {
            var row = "";
            $.each(data, function (i, task) {
                row +=
                    "<tr><td class='table-td'>" + task.title + "<br/></td>" +
                    "<td class='table-td'>" + task.description + "</td>";
                if (task.todo) {
                    row += "<td class='table-td'>" +
                        "<input type='radio' name='contact" + i + "' id='todo' checked='true' onchange='changeBox(" + task.id + ", " + 1 + ")'>";
                } else {
                    row += "<td class='table-td'>" +
                        "<input type='radio' id='todo'  name='contact" + i + "' onchange='changeBox(" + task.id + ", " + 1 + ")'>";
                }
                if (task.progress) {
                    row += "<td class='table-td'>" +
                        "<input type='radio' id='progress'  name='contact" + i + "' checked='true' onchange='changeBox(" + task.id + ", " + 2 + ")'>";
                } else {
                    row += "<td class='table-td'>" +
                        "<input type='radio' id='progress'  name='contact" + i + "' onchange='changeBox(" + task.id + ", " + 2 + ")'>";
                }
                if (task.done) {
                    row += "<td class='table-td'>" +
                        "<input type='radio' id='done' name='contact" + i + "' checked='true' onchange='changeBox(" + task.id + ", " + 3 + ")'>";
                } else {
                    row += "<td class='table-td'>" +
                        "<input type='radio' id='done'  name='contact" + i + "' onchange='changeBox(" + task.id + ", " + 3 + ")'>";
                }

                row += "<td><form action='./edit'> <input type='hidden' name='id' value='" + task.id +
                    "'/><input type='submit' class='btn btn-info' value='edit'></form></td></tr>";
            });
            return row;
        }
        function changeBox(id, rad) {
            $.ajax({
                url: "./update",
                method: "POST",
                data: {"id": id, "radio": rad},
                success: function () {
                }
            });
        }
    </script>

</head>
<body>
<div class="container">
    <div style="margin-top: 30px">
        <a href="./add" class="btn btn-info" style="width: 40%">Add new TODO</a>
        <a href="./find" class="btn btn-info" style="width: 40%; float: right">Find</a>
    </div>
</div>
<div class="container" style="margin-top: 30px">
    <div class="col-md-12">
        <table class="table" id="items">
            <thead>
            <tr>
                <th class="table-td">Title</th>
                <th class="table-td">Description</th>
                <th class="table-td">TODO</th>
                <th class="table-td">In Progress</th>
                <th class="table-td">Done</th>
            </tr>
            </thead>
            <tbody id="table_body"></tbody>
        </table>
    </div>
</div>
</body>
</html>