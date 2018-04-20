/**
 * @author               Tiago Brito
 * @Date                 20/04/2018
 */
package br.com.b2w.helper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.b2w.dto.MensagemDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class Helper.
 */
@Component
public class Helper {
 
	/** The log. */
	private final Logger LOG = LoggerFactory.getLogger(Helper.class);

	/** The Constant URL_BY_ID. */
	private final static String URL_BY_ID = "https://swapi.co/api/planets/";
	
	/** The Constant URL_BY_NAME. */
	private final static String URL_BY_NAME = "https://swapi.co/api/planets/?search=";

	/**
	 * Busca planeta API.
	 *
	 * @param value the value
	 * @return the linked hash map
	 */
	public LinkedHashMap<?, ?> buscaPlanetaAPI(Object value) {
 		
		String url = URL_BY_ID;
		
		if (value instanceof String) {
			url = URL_BY_NAME;
		}
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<Object> someObject = restTemplate.exchange(url + value, HttpMethod.GET, entity, Object.class);
		return (LinkedHashMap<?, ?>) someObject.getBody();
	}

	/**
	 * Retorna qdt apariacoes.
	 *
	 * @param value the value
	 * @return the int
	 */
	public int retornaQdtApariacoes(Object value) {
		try{
			LinkedHashMap<?, ?> hasMap= this.buscaPlanetaAPI(value);
			String ob = hasMap.get("results").toString();
			Map<String,String> map = convertToMap(ob);
			 String str = map.get("films");
			 if(str.length()<=2){
				 return 0;
			 }
			 
			 List<String> items = Arrays.asList( str.replace("[", "").replace("]", "").split("\\s*,\\s*"));

			return items.size(); 
		}catch(Exception e){
			LOG.error("Erro ao buscar qdt de apariações: " + e);
			return 0;
		}
		

	}
	
	/**
	 * Convert to map.
	 *
	 * @param value the value
	 * @return the map
	 */
	public Map<String,String> convertToMap(String value){
	 	value = value.substring(1, value.length()-1);            
		String[] keyValuePairs = value.split(",");              
		String lastKey=null;
		String lastValue=null;
		Map<String,String> map = new HashMap<>();               

		for(String pair : keyValuePairs) {
  			if(pair.contains("=")){
			    String[] entry = pair.split("=");  
			    lastKey =  entry[0].trim();
			    map.put(entry[0].trim(), entry[1].trim());          
 			}else{
 				lastValue = map.get(lastKey);
			    map.put(lastKey, lastValue +","+lastValue);           

 			}
 		}
		return map;
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
 