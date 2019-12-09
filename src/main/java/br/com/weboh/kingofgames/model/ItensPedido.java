/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.weboh.kingofgames.model;

/**
 *
 * @author guigasalim
 */
public class ItensPedido implements java.io.Serializable{
    private int idProduto;
    private int quantidade;
    private int idPedido;
    private float valorParcial;

    /**
     * @return the produto
     */
    public int getIdProduto() {
        return idProduto;
    }

    /**
     * @param produto the produto to set
     */
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the idPedido
     */
    public int getIdPedido() {
        return idPedido;
    }

    /**
     * @param idPedido the idPedido to set
     */
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * @return the valorParcial
     */
    public float getValorParcial() {
        return valorParcial;
    }

    /**
     * @param valorParcial the valorParcial to set
     */
    public void setValorParcial(float valorParcial) {
        this.valorParcial = valorParcial;
    }
    
   public String toString() {
        return idPedido + " - " + idProduto + " - " + quantidade + " - " + valorParcial;

    } 
}
