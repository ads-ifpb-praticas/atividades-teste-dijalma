package br.com.dijalmasilva.unit;

import br.com.dijalmasilva.enums.EstadoEnum;
import br.com.dijalmasilva.exception.FilmeException;
import br.com.dijalmasilva.model.Filme;
import br.com.dijalmasilva.service.FilmeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 04/04/17 - 08:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFilmeUnit {

    @Autowired
    private FilmeService service;
    private List<Filme> filmes;

    @Before
    public void setUp() {
        filmes = new ArrayList<>();
        filmes.add(new Filme("sdEusiaoisjdaoisjdoiasjdiaosjdoiasjdoiajsdoiajsdsdaa", "Ação", 20, EstadoEnum.DISPONIVEL));
        filmes.add(new Filme("Os vingadores", "", 20, EstadoEnum.EMPRESTADO));
        filmes.add(new Filme("Jackie Chan", "Ação", 0, EstadoEnum.EMPRESTADO));
        filmes.add(new Filme("Jogos Vorazes", "Ficção", 100, EstadoEnum.EMPRESTADO));
        filmes.add(new Filme("Logan", "Ação", 120, EstadoEnum.DISPONIVEL));
    }

    @Test
    public void TesteFilmeComMaisDe50Caracteres() {
        try {
            service.save(filmes.get(0));
        } catch (FilmeException e) {
            assertEquals("Título do filme não pode conter mais que 50 caracteres!", e.getMessage());
        }
    }

    @Test
    public void TesteFilmeSemGenero() {
        try {
            service.save(filmes.get(1));
        } catch (FilmeException e) {
            assertEquals("É obrigatório que o filme possua um gênero!", e.getMessage());
        }
    }
}
