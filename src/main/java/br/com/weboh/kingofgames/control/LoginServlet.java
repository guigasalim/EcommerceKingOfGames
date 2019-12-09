/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.weboh.kingofgames.control;

import br.com.weboh.kingofgames.dao.ClienteDAO;
import br.com.weboh.kingofgames.dao.DataSource;
import br.com.weboh.kingofgames.dao.EnderecoDAO;
import br.com.weboh.kingofgames.model.Cliente;
import br.com.weboh.kingofgames.model.Endereco;
import br.com.weboh.kingofgames.model.Pedido;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Salim
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/loginservlet"})
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cliente cliente = (Cliente) request.getSession().getAttribute("Cliente");
        if (cliente == null) {
            cliente = new Cliente();
        }
        request.getSession().setAttribute("Cliente", cliente);
        String email = request.getParameter("txtEmail");
        String senha = request.getParameter("txtSenha");
        Endereco incompletoE = new Endereco();
        Cliente incompletoC = new Cliente();
        Cliente achou = new Cliente();
        incompletoC.setEmail(email);
        incompletoC.setSenha(senha);
        String pagina = "/erro.jsp";

        try {
            DataSource ds = new DataSource();
            ClienteDAO clienteDAO = new ClienteDAO(ds);

            List<Object> res = clienteDAO.read(incompletoC);
            if (res != null && res.size() > 0) {
                pagina = "/acerto.jsp";
                achou = (Cliente) res.get(0);
                System.out.println(achou);

                request.getSession().setAttribute("Cliente", res.get(0));

            } else {

                request.setAttribute("erroSTR", "Email / Senha Incorretos");

            }

            ds.getConection().close();
            incompletoE.setIdCliente(achou.getId());
            ds = new DataSource();
            EnderecoDAO enderecoDAO = new EnderecoDAO(ds);
            System.out.println(incompletoE);

            List<Object> res2 = enderecoDAO.read(incompletoE);
            System.out.println(res2.get(0));

            request.getSession().setAttribute("Endereco", res2.get(0));

            ds.getConection().close();
        } catch (Exception ex) {
            request.setAttribute("erroSTR", "Erro ao recuperar: " + ex.getMessage());

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
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
        dispatcher.forward(request, response);

    }

}
