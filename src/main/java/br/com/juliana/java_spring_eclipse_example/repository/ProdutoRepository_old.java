package br.com.juliana.java_spring_eclipse_example.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.juliana.java_spring_eclipse_example.model.Produto;
import br.com.juliana.java_spring_eclipse_example.model.exception.ResourceNotFoundException;

@Repository
public class ProdutoRepository_old {
	
	private ArrayList<Produto> produtos = new ArrayList<Produto>();
	private Integer ultimoId = 0;
	
	/** 
	 * Método para retornar uma lista de produtos
	 * @return lista de produtos.
	 */

	
	public List<Produto> obterTodos(){
		return produtos;
	}
	
	
	/** 
	 * Método que retorna o produto encontrado pelo seu id.
	 * @param id do produto que será localizado
	 * @return Retorna um produto caso tenha encontrado 
	 */
	
	public Optional<Produto> obterPorId(Integer id) {
		return produtos
				.stream()
				.filter(produto -> produto.getId() == id).findFirst();
	}
	
	
	
	/**
	 * Método para adicionar produto na lista
	 * @param produto que será adicionado
	 * @return Retorna produto que foi adicionado na lista 
	 */
	
	public Produto adicionar(Produto produto) {
		
		ultimoId = ultimoId + 1;
		produto.setId(ultimoId);
		produtos.add(produto);
		
		return produto;
	}
	
	
	/** 
	 * Método para deletar produto por id.
	 * @param id do produto a ser deletado. 
	 */
	
	public void deletar(Integer id) {
		
		produtos.removeIf(produto -> produto.getId() == id);
	}
	
	
	/** 
	 * Método para atualizar o produto na lista
	 * @param produto que será atualizado.
	 * @return Retorna o rpoduto após atualizar a lista 
	 */
	
	public Produto atualizar(Produto produto) {
		
		//Encontrar produto na lista
	    Optional<Produto> produtoEncontrado = obterPorId(produto.getId());
	    
	    if(produtoEncontrado.isEmpty()) {
	    	throw new ResourceNotFoundException("Produto não pode ser atualizado pois não existe");
	    }
	    
	    //Remover produto antigo da lista
	    deletar(produto.getId());
	    
	    //Adicionar o produto atualizado na lista
	    produtos.add(produto);
	    
	    return produto;
	
	}

}
