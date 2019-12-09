use master;
drop database chq2;

create database chq2;
use chq2;
create table tblFornecedor(
id_fornecedor integer not null auto_increment,
nome_fornecedor varchar(40),
pais varchar(15),
fone int,
constraint pk_fornecedor primary key (id_fornecedor)
);

create table tblColecao(
	id_colecao integer not null auto_increment,
	nome_colecao varchar(50) not null,
	descricao_colecao text,
    categoria_colecao varchar(50),
    id_fornecedor integer,


	constraint pk_colecao primary key (id_colecao),
    constraint fk_fornecedorCol foreign key (id_fornecedor) references tblFornecedor(id_fornecedor)


);

create table tblTipoProduto(
id_tipoProduto integer not null auto_increment,

tipo varchar(20),
constraint pk_tipoProduto primary key (id_tipoProduto)



);

create table tblProduto(
   id_produto        integer not null auto_increment,
   nome_produto    varchar(50) not null,
   
   descricao_produto text,
   id_colecao integer,
   id_tipoProduto integer,
   
   preco_produto     float,
   quantidade_disp     int,
   
   
  
   constraint pk_produto primary key (id_produto),
   constraint fk_colecaoPro foreign key (id_colecao) references tblColecao(id_colecao),
   constraint fk_tipoProduto foreign key (id_tipoProduto) references tblTipoProduto(id_tipoProduto)
   
);


create table tblCliente(
   id_cliente integer not null auto_increment,
   nome varchar(100),
   cpf varchar(12) not null,
   data_nasc date,
   email varchar(70) unique,
   senha varchar(20),

   constraint pk_cliente primary key(id_cliente)

);

create table tblFone(
id_fone integer not null auto_increment,
id_cliente integer,
fone int,
tipo varchar(20),
constraint pk_fone primary key(id_fone),
constraint fk_clienteFone foreign key (id_cliente) references
              tblCliente(id_cliente)
);

create table tblEndereco(
    id_endereco integer not null auto_increment,
    tipo_logradouro varchar(30),
    logradouro varchar(100),
    numero int,    
    complemento varchar(30),
	bairro varchar(40),
    cidade varchar(40),
    estado varchar(2),
    cep    varchar(10) not null,
	pais varchar(20),
    id_cliente int not null,

    constraint pk_endereco primary key (id_endereco),
   constraint fk_clienteEnd foreign key (id_cliente) references tblCliente(id_cliente)
);

create table tblPedido(
id_pedido integer not null auto_increment,
data_pedido varchar(30),
situacao varchar(10),
id_cliente integer,
valor_total float,
tipo_entrega varchar(20),
constraint pk_pedido primary key (id_pedido),
    constraint fk_clientePed foreign key (id_cliente) references
              tblCliente(id_cliente)
);


create table tblItemsPedido(
	id_pedido integer,
	id_produto integer,
	quantidade int,
	valor_parcial float,
	constraint fk_produtoIP foreign key (id_produto) references
              tblProduto(id_produto),
	constraint fk_pedidoIP foreign key (id_pedido) references
              tblPedido(id_Pedido)
);


SELECT * FROM information_schema.columns WHERE table_schema = 'chq2';
Insert into tblFornecedor values (null,"Konami Holdings Corporation", "Japão", 222222222);
Insert into tblFornecedor values (null,"Copag", "Brasil", 33333333);
Insert into tblFornecedor values (null,"Wizards of the Coast", "Estados Unidos", 222222222);

Insert into tblTipoProduto values(null,"Carta Avulsa");
Insert into tblTipoProduto values(null,"Booster");
Insert into tblTipoProduto values(null,"Box de Boosters");
Insert into tblTipoProduto values(null,"Starter Deck");
Insert into tblTipoProduto values(null,"Deck Estrutural");
Insert into tblTipoProduto values(null,"Edição Especial");
Insert into tblTipoProduto values(null,"Mega-Tin");
Insert into tblTipoProduto values(null,"Acessorios");

