/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.weboh.kingofgames.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Salim
 */
public class Pedido implements java.io.Serializable {

    private int id;
    private String data;
    private String situacao;
    private int idCliente;
    private float valorTotal;
    private String tipoEntrega;
    

    public Pedido() {
        
        valorTotal = 0;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the situacao
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the cliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the valorTotal
     */
    public float getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * @return the tipoEntrega
     */
    public String getTipoEntrega() {
        return tipoEntrega;
    }

    /**
     * @param tipoEntrega the tipoEntrega to set
     */
    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }


    
  public String toString() {
        return id + " - " + data + " - " + situacao + " - " + idCliente + " - " + valorTotal + " - " + tipoEntrega;

    }
}
