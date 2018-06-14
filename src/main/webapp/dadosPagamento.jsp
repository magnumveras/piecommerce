<%-- 
    Document   : dadosPagamento
    Created on : 13/06/2018, 20:00:48
    Author     : magno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Music Store - Forma de Pagamento</title>
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
                    <div class="col-12 col-sm-1" align="center">
                        <label class="hEnd">Crédito</label>
                        <input class="form-check" type="radio" name="cartao" value="1" checked/>
                    </div>
                    <div class="col-12 col-sm-1" align="center">
                        <label class="hEnd">Débito</label>
                        <input class="form-check" type="radio" name="cartao" value="1"/>
                    </div>
                    <div class="col-12 col-sm-4">
                        <label class="hEnd">Número do Cartão</label>
                        <input type="number" name="numerocartao" value=""
                            class="form-control" placeholder="Número do Cartão" size="30"/>
                    </div>
                    <div class="col-12 col-sm-4">
                        <label class="hEnd">Nome no Cartão</label>
                        <input type="number" name="nomecartao" value=""
                            class="form-control" placeholder="Nome no Cartão" size="20"/>
                    </div>
                    <div class="col-12 col-sm-2">
                        <label class="hEnd">Código de Segurança</label>
                        <input type="number" name="codigoseguranca" value=""
                            class="form-control" placeholder="cod" size="3"/>
                    </div>
            </div>
            <div class="row">
                <div class="col-12" align="right">
                    <br>
                    <button class="btn btn-outline-primary bInfo bVoltar" type="button" onclick="window.location.href='enderecoEntrega.jsp'">Voltar</button>
                    <button class="btn btn-outline-primary bInfo" type="submit">Confirmar</button> 
                </div>
            </div>
            </form> 
        </div>
        <jsp:include page="rodape.jsp"/>
    </body>
</html>
