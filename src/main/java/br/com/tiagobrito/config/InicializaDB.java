/**
 * @author               Tiago Brito
 * @Date                 20/04/2018
 */
package br.com.tiagobrito.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tiagobrito.domain.Planeta;
import br.com.tiagobrito.dto.ResultDTO;
import br.com.tiagobrito.helper.Helper;
import br.com.tiagobrito.repository.PlanetaRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class InicializaDB.
 */
@Component
public class InicializaDB {

	/** The log. */
	private final Logger LOG = LoggerFactory.getLogger(InicializaDB.class);

	/** The helper. */
	@Autowired
	private Helper helper;

	/**
	 * Popupar planeta.
	 *
	 * @param domainRepository the domain repository
	 * @param limite the limite
	 */
	public void popuparPlaneta(PlanetaRepository domainRepository, int limite) {

		try {
			int qtd = 1;
			Planeta planeta = null;
			while (qtd <= limite) {

				planeta = retornaPlaneta(helper.buscaPlanetaAPI(qtd));
				
				LOG.info(planeta.toString());

				domainRepository.save(planeta);

				qtd++;

			}

		} catch (Exception e) {
			LOG.error("Erro ao popular planeta: " + e.getMessage());
		}
	}

	/**
	 * Retorna planeta.
	 *
	 * @param map the map
	 * @return the planeta
	 */
	private static Planeta retornaPlaneta(ResultDTO  request) {
		Planeta planeta = new Planeta();
		planeta.setNome(request.getResults().get(0).getName());
		planeta.setClima(request.getResults().get(0).getClimate());
		planeta.setTerreno(request.getResults().get(0).getTerrain());
		planeta.setApariacoes(request.getResults().get(0).getFilms().size());
 
		return planeta;
	}

}
