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
public class LoginOuMeusDadosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String hj = dtf.format(now).toString();

        String paginaEntra = "/login.jsp";
        Cliente cliente = (Cliente) request.getSession().getAttribute("Cliente");
        if (cliente == null) {
            cliente = new Cliente();
        }
        System.out.println(cliente);
        if (cliente.getId() != 0) {
            paginaEntra = "/acerto.jsp";
        }
        request.getSession().setAttribute("Cliente", cliente);
        Pedido pedido = (Pedido) request.getSession().getAttribute("Pedido");

        if (pedido == null) {
            pedido = new Pedido();
            pedido.setData(hj);
        }
        pedido.setData(hj);
        pedido.setIdCliente(cliente.getId());
        request.getSession().setAttribute("Pedido", pedido);
        request.getSession().setAttribute("Cliente", cliente);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaEntra);
        dispatcher.forward(request, response);

    }

}
