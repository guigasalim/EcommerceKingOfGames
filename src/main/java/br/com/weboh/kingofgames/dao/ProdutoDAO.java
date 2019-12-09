/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.weboh.kingofgames.dao;

import br.com.weboh.kingofgames.model.Colecao;
import br.com.weboh.kingofgames.model.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guigasalim
 */
public class ProdutoDAO implements GenericDAO {

    private DataSource dataSource;

    public ProdutoDAO(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    public void create(Object o) {

    }

    public void update(Object o) {

    }

    public void delete(Object o) {

    }

    public List<Object> read(Object o) {
        try {

            if (o instanceof Colecao) {
                Colecao incompleto = (Colecao) o;
                String SQL = "Select * from tblProduto as P inner join tblColecao as C where P.id_colecao = C.id_colecao and C.categoria_colecao = ?";
                PreparedStatement stm = dataSource.getConection().prepareStatement(SQL);
                stm.setString(1, incompleto.getCategoria());

                ResultSet rs = stm.executeQuery();
                
                ArrayList<Object> result = new ArrayList<Object>();
                while (rs.next()) {
                    Produto produto = new Produto();
                    produto.setId(rs.getInt("id_produto"));
                    produto.setNome(rs.getString("nome_produto"));
                    produto.setDescricao(rs.getString("descricao_produto"));
                    produto.setIdColecao(rs.getInt("id_colecao"));
                    produto.setIdTipoProduto(rs.getInt("id_tipoProduto"));
                    produto.setValor(rs.getFloat("preco_produto"));
                    produto.setQuantidade(rs.getInt("quantidade_disp"));
                    result.add(produto);

                }
                
                stm.close();
                rs.close();
                return result;

            } else if (o instanceof Produto) {
                Produto incompleto = (Produto) o;
                String SQL = "Select * from tblProduto where nome_produto = ?";
                PreparedStatement stm = dataSource.getConection().prepareStatement(SQL);
                stm.setString(1, incompleto.getNome());

                ResultSet rs = stm.executeQuery();
                ArrayList<Object> result = new ArrayList<Object>();
                if (rs.next()) {
                    Produto produto = new Produto();
                    produto.setId(rs.getInt("id_produto"));
                    produto.setNome(rs.getString("nome_produto"));
                    produto.setDescricao(rs.getString("descricao_produto"));
                    produto.setIdColecao(rs.getInt("id_colecao"));
                    produto.setIdTipoProduto(rs.getInt("id_tipoProduto"));
                    produto.setValor(rs.getFloat("preco_produto"));
                    produto.setQuantidade(rs.getInt("quantidade_disp"));
                    result.add(produto);

                }
                stm.close();
                rs.close();
                return result;

            } else {
                throw new RuntimeException("Invalid Object");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar o Produto" + ex.getMessage());

        }
        return null;
    }
    public List<Object> readbyID(Object o){
    try{
        if (o instanceof Produto) {
                Produto incompleto = (Produto) o;
                String SQL = "Select * from tblProduto where id_produto = ?";
                PreparedStatement stm = dataSource.getConection().prepareStatement(SQL);
                stm.setInt(1, incompleto.getId());

                ResultSet rs = stm.executeQuery();
                ArrayList<Object> result = new ArrayList<Object>();
                if (rs.next()) {
                    Produto produto = new Produto();
                    produto.setId(rs.getInt("id_produto"));
                    produto.setNome(rs.getString("nome_produto"));
                    produto.setDescricao(rs.getString("descricao_produto"));
                    produto.setIdColecao(rs.getInt("id_colecao"));
                    produto.setIdTipoProduto(rs.getInt("id_tipoProduto"));
                    produto.setValor(rs.getFloat("preco_produto"));
                    produto.setQuantidade(rs.getInt("quantidade_disp"));
                    result.add(produto);

                }
                stm.close();
                rs.close();
                return result;

            } else {
                throw new RuntimeException("Invalid Object");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar o Produto" + ex.getMessage());

        }
        return null;
    
    }
}
