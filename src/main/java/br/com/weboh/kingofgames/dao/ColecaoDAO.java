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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guigasalim
 */
public class ColecaoDAO implements GenericDAO {

    private DataSource dataSource;

    public ColecaoDAO(DataSource dataSource) {
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
                String SQL = "Select * from tblColecao where id_colecao =  ?;";
                PreparedStatement stm = dataSource.getConection().prepareStatement(SQL);
                stm.setInt(1, incompleto.getId());

                ResultSet rs = stm.executeQuery();
                ArrayList<Object> result = new ArrayList<Object>();
                if (rs.next()) {
                    Colecao colecao = new Colecao();
                    colecao.setId(rs.getInt("id_colecao"));
                    colecao.setNome(rs.getString("nome_colecao"));
                    colecao.setDescricao(rs.getString("descricao_colecao"));
                    colecao.setCategoria(rs.getString("categoria_colecao"));
                    colecao.setIdFornecedor(rs.getInt("id_fornecedor"));

                    result.add(colecao);

                }
                stm.close();
                rs.close();
                return result;

            } else {

                throw new RuntimeException("Invalid Object");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar a Colecao" + ex.getMessage());

        }
        return null;
    }
}
