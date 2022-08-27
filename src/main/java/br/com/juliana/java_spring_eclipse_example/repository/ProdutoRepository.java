package br.com.juliana.java_spring_eclipse_example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.juliana.java_spring_eclipse_example.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	

}
