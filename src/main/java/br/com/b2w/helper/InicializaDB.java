package br.com.b2w.helper;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.b2w.domain.Planeta;
import br.com.b2w.repository.PlanetaRepository;

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
	private static Planeta retornaPlaneta(LinkedHashMap<?, ?> map) {
		Planeta planeta = new Planeta();

		map.forEach((key, value) -> {

			if (key.equals("name")) {
				planeta.setNome(value.toString());
			} else if (key.equals("climate")) {
				planeta.setClima(value.toString());
			} else if (key.equals("terrain")) {
				planeta.setTerreno(value.toString());
			} else if (key.equals("films")) {
				ArrayList<?> ob = (ArrayList<?>) value;
				planeta.setApariacoes(ob.size());

			}
		});

		return planeta;
	}

}
