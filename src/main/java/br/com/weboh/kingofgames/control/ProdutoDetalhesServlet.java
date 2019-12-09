/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.weboh.kingofgames.control;

import br.com.weboh.kingofgames.dao.ColecaoDAO;
import br.com.weboh.kingofgames.dao.DataSource;
import br.com.weboh.kingofgames.dao.ProdutoDAO;
import br.com.weboh.kingofgames.dao.TipoProdutoDAO;
import br.com.weboh.kingofgames.model.Cliente;
import br.com.weboh.kingofgames.model.Colecao;
import br.com.weboh.kingofgames.model.Produto;
import br.com.weboh.kingofgames.model.TipoProduto;
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
public class ProdutoDetalhesServlet extends HttpServlet {

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
        if (cliente == null) {
            cliente = new Cliente();
        }
        request.getSession().setAttribute("Cliente", cliente);

        int id = 0;
        int src = Integer.parseInt(request.getParameter("id"));
        System.out.println(src);

        try {
            DataSource ds = new DataSource();
            Produto incompletaP = new Produto();

            Colecao incompletaC = new Colecao();
            TipoProduto incompletaT = new TipoProduto();
            ProdutoDAO produtoDAO = new ProdutoDAO(ds);
            incompletaP.setId(src);
            System.out.println(incompletaP);
            List<Object> res = produtoDAO.readbyID(incompletaP);
            Produto produto = new Produto();
            if (res != null && res.size() > 0) {

                produto = (Produto) res.get(0);
                System.out.println(produto);

                request.getSession().setAttribute("Produto", res.get(0));

            } else {

                request.setAttribute("erroSTR", "Produto Incorreto");

            }
            int idColecao = produto.getIdColecao();
            incompletaC.setId(idColecao);
            System.out.println(idColecao);
            ColecaoDAO colecaoDAO = new ColecaoDAO(ds);

            List<Object> res2 = colecaoDAO.read(incompletaC);
            if (res2 != null && res2.size() > 0) {

                Colecao colecao = new Colecao();
                colecao = (Colecao) res2.get(0);

                request.getSession().setAttribute("Colecao", colecao);

            } else {

                request.setAttribute("erroSTR", "Colecao Incorreto");

            }

            int idTipoProduto = produto.getIdTipoProduto();
            System.out.println(idTipoProduto);
            incompletaT.setId(idTipoProduto);
            TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO(ds);
            List<Object> res3 = tipoProdutoDAO.read(incompletaT);
            if (res3 != null && res3.size() > 0) {

                TipoProduto tipoProduto = new TipoProduto();
                tipoProduto = (TipoProduto) res3.get(0);

                request.getSession().setAttribute("TipoProduto", tipoProduto);

            } else {

                request.setAttribute("erroSTR", "Tipo Produto Incorreto");

            }

            ds.getConection().close();

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/detalhesproduto.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException ex) {
            System.out.println("Erro ao fechar conexão: " + ex.getMessage());
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
}
