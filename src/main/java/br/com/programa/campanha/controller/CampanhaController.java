package br.com.programa.campanha.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.MediaType; 
import org.springframework.transaction.annotation.Transactional;
import br.com.programa.campanha.entity.Campanha;
import br.com.programa.campanha.exceptions.CampanhaNaoEncontradaException;
import br.com.programa.campanha.repository.CampanhaRepository;

@RestController
@RequestMapping(value = "campanha")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class CampanhaController {
	
	@Autowired
	private CampanhaRepository campanhaRepository;

	@Transactional
	@ApiOperation(value = "Salva uma campanha", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Campanha salvar(@RequestBody @Valid Campanha campanha){
		List<Campanha> listaCampanhas = this.pesquisarVigentes();
		List<Campanha> listaAtualizada = this.corrigirData(listaCampanhas, campanha);
		listaAtualizada.add(campanha);
		for (Campanha c : listaAtualizada) {
			this.campanhaRepository.save(c);
		}
		return campanha;
	}

	@ApiOperation(value = "Exclui uma campanha", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE })
	public void deletar(@PathVariable Long id) throws CampanhaNaoEncontradaException{
		this.campanhaRepository.delete(id);
	}
	
	@ApiOperation(value = "Consulta um Campanha pelo id", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Campanha pesquisarPorId(@PathVariable Long id) {
		return this.campanhaRepository.findOne(id);
	}
	
	@ApiOperation(value = "Consulta todas as Campanhas vigentes", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Campanha> pesquisarTodas(){
		return this.campanhaRepository.findAll();
	}
	
	@ApiOperation(value = "Consulta todas as Campanhas vigentes", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/vigentes", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Campanha> pesquisarVigentes(){
		List<Campanha> listaTotal = campanhaRepository.findAll();
		List<Campanha> listaVigentes = new ArrayList<Campanha>();
		
		for (Campanha campanha : listaTotal) {
			if (this.campanhasVigente(campanha)) {
				listaVigentes.add(campanha);
			}
		}
		return listaVigentes;
	}
	
	@ApiOperation(value = "Consulta todas as Campanhas vigentes por time", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/time/{codigoTime}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Campanha> pesquisarVigentesPorTime(@PathVariable Long codigoTime) throws CampanhaNaoEncontradaException{
		return this.campanhaRepository.findByIdTimeCoracao(codigoTime).stream()
				.filter(this::campanhasVigente).collect(Collectors.toList());
	}
	
	public boolean campanhasVigente(Campanha campanha){
		Calendar dataAtual = Calendar.getInstance();
		if (campanha.getDataInicio().before(dataAtual) && campanha.getDataFim().after(dataAtual)) {
			return true;
		} else{
			return false;
		}
	}
	
	@Transactional
	@ApiOperation(value = "Altera uma campanha", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Campanha alterar(@PathVariable Long id, @RequestBody @Valid Campanha campanha) throws CampanhaNaoEncontradaException {
		Campanha campanhaAlterar = this.pesquisarPorId(id);
		campanhaAlterar.setId(id);
		return this.salvar(campanhaAlterar);
	}

	
	private List<Campanha> corrigirData(List<Campanha> listaCampanha, Campanha campanha) {
		List<Campanha> lista = listaCampanha;
		List<String> listaDatas = new ArrayList<String>();
		List<Campanha> listaAtualizada = new ArrayList<Campanha>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String dataCampanha = sdf.format(campanha.getDataFim().getTime());
		if (!lista.isEmpty()) {
			for (Campanha c : lista) {
				listaDatas.add(sdf.format(c.getDataFim().getTime()));
			}

			for (Campanha c : lista) {
				listaDatas.remove(sdf.format(c.getDataFim().getTime()));
				Calendar data = c.getDataFim();
				data.add(Calendar.DATE, +1);
				c.setDataFim(data);

				listaDatas.add(sdf.format(c.getDataFim().getTime()));
				listaAtualizada.add(c);
			}
			if (listaDatas.contains(dataCampanha)) {
				corrigirData(listaAtualizada, campanha);
			}
		}
		return lista;
	}
}