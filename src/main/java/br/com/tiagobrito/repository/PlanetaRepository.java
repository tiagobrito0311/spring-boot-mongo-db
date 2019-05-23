package br.com.tiagobrito.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.tiagobrito.domain.Planeta;

// TODO: Auto-generated Javadoc
/**
 * The Interface PlanetaRepository.
 */
@RepositoryRestResource(exported=false)
public interface PlanetaRepository extends MongoRepository<Planeta, String > {

    /**
     * Busca por nome.
     *
     * @param nome the nome
     * @return the planeta
     */
    @Query("{nome:'?0'}")
    public Planeta buscaPorNome(@Param("nome")String nome);
     
     
}
