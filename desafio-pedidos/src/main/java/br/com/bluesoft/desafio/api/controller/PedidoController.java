package br.com.bluesoft.desafio.api.controller;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.SplittableRandom;

import br.com.bluesoft.desafio.api.pedido.NovoPedido;
import br.com.bluesoft.desafio.api.pedido.PedidoDeProdutoDto;
import br.com.bluesoft.desafio.dominio.produto.Produto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.bluesoft.desafio.dominio.pedido.Fornecedor;
import br.com.bluesoft.desafio.dominio.pedido.Pedido;
import br.com.bluesoft.desafio.repository.FornecedorRepository;
import br.com.bluesoft.desafio.repository.PedidoRepository;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {
    
    private static final Logger log = LoggerFactory.getLogger(PedidoController.class);
    private PedidoRepository pedidoRepository;
    private final NovoPedido novoPedido;


   // @RequestMapping(value = "/", method = RequestMethod.GET)
   // ResponseEntity<?>findAll2() {
   //     List<Pedido> pedido =  pedidoRepository.findAll();
   //     if(pedido == null)
   //         return ResponseEntity.notFound().build();
   //     return ResponseEntity.ok(pedido);
   // }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Pedido> findAll() {

        return pedidoRepository.findAll();
    }

    @Autowired
    PedidoController(NovoPedido novoPedido, PedidoRepository pedidoRepository){
        this.novoPedido = novoPedido;
        this.pedidoRepository = pedidoRepository;
    }

    @RequestMapping(value = "/novo-pedido", method = RequestMethod.POST)
    ResponseEntity<?> post(@RequestBody List<PedidoDeProdutoDto> pedidosDeProduto) {


        Pedido pedido = novoPedido.criar(pedidosDeProduto);
        log.info("teste "+pedido);

         return ResponseEntity.created(URI.create("api/pedido/" + pedido.getId())).build();
       // return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<?> get(@PathVariable long id) {
        log.info(""+id);
        Pedido pedido = pedidoRepository.findOne(id);
        if(pedido == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pedido);
    }

}
