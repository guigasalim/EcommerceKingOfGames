/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.weboh.kingofgames.dao;

import br.com.weboh.kingofgames.model.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Salim
 */
public class ClienteDAO implements GenericDAO{

    private DataSource dataSource;

    public ClienteDAO(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    public void create(Object o) {
        try {
            if (o instanceof Cliente) {
                Cliente cliente = (Cliente) o;
                String SQL = "INSERT INTO tblCliente VALUES (null,?,?,?,?,?)";
                PreparedStatement stm = dataSource.getConection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                stm.setString(1, cliente.getNome());
                stm.setString(2, cliente.getCpf());
                stm.setString(3, cliente.getDataNascimento());
                stm.setString(4, cliente.getEmail());
                stm.setString(5, cliente.getSenha());
                
                int res = stm.executeUpdate();
                if (res != 0) {
                    ResultSet rs = stm.getGeneratedKeys();
                    if (rs.next()) {
                        cliente.setId(rs.getInt(1));
                    }
                    rs.close();
                }
                stm.close();
            }else{
                throw new RuntimeException("Invalid User Model Object");
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao Cadastrar: " + ex.getMessage());
        }

    }

    public void update(Object o) {

    }

    public void delete(Object o) {

    }

    public List<Object> read(Object o) {
        try {

            if (o instanceof Cliente) {
                Cliente incompleto = (Cliente) o;
                String SQL = "Select * from tblCliente where email = ? and senha = ?";
                PreparedStatement stm = dataSource.getConection().prepareStatement(SQL);
                stm.setString(1, incompleto.getEmail());
                stm.setString(2, incompleto.getSenha());
                ResultSet rs = stm.executeQuery();
                ArrayList<Object> result = new ArrayList<Object>();
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("id_cliente"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setSenha(rs.getString("senha"));
                    cliente.setDataNascimento("dataNascimento");
                    cliente.setCpf("cpf");
                    result.add(cliente);

                }
                stm.close();
                rs.close();
                return result;

            } else {

                throw new RuntimeException("Invalid Object");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar o Usuario: " + ex.getMessage());

        }
        return null;
    }
}
