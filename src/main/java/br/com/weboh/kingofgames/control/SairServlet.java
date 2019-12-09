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
public class SairServlet extends HttpServlet {

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
        Cliente cliente = new Cliente();

        Pedido pedido = (Pedido) request.getSession().getAttribute("Pedido");

        if (pedido == null) {
            pedido = new Pedido();
            pedido.setData(hj);
            pedido.setData(hj);
            pedido.setIdCliente(cliente.getId());
        } else if (pedido.getSituacao().equals("Pendente")) {

            ItensPedido incompletoIP = new ItensPedido();
            incompletoIP.setIdPedido(pedido.getId());

            try {
                DataSource ds = new DataSource();
                PedidoDAO pedidoDAO = new PedidoDAO(ds);
                ItensPedidoDAO itensPedidoDAO = new ItensPedidoDAO(ds);
                ItensPedido itensPedido = new ItensPedido();

                itensPedidoDAO.deleteall(itensPedido);
                ;
                pedidoDAO.delete(pedido);

                ds.getConection().close();
            } catch (SQLException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
            request.getSession().setAttribute("Cliente", cliente);
            request.getSession().setAttribute("Pedido", pedido);

           

        }

        String paginaSair = "/index.html";

        request.getSession().setAttribute("Cliente", cliente);
        request.getSession().setAttribute("Pedido", pedido);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaSair);
        dispatcher.forward(request, response);
    }

}
