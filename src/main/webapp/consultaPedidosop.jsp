<%-- 
    Document   : consultaProduto[]
    Created on : 30/05/2018, 15:14:31
    Author     : magno
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedidos - Made in Astec</title>
    </head>
    <jsp:include page="topo.jsp"/> 
    <body>
        <div class="container" align="center">
            <h3>Pedidos</h3>
            <div class="row">
                    <div class="col-12" align="center">
                        <form class="form-group-md" action="${pageContext.request.contextPath}/consultapedidos" method="post">
                            <button type="submit" class="btn btn-success center-block">PEDIDOS</button>
                        </form>    
                    </div>
            </div>
            <br>
            <div class="row">
                    <div class="col-12">
                        <div class="tabela">
                            
                            <table class="table table-selectable table-bordered table-hover" id="tabelaprodutos">
                                <caption>Lista de Pedidos</caption>
                                <tr>
                                    <th>Código do Pedido</th>
                                    <th>Data de inclusão</th>
                                    <th>Nome Cliente</th>
                                    <th>Valor Total</th>
                                    <th>Ação</th>
                                </tr>
                                <c:forEach items="${listapedidos}" var="pedido">
                                <tr>
                                    <td><c:out value="${pedido.getCodigo()}"  /></td>
                                    <td><c:out value="${pedido.getData()}" /></td>
                                    <c:forEach items="${listaclientes}" var="cliente">
                                        <c:if test="${cliente.getId() eq pedido.getCodigoCliente()}"></c:if>
                                        <td><c:out value="${cliente.getNome()} ${cliente.getSobrenome()}" /></td>
                                    </c:forEach>
                                    <td><fmt:setLocale value = "pt-BR"/> <fmt:formatNumber value="${pedido.getValorTotal()}" type="currency"/></td>
                                    
                                    <td>
                                        <div class="form-group">
                                            <form action="${pageContext.request.contextPath}/faturarpedido" method="post" >
                                                <input type="hidden" name="codigopedido" value="${pedido.getCodigo()}"/>
                                                <button type="submit" class="btn btn-success center-block">Faturar</button>
                                            </form>
                                        </div>  
                                    </td>
                                </tr>
                            </c:forEach>
                    
                            </table>
                        </div>
                    </div>
                
            </div>
        </div>
    </body>
    <jsp:include page="rodape.jsp"/> 
</html>
