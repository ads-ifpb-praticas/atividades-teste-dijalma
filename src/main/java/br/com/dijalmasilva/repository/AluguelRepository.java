package br.com.dijalmasilva.repository;

import br.com.dijalmasilva.model.Aluguel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 28/03/17 - 08:15
 */
@Repository
public interface AluguelRepository extends CrudRepository<Aluguel, Long>{
}
