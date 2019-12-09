/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.weboh.kingofgames.control;

import br.com.weboh.kingofgames.dao.DataSource;
import br.com.weboh.kingofgames.dao.ItensPedidoDAO;
import br.com.weboh.kingofgames.dao.PedidoDAO;
import br.com.weboh.kingofgames.dao.ProdutoDAO;
import br.com.weboh.kingofgames.model.Cliente;
import br.com.weboh.kingofgames.model.Colecao;
import br.com.weboh.kingofgames.model.ItemCarrinho;
import br.com.weboh.kingofgames.model.ItensPedido;
import br.com.weboh.kingofgames.model.Pedido;
import br.com.weboh.kingofgames.model.Produto;
import br.com.weboh.kingofgames.model.TipoProduto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author guigasalim
 */
public class AddPedidoServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String hj = dtf.format(now).toString();
        Cliente cliente = (Cliente) request.getSession().getAttribute("Cliente");
        String pagina = "";
        List<Object> carrinho =  (List<Object>) request.getSession().getAttribute("Carrinho");

        if (cliente == null || cliente.getId() == 0) {
            cliente = new Cliente();
            request.getSession().setAttribute("Cliente", cliente);
            pagina = "/login.jsp";

        } else {
            request.getSession().setAttribute("Cliente", cliente);

            Pedido pedido = (Pedido) request.getSession().getAttribute("Pedido");

            try {

                ItensPedido itensPedido = new ItensPedido();
                DataSource ds = new DataSource();
                int id = Integer.parseInt(request.getParameter("id"));
                System.out.println(id);
                int qtde = Integer.parseInt(request.getParameter("txtQtde" + id));
                System.out.println(qtde);

                Produto incompletaP = new Produto();

                ProdutoDAO produtoDAO = new ProdutoDAO(ds);
                incompletaP.setId(id);
                System.out.println(incompletaP);
                List<Object> res = produtoDAO.readbyID(incompletaP);
                Produto produto = new Produto();
                if (res != null && res.size() > 0) {

                    produto = (Produto) res.get(0);
                    System.out.println(produto);

                } else {

                    request.setAttribute("erroSTR", "Produto Incorreto");

                }
                if (request.getParameter("txtQtde" + id) == null || request.getParameter("txtQtde" + id).isEmpty()) {
                    pagina = "/index";
                } else {

                    PedidoDAO pedidoDAO = new PedidoDAO(ds);
                    if ((pedido.getId() == 0)) {
                        pedido = new Pedido();
                        pedido.setData(hj);
                        pedido.setIdCliente(cliente.getId());
                        pedido.setSituacao("Pendente");

                        pedido.setValorTotal((qtde * produto.getValor()) + pedido.getValorTotal());

                        pedidoDAO.create(pedido);
                        System.out.println(pedido);

                        request.getSession().setAttribute("Pedido", pedido);
                        itensPedido.setIdPedido(pedido.getId());
                        itensPedido.setIdProduto(id);
                        itensPedido.setQuantidade(qtde);
                        itensPedido.setValorParcial(qtde * produto.getValor());
                        ItensPedidoDAO itensPedidoDAO = new ItensPedidoDAO(ds);
                        itensPedidoDAO.create(itensPedido);
                        System.out.println(itensPedido);

                        carrinho = pedidoDAO.readToCarrinho(pedido);
                        request.getSession().setAttribute("Pedido", pedido);

                        request.getSession().setAttribute("Carrinho", carrinho);
                        ds.getConection().close();

                        pagina = "/carrinho.jsp";
                    } else {
                        System.out.println(pedido);

                        pedido.setData(hj);
                        pedido.setIdCliente(cliente.getId());
                        pedido.setSituacao("Pendente");

                        pedido.setValorTotal((qtde * produto.getValor()) + pedido.getValorTotal());

                        pedidoDAO.update(pedido);
                        if (pedido.getId() != 0) {
                            request.getSession().setAttribute("Pedido", pedido);
                            itensPedido.setIdPedido(pedido.getId());
                            itensPedido.setIdProduto(id);
                            itensPedido.setQuantidade(qtde);
                            itensPedido.setValorParcial(qtde * produto.getValor());
                            ItensPedidoDAO itensPedidoDAO = new ItensPedidoDAO(ds);
                            itensPedidoDAO.create(itensPedido);
                            System.out.println(itensPedido);
                        }

                        carrinho = pedidoDAO.readToCarrinho(pedido);

                        request.getSession().setAttribute("Pedido", pedido);
                        request.getSession().setAttribute("Carrinho", carrinho);
                        ds.getConection().close();

                        pagina = "/carrinho.jsp";
                    }
                }
            } catch (SQLException ex) {

            }
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
        dispatcher.forward(request, response);
    }

}
