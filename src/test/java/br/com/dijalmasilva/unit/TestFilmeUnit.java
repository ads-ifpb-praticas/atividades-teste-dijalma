package br.com.dijalmasilva.unit;

import br.com.dijalmasilva.enums.EstadoEnum;
import br.com.dijalmasilva.exception.FilmeException;
import br.com.dijalmasilva.model.Filme;
import br.com.dijalmasilva.service.FilmeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 04/04/17 - 08:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TestFilmeUnit {

    @Autowired
    private FilmeService service;
    private List<Filme> filmes;

    @Before
    public void setUp() {
        filmes = new ArrayList<>();
        filmes.add(new Filme("sdEusiaoisjdaoisjdoiasjdiaosjdoiasjdoiajsdoiajsdsdaa", "Ação", 20, EstadoEnum.DISPONIVEL));
        filmes.add(new Filme("O @do", "Aventura", 50, EstadoEnum.EMPRESTADO));
        filmes.add(new Filme("Os vingadores", "", 20, EstadoEnum.EMPRESTADO));
        filmes.add(new Filme("Jackie Chan", "Ação", 0, EstadoEnum.EMPRESTADO));
        filmes.add(new Filme("Jogos Vorazes", "Ficção", 100, EstadoEnum.EMPRESTADO));
        filmes.add(new Filme("Logan", "Ação", 120, EstadoEnum.DISPONIVEL));
    }

    @Test
    public void TesteSalvaFilme() throws FilmeException {
        Filme filme = service.save(filmes.get(5));
        assertNotNull(filme);
    }

    @Test
    public void acharFilmePorID() {
        Filme filme = service.findById(1L);
        assertNotNull(filme);
    }

    @Test
    public void TestAtualizarFilme() throws FilmeException {
        Filme filme = service.findById(1L);
        filme.setEstado(EstadoEnum.EMPRESTADO);
        Filme update = service.update(1L, filme);

        assertNotNull(update);
    }

    @Test
    public void TesteAtualizarFilmeEmprestado() {
        try {
            Filme filme = service.findById(1L);
            filme.setDuracao(200);
            service.update(1L, filme);
        } catch (FilmeException e) {
            e.printStackTrace();
        }
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
    public void TesteFilmeComCaracterEspecial() {
        try {
            service.save(filmes.get(1));
        } catch (FilmeException e) {
            assertEquals("Título do filme não pode conter caracteres especiais, exceto #,!,?", e.getMessage());
        }
    }

    @Test
    public void TesteFilmeSemGenero() {
        try {
            service.save(filmes.get(2));
        } catch (FilmeException e) {
            assertEquals("É obrigatório que o filme possua um gênero!", e.getMessage());
        }
    }

    @Test
    public void TesteFilmeSemDuracao() {
        try {
            service.save(filmes.get(3));
        } catch (FilmeException e) {
            assertEquals("O filme deve ter duração acima de 0 minutos!", e.getMessage());
        }
    }
}
