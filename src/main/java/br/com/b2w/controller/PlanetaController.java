package br.com.b2w.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.b2w.domain.Planeta;
import br.com.b2w.helper.Helper;
import br.com.b2w.repository.PlanetaRepository;

@Controller
@RequestMapping("/planetas")
public class PlanetaController {
	
	private final Logger LOG = LoggerFactory.getLogger(Helper.class);

	@Autowired
	private PlanetaRepository repository;
	@Autowired
	private Helper helper;

	@RequestMapping
	@ResponseBody
	public ResponseEntity<?> listar(@Param("nome")String nome) {
		
		if(!StringUtils.isEmpty(nome)){
			return buscaPorNome(nome);

		}else{
			return findAll();
		}	
	}

	
	@RequestMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> buscaPorId(@PathVariable("id") ObjectId id) {
		Optional<Planeta> planeta= repository.findById(id);
		if(planeta!=null){
			return new ResponseEntity<Object>(planeta, HttpStatus.OK);
		}else{
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

		}
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> save(@RequestBody Planeta planeta) {
		
		try{
			int qtd = helper.retornaQdtApariacoes(planeta.getNome());
			planeta.setApariacoes(qtd);
			planeta= repository.save(planeta);
			return new ResponseEntity<Planeta>(planeta, HttpStatus.OK);

		}catch(org.springframework.dao.DuplicateKeyException e){
			LOG.error("Erro ao salvar planeta: " + e.getMessage());
 			return new ResponseEntity<Object>(helper.retornaMsgErro(), HttpStatus.CONFLICT);


		}catch(Exception e){
			
			LOG.error("Erro ao salvar planeta: " + e.getMessage());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
		

	}


	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("id") Planeta id) {
		repository.delete(id);
	}
	

	private ResponseEntity<?> findAll() {
		List<Planeta> lista=repository.findAll();
		if(CollectionUtils.isEmpty(lista)){
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

		}else{
			return new ResponseEntity<Object>(lista, HttpStatus.OK);

		}
	}

	private ResponseEntity<?> buscaPorNome(String nome) {
		Planeta planeta = repository.buscaPorNome(nome);
		if(planeta!=null){
			return new ResponseEntity<Planeta>(planeta, HttpStatus.OK);
		}else{
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

		}
	}

}
