<%-- 
    Document   : consultarCliente
    Created on : 08/06/2018
    Author     : Magno Veras
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Consulta de Cliente</title>
        <!--        <style>
                    .com {
                        border: 2px solid gray;
                        border-collapse: collapse;
                    }
                    td.com{
                        border: 1px solid gray;
                        border-collapse: collapse;
                    }
                    tr, th , td{
                        padding: 1px;
                        text-align: left;    
                    }
                    .sem{
                        border: none;
                    }
                    td:nth-child(7) button{
                        border:none;   
                        background: none;
                        text-decoration:underline;
                    }
                    td:nth-child(8) button{
                        background: none;
                        text-decoration: underline;
                        border:none;   
                    }
        
                </style>-->
    </head>

    <body>
        <jsp:include page="topo.jsp"/> 
        <div class="container" align="center">
            <div class="row">
                <div class="col-12">
                <h3>Consulta de Cliente</h3>
                <!--<div class="well">-->
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <form class="form-inline" action="${pageContext.request.contextPath}/clientes" method="post">
                        <label for="nome">Cliente: </label>
                        <input type="text" class="form-control" name="cliente" placeholder="Digite nome Cliente" maxlength="30"/>
                        <button type="submit" class="btn btn-success center-block">Pesquisar</button>
                    </form>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-12">
                        <table class="table table-selectable table-bordered table-hover" id="tabelaclientes">
                            <caption>Lista de Clientes</caption>
                            <tr>
                                <th>Nome</th>
                                <th>CPF</th>
                                <th>Telefone</th>
                                <th>Email</th>
                                <th>Endereço</th>
                                <th>Cidade</th>
                                <th>Usuario</th>
                                <th>Senha</th>
                                <th>Ação</th>
                            </tr>
                            <c:forEach items="${listaClientes}" var="cliente">
                                <tr>
                                    <td><c:out value="${cliente.nome} ${cliente.sobrenome} "  /></td>    
                                    <td><c:out value="${cliente.cpf}" /></td>
                                    <td><c:out value="${cliente.telefone}" /></td>
                                    <td><c:out value="${cliente.email}" /></td>
                                    <td><c:out value="${cliente.endereco}, ${cliente.numero}" /></td>
                                    <td><c:out value="${cliente.cidade} - ${cliente.estado}" /></td>
                                    <c:forEach items="${listaUsuarios}" var="usuario">
                                        <c:if test = "${usuario.codigo == cliente.codigousuario}">
                                            <td> <c:out value="${usuario.login}" /></td>
                                            <td> <c:out value="${usuario.senha}" /></td>
                                        </c:if>
                                    </c:forEach>
                                    <td>
                                        <div>
                                            <form class="form-control-static" action="${pageContext.request.contextPath}/alterarCliente" method="post" >
                                                <div class="form-group">
                                                    <button type="submit" name="cpfcliente" value="${cliente.cpf}" 
                                                    class="btn btn-success center-block">Alterar/ Visualizar</button>
                                                </div>
                                            </form>
                                            <form class="form-control-static" action="${pageContext.request.contextPath}/excluirCliente" method="post" >
                                                <div class="form-group">
                                                    <button type="submit" name="cpfcliente" value="${cliente.cpf}" 
                                                        class="btn btn-danger center-block">Excluir</button> 
                                                </div>
                                            </form>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                </div>
            </div>
        </div>            
        <jsp:include page="rodape.jsp"/>      
    </body>

</html>