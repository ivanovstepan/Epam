<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
               <div class="control-group pull-right">

                        <div class="btn-group">
                             <form  action="/controller" method="post">
                                <input type="hidden" name="command" value="locale" >
                                <input type="hidden" name="currentUrl" value="/jsp/try.jsp" />
                                <input type="hidden" name="locale" value="ru" />
                                <input type="image" src="../img/russia_640.png" style="width: 40px;height: 30px">
                             </form>
    </div>
                        <div class="btn-group">
                             <form   action="/controller" method="post">
                                <input type="hidden" name="command" value="locale" >
                                <input type="hidden" name="currentUrl" value="/jsp/try.jsp" />
                                <input type="hidden" name="locale" value="en" />
                                <input type="image" src="../img/united_kingdom_640.png" style="width: 40px;height: 30px">
                             </form>
</div>
                        <div class="btn-group">
                        <form >
                             <a class="btn btn-danger"><fmt:message bundle="${loc}" key="login.logout"/> </a>
                        </form>
                            </div>

    </div>
</div>
    </div>

<div class="container">

    <form  class="pre-scrollable" name="goodListForm" method="POST" action="/controller">
        <input type="hidden" name="command" value="addtobasket" >
        <table class="table  table-striped">
            <tr>
                <td ><fmt:message bundle="${loc}" key="product.list.name"/> </td>
                <td ><fmt:message bundle="${loc}" key="product.list.price"/></td>
                <td ><fmt:message bundle="${loc}" key="product.list.count"/></td>
                <td ><fmt:message bundle="${loc}" key="product.list.amount"/></td>
                <td></td>>

            </tr>
            <c:forEach var="good" items="${product}">
                <tr >
                    <td >${good.getDescription()}</td>
                    <td >${good.getPrice()}</td>
                    <td >${good.getAmount()}</td>
                    <td ><input type="text"  name="count" value=""></td>
                    <td><input type="hidden" name="product" value="${good.getId_product()}"></td>
                </tr>
            </c:forEach>
        </table>
        <input class="btn btn-primary" type="submit" value="<fmt:message bundle="${loc}" key="product.list.submit"/>">
    </form>
</div>
<footer>
    <p>&copy; Company 2014</p>
</footer>
</body>
</html>
