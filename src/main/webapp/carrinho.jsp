<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% HttpSession sessao = request.getSession(); %>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .jumbotron {
                margin-bottom: 0;
            }
        </style>

        <script>

            function fMasc(objeto, mascara) {
                obj = objeto
                masc = mascara
                setTimeout("fMascEx()", 1)
            }
            function fMascEx() {
                obj.value = masc(obj.value)
            }

            function mCPF(cpf) {
                cpf = cpf.replace(/\D/g, "")
                cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2")
                cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2")
                cpf = cpf.replace(/(\d{3})(\d{1,2})$/, "$1-$2")
                return cpf
            }

        </script>
        <title>Venda</title>
    </head>
    <body>        
        <jsp:include page="topo.jsp"/>
        <c:choose>
            <c:when test="${not empty carrinhocadastrado}">
                <div class="container">
                        <div class="row" align="center">
                            <div class="col-12">     
                                <label><c:out value="Carrinho n°: ${codigocarrinho}"/></label>
                                <label><c:out value="Cliente: ${clientecarrinho.nome} ${clientecarrinho.sobrenome}"/></label>
                                <label><c:out value="/ CPF: ${clientecarrinho.cpf}"/></label>
                                <br>
                                <h6><c:out value="Valor Total: R$ ${carrinhocadastrado.getValorTotal()}"/></h6>
                               
                               
                
                    
                               <table class="table table-selectable table-bordered table-hover" id="tabelaProdutos">
                                    <thead>
                                        <tr>
                                            <th>Produto</th>
                                            <th>Valor Unitário</th>
                                            <th>Quantidade</th>
                                            <th>Valor Total</th>
                                            <th>Ação</th>
                                        </tr>
                                    </thead>
                
                                    <c:forEach items="${listacarrinhocadastrado}" var="item">
                                        <tr>
                                            <td>
                                            <c:forEach items="${listaprodutos}" var="prod">
                                                <c:if test="${prod.codigo eq item.getProduto()}">
                                                    <c:out value="${prod.nome}" />
                                                </c:if>
                                            </c:forEach>    
                                            </td>
                                            <td>
                                            <c:forEach items="${listaprodutos}" var="prod">
                                                <c:if test="${prod.codigo eq item.getProduto()}">
                                                    <c:out value="R$ ${prod.precovenda}" />
                                                </c:if>
                                            </c:forEach>
                                            </td>
                                            <td><c:out value="${item.getQuantidade()}" /></td>
                                            <td>
                                            <c:forEach items="${listaprodutos}" var="prod">
                                                <c:if test="${prod.codigo eq item.getProduto()}">
                                                    <c:out value="R$ ${prod.precovenda * item.getQuantidade()}" />
                                                </c:if>
                                            </c:forEach>
                                            </td>
                                            <td>
                                                <form action="${pageContext.request.contextPath}/removeritemcarrinho" method="post" >
                                                    <input type="hidden" name="codigoproduto" value="${item.getProduto()}" />
                                                    <input type="hidden" name="quantidade" value="${item.getQuantidade()}" />
                                                    <button type="submit" name="codigocarrinho" value="${carrinhocadastrado.getCodigo()}" 
                                                            class="btn btn-danger center-block">X</button> 
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12" align="right">
                            <form action="${pageContext.request.contextPath}/finalizarcarrinho" method="post">
                                <input type="hidden" name="codigoproduto" value="${item.getProduto()}" />
                                <button type="submit" name="codigocarrinho" value="${carrinhocadastrado.getCodigo()}" 
                                    class="btn btn-success center-block">Finalizar Pedido</button> 
                            </form>
                            </div>
                        </div>
                    </div>
            </c:when>
            <c:otherwise>
                <div class="container" align="center">
                    <h3>Carrinho</h3>
                    <div class="row">
                            <table class="table table-selectable table-bordered table-hover col-12" id="tabelaProdutos" align="center">
                                <thead>
                                    <tr>
                                        <th>Produto</th>
                                        <th>Valor Unitário</th>
                                        <th>Quantidade</th>
                                        <th>Valor Total</th>
                                        <th>Ação</th>
                                    </tr>
                                </thead>
                                
                                <tbody>
                                <c:forEach items="${itenscarrinho}" var="item">
                                <tr>
                                    <td>
                                        <c:forEach items="${listaprodutos}" var="prod">
                                            <c:if test="${prod.codigo eq item.getProduto()}">
                                                 <c:out value="${prod.nome}" />
                                            </c:if>
                                        </c:forEach>   
                                    </td>
                                    <td>
                                        <c:forEach items="${listaprodutos}" var="prod">
                                            <c:if test="${prod.codigo eq item.getProduto()}">
                                                <c:out value="R$ ${prod.precovenda}" />
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <c:out value="${item.quantidade}" />
                                    </td>
                                    <td>
                                        <c:forEach items="${listaprodutos}" var="prod">
                                            <c:if test="${prod.codigo eq item.getProduto()}">
                                                <c:out value="R$ ${prod.precovenda * item.quantidade}" />
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <form action="${pageContext.request.contextPath}/removeritemcarrinho" method="post" >
                                            <input type="hidden" name="quantidade" value="${item.quantidade}"/>
                                            <button type="submit" name="codigoproduto" value="${item.getProduto()}" 
                                                    class="btn btn-danger center-block">X</button> 
                                        </form>
                                    </td>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    <div class="row">
                        <h6>Cliente, faça login para finalizar pedido!</h6>
                    </div>
                </div>
            </c:otherwise>
        </c:choose> 
    <jsp:include page="rodape.jsp"/>           
    </body>
</html>
