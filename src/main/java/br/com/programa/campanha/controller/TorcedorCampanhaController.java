package br.com.programa.campanha.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.programa.campanha.entity.TorcedorCampanha;
import br.com.programa.campanha.exceptions.TorcedorNaoTemCampanhaAssociada;
import br.com.programa.campanha.repository.TorcedorCampanhaRepository;


@RestController
@RequestMapping(value = "associacao")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class TorcedorCampanhaController {
	
	@Autowired
	private TorcedorCampanhaRepository torcedorCampanhaRepository;
	
	@Transactional
	@ApiOperation(value = "Associa o Torcedor a uma Campanha", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public TorcedorCampanha salvar(final TorcedorCampanha torcedorCampanha){
		return this.torcedorCampanhaRepository.save(torcedorCampanha);
	}
	
	@Transactional
	@ApiOperation(value = "Exclui uma Associacao", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deletar(final TorcedorCampanha torcedorCampanha) throws TorcedorNaoTemCampanhaAssociada{
		this.torcedorCampanhaRepository.delete(torcedorCampanha);
	}
	
	@ApiOperation(value = "Consulta um Campanha pelo id", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public TorcedorCampanha pesquisarPorId(final Long id) {
		return this.torcedorCampanhaRepository.findOne(id);
	}
	
	@ApiOperation(value = "Consulta todas as Associacoes", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<TorcedorCampanha> pesquisarTodas(){
		return this.torcedorCampanhaRepository.findAll();
	}
	
	@ApiOperation(value = "Consulta uma Associacao por id da Campanha", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/campanha/{idCampanha}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<TorcedorCampanha> buscarPorIdCampanha(Long idCampanha) {
		return this.torcedorCampanhaRepository.findByIdCampanha(idCampanha);
	}
	
	@ApiOperation(value = "Consulta uma Associacao por Email do Torcedor", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/torcedor", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<TorcedorCampanha> buscarPorEmail(String email) throws TorcedorNaoTemCampanhaAssociada{
		return this.torcedorCampanhaRepository.findByEmail(email);
	}
}
