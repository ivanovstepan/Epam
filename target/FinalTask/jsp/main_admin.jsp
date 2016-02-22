
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="js/jquery.js"></script>
<script src="js/application.js"></script>
<link type="text/css" rel="stylesheet" href="../css/stylesheet.css "/>




<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="../css/stylesheet.css "/>
    <fmt:setLocale value="${locale}" />
    <fmt:setBundle basename="localization.local" var="loc" />
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <title><fmt:message bundle="${loc}" key="title.title"/></title>

</head>
<body>
<div class="navbar">
    <div class="container ">
        <div class="control-group pull-left">
            <h2><fmt:message bundle="${loc}" key="title.name"/></h2>
        </div>

        <div class="nav-collapse">
            <ul class="nav navbar-nav">
                <li><a href="#about"><fmt:message bundle="${loc}" key="link.buy"/></a></li>
                <li><a href="#about"><fmt:message bundle="${loc}" key="link.about"/></a></li>
                <li><a href="#contact"><fmt:message bundle="${loc}" key="link.basket"/></a></li>
            </ul>
        </div>

        <div class="control-group pull-right">

            <div class="btn-group">
                <form  action="/controller" method="post">
                    <input type="hidden" name="command" value="locale" >
                    <input type="hidden" name="currentUrl" value="/jsp/main_admin.jsp" />
                    <input type="hidden" name="locale" value="ru" />
                    <input type="image" src="../img/russia_640.png" style="width: 40px;height: 30px">
                </form>
            </div>
            <div class="btn-group">
                <form   action="/controller" method="post">
                    <input type="hidden" name="command" value="locale" >
                    <input type="hidden" name="currentUrl" value="/jsp/main_admin.jsp" />
                    <input type="hidden" name="locale" value="en" />
                    <input type="image" src="../img/united_kingdom_640.png" style="width: 40px;height: 30px">
                </form>
            </div>
            <div class="btn-group">
                <form >
                    <input type="hidden" name="command" value="logout">
                    <input type="submit" class="btn btn-danger" value="<fmt:message bundle="${loc}" key="login.logout"/>"/>
                </form>
            </div>

        </div>
    </div>
</div>


<div class="container">
    <form  class="pre-scrollable" name="userListForm" method="POST" action="/controller">
        <input type="hidden" name="command" value="blockuser" >
        <table class="table  table-striped">
            <tr>
                <td ><fmt:message bundle="${loc}" key="user.list.name"/> </td>
                <td ><fmt:message bundle="${loc}" key="user.list.surname"/></td>
                <td ><fmt:message bundle="${loc}" key="user.list.email"/></td>
                <td></td>>

            </tr>
            <c:forEach var="user" items="${users}">
                <tr >
                    <td >${user.getName()}</td>
                    <td >${user.getSurname()}</td>
                    <td >${user.getEmail()}</td>
                    <td >
                        <input type="hidden" name="block" value="${user.getId()}" >
                        <input type="text" name="description" placeholder="<fmt:message bundle="${loc}" key="user.list.reason"/>">
                        <input  type="submit" name="blockButton" value="<fmt:message bundle="${loc}" key="user.list.block"/>">
                    </td>

                </tr>
            </c:forEach>
        </table>
    </form>
</div>
<footer>
    <p>&copy; Company 2014</p>
</footer>
</body>
</html>