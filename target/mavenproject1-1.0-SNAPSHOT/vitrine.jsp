<jsp:useBean id="Cliente" type="br.com.weboh.kingofgames.model.Cliente" scope="session"/>
<jsp:useBean id="Lista" type="java.util.List" scope="session"/>
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


        <script src='main.js'></script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    </head>
    <body>


        <!-- MEU NAVBAR -->
        <div class="container">
            <div class="row bg-dark">
                <div class="col-md-2">
                    <a class="navbar-brand" href="./index"><img src="./icons/crown.png" style="height: 50px"></a>
                </div>
                <div class="col-md-7">

                    <nav class="navbar navbar-expand-sm navbar-dark bg-dark justify-content-between flex-row flex-wrap">





                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#conteudoNavbarSuportado" aria-controls="conteudoNavbarSuportado" aria-expanded="false" aria-label="Alterna navega��o">
                            <span class="navbar-toggler-icon "></span>
                        </button>

                        <div class="collapse navbar-collapse w-100" id="conteudoNavbarSuportado">
                            <ul class="navbar-nav mr-auto">
                                <li class="nav-item active">
                                    <a class="nav-link" href="./index">Home <span class="sr-only">(p�gina atual)</span></a>
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
        <!-- MINHA LISTA DE PRODUTOS -->
        <div class="container w-100">
            <c:forEach var="produto" items="${Lista}">
                <div class="row luz">
                    <div class="col-md-2 camp">
                        <a href="./produtodetalhes?id=${produto.id}"><strong>${produto.nome}</strong></a>
                    </div>
                    <div class="col-md-6 camp">
                        <h10>${produto.descricao}</h10>
                    </div>
                    <div class ="col-md-2 camp ">
                        <strong>${produto.valor}</strong>
                    </div>

                    <div class="col-md-2 camp">

                        Disponivel:${produto.quantidade}

                        <form role="form" action="addpedido?id=${produto.id}" method="POST">
                            <label for="inputQtde">Qtde</label>
                            <input type="number" class="form-control" id="inputQtde${produto.id}" name="txtQtde${produto.id}">

                            <button type="submit" class="addCart btn btn-sm" id="btnAddCart">Adicionar ao carrinho</button>
                        </form>
                    </div>

                </div>
            </c:forEach>

        </div>
    </body>
</html>
