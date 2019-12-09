/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.weboh.kingofgames.control;

import br.com.weboh.kingofgames.model.Cliente;
import br.com.weboh.kingofgames.model.Pedido;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author guigasalim
 */
public class IndexServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

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

        if (cliente == null) {
            cliente = new Cliente();
        }
        request.getSession().setAttribute("Cliente", cliente);
        Pedido pedido = (Pedido) request.getSession().getAttribute("Pedido");

        if (pedido == null) {
            pedido = new Pedido();
            pedido.setData(hj);
        }
        pedido.setData(hj);
        pedido.setIdCliente(cliente.getId());
        request.getSession().setAttribute("Cliente", cliente);
        request.getSession().setAttribute("Pedido", pedido);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.html");
       dispatcher.forward(request,response);
    }

   

}
