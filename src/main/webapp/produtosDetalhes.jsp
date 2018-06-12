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
        <title>Detalhe dos Produtos</title>
        <script type="text/javascript" src="js/programa.js"></script>
    </head>
    <body>
        <div class="container">
        <jsp:include page="topo.jsp"/> 
        
        <div class="row">
            <div class="col-12 col-sm-5" align="center">
                <img id="myimage" src="imagens/martin-0.jpg" alt="Violão Martin D-100 Deluxe">
                <div id="myresult" class="img-zoom-result"></div>
            </div>
            <div class="col-12 col-sm-7" style="margin-top: 10%;">
                <h5><c:out value="${produtodetalhes.nome}" /></h5>
                <h4><c:out value="R$ ${produtodetalhes.precovenda}" /></h4>
                <label><c:out value="${produtodetalhes.descricao}" /></label>
                
                <div class="form-inline">
                <form action="${pageContext.request.contextPath}/adicionarcarrinho" method="post">
                    <input type="number" size="4" name="quantidade"
                           required oninvalid="this.setCustomValidity('Preencha a Quantidade')" oninput="setCustomValidity('')" 
                           onkeydown="javascript: fMasc(this, mNum)" maxlength="5"/>&ensp;Qtd
                    <button type="submit" class="btn btn-primary">&ensp;Adicionar ao Carrinho</button>
                </form>
                </div>
            </div>
        </div>
        <jsp:include page="rodape.jsp"/>
        </div>
    </body>
</html>
