
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% HttpSession sessao = request.getSession(); %>
<!DOCTYPE html>
<html lang="pt-br" xmlns:th="http://www.thymeleaf.org">
    <head>
        <style>
            .fixedHeightProd{
                max-height: auto;
                margin-left: auto;
                margin-right: auto;
                width: auto;
            }
            
        </style>
    </head>
    <jsp:include page="topo.jsp"/>        
    <c:if test="${empty loginoperacao}">
    <section class="container">
            <div class="row">
                <hr class="hr col-12">
                
                <c:forEach items="${ListaProdutos}" var="produto">
                      
                <div class="col-12 col-sm-6 col-md-4  colcard-deck fixedHeightProd" id="produtobuscar">
                        <form class="form-group">
                        <figure id="teste">
                            <a href="consultadetalhes?&produto=<c:out value='${produto.codigo}'/>" class="d-flex justify-content-center">
                                <img id="figura" class="card-img"
                                src="imagens/martin-d-100.jpg"
                                alt="Violão Martin D-100">
                            </a>
                        </figure>
                        <hr style="color: gainsboro; background-color: gainsboro; height: 3px">
                        <div class="card-text" style="min-height: 70px;">
                            <p class="card-title d-flex justify-content-center"><c:out value="${produto.nome}"/></p>
                        </div>
                        <div class="card-text text-center">
                            <h6 class="card-text d-flex justify-content-center"><c:out value="R$ ${produto.precovenda}"/></h6>
                            <h6>Parcelado em até 10x sem juros de <c:out value="R$ ${produto.precovenda/10}"/></h6>
                        </div>
                        <br>
                        <div class="card-text">
                            <c:if test="${produto.estoque > 0}">
                                <a href="inserircarrinho?&produto=<c:out value='${produto.codigo}'/>" class="btn btn-primary d-flex justify-content-center">&ensp;Add ao Carrinho</a>
                            </c:if>
                            <c:if test="${produto.estoque == 0}">
                                <h6 align="center">Produto Indisponível</h6>
                            </c:if>
                        </div>
                        </form>
                </div>
                </c:forEach>
                
            </div>
    </section>
    </c:if>
    <!--<nav aria-label="Page navigation example" class="pagination col-12 d-flex justify-content-center">
        <ul class="pagination">
            <li class="page-item disabled"><a class="page-link" href="#">Anterior</a></li>
            <li class="page-item active"><a class="page-link" href="#">1<span class="sr-only">(current)</span></a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item"><a class="page-link" href="#">Próximo</a></li>
        </ul>
    </nav>-->
    <jsp:include page="rodape.jsp"/>
</html>
