package br.com.selecao.locadora.business;

import br.com.selecao.locadora.business.exception.ExessaoConteudoNaoEncontrado;
import br.com.selecao.locadora.dto.LeilaoDTO;
import br.com.selecao.locadora.entity.Leilao;
import br.com.selecao.locadora.mapper.LeilaoMapper;
import br.com.selecao.locadora.repository.LeilaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
@Transactional
public class LeilaoBO {

	@Autowired
	private LeilaoRepository leilaoRepository;

	@Autowired
	private LeilaoMapper leilaoMapper;

	public List<LeilaoDTO> buscarTodos() {
		List<Leilao> leilaos = leilaoRepository.findAll();
		return leilaos.stream()
				.map(leilaoMapper::toDTO)
				.collect(Collectors.toList());
	}

	public Leilao buscarLeilaoPorIdInterno(Long id) {
		return leilaoRepository.findById(id).get();
	}

	public LeilaoDTO buscarLeilaoPorId(Long id) {
		Optional<Leilao> leilaoOptional = leilaoRepository.findById(id);
		return leilaoOptional.map(leilaoMapper::toDTO).orElse(null);
	}

	public LeilaoDTO salvarLeilao(LeilaoDTO leilaoDTO) {
		Leilao leilao = leilaoMapper.toEntity(leilaoDTO);
		leilao.setCreatedAt(LocalDateTime.now());
		leilao.setUpdatedAt(LocalDateTime.now());
		Leilao savedLeilao = leilaoRepository.save(leilao);
		return leilaoMapper.toDTO(savedLeilao);
	}

	public Map<String, Long> deletarLeilao(Long id) {
		Optional<Leilao> leilaoOptional = leilaoRepository.findById(id);
		if (leilaoOptional.isPresent()) {
			leilaoRepository.deleteById(id);

			Map<String, Long> resposta = new HashMap<>();
			resposta.put("id", id);
			return resposta;
		} else {
			throw new ExessaoConteudoNaoEncontrado("Leilao não encontrada com o ID: " + id);
		}
	}

	public Leilao atualizarLeilao(LeilaoDTO novaLeilaoDTO, Long id) {
		Optional<Leilao> leilaoOptional = leilaoRepository.findById(id);

		if (leilaoOptional.isPresent()) {
			Leilao leilaoExistente = leilaoOptional.get();

			if (novaLeilaoDTO.getCodigo() != null) {
				leilaoExistente.setCodigo(novaLeilaoDTO.getCodigo());
			}

			if (novaLeilaoDTO.getDescricao() != null) {
				leilaoExistente.setDescricao(novaLeilaoDTO.getDescricao());
			}

			if (novaLeilaoDTO.getVendedor() != null) {
				leilaoExistente.setVendedor(novaLeilaoDTO.getVendedor());
			}

			if (novaLeilaoDTO.getInicioPrevisto() != null) {
				leilaoExistente.setInicioPrevisto(novaLeilaoDTO.getInicioPrevisto());
			}

			leilaoExistente.setUpdatedAt(LocalDateTime.now());

			return leilaoRepository.save(leilaoExistente);

		} else {
			throw new ExessaoConteudoNaoEncontrado("Leilao não encontrada com o ID: " + id);
		}
	}
}