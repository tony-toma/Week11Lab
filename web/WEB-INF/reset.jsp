<%-- 
    Document   : reset
    Created on : Nov 18, 2020, 9:40:24 AM
    Author     : 718707
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <form method="post" action="reset">
            <label>Email Address: </label>
            <input type="email" name="user_email" />
            <br>
            <input type="submit" value="Submit"/>
        </form>
        
        <p>${message}</p>
    </body>
</html>
