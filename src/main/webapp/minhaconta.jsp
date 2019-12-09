<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="Cliente" type="br.com.weboh.kingofgames.model.Cliente" scope="session"/>
<jsp:useBean id="Endereco" type="br.com.weboh.kingofgames.model.Endereco" scope="session"/>
<jsp:useBean id="Pedido" type="br.com.weboh.kingofgames.model.Pedido" scope="session"/>
<!DOCTYPE html>
<html lang ="en">
    <head>

        <meta http-equiv=”Content-Type” content=”text/html; charset=iso-8859-1″>
        <link rel='stylesheet' type='text/css' media='screen' href='css/style.css'>

        <link rel='stylesheet' type='text/css' media='screen' href='main.css'>
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
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-1" style="background-color: #003559"></div>  
                <div class="col-md-10">


                    <div class="row bg-dark">
                        <div class="col-md-1">
                            <a class="navbar-brand" href="./index"><img src="./icons/crown.png" style="height: 50px"></a>
                        </div>
                        <div class="col-md-7">

                            <nav class="navbar navbar-expand-sm navbar-dark bg-dark justify-content-between flex-row flex-wrap">

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
            </div>

        </div>
        <!--MENSAGEM-->
        <div class="row">
            <div class="col-md-12">
                <h3 class="text-center" >

                    Bem vindo aos seus dados ${Cliente.nome} !
                </h3>
            </div>
        </div>


        <div class="row" id="userMenu">

            <div class ="col-md-2"></div>
            <div class ="col-md-2 btn">
                <span class="text-center"><a class="nav-link"  href="./minhaconta">Meus Dados</a></span>
            </div>
            <div class ="col-md-1 "></div>
            <div class ="col-md-2 btn">
                <span class="text-center"><a class="nav-link"  href="#">Meus Pedidos</a></span>
            </div>
            <div class ="col-md-1"></div>
            <div class ="col-md-2 btn">
                <span class="text-center"><a class="nav-link"  href="./sair">Sair</a></span>
            </div>
            <div class ="col-md-2"></div>

        </div>


        <div class="container" action="">

            <div class ="row camp">
                <strong>Nome: </strong> ${Cliente.nome}
            </div>

            <div class ="row camp">
                <strong>Email: </strong> ${Cliente.email}

            </div>

            <div class ="row camp">
                <strong>CPF: </strong> ${Cliente.cpf}


            </div>

            <div class ="row camp">
                <strong>Data de Nascimento: </strong> ${Cliente.dataNascimento}

            </div>

            <div class ="row camp">
                <strong>Endereço: </strong> ${Endereco.tipoLogradouro} &emsp; ${Endereco.logradouro}


            </div>
            <div class ="row camp">
                <strong>Numero: </strong> ${Endereco.numero}


            </div>
            <div class ="row camp">
                <strong>Complemento: </strong> ${Endereco.complemento}


            </div>
            <div class ="row camp">
                <strong>Bairro: </strong> ${Endereco.bairro}


            </div>
            <div class ="row camp">
                <strong>Cidade: </strong> ${Endereco.cidade}


            </div>
            <div class ="row camp">
                <strong>Estado: </strong> ${Endereco.estado}


            </div>
            <div class ="row camp">
                <strong>Pais: </strong> ${Endereco.pais}


            </div>


        </div>






    </body>
</html>