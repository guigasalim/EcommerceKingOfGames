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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author guigasalim
 */
@WebServlet(name = "EntrarCarrinhoServlet", urlPatterns = {"/carrinho"})
public class EntrarCarrinhoServlet extends HttpServlet {

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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String hj = dtf.format(now).toString();
        Cliente cliente = (Cliente) request.getSession().getAttribute("Cliente");
        Pedido pedido = (Pedido) request.getSession().getAttribute("Pedido");
        List<Object> carrinho = new ArrayList<Object>();

        String pagina = "/login.jsp";
        try {

            DataSource ds = new DataSource();

            PedidoDAO pedidoDAO = new PedidoDAO(ds);
            if (pedido == null) {
                pedido = new Pedido();
                pedido.setData(hj);

            }

            if (cliente == null || cliente.getId() == 0) {
                cliente = new Cliente();

            } else if (pedido != null && pedido.getId() != 0) {

                carrinho = pedidoDAO.readToCarrinho(pedido);

            }
            ds.getConection().close();

        } catch (SQLException ex) {
            System.out.println("Erro ao entrar no carrinho: " + ex.getMessage());
        }

        request.getSession().setAttribute("Cliente", cliente);
        request.getSession().setAttribute("Pedido", pedido);
        request.getSession().setAttribute("Carrinho", carrinho);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/carrinho.jsp");
        dispatcher.forward(request, response);
    }

}
