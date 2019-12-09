<jsp:useBean id="Cliente" type="br.com.weboh.kingofgames.model.Cliente" scope="session"/>
<jsp:useBean id="Carrinho" type="java.util.List" scope="session"/>
<jsp:useBean id="Pedido" type="br.com.weboh.kingofgames.model.Pedido" scope="session"/>

<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>King of Cards</title>
        <meta name='viewport' content='width=device-width, initial-scale=1'>

        <link rel='stylesheet' type='text/css' media='screen' href='main.css'>
        <link rel='stylesheet' type='text/css' media='screen' href='css/style.css'>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <script src='main.js'></script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    </head>
    <body>


        <!-- MEU NAVBAR -->
        <div class="container">
            <div class="row bg-dark">
                <div class="col-md-1">
                    <a class="navbar-brand" href="./index"><img src="./icons/crown.png" style="height: 50px"></a>
                </div>
                <div class="col-md-7">

                    <nav class="navbar navbar-expand-sm navbar-dark bg-dark justify-content-between flex-row flex-wrap">





                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#conteudoNavbarSuportado" aria-controls="conteudoNavbarSuportado" aria-expanded="false" aria-label="Alterna navegação">
                            <span class="navbar-toggler-icon "></span>
                        </button>

                        <div class="collapse navbar-collapse w-100" id="conteudoNavbarSuportado">
                            <ul class="navbar-nav mr-auto">
                                <li class="nav-item active">
                                    <a class="nav-link" href="./index">Home <span class="sr-only">(página atual)</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="./vitrine?src=1" name="linkMTG">MTG</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="./vitrine?src=2" name="linkYGO">Yu-gi-oh</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="./vitrine?src=3" name="linkPKO">Pokemon</a>
                                </li>
                            </ul>

                            <!--<form class="form-inline my-2 my-lg-0">
                                <input class="form-control mr-sm-2" type="search" placeholder="Pesquisar" aria-label="Pesquisar">
                                <button class="btn  my-2 my-sm-0" type="submit">Pesquisar</button>
                            </form>-->
                        </div>










                    </nav>
                </div>

                <div class="col-md-1">
                    <a href="./loginoumeusdados"><img src="./icons/avatar.png" style="height: 50px;"></a>
                </div>
                
                <div class="col-md-1 nome">
                    <strong>${Cliente.nome}</strong>
                </div>
                <div class="col-md-1">
                    <a href="./carrinho"><img src="./icons/cart.png" style="height: 30px" id="cadastro"></a>
                </div>
            </div>

        </div>
                
                
                
        <c:if test="${Carrinho.size() eq 0}">
            <div class="row">
                <div class="col-md-12">
                    <h3 align="center">Carrinho Vazio </h3>
                </div>
            </div>
        </c:if>

        <c:if test="${Carrinho.size() gt 0}">

            <div class="container w-100">
                <c:forEach var="itens" items="${Carrinho}">
                    <div class="row luz">
                        <div class="col-md-6 camp">
                            <a href="./produtodetalhes?id=${itens.idProduto}"><strong>${itens.nomeProduto}</strong></a>
                        </div>
                        <div class="col-md-1 camp">
                            <h9>${itens.quantidade}</h9>
                        </div>
                        <div class ="col-md-1 camp ">
                            <strong>${itens.valorTotal}</strong>
                        </div>
                        <div class ="col-md-2 camp ">
                           <form role="form" action="addpedido?id=${itens.idProduto}" method="POST">
                            <label for="inputQtde">Alterar</label>
                            <input type="number" class="form-control" style="padding: 2% 0;" id="inputQtde${itens.idProduto}" name="txtQtde${itens.idProduto}">

                            <button type="submit" class="addCart btn btn-sm" id="btnAddCart"><i class="fa fa-refresh fa-spin" style="font-size:18px"></i></button>
                        </form>
                        </div>

                        <div class="col-md-2 camp name">

                            <a href="./deletaproduto?id=${itens.idProduto}">Remover</a>
                            
                        </div>
                    </div>

                </c:forEach>
            </div>
        </c:if>
                <div class="row">
                    <div class ="col-md-2 camp ">
                        <strong>Total: </strong>${Pedido.valorTotal}
                        </div>
                </div>  
    </body>
</html>