Insert into tblColecao(id_colecao,nome_colecao,descricao_colecao,categoria_colecao,id_fornecedor) values(null,
"Savage Strike",
"Prepare-se para o Golpe Selvagem, a primeira coleção de 100 estampas de 2019! O Horizonte Cibernético revitalizou a Invocação Ritual, e a Fusão da Alma criou estratégias poderosas para as Invocações-Fusão. Agora, o Golpe Selvagem está pronto para fazer o mesmo com as Invocações-Sincro! Confira uma prévia do que você poderá encontrar nesta nova coleção:
A Invocação-Sincro invadiu o VRAINS! Os Duelistas que têm acompanhado a transmissão simultânea de Yu-Gi-Oh! VRAINS já sabem que a Invocação-Sincro finalmente deu as caras no anime! Os primeiros Monstros Sincro vistos na série farão sua grande estreia no Golpe Selvagem!

Este verão pertence aos mortos-vivos! Seguindo os passos da coleção Invocadores Ocultos e do Deck Estrutural: Horda Zumbi, o Golpe Selvagem apresenta mais monstros Zumbis baseados na estratégia Sincro. Os Shiranui estiveram implacavelmente conectados aos Decks de Zumbis desde o lançamento do Shiranui Solitário – e agora eles retornam com força total graças a um novo Monstro Sincro e um Monstro Link inédito que trazem diversas vantagens às estratégias dos Zumbis-Sincro!

Retorne ao Neo Espaço!

Neos Nébula, o HERÓI do Elemento e o Conector Neo Espaço foram apenas o começo! A coleção Golpe Selvagem está chegando com mais estampas inéditas para fortalecer os Decks baseados nos HERÓIs do Elemento de Jaden! As estampas básicas para construir um Deck Neos também podem ser encontradas nas coleções Batalha das Lendas: Vingança Implacável e Sombras em Valhala. As Valquírias voltam a cavalgar!

Tudo começou nas Sombras em Valhala, onde foram introduzidos vários cards Valquíria de Ziegfried von Shroeder da série animada original. A coleção Golpe Selvagem traz novas estampas do anime e Valquírias inéditas!

Mas isso não é nem a metade do que te espera na coleção Golpe Selvagem! Você também poderá encontrar um card que destrói a mão e o campo do oponente que ousar negar uma Invocação ou ativação de card ou efeito, uma Magia que permite trocar parte do seu Deck Adicional pela compra de cards, novos monstros “T.G.” e muito mais!

A coleção Golpe Selvagem contém 100 estampas no total: 48 Comuns 20 Raras 14 Super Raras 10 Ultra Raras 8 Raras Secretas ",
"Yu-gi-Oh!",
1);

Insert into tblColecao(id_colecao,nome_colecao,descricao_colecao,categoria_colecao,id_fornecedor)  values(null,
"Chaos Impact",
"Brace for Chaos Impact, Fall 2019’s 100-card booster set! Building off themes from Rising Rampage, bringing new strategies to the limelight, and even supporting some classics, Chaos Impact is here to live up to its name and shake up the Dueling scene as Konami prepares for the new year! The Chaos Impact booster set contains 100 cards:
48 Commons
20 Rares
14 Super Rares
10 Ultra Rares
8 Secret Rares ",
"Yu-gi-Oh!",
1);
Insert into tblColecao(id_colecao,nome_colecao,descricao_colecao,categoria_colecao,id_fornecedor)  values(null,"Mega Tin 2009 Gold Sarcophagus",
"The 2019 Gold Sarcophagus Tin is fashioned after the iconic Gold Sarcophagus and is designed to reduce the amount of wear and tear sustained by the cards you keep in it, making it a stylish and efficient way to store cards that are important to you!

Each tin will come with 5 Prismatic Secret Rare variant cards:

    2 cards (from a set of 6 new cards) with art by Kazuki Takahashi, the creator of Yu-Gi-Oh!
    2 cards (from a set of 5 cards) from the original Yu-Gi-Oh! animated series, which includes the 3 Egyptian God Cards with art by Kazuki Takahashi.
    1 of 3 new World Premiere cards designed to be usable in any Deck, and help you stage a comeback against some of the fastest strategies that might come from your opponent. One card prevents Graveyard dumping, another punishes your opponent if they Summoned too many monsters, and the third gives you a reprieve if they set up a monstrously large field on their first turn.  

In addition, each tin has 3 Mega-Packs of a specially crafted set featuring popular cards released in 2018 from booster sets Flames of Destruction, Dark Saviors, Cybernetic Horizon, and Soul Fusion, like Danger!? Tsuchinoko!?, Knightmare Mermaid, and Called by the Grave. Each 16-card Mega-Pack has 12 Commons,1 Rare, 1 Super Rare, 1 Ultra Rare, and 1 Prismatic Secret Rare. All of the foil cards in these Mega-Packs appear in different rarities than their original 2018 release, adding a further element of mystery and excitement to the 2019 Gold Sarcophagus Tin!",
"Yu-gi-Oh!",
1);


