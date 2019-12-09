/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.weboh.kingofgames.dao;

import br.com.weboh.kingofgames.model.ItensPedido;
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
public class ItensPedidoDAO implements GenericDAO {

    private DataSource dataSource;

    public ItensPedidoDAO(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    public void create(Object o) {
        try {
            if (o instanceof ItensPedido) {
                ItensPedido itensPedido = (ItensPedido) o;
                String SQL = "INSERT INTO tblItemsPedido VALUES (?,?,?,?)";
                PreparedStatement stm = dataSource.getConection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                stm.setInt(1, itensPedido.getIdPedido());
                stm.setInt(2, itensPedido.getIdProduto());
                stm.setInt(3, itensPedido.getQuantidade());
                stm.setFloat(4, itensPedido.getValorParcial());

                int res = stm.executeUpdate();
                if (res != 0) {
                    ResultSet rs = stm.getGeneratedKeys();
                    if (rs.next()) {
                        itensPedido.setIdPedido(rs.getInt(1));
                    }
                    rs.close();
                }
                stm.close();
            } else {
                throw new RuntimeException("Invalid User Model Object");
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao Colocar Produto: " + ex.getMessage());
        }
    }

    public void update(Object o) {
 try {

            if (o instanceof ItensPedido) {
                ItensPedido incompleto = (ItensPedido) o;
                String SQL = "UPDATE tblPedido SET quantidade =  ?, valor_parcial = ? where id_pedido = ?;";
                PreparedStatement stm = dataSource.getConection().prepareStatement(SQL);
                stm.setInt(1, incompleto.getQuantidade());
                stm.setFloat(2, incompleto.getValorParcial());
                stm.setInt(3, incompleto.getIdPedido());
                
                stm.executeUpdate();
                stm.close();
                
                       

            } else {

                throw new RuntimeException("Invalid Object");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar o Item Pedido" + ex.getMessage());

        }
    }

    public void deleteall(Object o) {
        try {

            if (o instanceof ItensPedido) {
                ItensPedido incompleto = (ItensPedido) o;
                String SQL = "Delete from tblItemsPedido where id_pedido =  ?";
                PreparedStatement stm = dataSource.getConection().prepareStatement(SQL);
                stm.setInt(1, incompleto.getIdPedido());
            
                stm.executeUpdate();
                stm.close();
                
                       

            } else {

                throw new RuntimeException("Invalid Object");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar o Item Pedido" + ex.getMessage());

        }
    }
 public void delete(Object o) {
        try {

            if (o instanceof ItensPedido) {
                ItensPedido incompleto = (ItensPedido) o;
                String SQL = "Delete from tblItemsPedido where id_pedido =  ? and id_produto = ?;";
                PreparedStatement stm = dataSource.getConection().prepareStatement(SQL);
                stm.setInt(1, incompleto.getIdPedido());
                stm.setInt(2, incompleto.getIdProduto());
                stm.executeUpdate();
                stm.close();
                
                       

            } else {

                throw new RuntimeException("Invalid Object");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar o Item Pedido" + ex.getMessage());

        }
    }
    public List<Object> read(Object o) {
        try {

            if (o instanceof ItensPedido) {
                ItensPedido incompleto = (ItensPedido) o;
                String SQL = "Select * from tblItemsPedido where id_pedido =  ?;";
                PreparedStatement stm = dataSource.getConection().prepareStatement(SQL);
                stm.setInt(1, incompleto.getIdPedido());

                ResultSet rs = stm.executeQuery();
                ArrayList<Object> result = new ArrayList<Object>();
                if (rs.next()) {
                    ItensPedido itensPedido = new ItensPedido();
                    itensPedido.setIdPedido(rs.getInt("id_pedido"));
                    itensPedido.setIdProduto(rs.getInt("id_produto"));
                    itensPedido.setQuantidade(rs.getInt("quantidade"));
                    itensPedido.setValorParcial(rs.getFloat("valor_parcial"));

                    result.add(itensPedido);

                }
                stm.close();
                rs.close();
                return result;

            } else {

                throw new RuntimeException("Invalid Object");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar o Item Pedido" + ex.getMessage());

        }
        return null;
    }
}
