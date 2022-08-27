package br.com.juliana.java_spring_eclipse_example.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.lang.String;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.juliana.java_spring_eclipse_example.model.Produto;
import br.com.juliana.java_spring_eclipse_example.model.exception.ResourceNotFoundException;
import br.com.juliana.java_spring_eclipse_example.repository.ProdutoRepository;
import br.com.juliana.java_spring_eclipse_example.repository.ProdutoRepository_old;
import br.com.juliana.java_spring_eclipse_example.shared.ProdutoDTO;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	/** 
	 * Método para retornar uma lista de produtos
	 * @return lista de produtos.
	 */
	
	public List<ProdutoDTO> obterTodos(){
		
		//Retorna uma lista de produto model
		List<Produto> produtos =  produtoRepository.findAll();
		
		//converte produto que vem do banco em uma lista de  ProdutoDTO
		return produtos.stream()
		.map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
		.collect(Collectors.toList());
				
	}
	
	
	/** 
	 * Método que retorna o produto encontrado pelo seu id.
	 * @param id do produto que será localizado
	 * @return Retorna um produto caso tenha encontrado 
	 */
	
	public Optional<ProdutoDTO> obterPorId(Integer id) {
		
		//Obtendo optional de produto por id
		Optional<Produto> produto = produtoRepository.findById(id);
		
		// se não encontrar, lança exception
		if(produto.isEmpty()) {
			throw new ResourceNotFoundException("Produto com id: " + id + " não encontrado");
		}
		
		// Convertendo o meu optional de produto em um ProdutoDTO
		ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);
		
		//Criando e retornando um optional de dto
		return Optional.of(dto);
	}
	
	/**
	 * Método para adicionar produto na lista
	 * @param produto que será adicionado
	 * @return Retorna produto que foi adicionado na lista 
	 */
	
	public ProdutoDTO adicionar(ProdutoDTO produtoDTO) {
		//Removendo o id para conseguir fazer o cadastro
		produtoDTO.setId(null);
		
		//Criar um objeto de mapeamento
		ModelMapper mapper = new ModelMapper();
		
		//Converter o nosso ProdutoDTO em um Produto
		Produto produto = mapper.map(produtoDTO, Produto.class);
		
		//Salvar o produto no banco de dados
		produto = produtoRepository.save(produto);
		
		produtoDTO.setId(produto.getId());
		
		//Retornar o ProdutoDTO atualizado
		
		return produtoDTO;

	}
	
	/** 
	 * Método para deletar produto por id.
	 * @param id do produto a ser deletado. 
	 */
	
	public void deletar(Integer id) {
		//Verificar se o produto existe
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if(produto.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possível deletar o roduto com o id " + id + " - Produto não existe");
		}
		produtoRepository.deleteById(id);
	}
	
	
	/** 
	 * Método para atualizar o produto na lista
	 * @param produto que será atualizado.
	 * @return Retorna o rpoduto após atualizar a lista 
	 */
	
	public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDTO) {
		
		//Passar o id para o produtoDTO
		produtoDTO.setId(id);
		
		//Criar um objeto de apeamento
		ModelMapper mapper = new ModelMapper();
		
		//Converter o ProdutoDTO em um produto
		Produto produto = mapper.map(produtoDTO, Produto.class);
		
		//Atualizar o produto no Banco de dados
		produtoRepository.save(produto);
		
		//Retornar o produtoDTO atualizado
		return produtoDTO;
	
	}

}
