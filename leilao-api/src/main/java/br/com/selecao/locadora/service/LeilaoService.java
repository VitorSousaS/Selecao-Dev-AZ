package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.LeilaoBO;
import br.com.selecao.locadora.dto.LeilaoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/leilao")
public class LeilaoService {

	@Autowired
	private LeilaoBO leilaoBO;

	@GetMapping
	public ResponseEntity<Object> buscarTodos() {
		return new ResponseEntity<>(leilaoBO.buscarTodos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarLeilao(@PathVariable Long id) {
		return new ResponseEntity<>(leilaoBO.buscarLeilaoPorId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> criarLeilao(@RequestBody LeilaoDTO leilaoDTO) {
		return new ResponseEntity<>(leilaoBO.salvarLeilao(leilaoDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarLeilao(@PathVariable Long id) {
    return new ResponseEntity<>(leilaoBO.deletarLeilao(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizarLeilao(@RequestBody LeilaoDTO
	novaLeilaoDTO, @PathVariable Long id) {		
		return new ResponseEntity<>(leilaoBO.atualizarLeilao(novaLeilaoDTO, id), HttpStatus.OK);
	};
}