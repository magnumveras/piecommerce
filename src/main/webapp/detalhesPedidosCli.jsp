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
            <h3>Pedido</h3>
            <br>
            <div class="row">
                    <div class="col-12">
                        <table class="table" align="center">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Data</th>
                                    <th>Valor Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${pedidofatura.getCodigo()}</td>
                                    <td>${pedidofatura.getData()}</td>
                                    <td>${pedidofatura.getValorTotal()}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
            </div>
            <hr>
            <div class="row">
                    <div class="col-12">
                        <table class="table" align="center">
                            <thead>
                                <tr>
                                    <th>Nome</th>
                                    <th>Preço</th>
                                    <th>Quantidade</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listaitensfatura}" var="item">
                                    <c:forEach items="${listaprodutos}" var="produto">
                                <tr>
                                    <c:if test="${item.getCodigoproduto() eq produto.getCodigo()}">
                                    <td>${produto.getNome()}</td>
                                    <td>${produto.getPrecovenda()}</td>
                                    <td>${item.getQuantidade()}</td>
                                    </c:if>
                                </tr>
                                    </c:forEach>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
            </div>
            <hr>
            <div class="row">
                    <div class="col-12">
                        <table class="table" align="center">
                            <thead>
                                <tr>
                                    <th>Nome</th>
                                    <th>cpf</th>
                                    <th>Email</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${clientefatura.getNome()} ${clientefatura.getSobrenome()}</td>
                                    <td>${clientefatura.getCpf()}</td>
                                    <td>${clientefatura.getEmail()}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
            </div>
            <div class="row">
                <div class="col-12 form-inline">
                    <button class="btn btn-outline-primary bInfo bVoltar" onclick="window.location.href='consultaPedidoscli.jsp'">Voltar</button>
                    &nbsp
                    <c:choose>
                        <c:when test="${empty Faturado}">
                            <h5 style="color:red">Pedido Pendente</h5>
                        </c:when>
                        <c:otherwise>
                            <h5 style="color:green">Pedido Faturado</h5>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </body>
    <jsp:include page="rodape.jsp"/> 
</html>