Insert into tblColecao(id_colecao,nome_colecao,descricao_colecao,categoria_colecao,id_fornecedor)  values(null,
"Modern Horizons",
"Explore a rica história de Magic e um novo mundo de poder inexplorado com a primeira coleção desenhada para jogadores de Moderno. Modern Horizons liberta 254 cards para o formato, incluindo 209 cards totalmente novos, 40 mecânicas poderosas e terrenos de neve de arte cheia.",
"Magic: The Gathering",
3);

Insert into tblColecao (id_colecao,nome_colecao,descricao_colecao,categoria_colecao,id_fornecedor) values(null,"Sol e Lua 7 - Tempestade Celestial",
"A fúria rompante da natureza!

Cuidado com o vórtice! A previsão para a estação é de céus esmeralda tempestuosos e cobertos de presságios e de batalhas entre Pokémon e criaturas do Ultraespaço. Alguns se deixam levar pela tempestade, enquanto outros a desafiam. A escalação para a batalha conta com Articuno-GX, Blaziken-GX, Scizor-GX, Stakataka-GX e com o incrível
Rayquaza-GX, além de novas cartas de Latios, Latias e Jirachi. Encare de frente esta ventania de novidades com a expansão Sol e Lua Tempestade Celestial do Pokémon Estampas Ilustradas!",
"Pokemon",
2);

Insert into tblProduto (id_produto,nome_produto,descricao_produto,id_colecao,id_tipoProduto,preco_produto,quantidade_disp)values(null,
"Borreload Savage Dragon (#SAST-EN037)",
"Atributo 	TREVAS 
Tipos 	Dragão / Sincro / Efeito
Nível 	8 
ATK / DEF 	3000 / 2500
Número 	27548199
Situação 	Ilimitado (OCG e TCG)
Descrição
1 Regulador + 1+ monstros não-Reguladores
Se este card for Invocado por Invocação-Sincro: você pode equipar a este card 1 Monstro Link do seu Cemitério e, se isso acontecer, coloque Marcadores de Callibre neste card igual ao Valor Link desse Monstro Link. Este card ganha ATK igual à metade do ATK do monstro equipado a ele por este efeito. Quando seu oponente ativar um card ou efeito (Efeito Rápido): você pode remover 1 Marcador de Callibre deste card; negue a ativação. Você só pode usar este efeito de 
Dragão Selvagem Callibregado uma vez por turno.",
1,
1,
150.00,
50);
Insert into tblProduto (id_produto,nome_produto,descricao_produto,id_colecao,id_tipoProduto,preco_produto,quantidade_disp) values(null,
"Fantastical Dragon Phantazmay (#SAST-EN020)",
"Atributo 	TREVAS 
Tipos 	Dragão / Efeito
Nível 	7 
ATK / DEF 	2400 / 1800
Número 	78661338
Situação 	Ilimitado (OCG e TCG)
Descrição
Se seu oponente Invocar um ou mais Monstros Link por Invocação-Especial (exceto durante a Etapa de Dano): você pode Invocar este card por Invocação-Especial da sua mão, comprar cards igual ao número de Monstros Link que seu oponente controla +1 e, depois, embaralhar cards da sua mão no Deck igual ao número de Monstros Link que ele controla. Quando seu oponente ativar um card ou efeito que escolha como alvo um ou mais monstros que você controla (Efeito Rápido): você pode descartar 1 card; negue a ativação e, se isso acontecer, destrua-o. Você só pode usar cada efeito de Dragão Fantástico Fantazmei uma vez por turno..",
1,
1,
300.00,
3);

