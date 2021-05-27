<%-- 
    Document   : register
    Created on : May 27, 2021, 11:00:23 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <title>Register your account</title>
        <meta charset="UTF-16">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/register" method="post">
            Username:<input type="text" name="userLoginName" maxlength="12"> <br> 
            Password:<input type="password" name="userPassword"> <br> 
            Retype your password:<input type="password"> <br> 
            Email:<input type="email" name="userEmail"> <br> 
            Your name:<input type="text" name="userRealName"> <br> 
            DoB:<input type="date" name="userDoB"> <br> 
            Address:<input type="text" name="userAddress"> <br>
            <input type="checkbox" name="agreeCondition"> Tôi đồng ý với điều khoản sử dụng <br>
            <input type="submit" value="submit"><br> 

        </form>
    </body>
</html>