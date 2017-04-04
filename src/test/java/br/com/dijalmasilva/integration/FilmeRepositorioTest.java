package br.com.dijalmasilva.integration;

import br.com.dijalmasilva.enums.EstadoEnum;
import br.com.dijalmasilva.model.Filme;
import br.com.dijalmasilva.repository.FilmeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 04/04/17 - 11:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FilmeRepositorioTest {

    @Autowired
    private FilmeRepository dao;

    @Test
    public void findAllTest() {
        List<Filme> filmes = (List<Filme>) dao.findAll();
        assertTrue(filmes.size() >= 4);
    }

    @Test
    public void save() {
        Filme filme = new Filme("Power Rangers", "Ação", 80, EstadoEnum.DISPONIVEL);
        Filme save = dao.save(filme);
        assertNotNull(save);
    }

    @Test
    public void update() {
        Filme filme = new Filme("Resident Evil 7", "Ação", 92, EstadoEnum.DISPONIVEL);
        Filme save = dao.save(filme);
        save.setDuracao(100);
        Filme saved = dao.save(save);
        assertEquals(100, saved.getDuracao());
    }

    @Test
    public void findById() {
        Filme filme = new Filme("Dr. Estranho", "Ação", 100, EstadoEnum.DISPONIVEL);
        Filme save = dao.save(filme);
        Filme one = dao.findOne(save.getId());
        assertEquals(save.getTitulo(), one.getTitulo());
    }
}
