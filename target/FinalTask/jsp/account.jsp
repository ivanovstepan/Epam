<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Web Store</title>
    <link rel="stylesheet" href="../css/style.cs.css" type="text/css" >
</head>

<body >
Добро пожаловать, дорогой ${user.getName()}
       <form action="controller">
        <ul class="product" >
            <li>
                <a><img src="../img/usa.png" alt="English" /></a>
                <span>Ручка</span>
                <span>
                    <input  type="hidden" name="command" value="addtobasket">
                    <button type="submit" >В карзину</button>
                </span>
            </li>
            <li>
                <a><img src="../img/india.png" alt="Russian" /></a>
                <span>Тетрадь</span>
            </li>
        </ul>
           </form>>


</body>

</html>
