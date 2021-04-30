package br.com.bluesoft.desafio.dominio.pedido;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import br.com.bluesoft.desafio.dominio.produto.Produto;
//https://github.com/StephanyBatista/pedidos-spring-boot/blob/master/src/main/java/com/brasilprev/pedidos/dominio/pedidos/Pedido.java
@Entity
public class Pedido {
        
    @Id  @GeneratedValue
    private int id;

   // private Itens itens;
   @Column
   @ElementCollection
   private List<Fornecedor> fornecedores; 
    

   @NotNull
   @OneToMany(cascade = CascadeType.ALL)
   private List<Itens> items;
     
    public Pedido() {
        this.fornecedores = new ArrayList<>(); 
        this.items = new ArrayList<>();
    }

    public void adcionarProduto(Produto produto, int quantidade,Fornecedor fornecedor) {
        this.fornecedores.add(fornecedor);
        Itens item = new Itens(produto, quantidade);
        items.add(item);
    }

    public double getValorTotal() {
        double valorTotal = 0;
        for (Itens item: items) {
            valorTotal += item.getTotal();
        }
        return valorTotal;
    }  

    public int getId(){ return this.id;}
  
    public List<Fornecedor> getFornecedor() {
        return this.fornecedores;
    }
 
    public List<Itens> getItens(){
        return this.items;
    }

    @Override
    public String toString(){
        return "Pedido => id : " + this.id + "|" + 
            //    " item: " + this.itens.toString() + "|" +  
               " fornecedor: " + this.fornecedores;
    }

}
