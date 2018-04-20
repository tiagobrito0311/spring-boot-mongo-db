/**
 * @author               Tiago Brito
 * @Date                 20/04/2018
 */
package br.com.b2w.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Component;

import br.com.b2w.domain.Planeta;
import br.com.b2w.dto.MensagemDTO;
import br.com.b2w.exception.PlanetasException;

// TODO: Auto-generated Javadoc
/**
 * The Class ValidacaoHelper.
 */
@Component
public class ValidacaoHelper {
	
	
	/**
	 * Valida planeta.
	 *
	 * @param planeta the planeta
	 * @return the list
	 */
	public void validaPlaneta(Planeta planeta) throws PlanetasException{
		List<MensagemDTO> listaErro = null;
		MensagemDTO dto = null;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
 
		Set<ConstraintViolation<Planeta>> violations = validator.validate(planeta);
		
		if(!violations.isEmpty()){
			listaErro = new ArrayList<>();
		}
		
		for (ConstraintViolation<Planeta> violation : violations) {
			dto =new MensagemDTO();
			dto.setCodigo(violation.getPropertyPath().toString()); 
			dto.setMensagem(violation.getMessage()); 
			listaErro.add(dto);
		}
		if(listaErro!=null){
			throw new PlanetasException(listaErro.toString());
		}
 			
	}

}
