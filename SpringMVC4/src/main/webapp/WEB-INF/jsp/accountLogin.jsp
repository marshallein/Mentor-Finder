<%-- 
    Document   : AccountLogin
    Created on : Mar 29, 2021, 2:51:22 AM
    Author     : User
--%>
 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-16">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h3>Login</h3>
        
        Server info: <%= application.getServerInfo() %><br>
        Servlet version: <%= application.getMajorVersion() %>.<%= application.getMinorVersion() %><br>
        JSP version: <%= JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %><br>
        Java version: <%= System.getProperty("java.version") %><br>
        
        ${error}
        
        <form action="${pageContext.request.contextPath}/login" method="post">
            Username:<input type="text" name="userLoginName"> <br> 
            Password:<input type="password" name="userPassword"> <br> 
            <div>
                Chưa có tài khoản? <a href ="${pageContext.request.contextPath}/register">Đăng kí ngay! </a><br>
            </div>
            <input type="submit" value="login" name="loginbutton"> <br> 
            
        </form>
            
       
        
    </body>
</html>