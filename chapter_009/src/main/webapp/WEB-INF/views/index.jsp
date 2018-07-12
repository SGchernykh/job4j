<%@ page language="java" contentType="text/html;charset=UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            $.each(data, function (i, saleOrder) {
                row += "<tr>" +
                    "<td class='table-td'><img src='./image/" + saleOrder.photo.id + "' alt='Not Photo'" +
                    " style='display: block; width: 100%; margin: 0 auto'></td>" +
                    "<td class='size'>" + saleOrder.title + "<br/>" +
                    "Продавец " + saleOrder.author.name + "<br/>" +
                    "Марка: " + saleOrder.car.brand.name + "<br/>Модель: " + saleOrder.car.model.name + "<br/>Кузов: "
                    + saleOrder.car.carBody.name + "<br/></td>" +
                    "<td class='table-td'>" + saleOrder.city.name + "</td>" +
                    "<td class='table-td'>" + new Date(saleOrder.created).toDateString() + "</td>" +
                    "<td class='table-td'>" + saleOrder.price + "</td>";
                if (saleOrder.sale) {
                    row += "<td class='table-td'>" +
                        "<input type='checkbox' id='sale' checked='true' onchange='changeBox(" + saleOrder.id + ")'";
                } else {
                    row += "<td class='table-td'>" +
                        "<input type='checkbox' id='sale' onchange='changeBox(" + saleOrder.id + ")'";
                }
                row += "'></td></tr>";
            });
            return row;
        }
        function changeBox(id) {
            var checked = $("#sale").is(":checked");
            $.ajax({
                url: "./update",
                method: "POST",
                data: {"id": id},
                success: function () {
                }
            });
        }
    </script>

</head>
<carBody>
<div class="container">
    <div style="margin-top: 30px">
        <a href="./add" class="btn btn-info" style="width: 100%">Add new Advert</a>
    </div>
</div>
<div class="container" style="margin-top: 30px">
    <div class="col-md-12">
        <table class="table" id="items">
            <thead>
            <tr>
                <th class="table-td">Фото</th>
                <th class="table-td">Описание</th>
                <th class="table-td">Город</th>
                <th class="table-td">Создано</th>
                <th class="table-td">Цена</th>
                <th class="table-td">Состояние</th>
            </tr>
            </thead>
            <tbody id="table_body"></tbody>
        </table>
    </div>
</div>
</carBody>
</html>