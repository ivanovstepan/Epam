<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="../css/stylesheet.css "/>
    <fmt:setLocale value="${locale}" />
    <fmt:setBundle basename="localization.local" var="loc" />
    <link href="../bootstrap/css/bot.css" rel="stylesheet" media="screen">
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
                    <input type="hidden" name="currentUrl" value="/jsp/error.jsp" />
                    <input type="hidden" name="locale" value="ru" />
                    <input type="image" src="../img/russia_640.png" style="width: 40px;height: 30px">
                </form>
            </div>
            <div class="btn-group">
                <form   action="/controller" method="post">
                    <input type="hidden" name="command" value="locale" >
                    <input type="hidden" name="currentUrl" value="/jsp/error.jsp" />
                    <input type="hidden" name="locale" value="en" />
                    <input type="image" src="../img/united_kingdom_640.png" style="width: 40px;height: 30px">
                </form>
            </div>
            <div class="btn-group">
                <form >
                    <input type="hidden" name="command" value="logout">
                    <input type="submit" class="btn btn-default" value="<fmt:message bundle="${loc}" key="login.logout"/>"/>
                </form>
            </div>

        </div>
    </div>
</div>

    <div class="row">
        <div class="col-md-6">
            <h2><fmt:message bundle="${loc}" key="admin.block"/></h2>
            <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
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
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <input  type="submit" class="btn btn-default"  value="<fmt:message bundle="${loc}" key="user.list.block"/>">
            </form>

        </div>
        <div class="col-md-6">
            <h2><fmt:message bundle="${loc}" key="admin.add"/></h2>
            <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
                <form  name="adminProductForm" method="POST" action="/controller">
                    <input type="hidden" name="command" value="addproduct" >
                    <table class="table">
                        <tr>
                            <td >Описание</td>
                            <td >Цена</td>
                            <td >Коступное количество</td>
                        </tr>
                        <tr >
                            <td ><input type="text" name="description" placeholder="description"></td>
                            <td ><input type="text" name="price" placeholder="price"></td>
                            <td ><input type="text" name="amount" placeholder="amount"></td>
                            <td ></td>
                        </tr>
                    </table>
                    <input type="submit" class="btn btn-default" value="Добавить"/>
                </form>
             </div>
        </div>

</body>
</html>
