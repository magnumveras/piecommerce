<%-- 
    Document   : index
    Created on : 23/05/2018, 14:02:36
    Author     : magno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% HttpSession sessao = request.getSession(); %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <% 
            sessao.setAttribute("mensagemErroCampos", "");
            sessao.setAttribute("produtoexiste", "");
        %>
        <jsp:forward page="/consultaprodutos" />
    </body>
</html>
