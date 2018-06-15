<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% HttpSession sessao = request.getSession();%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <link rel="stylesheet" type="text/css" href="css/reset.css">
        <link rel="apple-touch-icon" sizes="57x57" href="/apple-icon-57x57.png">
        <link rel="apple-touch-icon" sizes="60x60" href="/apple-icon-60x60.png">
        <link rel="apple-touch-icon" sizes="72x72" href="/apple-icon-72x72.png">
        <link rel="apple-touch-icon" sizes="76x76" href="/apple-icon-76x76.png">
        <link rel="apple-touch-icon" sizes="114x114" href="/apple-icon-114x114.png">
        <link rel="apple-touch-icon" sizes="120x120" href="/apple-icon-120x120.png">
        <link rel="apple-touch-icon" sizes="144x144" href="/apple-icon-144x144.png">
        <link rel="apple-touch-icon" sizes="152x152" href="/apple-icon-152x152.png">
        <link rel="apple-touch-icon" sizes="180x180" href="/apple-icon-180x180.png">
        <link rel="icon" type="image/png" sizes="192x192"  href="/android-icon-192x192.png">
        <link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="96x96" href="/favicon-96x96.png">
        <link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png">
        <link rel="manifest" href="/manifest.json">
        <meta name="msapplication-TileColor" content="#ffffff">
        <meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
        <meta name="theme-color" content="#ffffff">


        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
        <link rel="stylesheet" href="css/estilo.css"></link>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.matchHeight/0.7.0/jquery.matchHeight-min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

        <script type="text/javascript" src="js/programa.js"></script>

        <title>Music Store</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-12" id="logotopo">
                    <!-- Logo -->
                    <a href="index.jsp" onclick="<%sessao.removeAttribute("escondecarr");%>"><img src="imagens/logomarca.png"  alt="Logotipo" class="logotipo"
                                             title="Voltar ao Home"></a>

                    <!--Botões superiores -->
                    <div class="btn btn-group">
                        <c:if test="${not empty loginoperacao}">
                            <form action="${pageContext.request.contextPath}/logout" method="Get">
                                <button class="btn btn-danger" type="submit">Sair</button>
                            </form>
                        </c:if>
                        <c:if test="${not empty logincliente}">
                            <form action="${pageContext.request.contextPath}/logout" method="Get">
                                <button class="btn btn-danger" type="submit">Sair</button>
                            </form>
                        </c:if>
                        <c:if test="${(empty loginoperacao) && (empty logincliente)}">
                            <button class="btn btn-outline-secondary" id="btnLogin" type="button" data-toggle="modal" data-target="#exampleModalCenter"> Login </button>
                        </c:if>
                        <c:if test="${(empty loginoperacao)}">    
                            <a class="btn btn-outline-secondary" id="btnCarrinho" href="carrinho.jsp" onclick="<%sessao.setAttribute("escondecarr", "ok");%>">Carrinho</a>
                        </c:if>
                        &nbsp
                        <c:if test="${(not empty loginoperacao) || (not empty logincliente)}">
                            <h6><c:out value=" Bem-vindo(a) ${perfilusuario.getNome()}"/></h6>
                        </c:if>
                    </div>
                </div>

                <!-- Modal de login e senha do usuário -->
                <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
                     aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h3 class="modal-title" id="exampleModalLongTitle">Login</h3>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>

                            <div class="modal-body">
                                <form action="${pageContext.request.contextPath}/login" method="post">
                                    <div class="form-group col-md-12">
                                        <label for="usuarioLogin">Usuário</label>
                                        <input name="usuario" type="text" class="form-control" id="usuarioLogin"
                                               required oninvalid="this.setCustomValidity('Preencha o Usuário')" oninput="setCustomValidity('')" maxlength="30"
                                               placeholder="Usuário" size="30" required>
                                    </div>

                                    <div class="form-group col-md-12">
                                        <label for="senhaLogin">Senha</label>
                                        <input name="senha" type="password" class="form-control" id="senhaLogin"
                                               required oninvalid="this.setCustomValidity('Preencha a senha')" oninput="setCustomValidity('')" maxlength="30"
                                               accept=""placeholder="Senha" size="30" required>
                                        <c:if test="${not empty erro}">
                                            <h5 class="blink" style="color: red"><c:out value="${erro}"/></h5>
                                        </c:if>
                                    </div>

                                    <div class="novoUsu">
                                        <label>Novo usuário? <a href="cadastroCliente.jsp" onclick="<%sessao.setAttribute("escondecarr", "ok");%>">Criar cadastro</a></label>
                                    </div>

                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                        <button type="submit" class="btn btn-primary" >Entrar</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div> 
            <c:if test="${empty loginoperacao}">                    
                
                <div class="row">
                    <nav class="navbar navbar-expand col-4 col-sm-12" id="menuprincipal">
                             <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="nav justify-content-center">
                        <li class="nav-item">
                            <a class="nav-link active" name="categoria"
                                            href="consultaporcategoria?&categoria=1">Áudio Profissional</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" name="categoria" 
                                            href="consultaporcategoria?&categoria=2"> Bateria & Percussão</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" name="categoria"  
                                            href="consultaporcategoria?&categoria=3">Cordas & Acessórios</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" name="categoria" 
                                            href="consultaporcategoria?&categoria=4"> Pianos & Teclados</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" name="categoria" 
                                            href="consultaporcategoria?&categoria=5"> Sopro</a>
                        </li>
                    </ul>

                    <form action="${pageContext.request.contextPath}/consultaprodutosmenu" method="post" id="formbuscarprod" class="form-inline col-12 col-sm-4 input-group">
                        <div class="col-12 col-sm-7" >
                            <input class="textbox pesquisa" id="produto" name="produto" type="search" placeholder="Pesquisa">
                        </div>
                        <div class="col-12 col-sm-5" id="btnbuscar">
                            <input type="submit"class="btn btn-outline-primary">
                        </div>
                    </form>
                </div>
            </nav>
        </div>
    
            
            </c:if>
                                    
                                    
                                    
    <c:if test="${not empty loginoperacao}">
        <div class="row">
            <nav class="navbar navbar-expand col-12" id="menuoperador">
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Clientes
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="consultaCliente.jsp">Consultar/Alterar</a>
                                <a class="dropdown-item" href="cadastroCliente.jsp">Cadastrar</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                Produtos
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="consultaprodutosmenu">Consultar/Alterar</a>
                                <a class="dropdown-item" href="cadastroProduto.jsp">Cadastrar</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link" href="consultaPedidosop.jsp" role="button" aria-haspopup="true" aria-expanded="false">
                                Pedidos
                            </a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link" href="consultaVendasop.jsp" role="button" aria-haspopup="true" aria-expanded="false">
                                Vendas
                            </a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Relatórios
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </c:if>
    <c:if test="${not empty logincliente}">
        <div class="row">
            <nav class="navbar navbar-expand col-12" id="menucliente">
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Compras
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                Dados
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </c:if>
    <c:if test="${empty loginoperacao}">
        <div class="row">
            <div id="carouselExampleIndicators" class="carousel slide col-12" data-ride="carousel">
                <h2 style="display: none">Validação w3</h2>
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active fixedHeightImg">
                        <img class="d-block w-100 h-80" src="imagens/carrossel6.jpg"alt="Primeiro Slide Boss Pedais">
                    </div>
                    <div class="carousel-item fixedHeightImg">
                        <img class="d-block w-100 h-80" src="imagens/carrossel4.jpg"
                             alt="Segundo Slide FlyingV Gibson">
                    </div>
                    <div class="carousel-item fixedHeightImg">
                        <img class="d-block w-100 h-80" src="imagens/carrosel2.jpg"
                             alt="Terceiro Slide Fender Telecaster">
                    </div>
                    <div class="carousel-item fixedHeightImg">
                        <img class="d-block w-100 h-80" src="imagens/tama.jpg"
                             alt="Terceiro Slide Fender Telecaster">
                    </div>

                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>
