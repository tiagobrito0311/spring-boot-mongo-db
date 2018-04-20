/**
 * @author               Tiago Brito
 * @Date                 20/04/2018
 */
package br.com.b2w;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.b2w.domain.Planeta;
import br.com.b2w.exception.PlanetasException;
import br.com.b2w.helper.ValidacaoHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class ValidacaoHelperTest.
 *
 * @author Tiago
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidacaoHelperTest {

	/** The helper. */
	@Autowired
	private ValidacaoHelper helper;

	/**
	 * Valida planeta.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void validaPlaneta() throws Exception {

		Planeta planeta = new Planeta();
		planeta.setNome("Alderaan");
		planeta.setTerreno("grasslands, mountains");
		planeta.setClima("temperate");

		helper.validaPlaneta(planeta);
	}

	/**
	 * Valida planeta com erro.
	 */
	@Test
	public void validaPlanetaComErro() {

		try {
			helper.validaPlaneta(new Planeta());
		} catch (PlanetasException e) {
			assertThat(e.getMessage()).isNotEmpty();
			
		}
	}

}
