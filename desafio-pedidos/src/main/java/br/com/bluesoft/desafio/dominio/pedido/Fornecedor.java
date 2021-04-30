package br.com.bluesoft.desafio.dominio.pedido;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Fornecedor {
     
    
    @Id  @GeneratedValue
    private int id;
    
    private String nome;

    private String cnpj;

    public String getNome(){
        return this.nome;
    } 

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCnpj(){return  this.cnpj;}

    public void setCnpj(String cnpj){ this.cnpj = cnpj;}

    @Override
    public String toString(){
        return "Fornecedor  : " + this.nome + "; Cnpj : " + this.cnpj;
    }
}
