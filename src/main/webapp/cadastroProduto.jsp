<%-- 
    Document   : cadastroproduto
    Created on : 28/05/2018, 11:47:27
    Author     : magno
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:choose>
            <c:when test="${empty Altera}">
                <title>Cadastro de Produtos</title>
            </c:when>
            <c:otherwise>
                <title>Alteração de Produtos</title>
            </c:otherwise>
        </c:choose>
        <script type="text/javascript" src="js/programa.js"></script>
    </head>
    <jsp:include page="topo.jsp"/>   
    <body>
        <div class="container">
            <hr class="hr">
            <div class="row">
                <div class="col-12 text-center">
                <section class="row d-flex justify-content-center">
                    <c:choose>
                        <c:when test="${empty Altera}">
                            <h1>Cadastro de Produto</h1>
                        </c:when>
                        <c:otherwise>
                            <h1>Alteração de Produto</h1>
                        </c:otherwise>
                    </c:choose>          
                </section>
                </div>
                <div class="col-12 text-center">
                    <!--Variáveis vem do Servlet para verificação de campos -->
                        <c:if test="${not empty mensagemErroCampos}">
                        <h5 class="blink" style="color: red"><c:out value="${mensagemErroCampos}"/></h5>
                        </c:if>
                        <c:if test="${empty mensagemErroCampos}">
                        <h5 class="blink" style="color: red"><c:out value="${mensagemErroCampos}"/></h5>
                        </c:if>
                        <c:if test="${not empty produtoexiste}">
                        <h5 class="blink" style="color: red"><c:out value="${produtoexiste}"/></h5>
                        </c:if>
                        <c:if test="${empty produtoexiste}">
                        <h5 class="blink" style="color: red"><c:out value="${produtoexiste}"/></h5>
                        </c:if> 
                </div>
            </div>
            <c:choose>
                <c:when test="${empty Altera}">
                    <form action="${pageContext.request.contextPath}/cadastroproduto" method="post">
                </c:when>
                <c:otherwise>
                    <form action="${pageContext.request.contextPath}/alterarProduto" method="post">
                </c:otherwise>
            </c:choose>
            
            <c:choose>
                <c:when test="${empty Altera}">
                    <div class="row">
                    <div class="form-group col-12 col-sm-4">
                        <label for="nomeproduto">Nome*</label>
                        <input type="text" class="form-control" name="nomeproduto" id="nome" placeholder="nome"
                            required oninvalid="this.setCustomValidity('Preencha o Produto')" oninput="setCustomValidity('')" maxlength="70"/>
                    </div>
                    
                    <div class="form-group col-12 col-sm-4">
                        <label for="categoria" >Categoria*</label>
                        <select style="width: 100%; height: 50px" class="custom-select" 
                                name="categoria" id="InputCat">
                                <option disabled="true" selected="selected" value="">Escolher</option>
                                <option value="1">Áudio Profissional</option>
                                <option value="2">Bateria & Percussão</option>
                                <option value="3">Cordas & Acessórios</option>
                                <option value="4">Pianos & Teclados</option>
                                <option value="5">Sopro</option>
                        </select>
                    </div>
                    
                    <div class="form-group col-12 col-sm-4">
                        <label  for="fornecedor" >Fornecedor*</label>
                        <select style="width: 100%; height: 50px" class="custom-select" 
                            name="fornecedor" id="fornecedor">
                            <option disabled="true" selected="selected" value="">Escolher</option>
                            <option value="1">Daddarío</option>
                            <option value="2">Fender</option>
                            <option value="3">Gibson</option>
                            <option value="4">Roland</option>
                            <option value="5">Martin e co</option>
                            <option value="6">Casio</option>
                            <option value="7">BlackStar</option>
                            <option value="8">Mark bass</option>
                            <option value="9">Ik multimedia</option>
                            <option value="10">Boss</option>
                            <option value="11">Korg</option>
                            <option value="12">Tagina</option>
                            <option value="13">Ibanez</option>
                            <option value="14">Sigma guitars</option>
                            <option value="15">Shure</option>
                            <option value="16">Eletro-harmonix</option>
                            <option value="17">On-stage</option>
                        </select>
                    </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-12 col-sm-4">
                            <label for="precoCompra">Preço de Compra*</label>
                            <input class="form-control" id="inputpccompra" aria-describedby="#"
                                onkeypress="reais(this, event)" onkeydown="backspace(this, event)" maxlength="11"
                                name="precoCompra" placeholder="Preço Compra">
                        </div>
            
                        <div class="form-group col-12 col-sm-4">
                            <label for="precoVenda">Preço de Venda*</label>
                            <input class="form-control" id="inputprecovenda" aria-describedby="#"
                               onkeypress="reais(this, event)" onkeydown="backspace(this, event)" maxlength="11"
                               name="precoVenda" placeholder="Preço Venda">
                        </div>
            
                        <div class=" form-group col-12 col-sm-4">
                            <label for="estoque">Estoque*</label>
                            <input class="form-control" id="inputestoque" aria-describedby="#" 
                                onkeydown="javascript: fMasc(this, mNum)" maxlength="10" required oninvalid="this.setCustomValidity('Preencha o Estoque')" oninput="setCustomValidity('')"
                                name="estoque" placeholder="Estoque">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-12 text-center">
                            <label for="descricao">Descrição do Produto*</label>
                            <textarea rows="10" cols="140" id="txtareadescricao"
                                required oninvalid="this.setCustomValidity('Preencha a Descrição')" oninput="setCustomValidity('')" maxlength="1000"
                                name="descricao" placeholder="descricao"></textarea>
                        </div>
                    </div>
            
                    <div class="row">
                        <div class="form-group col-12 col-sm-6">
                            <label for="imagens">Imagens*</label>
                            <input type="file" class="form-group" id="inputimagens" accept="image/png, image/jpeg"
                               accesskey=""multiple placeholder="Imagens">
                        </div>
                        <div class="col-12 col-sm-6 text-center">
                            <button type="submit">Enviar</button>
                        </div>
                
                    </div>        
                    </c:when>
                    <c:otherwise>
                        <div class="row">
                    <div class="form-group col-12 col-sm-4">
                        <label for="nomeproduto">Nome*</label>
                        <input type="text" class="form-control" name="nomeproduto" value="${pro.nome}" id="nome" placeholder="nome"
                            required oninvalid="this.setCustomValidity('Preencha o Produto')" oninput="setCustomValidity('')" maxlength="70"/>
                    </div>
                    
                    <div class="form-group col-12 col-sm-4">
                        <label for="categoria">Categoria*</label>
                            <c:set var="categoria" value="${pro.getCategoria()}"/>
                            <select class="form-control" name="categoria" id="categoria">
                                <c:if test="${categoria == 1}">
                                    <option value="1">Audio Profissional</option>
                                    <option value="2">Bateria & Percussão</option>
                                    <option value="3">Cordas & Acessórios</option>
                                    <option value="4">Pianos & Teclados</option>
                                    <option value="5">Sopro</option>
                                </c:if>
                                <c:if test="${categoria == 2}">
                                    <option value="2">Bateria & Percussão</option>
                                    <option value="1">Audio Profissional</option>
                                    <option value="3">Cordas & Acessórios</option>
                                    <option value="4">Pianos & Teclados</option>
                                    <option value="5">Sopro</option>
                                </c:if>
                                <c:if test="${categoria == 3}">
                                    <option value="3">Cordas & Acessórios</option>
                                    <option value="1">Audio Profissional</option>
                                    <option value="2">Bateria & Percussão</option>
                                    <option value="4">Pianos & Teclados</option>
                                    <option value="5">Sopro</option>
                                </c:if>
                                <c:if test="${categoria == 4}">
                                    <option value="4">Pianos & Teclados</option>
                                    <option value="1">Audio Profissional</option>
                                    <option value="2">Bateria & Percussão</option>
                                    <option value="3">Cordas & Acessórios</option>
                                    <option value="5">Sopro</option>
                                </c:if>
                                <c:if test="${categoria == 5}">
                                    <option value="5">Sopro</option>
                                    <option value="1">Audio Profissional</option>
                                    <option value="2">Bateria & Percussão</option>
                                    <option value="3">Cordas & Acessórios</option>
                                    <option value="4">Pianos & Teclados</option>
                                </c:if> 
                            </select>
                    </div>
                    
                    <div class="form-group col-12 col-sm-4">
                        <label for="desc">Fornecedor*</label>
                            <select class="form-control" name="fornecedor" id="fornecedorproduto">
                                <c:set var="codigofornecedor" value="${pro.getFornecedor()}"/>
                                <c:forEach items="${ListaFornecedores}" var="f">
                                    <c:if test="${codigofornecedor eq f.getCodigo()}">
                                        <option value="${f.getCodigo()}">
                                        <c:out value ="${f.getNome()}" />
                                        </option>
                                    </c:if>
                                </c:forEach>
                                <c:forEach items="${ListaFornecedores}" var="f">
                                    <option value="${f.getCodigo()}">
                                        <c:out value ="${f.getNome()}" />
                                    </option>
                                </c:forEach>
                            </select>
                    </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-12 col-sm-4">
                            <label for="precoCompra">Preço de Compra*</label>
                            <input class="form-control" id="inputpccompra" aria-describedby="#"
                                   value="${pro.precocompra}" 
                                   onkeypress="reais(this, event)" onkeydown="backspace(this, event)" maxlength="11"
                                    name="precoCompra" placeholder="Preço Compra">
                        </div>
            
                        <div class="form-group col-12 col-sm-4">
                            <label for="precoVenda">Preço de Venda*</label>
                            <input class="form-control" id="inputprecovenda" aria-describedby="#"
                                   value="${pro.precovenda}" 
                                   onkeypress="reais(this, event)" onkeydown="backspace(this, event)" maxlength="11"
                                   accept=""name="precoVenda" placeholder="Preço Venda">
                        </div> 
            
                        <div class=" form-group col-12 col-sm-4">
                            <label for="estoque">Estoque*</label>
                            <input class="form-control" id="inputestoque" aria-describedby="#" 
                                   value="${pro.estoque}"
                                   onkeydown="javascript: fMasc(this, mNum)" maxlength="10" required oninvalid="this.setCustomValidity('Preencha o Estoque')" oninput="setCustomValidity('')"
                                   name="estoque" placeholder="Estoque">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-12 text-center">
                            <label for="descricao">Descrição do Produto*</label>
                            <textarea rows="10" cols="140" id="txtareadescricao"
                                required oninvalid="this.setCustomValidity('Preencha a Descrição')" oninput="setCustomValidity('')" maxlength="200"
                                name="descricao" placeholder="descricao">${pro.descricao}</textarea>
                        </div>
                    </div>
            
                    <div class="row">
                        <div class="form-group col-12 col-sm-6">
                            <label for="imagens">Imagens</label>
                            <input type="file" class="form-group" id="inputimagens" accept="image/png, image/jpeg"
                               accesskey=""multiple placeholder="Imagens">
                        </div>
                        <div class="col-12 col-sm-6 text-center">
                            <button type="submit">Enviar</button>
                        </div>
                    </c:otherwise>
            </c:choose>
  
                </form>
        </div>
    </body>
    <jsp:include page="rodape.jsp"/> 
</html>
