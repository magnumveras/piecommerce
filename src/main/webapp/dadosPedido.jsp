<%-- 
    Document   : dadosPedido
    Created on : 13/06/2018, 20:00:48
    Author     : magno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Music Store - Pedido</title>
    </head>
    <body>
        <jsp:include page="topo.jsp"/>
        <div class="container">
            <div class="row">
                <h2 class="col-12" align="center">Pedido</h2>
                <hr>
            </div>
            <div class="row">
                    <div class="col-12" align="center">
                        <h6>Pedido enviado com Sucesso!</h6>
                        <label class="hEnd">Pedido N°${pedidocliente.getCodigo()}</label>
                        <label class="hEnd">Cliente: ${clientepedido.getNome()} ${clientepedido.getSobrenome()}</label>
                        <label class="hEnd">Valor: <fmt:setLocale value = "pt-BR"/> <fmt:formatNumber value="${pedidocliente.getValorTotal()}" type="currency"/></label>

                    </div>
                   
            </div>
        </div>
        <jsp:include page="rodape.jsp"/>
    </body>
</html>