Insert into tblProduto  (id_produto,nome_produto,descricao_produto,id_colecao,id_tipoProduto,preco_produto,quantidade_disp)values(null,
"Pot of Extravagance(#SAST-EN067)",
"Tipo de card 	Magia MAGIA
Propriedade 	Normal Normal
Número 	49238328
Situação 	Ilimitado (OCG e TCG)
Descrição
No começo da sua Fase Principal 1: bana, com a face para baixo, 3 ou 6 cards aleatórios com a face para baixo no seu Deck Adicional; compre 1 card para cada 3 cards banidos. Pelo resto deste turno depois que este card resolver, você não pode comprar nenhum card devido ao efeito de cards.",1,1,249.99,50);

Insert into tblProduto (id_produto,nome_produto,descricao_produto,id_colecao,id_tipoProduto,preco_produto,quantidade_disp) values(null,
"Booster Chaos Impact",
"Cada pacote de boster vem com: 9 cards
A coleção contém:
8 Secret Rares
10 Ultra Rares
14 Super Rares
20 Rares
48 Commons",
2,
2,
17.99,
50);
Insert into tblProduto  (id_produto,nome_produto,descricao_produto,id_colecao,id_tipoProduto,preco_produto,quantidade_disp)values(null,
"Booster Box Chaos Impact",
"Contém 24 pacotes de booster
Cada pacote de boster vem com: 9 cards
A coleção contém:
8 Secret Rares
10 Ultra Rares
14 Super Rares
20 Rares
48 Commons",
2,
3,
349.50,
50);
Insert into tblProduto (id_produto,nome_produto,descricao_produto,id_colecao,id_tipoProduto,preco_produto,quantidade_disp) values(null,
"Booster Box Chaos Impact",
"Contém 24 pacotes de booster
Cada pacote de boster vem com: 9 cards
A coleção contém:
8 Secret Rares
10 Ultra Rares
14 Super Rares
20 Rares
48 Commons",
2,
3,
349.50,
50);


Insert into tblProduto values(null,
"Caixa de Booster - Modern Horizons",
"Explore a rica história de Magic e um novo mundo de poder inexplorado com a primeira coleção desenhada para jogadores de Modern. Modern Horizons liberta 254 cards para o formato, incluindo 209 cards totalmente novos, 40 mecânicas poderosas e terrenos de neve de arte cheia.

Compre sua caixa de boosters de Modern Horizons e ganhe um card promocional especial!

Abra seu destino e atualize sua coleção hoje!
COMPONENTES
36 Booster com 15 Cards cada da Coleção Modern Horizons

01 Promo Tempestade Atordoante
Total de 540 Cards",
4,
3,
1349.50,
5);
Insert into tblProduto values(null,
"Booster Sol e Luz 7 - Tempestade Celestial",
"A fúria rompante da natureza!

Cuidado com o vórtice! A previsão para a estação é de céus esmeralda tempestuosos e cobertos de presságios e de batalhas entre Pokémon e criaturas do Ultraespaço. Alguns se deixam levar pela tempestade, enquanto outros a desafiam. A escalação para a batalha conta com Articuno-GX, Blaziken-GX, Scizor-GX, Stakataka-GX e com o incrível
Rayquaza-GX, além de novas cartas de Latios, Latias e Jirachi. Encare de frente esta ventania de novidades com a expansão Sol e Lua Tempestade Celestial do Pokémon Estampas Ilustradas!",
5,
2,
6.50,
50);