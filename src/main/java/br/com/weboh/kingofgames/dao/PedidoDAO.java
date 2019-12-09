/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.weboh.kingofgames.dao;

import br.com.weboh.kingofgames.model.Cliente;
import br.com.weboh.kingofgames.model.ItemCarrinho;
import br.com.weboh.kingofgames.model.Pedido;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 *
 * @author guigasalim
 */
public class PedidoDAO implements GenericDAO {

    private DataSource dataSource;

    public PedidoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void create(Object o) {
        try {
            if (o instanceof Pedido) {
                Pedido pedido = (Pedido) o;
                String SQL = "INSERT INTO tblPedido VALUES (null,?,?,?,?,?)";
                PreparedStatement stm = dataSource.getConection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                stm.setString(1, pedido.getData());
                stm.setString(2, pedido.getSituacao());
                stm.setInt(3, pedido.getIdCliente());
                stm.setFloat(4, pedido.getValorTotal());
                stm.setString(5, pedido.getTipoEntrega());

                int res = stm.executeUpdate();
                if (res != 0) {
                    ResultSet rs = stm.getGeneratedKeys();
                    if (rs.next()) {
                        pedido.setId(rs.getInt(1));
                    }
                    rs.close();
                }
                stm.close();
            } else {
                throw new RuntimeException("Invalid User Model Object");
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao criar pedido: " + ex.getMessage());
        }

    }

    public void update(Object o) {
        try {
            if (o instanceof Pedido) {
                Pedido pedido = (Pedido) o;
                String SQL = "UPDATE tblPedido SET data_pedido = ?, situacao = ?, id_cliente = ?, valor_total = ?, tipo_entrega = ? WHERE id_pedido = ?";
                PreparedStatement stm = dataSource.getConection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                stm.setString(1, pedido.getData());
                stm.setString(2, pedido.getSituacao());
                stm.setInt(3, pedido.getIdCliente());
                stm.setFloat(4, pedido.getValorTotal());
                stm.setString(5, pedido.getTipoEntrega());
                stm.setInt(6, pedido.getId());
                stm.executeUpdate();
                
                stm.close();
            } else {
                throw new RuntimeException("Invalid User Model Object");
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao Atualizar: " + ex.getMessage());
        }
    }

    public void delete(Object o) {
        try {
            if (o instanceof Pedido) {
                Pedido pedido = (Pedido) o;
                String SQL = "delete from tblPedido WHERE id_cliente = ?";
                PreparedStatement stm = dataSource.getConection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
               
                stm.setInt(1, pedido.getIdCliente());
                stm.executeUpdate();
                
                stm.close();
            } else {
                throw new RuntimeException("Invalid User Model Object");
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao Atualizar: " + ex.getMessage());
        }
    }

    public List<Object> read(Object o) {
        try {

            if (o instanceof Pedido) {
                Pedido incompleto = (Pedido) o;
                String SQL = "Select * from tblPedido where id_pedido = ?";
                PreparedStatement stm = dataSource.getConection().prepareStatement(SQL);
                stm.setInt(1, incompleto.getId());
                ResultSet rs = stm.executeQuery();
                ArrayList<Object> result = new ArrayList<Object>();
                while (rs.next()) {
                    Pedido pedido = new Pedido();
                    pedido.setId(rs.getInt("id_cliente"));
                    pedido.setData(rs.getString("data_pedido"));
                    pedido.setSituacao(rs.getString("situacao"));
                    pedido.setIdCliente(rs.getInt("id_cliente"));
                    pedido.setValorTotal(rs.getInt("valor_total"));
                    pedido.setTipoEntrega(rs.getString("tipo_entrega"));
                    result.add(pedido);

                }
                stm.close();
                rs.close();
                return result;

            } else {

                throw new RuntimeException("Invalid Object");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar o Pedido: " + ex.getMessage());

        }
        return null;
    }

    public List<Object> readToCarrinho(Object o) {
        try {

            if (o instanceof Pedido) {
                Pedido incompleto = (Pedido) o;
                String SQL = "Select pr.id_produto, pr.nome_produto, ip.quantidade, ip.valor_parcial from tblProduto pr "
                        + "inner join tblItemsPedido ip on ip.id_produto = pr.id_produto "
                        + "inner Join tblPedido pe on pe.id_pedido = ? and pe.id_pedido = ip.id_pedido;";
                PreparedStatement stm = dataSource.getConection().prepareStatement(SQL);
                stm.setInt(1, incompleto.getId());
                ResultSet rs = stm.executeQuery();
                ArrayList<Object> result = new ArrayList<Object>();
                while (rs.next()) {
                    ItemCarrinho itemCarrinho = new ItemCarrinho();
                    itemCarrinho.setIdProduto(rs.getInt("pr.id_produto"));
                    itemCarrinho.setNomeProduto(rs.getString("pr.nome_produto"));
                    itemCarrinho.setQuantidade(rs.getInt("ip.quantidade"));
                    itemCarrinho.setValorTotal(rs.getFloat("ip.valor_parcial"));

                    result.add(itemCarrinho);

                }
                stm.close();
                rs.close();
                return result;

            } else {

                throw new RuntimeException("Invalid Object");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar o Pedido: " + ex.getMessage());

        }
        return null;
    }

}
