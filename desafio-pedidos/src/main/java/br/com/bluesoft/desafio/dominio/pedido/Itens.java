package br.com.bluesoft.desafio.dominio.pedido;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import br.com.bluesoft.desafio.dominio.produto.Produto;
import br.com.bluesoft.desafio.utils.BusinessException; 

@Entity
public class Itens {

    @Id  @GeneratedValue
    private int id;

    @NotNull
    @OneToOne
    private Produto produto;
    
    @NotNull
    private int quantidade;

    private double total;
    private double preco;

    
    public Itens(Produto produto, int quantidade) {

        BusinessException.Validar(produto == null, "Produto é obrigatório");
        BusinessException.Validar(quantidade <= 0, "Quantidade informada é inválida");

        this.produto = produto;
        this.quantidade = quantidade;
    }

    public void setProduto(Produto produto){
        this.produto = produto;
    }


    public void setQuantidade(int qtd){
        this.quantidade = qtd;
    }
 
    public Produto getProduto(){return this.produto;}
    public int getQuantidade(){ return this.quantidade;}
    public double getTotal(){ return this.produto.getPreco() * this.quantidade ;}
    public double getPreco(){ return this.produto.getPreco() ;}

    
    @Override
    public String toString(){
        return "Item =>  " +
               " produto: " + this.produto.getNome() + "|" + 
               " quantidade: " + this.quantidade + "|" + 
               " preço: " + this.preco + "|" + 
               " Total " + this.total;
    }
}
