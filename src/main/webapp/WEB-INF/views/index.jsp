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
                <a class="nav-link" href="<c:url value='/create'/>">Добавить инцидент</a>
            </li>
        </ul>
    </div>
</div>

<div class="container">
    <h3 class="text-center">Список автонарушений</h3>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>#</th>
            <th>Type</th>
            <th>Name</th>
            <th>Text</th>
            <th>Address</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="accident" items="${accidents}">
            <tr>
                <td>
                    <a href="<c:url value='/edit?id=${accident.id}'/>">
                        <i class="fas fa-edit mr-3"></i>
                    </a>
                    <c:out value="${accident.id}"/>
                </td>
                <td><c:out value="${accident.type.name}"/></td>
                <td><c:out value="${accident.name}"/></td>
                <td><c:out value="${accident.text}"/></td>
                <td><c:out value="${accident.address}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
</body>
</html>