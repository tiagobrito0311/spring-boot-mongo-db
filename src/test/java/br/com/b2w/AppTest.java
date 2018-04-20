package br.com.b2w;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.b2w.controller.PlanetaController;
import br.com.b2w.domain.Planeta;
import br.com.b2w.helper.InicializaDB;
import br.com.b2w.repository.PlanetaRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class AppTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "spring.data.mongodb.database=test_db" }, webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppTest {

	/** The Constant HOST. */
	private static final String HOST = "http://localhost:";

	/** The Constant RECURSO. */
	private static final String RECURSO = "/planetas";

	/** The port. */
	@LocalServerPort
	private int port;

	/** The rest template. */
	@Autowired
	private TestRestTemplate restTemplate;

	/** The controller. */
	@Autowired
	private PlanetaController controller;

	/** The repository. */
	@Autowired
	private PlanetaRepository repository;

	/** The inicializa DB. */
	@Autowired
	private InicializaDB inicializaDB;

	/** The id planeta. */
	private static String idPlaneta;

	/**
	 * Limpar banco.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void limparBanco() throws Exception {
		repository.deleteAll();
		inicializaDB.popuparPlaneta(repository, 2);
	}

	/**
	 * Contex loads.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	/**
	 * Adicionar planeta ja cadastrado.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void adicionarPlanetaJaCadastrado() throws Exception {
		Planeta planeta = new Planeta();
		planeta.setNome("Alderaan");
		planeta.setTerreno("grasslands, mountains");
		planeta.setClima("temperate");

		ResponseEntity<?> response = this.restTemplate.postForEntity(HOST + port + RECURSO, planeta, Object.class);
		assertThat(response.getStatusCode(), is(HttpStatus.CONFLICT));

	}

	/**
	 * Adicionar planeta.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void adicionarPlaneta() throws Exception {
		Planeta planeta = new Planeta();
		planeta.setNome("Dagobah");
		planeta.setTerreno("mountains, fields, forests, rock arches");
		planeta.setClima("temperate, moist");

		ResponseEntity<?> response = this.restTemplate.postForEntity(HOST + port + RECURSO, planeta, Object.class);

		LinkedHashMap<?, ?> pla = (LinkedHashMap<?, ?>) response.getBody();
		idPlaneta = pla.get("id").toString();
		assertThat(response.getStatusCode(), is(HttpStatus.OK));

	}

	/**
	 * Listar planetas.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void listarPlanetas() throws Exception {
		assertThat(this.restTemplate.getForObject(HOST + port + RECURSO, Object.class)).asList();

	}

	/**
	 * Buscar planeta por nome.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void buscarPlanetaPorNome() throws Exception {

		ResponseEntity<?> response = this.restTemplate.getForEntity(HOST + port + RECURSO + "?nome=Dagobah",
				Object.class);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));

	}

	/**
	 * Buscar planeta po id.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void buscarPlanetaPoId() throws Exception {

		ResponseEntity<?> response = this.restTemplate.getForEntity(HOST + port + RECURSO +"/"+ idPlaneta, Object.class);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));

	}

	/**
	 * Remover planeta.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void removerPlaneta() throws Exception {
		this.restTemplate.delete(HOST + port + RECURSO +"/"+ idPlaneta);
	}

}
