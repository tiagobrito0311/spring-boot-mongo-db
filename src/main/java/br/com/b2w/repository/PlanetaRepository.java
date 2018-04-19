package br.com.b2w.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.b2w.domain.Planeta;

@RepositoryRestResource(exported=false)
public interface PlanetaRepository extends MongoRepository<Planeta, ObjectId> {

    @Query("{nome:'?0'}")
    public Planeta buscaPorNome(@Param("nome")String nome);
     
     
}
