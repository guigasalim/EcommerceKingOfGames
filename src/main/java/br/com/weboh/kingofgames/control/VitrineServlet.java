/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.weboh.kingofgames.control;

import br.com.weboh.kingofgames.dao.ColecaoDAO;
import br.com.weboh.kingofgames.dao.DataSource;
import br.com.weboh.kingofgames.dao.ProdutoDAO;
import br.com.weboh.kingofgames.model.Cliente;
import br.com.weboh.kingofgames.model.Colecao;
import br.com.weboh.kingofgames.model.Pedido;
import br.com.weboh.kingofgames.model.Produto;
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
public class VitrineServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>POST</code> method.
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
        if (cliente == null) {
            cliente = new Cliente();
        }
        request.getSession().setAttribute("Cliente", cliente);
        Pedido pedido = (Pedido) request.getSession().getAttribute("Pedido");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String hj = dtf.format(now).toString();
        if (pedido == null) {
            pedido = new Pedido();
            pedido.setData(hj);
        }

        pedido.setData(hj);
        pedido.setIdCliente(cliente.getId());
        request.getSession().setAttribute("Pedido", pedido);
        try {
            String categoria = "";
            int src = Integer.parseInt(request.getParameter("src"));

            switch (src) {
                case 1:
                    categoria = "Magic: The Gathering";
                    break;
                case 2:
                    categoria = "Yu-gi-Oh!";
                    break;
                case 3:
                    categoria = "Pokemon";
                    break;

            }
            DataSource ds = new DataSource();
            Colecao incompletaC1 = new Colecao();

            incompletaC1.setCategoria(categoria);
            

            ProdutoDAO produtoDAO = new ProdutoDAO(ds);
            List<Object> lista = produtoDAO.read(incompletaC1);

            request.getSession().setAttribute("Lista", lista);

            ds.getConection().close();

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vitrine.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException ex) {
            System.out.println("Erro ao fechar conexão: " + ex.getMessage());
        }
    }

}
