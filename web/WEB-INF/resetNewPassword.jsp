<%-- 
    Document   : resetNewPassword
    Created on : Nov 21, 2020, 2:07:36 PM
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
        <h1>Enter a new password</h1>
        <form method="post" action="reset">
            <input type="password" name="new_password"/>
            <br>
            <input type="submit" name="action" value="Submit"/>
        </form>
    </body>
</html>
