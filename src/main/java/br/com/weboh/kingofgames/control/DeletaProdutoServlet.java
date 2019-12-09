/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.weboh.kingofgames.control;

import br.com.weboh.kingofgames.dao.DataSource;
import br.com.weboh.kingofgames.dao.ItensPedidoDAO;
import br.com.weboh.kingofgames.dao.PedidoDAO;
import br.com.weboh.kingofgames.model.Cliente;
import br.com.weboh.kingofgames.model.ItensPedido;
import br.com.weboh.kingofgames.model.Pedido;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author guigasalim
 */
public class DeletaProdutoServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cliente cliente = (Cliente) request.getSession().getAttribute("Cliente");
        Pedido pedido = (Pedido) request.getSession().getAttribute("Pedido");

        int idProduto = Integer.parseInt(request.getParameter("id"));
        ItensPedido incompletoIP = new ItensPedido();
        incompletoIP.setIdPedido(pedido.getId());
        incompletoIP.setIdProduto(idProduto);
        try {
            DataSource ds = new DataSource();
            PedidoDAO pedidoDAO = new PedidoDAO(ds);
            ItensPedidoDAO itensPedidoDAO = new ItensPedidoDAO(ds);
            ItensPedido itensPedido = new ItensPedido();

            List<Object> res = itensPedidoDAO.read(incompletoIP);
            if (res != null && res.size() > 0) {

                itensPedido = (ItensPedido) res.get(0);

            } else {

                request.setAttribute("erroSTR", "Produto Incorreto");

            }

            float dif = itensPedido.getValorParcial();
            itensPedidoDAO.delete(itensPedido);
            pedido.setValorTotal(pedido.getValorTotal() - dif);
            pedidoDAO.update(pedido);
            List<Object> carrinho = pedidoDAO.readToCarrinho(pedido);

            request.getSession().setAttribute("Carrinho", carrinho);

            ds.getConection().close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        request.getSession().setAttribute("Cliente", cliente);
        request.getSession().setAttribute("Pedido", pedido);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/carrinho.jsp");
        dispatcher.forward(request, response);

    }

}
