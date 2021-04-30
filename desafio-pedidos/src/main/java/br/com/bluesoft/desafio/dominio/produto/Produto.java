package br.com.bluesoft.desafio.dominio.produto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Produto {

    @Id  @GeneratedValue
    private String gtin;

    private String nome;
    
    private double preco;
    
    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco(){
        return this.preco;
    }

    public void setPreco(double preco){
        this.preco = preco;
    }

    @Override
    public String toString(){
        return "id : " + this.gtin + "|" + "nome: " + this.nome + "|" + "preço : " + this.preco;
    }
}
