package br.com.bluesoft.desafio.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.bluesoft.desafio.dominio.pedido.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {
}
 