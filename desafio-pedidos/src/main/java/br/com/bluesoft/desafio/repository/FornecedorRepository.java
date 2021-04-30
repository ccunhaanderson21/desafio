package br.com.bluesoft.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import br.com.bluesoft.desafio.dominio.pedido.Fornecedor;
  
public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
} 