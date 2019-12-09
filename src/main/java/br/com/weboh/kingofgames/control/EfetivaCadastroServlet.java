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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author guigasalim
 */
public class EfetivaCadastroServlet extends HttpServlet {

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
        //receber dados do formulario
        //criar um objeto usuario com estes dados
        //instanciar o datasource e o dao
        //gravar
        //dependendo do resultado, vamos trabalhar em qual pagina retornar

        String pagina = "/acerto.jsp";
        String nome = request.getParameter("txtNome");
        String email = request.getParameter("txtEmail");
        String senha = request.getParameter("txtSenha");
        String cpf = request.getParameter("txtCPF");
        String dataNasc = request.getParameter("txtData");
        String cep = request.getParameter("txtCep");
        String tipoLogradouro = request.getParameter("txtTipoLogradouro");
        String Logradouro = request.getParameter("txtLogradouro");
        String numero = request.getParameter("txtNumero");
        System.out.println(numero);

        String complemento = request.getParameter("txtCompl");
        String bairro = request.getParameter("txtBairro");
        String cidade = request.getParameter("txtCidade");
        String estado = request.getParameter("txtEstado");
        String pais = request.getParameter("txtPais");

        Cliente cliente = new Cliente();
        Endereco endereco = new Endereco();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setCpf(cpf);
        cliente.setSenha(senha);
        int num = Integer.parseInt(numero);

        cliente.setDataNascimento(dataNasc);
        endereco.setCep(cep);
        endereco.setTipoLogradouro(tipoLogradouro);
        endereco.setLogradouro(Logradouro);
        endereco.setNumero(num);
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setPais(pais);

        DataSource dataSource = new DataSource();
        ClienteDAO clienteDao = new ClienteDAO(dataSource);

        clienteDao.create(cliente);

        System.out.println(cliente);

        try {
            dataSource.getConection().close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar conexão: " + ex.getMessage());
            request.setAttribute("ErroMSG", "Erro ao criar nova conta de usuario");
            pagina = "/erro.jsp";
        }
        if (cliente.getId() != 0) {
            request.getSession().setAttribute("Cliente", cliente);

        }
        dataSource = new DataSource();
        EnderecoDAO enderecoDao = new EnderecoDAO(dataSource);
        endereco.setIdCliente(cliente.getId());

        enderecoDao.create(endereco);
        System.out.println(endereco);

        try {
            dataSource.getConection().close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar conexão: " + ex.getMessage());
            request.setAttribute("ErroMSG", "Erro ao adicionar o endereço");
            pagina = "/erro.jsp";
        }
        if (endereco.getId() != 0) {
            request.getSession().setAttribute("Endereco", endereco);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
        dispatcher.forward(request, response);

    }

}
