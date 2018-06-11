<%-- 
    Document   : consultaProduto[]
    Created on : 30/05/2018, 15:14:31
    Author     : magno
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <jsp:include page="topo.jsp"/> 
    <body>
        <div class="container" align="center">
            <h3>Consulta de Produtos</h3>
           
            <label for="nome">Produto: </label>
            <div class="row">
                    <div class="col-12 col-sm-10">
                        <form class="form-group-md" action="${pageContext.request.contextPath}/consultaprodutos" method="post">
                                <input type="text" class="form-control" name="produto" placeholder="Digite nome Produto" maxlength="70"/>
                    </div>
                    <div class="col-12 col-sm-2">
                            <button type="submit" class="btn btn-success center-block" action="">Pesquisar</button>
                        </form>
                    </div>
            </div>
            <br>
            <div class="row">
                    <div class="col-12">
                        <div class="tabela">
                            
                            <table class="table table-selectable table-bordered table-hover" id="tabelaprodutos">
                                <caption>Lista de Produtos</caption>
                                <tr>
                                    <th>Código</th>
                                    <th>Nome</th>
                                    <th>Descrição</th>
                                    <th>Fornecedor</th>
                                    <th>Categoria</th>
                                    <th>Preço de Compra (R$)</th>
                                    <th>Preço de Venda (R$)</th>
                                    <th>Estoque</th>
                                    <th>Data de Cadastro</th>
                                    <th>Ação</th>
                                </tr>
                                <c:forEach items="${ListaProdutos}" var="produto">
                                <tr>
                                    <td><c:out value="${produto.codigo}"  /></td>
                                    <td><c:out value="${produto.nome}" /></td>
                                    <td><c:out value="${produto.descricao}" /></td>
                                    <td>
                                    <c:forEach items="${ListaFornecedores}" var="f">
                                    <c:if test="${produto.fornecedor eq f.getCodigo()}">
                                        <c:out value="${f.getNome()}" />
                                    </c:if>
                                    </c:forEach>
                                    </td>
                                    <td><c:set var="categoria" scope="session" value="${produto.categoria}"/>
                                        <c:if test = "${categoria == 1}">
                                            <c:out value="Audio Profissional" />
                                        </c:if>
                                        <c:if test = "${categoria == 2}">
                                            <c:out value="Bateria & Percussão" />
                                        </c:if>
                                        <c:if test = "${categoria == 3}">
                                            <c:out value="Cordas & Acessórios" />
                                        </c:if>
                                        <c:if test = "${categoria == 4}">
                                            <c:out value="Pianos e Teclados" />
                                        </c:if>
                                        <c:if test = "${categoria == 5}">
                                            <c:out value="Sopro"/>
                                        </c:if>
                                    </td>        
                                  
                                    <td><c:out value="${produto.precocompra}" /></td>
                                    <td><c:out value="${produto.precovenda}" /></td>
                                    <td><c:out value="${produto.estoque}"/></td>
                                    <td><c:out value="${produto.datacadastro}"/></td>
                                    <td>
                                        <div class="form-group">
                                            <form action="${pageContext.request.contextPath}/alterarProduto" method="post" >
                                                <button type="submit" name="codigoproduto" value="${produto.codigo}" 
                                                    class="btn btn-success center-block">Alterar</button>
                                            </form>
                                        </div>  
                                        <div class="form-group">
                                            <form action="${pageContext.request.contextPath}/excluirProduto" method="post" >         
                                                <button type="submit" name="codigoproduto" value="${produto.codigo}" 
                                                    class="btn btn-danger center-block">Excluir</button>
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
