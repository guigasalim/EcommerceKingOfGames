/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.weboh.kingofgames.model;

/**
 *
 * @author Salim
 */
public class Produto  implements java.io.Serializable{
     private int id;
    private String nome;
    private String descricao;
    private float valor;
    private int quantidade;
    private int idColecao;
    private int idTipoProduto;

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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the valor
     */
    public float getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(float valor) {
        this.valor = valor;
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
     * @return the colecao
     */
    public int getIdColecao() {
        return idColecao;
    }

    /**
     * @param colecao the colecao to set
     */
    public void setIdColecao(int idColecao) {
        this.idColecao = idColecao;
    }

    /**
     * @return the tipoproduto
     */
    public int getIdTipoProduto() {
        return idTipoProduto;
    }

    /**
     * @param tipoproduto the tipoproduto to set
     */
    public void setIdTipoProduto(int idTipoproduto) {
        this.idTipoProduto = idTipoproduto;
    }
     public String toString() {
        return id + " - " + nome + " - " + descricao + " - " + valor + " - " + quantidade + " - "+idColecao+ " - " + idTipoProduto;

    }

}
