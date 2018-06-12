<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <c:when test="${not empty carrinhocliente}">
                <div class="container" align="center">
                    <h3>Carrinho</h3>
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group-md">
                                    <label><c:out value="Carrinho n°:${carrinhocliente.codigo}"/></label>
                                    <label>
                                            <c:out value="Cliente: ${cliente.nome} ${cliente.sobrenome}"/>
                                            <c:out value="/ CPF: ${cliente.cpf}"/>
                                    </label>
                                    <label><c:out value="Data Carrinho: ${carrinhocliente.data}"/></label>
                                    <label><c:out value="Valor Total: R$ ${carrinhocliente.valorTotal}"/></label>
                                </div>
                
                    
                                <table class="table table-selectable table-bordered table-hover col-md-8" id="tabelaProdutos">
                                    <thead>
                                        <tr>
                                            <th>Produto</th>
                                            <th>Descrição</th>
                                            <th>Valor Unitário</th>
                                            <th>Quantidade</th>
                                            <th>Ação</th>
                                        </tr>
                                    </thead>
                
                                    <c:forEach items="${itenscarrinhocadastrado}" var="item">
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
                                                    <c:out value="${prod.descricao}" />
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
                                            <td><c:out value="${item.quantidade}" /></td>
                                            <td>
                                                <form class="form-control-static" action="${pageContext.request.contextPath}/removeritemcarrinho" method="post" >
                                                    <div class="form-group">
                                                        <input type="hidden" name="codigocarrinho" value="${carrinhocliente.getCodigo()}">
                                                        <input type="hidden" name="quantidadeitem" value="${item.quantidade}">
                                                        <input type="hidden" name="codigoitem" value="${item.codigo}">
                                                        <button type="submit" name="produtoitem" value="${item.getProduto()}" 
                                                                class="btn btn-danger center-block">Remover Produto</button>
                                                    </div>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                    </div>
                <div class="container form-control-static">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12"> 
                                <form class="form-group-md" action="${pageContext.request.contextPath}/finalizarvenda" method="post">
                                    <input type="hidden" name="codigocarrinho" value="${cabecalhocarrinho.getCodigo()}">
                                    <input type="submit" class="btn btn-success" title="Finalizar Venda" value="Finalizar Venda">            
                                    <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-danger">Voltar</a>
                                </form>
                            </div>
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
                                            <button type="submit" name="produtoitem" value="${item.getProduto()}" 
                                                    class="btn btn-danger center-block">X</button> 
                                        </form>
                                    </td>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                </div>
            </c:otherwise>
        </c:choose> 
    <jsp:include page="rodape.jsp"/>           
    </body>
</html>
