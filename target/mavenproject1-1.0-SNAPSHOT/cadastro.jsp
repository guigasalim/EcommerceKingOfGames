<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <script src="xregexp.js"></script>
        <script src="addons/unicode/unicode-base.js"></script>
        <script>
            var unicodeWord = XRegExp("^\\p{L}+$");

            unicodeWord.test("Русский"); // true
            unicodeWord.test("日本語"); // true
            unicodeWord.test("العربية"); // true
        </script>
        <script type="text/javascript">
            function buscaEndereco() {
                var cep = document.getElementById("inputCEP").value;
                var xmlhttp = new XMLHttpRequest();
                var url = "http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=json";
                xmlhttp.onreadystatechange = function () {
                    if (xmlhttp.status === 200) {
                        if (xmlhttp.readyState === 4) {
                            var endereco = JSON.parse(xmlhttp.responseText);
                            if (endereco.resultado == "1") {
                                document.getElementById("inputTipoLogradouro").value = endereco.tipo_logradouro;
                                document.getElementById("inputLogradouro").value = endereco.logradouro;
                                document.getElementById("inputBairro").value = endereco.bairro;
                                document.getElementById("inputCidade").value = endereco.cidade;
                                document.getElementById("inputUF").value = endereco.uf;
                                document.getElementById("inputPais").value = "Brasil"
                                document.getElementById("inputNumber").value = 0;
                            } else {
                                alert("Endereco nao encontrado pelo CEP informado");
                            }
                        }
                    }
                };
                xmlhttp.open("GET", url, true);
                xmlhttp.send();

            }
        </script>


        <title>King of Cards</title>
    </head>
    <body>


       <!-- MEU NAVBAR -->
                    <div class="row bg-dark">
                        <div class="col-md-1">
                            <a class="navbar-brand" href="./index"><img src="./icons/crown.png" style="height: 50px"></a>
                        </div>
                        <div class="col-md-9">

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
                            <a href="./loginoumeusdados"><img src="./icons/avatar.png" style="height: 45px;padding-top:15px;"></a>
                        </div>
                        <div class="col-md-1">
                            <a href="./carrinho"><img src="./icons/cart.png" style="height: 45px;padding-top:15px" id="cadastro"></a>
                        </div>

                    </div>


                    <!--MEU FORMULARIO-->


                    <div class="row">
                        <div class="col-md-12">
                            <h3 class="text-center" style="padding:50px;">
                                Cadastro de Usuário
                                <br>        
                            </h3>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-8">
                            <form role="form" action="efetivacadastro" method="POST"> 
                                <div class="form-group">
                                    <input type="name" class="form-control" id="inputName" placeholder="Nome Completo" name="txtNome">
                                    <br>
                                </div>
                                <div class="form-group">

                                    <input type="email" class="form-control" id="inputEmail" placeholder="Email" name="txtEmail">
                                    <br>
                                </div>
                                <div class="form-group">    
                                    <input type="password" class="form-control" id="inputPass" placeholder="Senha" name="txtSenha">
                                    <br>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control cpf-mask" id="inputCPF" placeholder="CPF" name="txtCPF">
                                    <br>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control cpf-mask" id="celular" placeholder="Celular" name="txtTelefone">
                                    <br>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control"  id="inputDate" placeholder="Data de Nascimento" name="txtAniversario">
                                    <br>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="inputCEP" placeholder="CEP" name="txtCep" onblur="buscaEndereco();">
                                </div>
                                <br>
                                <div class="form-group input-group mb-3">

                                    <input type="text" class="form-control" id="inputTipoLogradouro" placeholder="Tipo" name="txtTipoLogradouro" readonly>
                                    <input type="text" class="form-control" id="inputLogradouro" placeholder="Endereço" name="txtLogradouro" readonly>
                                    <br>
                                </div>
                                <br>
                                <div class="form-group">

                                    <input type="text" class="form-control" id="inputNumber" placeholder="Número" name="txtNumero">
                                    <br>
                                </div>
                                <div class="form-group">    
                                    <input type="text" class="form-control" id="inputCompl" placeholder="Complemento" name="txtCompl">
                                    <br>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="inputBairro" placeholder="Bairro" name="txtBairro" readonly>
                                    <br>
                                </div>
                                <div class="form-group">    
                                    <input type="text" class="form-control" id="inputCidade" placeholder="Cidade" name="txtCidade" readonly>
                                    <br>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="inputUF" placeholder="Estado" name="txtEstado" readonly>
                                    <br>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="inputPais" placeholder="Pais" name="txtPais">
                                    <br>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" /> Desejo receber as melhores promoções e noticias por e-mail.
                                    </label>
                                </div> 
                                <button type="submit" class="btn btn-primary">
                                    Efetivar Cadastro
                                </button>
                                <br><br>
                                </div>
                            </form>   


                        </div>

                    </div>

                    <div class="col-md-1" style="background-color: #003559"></div>  
                </div>
            </div>        
    </body>
</html>