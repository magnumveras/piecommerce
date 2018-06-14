<%-- 
    Document   : enderecoEntrega
    Created on : 13/06/2018, 15:30:45
    Author     : magno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Music Store - Local de Entrega</title>
        <script type="text/javascript" src="js/programa.js"></script>
    </head>
    <body>
        <div class="container">
            <jsp:include page="topo.jsp"/>
            <form class="col-lg-12" action="${pageContext.request.contextPath}/finalizarcarrinho" method="post">
            <div class="row">
                <h2 class="col-12" align="center">Local de Entrega</h2>
                <hr>
            </div>
            <div class="row">
                    <div class="col-12 col-sm-6">
                        <label class="hEnd">Endereço</label>
                        <input type="text" name="endereco" value="${ClienteEndereco.getEndereco()}" placeholder="Endereço"
                            class="form-control" onkeydown="javascript: fMasc(this, soLetras);" required oninvalid="this.setCustomValidity('Preencha o Endereço')" oninput="setCustomValidity('')" maxlength="70" size="60"/>
                    </div>
                    <div class="col-12 col-sm-2">
                        <label class="hEnd">Número</label>
                        <input type="text" name="numero" value="${ClienteEndereco.getNumero()}"
                            class="form-control" required oninvalid="this.setCustomValidity('Preencha o número')" oninput="setCustomValidity('')" maxlength="10" placeholder="Nº" size="10"/>
                    </div>
                    <div class="col-12 col-sm-4">
                        <label class="hEnd">Complemento</label>
                        <input type="text" name="complemento" value="${ClienteEndereco.getComplemento()}"
                            class="form-control" placeholder="Complemento" size="40"/>
                    </div>
            </div>
            <div class="row">
                    <div class="col-12 col-sm-3">
                        <label class="hEnd">CEP</label>
                        <input type="text" name="cep" value="${ClienteEndereco.getCep()}"
                            size="10"
                            maxlength="9" onblur="checarCEP();"
                            class="form-control" onkeypress="return MM_formtCep(event, this, '#####-###');" placeholder="Ex 00000-000" required oninvalid="this.setCustomValidity('Preencha o CEP')" oninput="setCustomValidity('')"/>
                    </div>
                    <div class="col-12 col-sm-3">
                        <label class="hEnd">Bairro</label>
                        <input type="text" name="bairro" value="${ClienteEndereco.getBairro()}"
                            class="form-control" required oninvalid="this.setCustomValidity('Preencha o bairro')" oninput="setCustomValidity('')" maxlength="10" placeholder="Bairro" size="10"/>
                    </div>
                    <div class="col-12 col-sm-3">
                        <label class="hEnd">Estado*</label>
                            <select class="form-control" name="estado" id="estado">
                                <option value="SP">${ClienteEndereco.getEstado()}</option>
                                <option value="AC">Acre</option>
                                <option value="AL">Alagoas</option>
                                <option value="AP">Amapá</option>
                                <option value="AM">Amazonas</option>
                                <option value="BA">Bahia</option>
                                <option value="CE">Ceará</option>
                                <option value="DF">Distrito Federal</option>
                                <option value="ES">Espírito Santo</option>
                                <option value="GO">Goiás</option>
                                <option value="MA">Maranhão</option>
                                <option value="MT">Mato Grosso</option>
                                <option value="MS">Mato Grosso do Sul</option>
                                <option value="MG">Minas Gerais</option>
                                <option value="PA">Pará</option>
                                <option value="PB">Paraíba</option>
                                <option value="PR">Paraná</option>
                                <option value="PE">Pernambuco</option>
                                <option value="PI">Piauí</option>
                                <option value="RJ">Rio de Janeiro</option>
                                <option value="RN">Rio Grande do Norte</option>
                                <option value="RS">Rio Grande do Sul</option>
                                <option value="RO">Rondonia</option>
                                <option value="RR">Rorâima</option>
                                <option value="SC">Santa Catarina</option>
                                <option value="SP">São Paulo</option>
                                <option value="SG">Sergipe</option>
                                <option value="TO">Tocantins</option>
                            </select>
                    </div>
                    <div class="col-12 col-sm-3">
                        <label class="hEnd">Cidade</label>
                        <input type="hidden" name="endrecook" value="endrecook" />
                        <input type="text" name="cidade" value="${ClienteEndereco.getCidade()}"
                            placeholder="Cidade" size="30"
                            class="form-control" onkeydown="javascript: fMasc(this, soLetras);" required oninvalid="this.setCustomValidity('Preencha a Cidade')" oninput="setCustomValidity('')" maxlength="30"/>
                    </div>
            </div>
            <div class="row">
                <div class="col-12" align="right">
                    <br>
                    <button class="btn btn-outline-primary bInfo bVoltar" type="button" onclick="window.location.href='carrinho.jsp'">Voltar</button>
                    <button class="btn btn-outline-primary bInfo" type="submit">Confirmar</button> 
                </div>
            </div>
            </form> 
       </div>
        <jsp:include page="rodape.jsp"/>
    </body>
</html>
