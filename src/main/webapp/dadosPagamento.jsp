<%-- 
    Document   : dadosPagamento
    Created on : 13/06/2018, 20:00:48
    Author     : magno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Music Store - Forma de Pagamento</title>
        <script type="text/javascript" src="js/programa.js"></script>
    </head>
    <body>
        <div class="container">
            <jsp:include page="topo.jsp"/>
            <form class="col-lg-12" action="${pageContext.request.contextPath}/finalizarcarrinho" method="post">
            <div class="row">
                <h2 class="col-12" align="center">Forma da Pagamento</h2>
                <hr>
            </div>
            <div class="row">
                    <div class="col-12 col-sm-2" align="center">
                        <h6>Tipo de Cartão*</h6>
                        <label class="hEnd">Crédito</label>
                        <input class="form-check" type="radio" name="cartao" value="cartaocredito" checked/>
         
                        <label class="hEnd">Débito</label>
                        <input class="form-check" type="radio" name="cartao" value="cartaodebito"/>
                    </div>
                    <div class="col-12 col-sm-4">
                        <label class="hEnd">Número do Cartão*</label>
                        <input type="number" name="numerocartao" value=""
                            class="form-control" placeholder="Número do Cartão" size="30"
                            required oninvalid="this.setCustomValidity('Preencha o número')" oninput="setCustomValidity('')" maxlength="30"/>
                    </div>
                    <div class="col-12 col-sm-4">
                        <label class="hEnd">Nome no Cartão*</label>
                        <input type="text" name="nomecartao" value=""
                            class="form-control" placeholder="Nome no Cartão" size="20"
                            required oninvalid="this.setCustomValidity('Preencha o nome')" oninput="setCustomValidity('')" maxlength="20"/>
                    </div>
                    <div class="col-12 col-sm-2">
                        <label class="hEnd">Cod Seg. *</label>
                        <input type="number" name="codigoseguranca" value=""
                            class="form-control" placeholder="cod" size="3"
                            required oninvalid="this.setCustomValidity('Preencha o código')" oninput="setCustomValidity('')" maxlength="3"/>
                    </div>
            </div>
            <div class="row">
                <div class="col-12 col-sm-4">
                    <label>Vencimento*</label>
                    <input type="text" name="vencimento" 
                            class="form-control" placeholder="DD/YY" size="5"
                            required oninvalid="this.setCustomValidity('Preencha o vencimento')" oninput="setCustomValidity('')" maxlength="5"/>
                </div>
                <div class="col-12 col-sm-8">
                    <label>Parcelas*</label>
                    <select class="form-control" name="parcelas">
                        <option value="1x">1x de <fmt:setLocale value = "pt-BR"/> <fmt:formatNumber value="${valortotal}" type="currency"/> s/ juros</option>
                        <option value="2x">2x de <fmt:setLocale value = "pt-BR"/> <fmt:formatNumber value="${valortotal/2}" type="currency"/> s/ juros</option>
                        <option value="3x">3x de <fmt:setLocale value = "pt-BR"/> <fmt:formatNumber value="${valortotal/3}" type="currency"/> s/ juros</option>
                        <option value="4x">4x de <fmt:setLocale value = "pt-BR"/> <fmt:formatNumber value="${valortotal/4}" type="currency"/> s/ juros</option>
                        <option value="5x">5x de <fmt:setLocale value = "pt-BR"/> <fmt:formatNumber value="${valortotal/5}" type="currency"/> s/ juros</option>
                        <option value="6x">6x de <fmt:setLocale value = "pt-BR"/> <fmt:formatNumber value="${valortotal/6}" type="currency"/> s/ juros</option>
                        <option value="7x">7x de <fmt:setLocale value = "pt-BR"/> <fmt:formatNumber value="${valortotal/7}" type="currency"/> s/ juros</option>
                        <option value="8x">8x de <fmt:setLocale value = "pt-BR"/> <fmt:formatNumber value="${valortotal/8}" type="currency"/> s/ juros</option>
                        <option value="9x">9x de <fmt:setLocale value = "pt-BR"/> <fmt:formatNumber value="${valortotal/9}" type="currency"/> s/ juros</option>
                        <option value="10x">10x de <fmt:setLocale value = "pt-BR"/> <fmt:formatNumber value="${valortotal/10}" type="currency"/> s/ juros </option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-12" align="right">
                    <br>
                    <input type="hidden" name="pagamentos" value="pagamentos" />
                    <input type="hidden" name="codigousuario" value="${codigousuario}" >
                    <input type="hidden" name="endereco" value="${CodigoEndereco}" >
                    <button class="btn btn-outline-primary bInfo bVoltar" type="button" onclick="window.location.href='enderecoEntrega.jsp'">Voltar</button>
                    <button class="btn btn-outline-primary bInfo" type="submit">Confirmar</button> 
                </div>
            </div>
            </form> 
        </div>
        <jsp:include page="rodape.jsp"/>
    </body>
</html>
