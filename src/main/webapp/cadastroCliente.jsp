<%-- 
    Document   : cadastroCliente
    Created on : 01/06/2018, 21:16:18
    Author     : magno
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="js/programa.js"></script>
        <script>
        function fMasc(objeto, mascara) {
                obj = objeto;
                masc = mascara;
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
            function mTel(tel) {
                tel = tel.replace(/\D/g, "")
                tel = tel.replace(/^(\d)/, "($1")
                tel = tel.replace(/(.{3})(\d)/, "$1)$2")
                if (tel.length === 9) {
                    tel = tel.replace(/(.{1})$/, "-$1")
                } else if (tel.length == 10) {
                    tel = tel.replace(/(.{2})$/, "-$1")
                } else if (tel.length == 11) {
                    tel = tel.replace(/(.{3})$/, "-$1")
                } else if (tel.length == 12) {
                    tel = tel.replace(/(.{4})$/, "-$1")
                } else if (tel.length > 12) {
                    tel = tel.replace(/(.{4})$/, "-$1")
                }
                return tel;
            }
            function mNum(num) {
                num = num.replace(/\D/g, "")
                return num
            }

            function MM_formtCep(e, src, mask) {
                if (window.event) {
                    _TXT = e.keyCode;
                } else if (e.which) {
                    _TXT = e.which;
                }
                if (_TXT > 47 && _TXT < 58) {
                    var i = src.value.length;
                    var saida = mask.substring(0, 1);
                    var texto = mask.substring(i)
                    if (texto.substring(0, 1) != saida) {
                        src.value += texto.substring(0, 1);
                    }
                    return true;
                } else {
                    if (_TXT != 8) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            function mRG(v) {
                v = v.replace(/\D/g, '');
                v = v.replace(/^(\d{2})(\d)/g, "$1.$2");
                v = v.replace(/(\d{3})(\d)/g, "$1.$2");
                v = v.replace(/(\d{3})(\d)/g, "$1-$2");
                return v;
            }

            /*function verifica() {
                if (document.forms[0].email.value.length == 0) {
                    alert('Por favor, informe o seu EMAIL.');
                    document.frmEnvia.email.focus();
                    return false;
                }
                return true;
            }

            function checarEmail() {
                if (document.forms[0].email.value == ""
                        || document.forms[0].email.value.indexOf('@') == -1
                        || document.forms[0].email.value.indexOf('.') == -1)
                {
                    alert("Por favor, informe um E-mail válido!");
                    return false;
                }
            }

            function checarTelefone1() {
                if (document.forms[0].tel1.value == ""
                        || document.forms[0].tel1.value.indexOf('-') == -1)
                {
                    alert("Por favor, informe um Telefone válido!");
                    return false;
                }
            }

            function checarTelefone2() {
                if (document.forms[0].tel2.value == ""
                        || document.forms[0].tel2.value.indexOf('-') == -1)
                {
                    alert("Por favor, informe um Telefone válido!");
                    return false;
                }
            }

            function checarRG() {
                if (document.forms[0].rg.value == ""
                        || document.forms[0].rg.value.indexOf('-') == -1)
                {
                    alert("Por favor, informe um RG válido!");
                    return false;
                }
            }

            function checarCPF() {
                if (document.forms[0].cpf.value == ""
                        || document.forms[0].cpf.value.indexOf('-') == -1)
                {
                    alert("Por favor, informe um CPF válido!");
                    return false;
                }
            }

            function checarCEP() {
                if (document.forms[0].cep.value == ""
                        || document.forms[0].cep.value.indexOf('-') == -1)
                {
                    alert("Por favor, informe um CEP válido!");
                    return false;
                }
            }*/

            function soLetras(v) {
                return v.replace(/\d/g, "") //Remove tudo o que não é Letra
            }

        </script>
    </head>
    <jsp:include page="topo.jsp"/> 
    <body>
        <div class="container">
            <div class="row">
                <div class="col-12">
                <hr class="hr">
                <c:choose>
                        <c:when test="${empty Altera}">
                            <h1 class="d-flex justify-content-center">Cadastro de Clientes</h1>
                        </c:when>
                        <c:otherwise>
                            <h1 class="d-flex justify-content-center">Alteração de Clientes</h1>
                        </c:otherwise>
                    </c:choose>   
                </div>
            </div>
            <div class="row">
                <div class="col-12 text-center">
                    <!--Variáveis vem do Servlet para verificação de campos -->
                        <c:if test="${not empty cpfexiste}">
                        <h5 class="blink" style="color: red"><c:out value="${cpfexiste}"/></h5>
                        </c:if>
                        <c:if test="${empty cpfexiste}">
                        <h5 class="blink" style="color: red"><c:out value="${cpfexiste}"/></h5>
                        </c:if>
                        <c:if test="${not empty loginexiste}">
                        <h5 class="blink" style="color: red"><c:out value="${loginexiste}"/></h5>
                        </c:if>
                        <c:if test="${empty loginexiste}">
                        <h5 class="blink" style="color: red"><c:out value="${loginexiste}"/></h5>
                        </c:if> 
                </div>
            </div>
            <c:choose>
                <c:when test="${empty Altera}">
                    <form class="col-lg-12" action="${pageContext.request.contextPath}/cadastroCliente" method="post">
                    <div class="row">
                        <div class="form-group col-12 col-sm-3">
                            <label for="inputNome" >Nome*</label>
                            <input name="nome" type="text" class="form-control" style="width: 100%;" id="inputNome" placeholder="Nome">
                        </div> 
                        <div class="form-group col-12 col-sm-3">
                            <label for="inputNome" >Sobrenome*</label>
                            <input name="sobrenome" type="text" class="form-control" style="width: 100%;" id="inputNome" placeholder="Nome">
                        </div>
                        <div class="form-group col-12 col-sm-3">
                            <label for="inputNome" >Sexo*</label>
                            <br>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="sexo" id="radioFeminino" value="f">Feminino
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="sexo" id="radioMasculino" value="m">Masculino
                            </div>
                        </div> 
                        <div class="form-group col-12 col-sm-3">
                            <form method="post" action="" onSubmit="return (verifica())" name="frmEnvia"/>
                            <label for="labelCPF">CPF*</label>
                            <input type="text" class="form-control" style="width: 100%;" id="inputTelefone"
                                name="cpf" onblur="checarCPF()();" onkeydown="javascript: fMasc(this, mCPF);" maxlength="14" placeholder="Ex 000.000.000-00" required oninvalid="this.setCustomValidity('Preencha o CPF')" oninput="setCustomValidity('')">
                        </div>
             
                    </div>
                    <div class="row">
                        <div class="form-group col-12 col-sm-3">
                            <form method="post" action="" onSubmit="return (verifica())" name="frmEnvia"/>
                                <label for="labelCPF">RG*</label>
                                <input type="text" class="form-control" style="width: 100%;" id="inputTelefone"
                                name="rg" onblur="checarRG()();" onkeydown="javascript: fMasc(this, mRG);" maxlength="12" placeholder="Ex 00.000.000-0" required oninvalid="this.setCustomValidity('Preencha o RG')" oninput="setCustomValidity('')">
                        </div>
                        <div class="form-group col-12 col-sm-3">
                            <label for="inputNascimento">Nascimento</label>
                            <input name="nascimento" type="date" class="form-control" style="width: 100%;" id="inputNascimento">
                        </div>
                        <div class="form-group col-12 col-sm-3">
                            <label for="inputUsuario">Usuário*</label>
                            <input type="text" class="form-control" style="width: 100%;" id="inputUsuario"
                                name="usuario" placeholder="usuário">
                        </div>
                        <div class="form-group col-12 col-sm-3">
                            <label for="inputSenha">Senha*</label>
                            <input type="password" class="form-control" style="width: 100%;" id="inputSenha"
                               name="senha" placeholder="Senha">
                        </div>
                    </div>
                    <div  class="row">
                        <div class="form-group col-12 col-sm-3">
                            <label for="inputEmail">Email</label>
                            <input name="email" type="email" class="form-control" style="width: 100%;" id="inputEmail" placeholder="Email">
                        </div>
                        <div class="form-group col-12 col-sm-3">
                            <label for="cep">CEP*</label>
                            <input name="cep" type="text" class="form-control" style="width: 100%;" id="cep" placeholder="CEP"
                                   size="10"
                                   maxlength="9" onblur="checarCEP();"
                                   onkeypress="return MM_formtCep(event, this, '#####-###');" maxlength="9" placeholder="Ex 00000-000" required oninvalid="this.setCustomValidity('Preencha o CEP')" oninput="setCustomValidity('')">
                        </div>
                        <div class="form-group col-12 col-sm-4">
                            <label for="endereco">End*</label>
                            <input name="endereco" type="text" class="form-control" style="width: 100%;" id="rua" placeholder="Rua"
                                   accept=""onkeydown="javascript: fMasc(this, soLetras);" required oninvalid="this.setCustomValidity('Preencha o Endereço')" oninput="setCustomValidity('')" maxlength="70" size="60">
                        </div>
                        <div class="form-group col-12 col-sm-2">
                            <label for="numero">Número*</label>
                            <input name="numero" type="text" class="form-control" style="width: 100%;" id="numero"
                            placeholder="Nº" size="10">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-12 col-sm-3">
                            <label for="complemento">Complemento</label>
                            <input name="complemento" type="text"  class="form-control" style="width: 100%;" id="complemento"
                                   accept=""placeholder="Complemento" size="40">
                        </div>
                        <div class="form-group col-12 col-sm-3">
                            <label for="bairro">Bairro*</label>
                            <input name="bairro" type="text" class="form-control" style="width: 100%;" id="bairro"
                                   placeholder="Bairro" size="40">
                        </div>
                        <div class="form-group col-12 col-sm-3">
                            <label for="cidade">Cidade*</label>
                            <input name="cidade" type="text" class="form-control" style="width: 100%;" id="cidade"
                                placeholder="Cidade" size="30"
                                onkeydown="javascript: fMasc(this, soLetras);" required oninvalid="this.setCustomValidity('Preencha a Cidade')" oninput="setCustomValidity('')" maxlength="30">
                        </div>
                        <div class="form-group col-12 col-sm-3">
                            <label for="perfil">Estado*</label>
                            <select class="form-control" name="estado" id="estado">
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
                    </div>
                    <div class="row">
                        <div class="form-group col-12 col-sm-3">
                            <form method="post" action="" onSubmit="return (verificaTel1())" name="frmEnvia"/>
                            <label for="desc" id="tel">Telefone</label>
                            <input class="form-control" name="telefone" id="tel1" onblur="checarTelefone1();" onkeydown="javascript: fMasc(this, mTel);" maxlength="14" placeholder="Ex (00)0000-0000">
                        </div>
                        <div class="form-group col-12 col-sm-4">
                            <input name="ofertas" type="checkbox" value="True" >Desejo receber ofertas por e-mail
                        </div>
                        <div class="col-12 col-sm-2">
                            <button type="reset" class="btn" onclick="voltar()">Cancelar</button>
                        </div>
                        <div class="col-12 col-sm-3" >
                            <button type="submit" class="btn">Salvar</button>
                        </div>
                    </div>
                    </form>
                </c:when>
                <c:otherwise>
                    <form class="col-lg-12" action="${pageContext.request.contextPath}/alterarCliente" method="post">
                    <div class="row">
                        <div class="form-group col-12 col-sm-3">
                            <label for="inputNome" >Nome*</label>
                            <input name="nome" value="${cli.nome}" type="text" class="form-control" style="width: 100%;" id="inputNome" placeholder="Nome">
                        </div> 
                        <div class="form-group col-12 col-sm-3">
                            <label for="inputNome" >Sobrenome*</label>
                            <input name="sobrenome" value="${cli.sobrenome}" type="text" class="form-control" style="width: 100%;" id="inputNome" placeholder="Nome">
                        </div>
                        <div class="form-group col-12 col-sm-3">
                            <c:set var="sexo" value="${cli.getSexo()}"/>
                            <label for="inputNome" >Sexo*</label>
                            <br>
                            <c:if test="${sexo eq 'Masculino'}">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="sexo" id="radioFeminino" value="f">Feminino
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="sexo" id="radioMasculino" value="m" checked>Masculino
                                </div>
                            </c:if>
                            <c:if test="${sexo eq 'Feminino'}">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="sexo" id="radioFeminino" value="f" checked>Feminino
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="sexo" id="radioMasculino" value="m">Masculino
                                </div>
                            </c:if>
                        </div> 
                        <div class="form-group col-12 col-sm-3">
                            <form method="post" action="" onSubmit="return (verifica())" name="frmEnvia"/>
                            <label for="labelCPF">CPF*</label>
                            <input type="text" class="form-control" style="width: 100%;" id="inputTelefone"
                                   value="${cli.cpf}" name="cpf" onblur="checarCPF()();" onkeydown="javascript: fMasc(this, mCPF);" maxlength="14" placeholder="Ex 000.000.000-00" required oninvalid="this.setCustomValidity('Preencha o CPF')" oninput="setCustomValidity('')">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-12 col-sm-3">
                            <form method="post" action="" onSubmit="return (verifica())" name="frmEnvia"/>
                            <label for="labelCPF">RG*</label>
                            <input type="text" class="form-control" style="width: 100%;" id="inputTelefone"
                                   value="${cli.rg}" accept=""name="rg" onblur="checarRG()();" onkeydown="javascript: fMasc(this, mRG);" maxlength="12" placeholder="Ex 00.000.000-0" required oninvalid="this.setCustomValidity('Preencha o RG')" oninput="setCustomValidity('')">
                        </div>  
                        <div class="form-group col-12 col-sm-3">
                            <label for="inputNascimento">Nascimento</label>
                            <input value="${cli.datanascimento}" name="nascimento" type="date" class="form-control" style="width: 100%;" id="inputNascimento">
                        </div>
                        <div class="form-group col-12 col-sm-3">
                            <label for="inputUsuario">Usuário*</label>
                            <input type="text" class="form-control" style="width: 100%;" id="inputUsuario"
                                   value="${usu.login}" name="usuario" placeholder="usuário">
                        </div>
                        <div class="form-group col-12 col-sm-3">
                            <label for="inputSenha">Senha*</label>
                            <input type="password" class="form-control" style="width: 100%;" id="inputSenha"
                                   value="${usu.senha}" name="senha" placeholder="Senha">
                        </div>
                    </div>
                    <div  class="row">
                        <div class="form-group col-12 col-sm-3">
                            <label for="inputEmail">Email</label>
                            <input value="${cli.email}" name="email" type="email" class="form-control" style="width: 100%;" id="inputEmail" placeholder="Email">
                        </div>
                        <div class="form-group col-12 col-sm-3">
                            <label for="cep">CEP*</label>
                            <input name="cep" type="text" class="form-control" style="width: 100%;" id="cep" placeholder="CEP"
                                   value="${cli.cep}" size="10"
                                   maxlength="9" onblur="checarCEP();"
                                   onkeypress="return MM_formtCep(event, this, '#####-###');" maxlength="9" placeholder="Ex 00000-000" required oninvalid="this.setCustomValidity('Preencha o CEP')" oninput="setCustomValidity('')">
                        </div>
                        <div class="form-group col-12 col-sm-4">
                            <label for="endereco">End*</label>
                            <input name="endereco" type="text" class="form-control" style="width: 100%;" id="rua" placeholder="Rua"
                                   value="${cli.endereco}" onkeydown="javascript: fMasc(this, soLetras);" required oninvalid="this.setCustomValidity('Preencha o Endereço')" oninput="setCustomValidity('')" maxlength="70" size="60">
                        </div>
                        <div class="form-group col-12 col-sm-2">
                            <label for="numero">Número*</label>
                            <input name="numero" type="text" class="form-control" style="width: 100%;" id="numero"
                                   value="${cli.numero}" placeholder="Nº" size="10">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-12 col-sm-3">
                            <label for="complemento">Complemento</label>
                            <input name="complemento" type="text"  class="form-control" style="width: 100%;" id="complemento"
                                   value="${cli.complemento}" placeholder="Complemento" size="40">
                        </div>
                        <div class="form-group col-12 col-sm-3">
                            <label for="bairro">Bairro*</label>
                            <input name="bairro" type="text" class="form-control" style="width: 100%;" id="bairro"
                                   value="${cli.bairro}" placeholder="Bairro" size="40">
                        </div>
                        <div class="form-group col-12 col-sm-3">
                            <label for="cidade">Cidade*</label>
                            <input name="cidade" type="text" class="form-control" style="width: 100%;" id="cidade"
                                   value="${cli.cidade}" placeholder="Cidade" size="30"
                                   onkeydown="javascript: fMasc(this, soLetras);" required oninvalid="this.setCustomValidity('Preencha a Cidade')" oninput="setCustomValidity('')" maxlength="30">
                        </div>
                        <div class="form-group col-12 col-sm-3">
                            <label for="perfil">Estado*</label>
                            <select class="form-control" name="estado" id="estado">
                                <option><c:out value="${cli.estado}"/></option>
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
                    </div>
                    <div class="row">
                        <div class="form-group col-12 col-sm-3">
                            <form method="post" action="" onSubmit="return (verificaTel1())" name="frmEnvia"/>
                            <label for="desc" id="tel">Telefone</label>
                            <input value="${cli.telefone}" class="form-control" name="telefone" id="tel1" onblur="checarTelefone1();" onkeydown="javascript: fMasc(this, mTel);" maxlength="14" placeholder="Ex (00)0000-0000">
                        </div>
                        <div class="form-group col-12 col-sm-4">
                            <c:if test="${cli.ofertas eq true}">
                                <input name="ofertas" type="checkbox" value="True" checked>Desejo receber ofertas por e-mail
                            </c:if>
                            <c:if test="${cli.ofertas eq false}">
                                <input name="ofertas" type="checkbox" value="False" >Desejo receber ofertas por e-mail
                            </c:if>
                        </div>
                        <div class="col-12 col-sm-2">
                            <button type="reset" class="btn" onclick="voltar()">Cancelar</button>
                        </div>
                        <div class="col-12 col-sm-3" >
                            <button type="submit" class="btn">Salvar</button>
                        </div>
                    </div>
                    </form>
                </c:otherwise>
           </c:choose>
    </div>
    </body>
    <jsp:include page="rodape.jsp"/> 
</html>
