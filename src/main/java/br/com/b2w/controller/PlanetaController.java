/**
 * @author               Tiago Brito
 * @Date                 20/04/2018
 */
 
 
package br.com.b2w.controller;

import java.util.List;
import java.util.Optional;

import org.json.JSONException;
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

import br.com.b2w.domain.Planeta;
import br.com.b2w.exception.PlanetasException;
import br.com.b2w.helper.Helper;
import br.com.b2w.helper.ValidacaoHelper;
import br.com.b2w.repository.PlanetaRepository;
 
 
// TODO: Auto-generated Javadoc
/**
 * The Class PlanetaController.
 */
@Controller
@RequestMapping("/planetas")
public class PlanetaController {

	/** The log. */
	private final Logger LOG = LoggerFactory.getLogger(Helper.class);
	
	/** The Constant TYPE_JSON. */
	private static final String TYPE_JSON="application/json";
	
	/** The repository. */
	@Autowired
	private PlanetaRepository repository;

	/** The helper. */
	@Autowired
	private Helper helper;

	/** The validation. */
	@Autowired
	private ValidacaoHelper validation;

	/**
	 * Listar.
	 *
	 * @param nome
	 *            the nome
	 * @return the response entity
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listar(@Param("nome") String nome) {

		if (!StringUtils.isEmpty(nome)) {
			return buscaPorNome(nome);

		} else {
			return findAll();
		}
	}

	/**
	 * Busca por id.
	 *
	 * @param id
	 *            the id
	 * @return the response entity
	 */
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> buscaPorId(@PathVariable("id") String id) {
		Optional<Planeta> planeta = repository.findById(id);
		if (planeta != null) {
			return new ResponseEntity<Object>(planeta, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

		}
	}

	/**
	 * Save.
	 *
	 * @param planeta the planeta
	 * @return the response entity
	 * @throws JSONException the JSON exception
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = TYPE_JSON ,produces=TYPE_JSON )
	public ResponseEntity<?> save(@RequestBody Planeta planeta) throws JSONException {
		
		try{ 
			validation.validaPlaneta(planeta);
			
			int qtd = helper.retornaQdtApariacoes(planeta.getNome());
			planeta.setApariacoes(qtd);
			planeta= repository.save(planeta);
			return new ResponseEntity<Planeta>(planeta, HttpStatus.CREATED);

		 
	}catch(	PlanetasException str){ 
 		  return new ResponseEntity<>( str.getMessage(), HttpStatus.BAD_REQUEST);
		 
	}catch(org.springframework.dao.DuplicateKeyException e){
			LOG.error("Erro ao salvar planeta: " + e.getMessage());
 			return new ResponseEntity<Object>(helper.retornaMsgErro(), HttpStatus.CONFLICT);
	 }catch( Exception e) {
		LOG.error("Erro ao salvar planeta: " + e.getMessage());
		return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		repository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	/**
	 * Find all.
	 *
	 * @return the response entity
	 */
	private ResponseEntity<?> findAll() {
		List<Planeta> lista = repository.findAll();
		if (CollectionUtils.isEmpty(lista)) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

		} else {
			return new ResponseEntity<Object>(lista, HttpStatus.OK);

		}
	}

	/**
	 * Busca por nome.
	 *
	 * @param nome
	 *            the nome
	 * @return the response entity
	 */
	private ResponseEntity<?> buscaPorNome(String nome) {
		Planeta planeta = repository.buscaPorNome(nome);
		if (planeta != null) {
			return new ResponseEntity<Planeta>(planeta, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

		}
	}
	
	
	/**
	 * Valida planeta.
	 *
	 * @param planeta the planeta
	 * @throws PlanetasException the planetas exception
	 */
	 

}
