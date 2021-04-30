package br.com.bluesoft.desafio.utils.config;
 
import br.com.bluesoft.desafio.repository.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class LoadDatabase { 
 
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
     
    @Bean
    CommandLineRunner initDatabase(ProdutoRepository repository) {
        return args -> { };
    }
}
