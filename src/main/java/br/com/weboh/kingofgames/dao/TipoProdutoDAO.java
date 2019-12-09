/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.weboh.kingofgames.dao;

import br.com.weboh.kingofgames.model.Colecao;
import br.com.weboh.kingofgames.model.TipoProduto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guigasalim
 */
public class TipoProdutoDAO implements GenericDAO {

    private DataSource dataSource;

    public TipoProdutoDAO(DataSource dataSource) {
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

            if (o instanceof TipoProduto) {
                TipoProduto incompleto = (TipoProduto) o;
                String SQL = "Select * from tblTipoProduto where id_tipoProduto =  ?;";
                PreparedStatement stm = dataSource.getConection().prepareStatement(SQL);
                stm.setInt(1, incompleto.getId());

                ResultSet rs = stm.executeQuery();
                ArrayList<Object> result = new ArrayList<Object>();
                if (rs.next()) {
                    TipoProduto tipoProduto = new TipoProduto();
                    tipoProduto.setId(rs.getInt("id_tipoProduto"));
                    tipoProduto.setTipo(rs.getString("tipo"));

                    result.add(tipoProduto);

                }
                stm.close();
                rs.close();
                return result;

            } else {

                throw new RuntimeException("Invalid Object");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar o Tipo Produto" + ex.getMessage());

        }
        return null;
    }
}
