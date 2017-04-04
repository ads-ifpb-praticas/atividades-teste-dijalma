package br.com.dijalmasilva.repository;

import br.com.dijalmasilva.model.Filme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 28/03/17 - 08:13
 */
@Repository
public interface FilmeRepository extends CrudRepository<Filme, Long> {
}
