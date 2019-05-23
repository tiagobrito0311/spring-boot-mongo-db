/**
 * @author               Tiago Brito
 * @Date                 20/04/2018
 */
package br.com.tiagobrito.helper;

import java.util.Arrays;
 
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.tiagobrito.domain.Planeta;
import br.com.tiagobrito.dto.MensagemDTO;
import br.com.tiagobrito.dto.ResultDTO;
import br.com.tiagobrito.exception.PlanetasException;

// TODO: Auto-generated Javadoc
/**
 * The Class Helper.
 */
@Component
public class Helper {

	/** The Constant URL_BY_ID. */
	private final static String URL_BY_ID = "https://swapi.co/api/planets/";

	/** The Constant URL_BY_NAME. */
	private final static String URL_BY_NAME = "https://swapi.co/api/planets/?search=";

	private final static String USER_AGENT_KEY = "user-agent";
	private final static String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";
	private final static String PARAMETERS = "parameters";

	/**
	 * Busca planeta API.
	 *
	 * @param value the value
	 * @return the linked hash map
	 */
	public ResultDTO buscaPlanetaAPI(Object value) {

		String url = URL_BY_ID;

		if (value instanceof String) {
			url = URL_BY_NAME;
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add(USER_AGENT_KEY, USER_AGENT_VALUE);
		HttpEntity<String> entity = new HttpEntity<String>(PARAMETERS, headers);

		ResponseEntity<ResultDTO> someObject = new RestTemplate().exchange(url+value, HttpMethod.GET, entity,
				ResultDTO.class);
		return (ResultDTO) someObject.getBody();
	}

	/**
	 * Retorna qdt apariacoes.
	 *
	 * @param value the value
	 * @return the int
	 */
	public Planeta retornaPlaneta(Planeta value) throws Exception {
		 
		ResultDTO resultDTO = this.buscaPlanetaAPI(value.getNome());

		if (resultDTO != null && resultDTO.getResults() != null && !resultDTO.getResults().isEmpty()) {
			value.setNome(resultDTO.getResults().get(0).getName());
			value.setClima(resultDTO.getResults().get(0).getClimate());
			value.setTerreno(resultDTO.getResults().get(0).getTerrain());
			value.setApariacoes(resultDTO.getResults().get(0).getFilms().size());

			return value;

		} else {
			throw new PlanetasException("Nenhum planeta encontrado!");
		} 

	}

	/**
	 * Retorna msg erro.
	 *
	 * @return the mensagem DTO
	 */
	public MensagemDTO retornaMsgErro() {
		MensagemDTO dto = new MensagemDTO();
		dto.setCodigo("Error");
		dto.setMensagem("Planeta já cadastrado.");
		return dto;
	}
}
