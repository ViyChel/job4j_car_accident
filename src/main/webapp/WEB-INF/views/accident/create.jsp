<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <title>Accident</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <title>Автонарушения</title>
</head>
<body>
<div class="container">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/'/>">Главная</a>
            </li>
        </ul>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="card" style="width: 50%">
            <div class="card-header">
                Новое нарушение
            </div>
            <div class="card-body">
                <form action="<c:url value='/save'/>" method='POST'>
                    <div class="form-group">
                        <label for="name">Название</label>
                        <input type="text" class="form-control" id="name" name="name" value="" required>
                    </div>
                    <div class="form-group">
                        <label for="type.id">Тип</label>
                        <select name="type.id" id="type.id" required>
                            <c:forEach var="type" items="${types}" >
                                <option value="${type.id}">${type.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="rIds">Статьи</label>
                        <select name="rIds" id="rIds" multiple >
                            <c:forEach var="rule" items="${rules}" >
                                <option value="${rule.id}">${rule.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="description">Описание</label>
                        <input type="text" class="form-control" id="description" name="text" value="" required>
                    </div>
                    <div class="form-group">
                        <label for="address">Адрес</label>
                        <input type="text" class="form-control" id="address" name="address" value="" required>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">Сохранить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>