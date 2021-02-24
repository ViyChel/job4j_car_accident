<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <title>Accident</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Автонарушения</title>
</head>
<body>
<div>
    Login as : ${user.username}
</div>
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
            <th>Rules</th>
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
                <td>
                    <c:forEach var="rule" items="${accident.rules}">
                        <c:out value="${rule.name}; "/>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
</body>
</html>