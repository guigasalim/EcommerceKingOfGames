<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="erroSTR" type="java.lang.String" scope="request"/>
<jsp:useBean id="Cliente" type="br.com.weboh.kingofgames.model.Cliente" scope="session"/>
<jsp:useBean id="Pedido" type="br.com.weboh.kingofgames.model.Pedido" scope="session"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel='stylesheet' type='text/css' media='screen' href='main.css'>
        <link rel='stylesheet' type='text/css' media='screen' href='css/style.css'>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">



        <script src='main.js'></script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script>
            var unicodeWord = XRegExp("^\\p{L}+$");

            unicodeWord.test("Русский"); // true
            unicodeWord.test("日本語"); // true
            unicodeWord.test("العربية"); // true
        </script>


        <title>King of Cards</title>
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

                <<div class="col-md-1">
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
        <!--MENSAGEM-->


        <div class="row">
            <div class="col-md-12">
                <h3 class="text-center" style="padding-top:200px;">
                    <img src="./img/dead-pikachu.gif" style="height: 200px">
                    <br>
                    ERRO: Desculpe-nos pelo transtorno, estamos trabalhando para melhor atendê-los, tente novamente em segundos.
                </h3>
            </div>
        </div>



    </body>
</html>
