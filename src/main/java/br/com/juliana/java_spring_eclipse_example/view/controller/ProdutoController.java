package br.com.juliana.java_spring_eclipse_example.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.juliana.java_spring_eclipse_example.model.Produto;
import br.com.juliana.java_spring_eclipse_example.services.ProdutoService;
import br.com.juliana.java_spring_eclipse_example.shared.ProdutoDTO;
import br.com.juliana.java_spring_eclipse_example.view.model.ProdutoRequest;
import br.com.juliana.java_spring_eclipse_example.view.model.ProdutoResponse;


@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	/**
	 * @GetMapping - > quando alguém fizer qualquer consulta em /produtos 
	 * vai chamar esse método obterTodos
	 * @return -> mostra todos os produtos
	 */
	
	@GetMapping
	public ResponseEntity<List<ProdutoResponse>> obterTodos(){
		List<ProdutoDTO> produtos =  produtoService.obterTodos();
		
		ModelMapper mapper = new ModelMapper();
		
		List<ProdutoResponse> resposta = produtos.stream()
		.map(produtoDTO -> mapper.map(produtoDTO, ProdutoResponse.class))
		.collect(Collectors.toList());
		
		return new ResponseEntity<>(resposta, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<ProdutoResponse>> obterPorId(@PathVariable Integer id){
		
		try {
			Optional<ProdutoDTO> dto = produtoService.obterPorId(id);
			ProdutoResponse produto = new ModelMapper().map(dto.get(), ProdutoResponse.class);
			
			return new ResponseEntity<>(Optional.of(produto), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
			
		
	}
	
	@PostMapping
	public ResponseEntity<ProdutoResponse> adicionar(@RequestBody ProdutoRequest produtoRequest) {
		ModelMapper mapper = new ModelMapper();
		ProdutoDTO produtoDTO = mapper.map(produtoRequest, ProdutoDTO.class);
		produtoDTO = produtoService.adicionar(produtoDTO);
		
		return new ResponseEntity<>(mapper.map(produtoDTO, ProdutoResponse.class), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Integer id) {
		produtoService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoResponse> atualizar(@RequestBody ProdutoRequest produtoRequest, @PathVariable Integer id) {
		
		ModelMapper mapper = new ModelMapper();
		ProdutoDTO produtoDTO = mapper.map(produtoRequest, ProdutoDTO.class);
		
		produtoDTO =  produtoService.atualizar(id, produtoDTO);
		
		return new ResponseEntity<>(
				mapper.map(produtoDTO, ProdutoResponse.class),
				HttpStatus.OK);
				
	}


}
