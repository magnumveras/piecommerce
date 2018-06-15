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
        <title>Vendas - Made in Astec</title>
    </head>
    <jsp:include page="topo.jsp"/> 
    <body>
        <div class="container" align="center">
            <h3>Vendas</h3>
            <div class="row">
                    <div class="col-12" align="center">
                        <form class="form-group-md" action="${pageContext.request.contextPath}/consultavendas" method="post">
                            <button type="submit" class="btn btn-success center-block">VENDAS</button>
                        </form>    
                    </div>
            </div>
            <br>
            <div class="row">
                    <div class="col-12">
                        <div class="tabela">
                            
                            <table class="table table-selectable table-bordered table-hover" id="tabelaprodutos">
                                <caption>Lista de Vendas</caption>
                                <tr>
                                    <th>Código da Venda</th>
                                    <th>Data de inclusão</th>
                                    <th>Nome Cliente</th>
                                    <th>Valor Total</th>
                                    <th>Ação</th>
                                </tr>
                                <c:forEach items="${listavendas}" var="venda">
                                <tr>
                                    <td><c:out value="${venda.getCodigo()}"  /></td>
                                    <td><c:out value="${venda.getData()}" /></td>
                                    <c:forEach items="${listaclientes}" var="cliente">
                                        <c:if test="${cliente.getId() eq venda.getCodigoCliente()}"></c:if>
                                        <td><c:out value="${cliente.getNome()} ${cliente.getSobrenome()}" /></td>
                                    </c:forEach>
                                    <td><fmt:setLocale value = "pt-BR"/> <fmt:formatNumber value="${venda.getValorTotal()}" type="currency"/></td>
                                    
                                    <td>
                                        <div class="form-group">
                                            <form action="${pageContext.request.contextPath}/visualizarvenda" method="post" >
                                                <input type="hidden" name="codigovenda" value="${venda.getCodigo()}"/>
                                                <button type="submit" class="btn btn-success center-block">Visualizar</button>
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
