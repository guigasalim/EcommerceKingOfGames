/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.weboh.kingofgames.dao;


import br.com.weboh.kingofgames.model.Cliente;
import br.com.weboh.kingofgames.model.Endereco;
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
public class EnderecoDAO implements GenericDAO{
private DataSource dataSource;

    public EnderecoDAO(DataSource dataSource) {
        this.dataSource = dataSource;    

        
        
        
    }
    
    public void create(Object o) {
        try {
            if (o instanceof Endereco) {
                Endereco endereco = (Endereco) o;
                String SQL = "INSERT INTO tblEndereco VALUES (null,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement stm = dataSource.getConection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                stm.setString(1, endereco.getTipoLogradouro());
                stm.setString(2, endereco.getLogradouro());
                stm.setInt(3, endereco.getNumero());
                stm.setString(4, endereco.getComplemento());
                stm.setString(5, endereco.getBairro());
                stm.setString(6, endereco.getCidade());
                stm.setString(7, endereco.getEstado());
                stm.setString(8, endereco.getCep());
                stm.setString(9, endereco.getPais());
                stm.setInt(10, endereco.getIdCliente());
                
                
                int res = stm.executeUpdate();
                if (res != 0) {
                    ResultSet rs = stm.getGeneratedKeys();
                    if (rs.next()) {
                        endereco.setId(rs.getInt(1));
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

            if (o instanceof Endereco) {
                Endereco incompleto = (Endereco) o;
                String SQL = "Select * from tblEndereco where id_cliente = ?";
                PreparedStatement stm = dataSource.getConection().prepareStatement(SQL);
                stm.setInt(1, incompleto.getIdCliente());
              
                
                ResultSet rs = stm.executeQuery();
                ArrayList<Object> result = new ArrayList<Object>();
                if (rs.next()) {
                    Endereco endereco = new Endereco();

                    endereco.setTipoLogradouro(rs.getString("tipo_logradouro"));
                    endereco.setLogradouro(rs.getString("logradouro"));
                    endereco.setNumero(rs.getInt("numero"));
                    endereco.setComplemento(rs.getString("complemento"));
                    endereco.setBairro(rs.getString("bairro"));
                    endereco.setCidade(rs.getString("cidade"));
                    endereco.setEstado(rs.getString("estado"));
                    endereco.setPais(rs.getString("pais"));
                    endereco.setIdCliente(rs.getInt("id_cliente"));
                    result.add(endereco);

                }
                stm.close();
                rs.close();
                return result;

            } else {

                throw new RuntimeException("Invalid Object");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar o Endereco" + ex.getMessage());

        }
        return null;
    }
    
    
    
    }
