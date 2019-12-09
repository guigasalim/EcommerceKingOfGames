/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.weboh.kingofgames.control;

import br.com.weboh.kingofgames.model.Cliente;
import br.com.weboh.kingofgames.model.Endereco;
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
 * @author Salim
 */
public class MinhaContaServlet extends HttpServlet {

   
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
        String paginaRetorno;
        Cliente cliente = (Cliente)request.getSession().getAttribute("Cliente");
        Endereco endereco = (Endereco)request.getSession().getAttribute("Endereco");
       if(cliente ==null){
          paginaRetorno = "/index"; 
       
       }else{
       
           paginaRetorno = "/minhaconta.jsp";
           
          
       }
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
       request.getSession().setAttribute("Cliente", cliente);
       request.getSession().setAttribute("Endereco", endereco);
       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaRetorno);
       dispatcher.forward(request,response);
    }  

 
}
