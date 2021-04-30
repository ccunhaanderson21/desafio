package br.com.bluesoft.desafio.api.pedido;

import java.util.List;
import java.util.SplittableRandom;

import org.springframework.stereotype.Service;

import br.com.bluesoft.desafio.dominio.pedido.Fornecedor;
import br.com.bluesoft.desafio.dominio.pedido.Itens;
import br.com.bluesoft.desafio.dominio.pedido.Pedido;
import br.com.bluesoft.desafio.dominio.produto.Produto;
import br.com.bluesoft.desafio.repository.ProdutoRepository;
import br.com.bluesoft.desafio.repository.FornecedorRepository;
import br.com.bluesoft.desafio.repository.PedidoRepository;
import br.com.bluesoft.desafio.utils.BusinessException;

@Service
public class NovoPedido {
    
    private final ProdutoRepository produtoRepository; 
    private final PedidoRepository pedidoRepository;
    private final FornecedorRepository fornecedorRepository;
  
    public NovoPedido(ProdutoRepository produtoRepository, FornecedorRepository fornecedorRepository, PedidoRepository pedidoRepository) {

        this.produtoRepository = produtoRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.pedidoRepository = pedidoRepository;
    }

    
    public Pedido criar(List<PedidoDeProdutoDto> itens) {

        BusinessException.Validar(itens.isEmpty(), "Pelo menos um produto deve ser informado no pedido");
        SplittableRandom splittableRandom = new SplittableRandom();
        int random = splittableRandom.nextInt(1, 2);

        Fornecedor fornecedorpedido = getFornecedor();
        BusinessException.Validar(fornecedorpedido == null, "Fornecedor não informado");

        Pedido pedido = new Pedido();

         for (PedidoDeProdutoDto item: itens)
         {
             Produto produto = produtoRepository.findOne(item.gtin);
        //     Produto produto = produtoRepository.findOne(item.getItens()).orElse(null);
             BusinessException.Validar(produto == null, "Produto não encontrado para efetuar o pedido");
             pedido.adcionarProduto(produto, item.quantidade, fornecedorpedido);
        //     produto.removerQuantidade(item.quantidade);
        //     pedido.adcionarProduto(produto, item.quantidade);
         }
         pedidoRepository.save(pedido);

        return pedido;
    }

    public Fornecedor getFornecedor() {
        SplittableRandom splittableRandom = new SplittableRandom();
        int random = splittableRandom.nextInt(1, 4);
        return fornecedorRepository.findOne(random);
    }
}
