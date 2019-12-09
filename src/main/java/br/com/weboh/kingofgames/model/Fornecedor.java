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
public class Fornecedor  implements java.io.Serializable{
    private int id;
    private String nome;
    private String pais;
    private int fone;

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
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * @return the fone
     */
    public int getFone() {
        return fone;
    }

    /**
     * @param fone the fone to set
     */
    public void setFone(int fone) {
        this.fone = fone;
    }
}
