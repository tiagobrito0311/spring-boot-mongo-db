/**
 * @author               Tiago Brito
 * @Date                 20/04/2018
 */
 
package br.com.b2w;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
 
// TODO: Auto-generated Javadoc
/**
 * The Class Application.
 */
@SpringBootApplication
public class Application {
	
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Mongo template.
     *
     * @param mongoDbFactory the mongo db factory
     * @param context the context
     * @return the mongo template
     */
    
     /* 
      * 
      * @Autowired
	 private InicializaDB inicializaDB;
    
    @Bean
    CommandLineRunner init(PlanetaRepository repository) {

        return args -> {

        	repository.deleteAll();
        	 
        	inicializaDB.popuparPlaneta(repository,5);

        };

    }
//  */
    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory,
                                       MongoMappingContext context) {

        MappingMongoConverter converter =
                new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);
      

        return mongoTemplate;

    }

}
